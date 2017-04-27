package wxw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wxw.dao.RoleDao;
import wxw.model.base.Module;
import wxw.model.base.Role;
import wxw.model.page.RolePage;
import wxw.service.RoleService;
import wxw.util.JsonResult;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;

	@Override
	public JsonResult<List<Role>> findByPage(RolePage page) {
		page.setRows(roleDao.findRows(page));
		return new JsonResult<>(roleDao.findByPage(page));
	}

	@Override
	public JsonResult<List<Module>> findAllModules() {
		return new JsonResult<>(roleDao.findAllModules());
	}

	@Override
	public JsonResult<Role> saveRole(Role role) {
		roleDao.saveRole(role);
		List<Integer> moduleIds = role.getModuleIds();
		if (moduleIds != null && moduleIds.size() > 0) {
			for (Integer moduleId : moduleIds) {
				Map<String, Object> roleModules = new HashMap<>();
				roleModules.put("role_id", role.getRole_id());
				roleModules.put("module_id", moduleId);
				roleDao.saveRoleModules(roleModules);
			}
		}
		return new JsonResult<>(0, "新增角色成功", role);
	}

	@Override
	public JsonResult<Role> findByName(String name) {
		return new JsonResult<>(roleDao.findByName(name));
	}

	@Override
	public JsonResult<Role> findById(int id) {
		return new JsonResult<>(roleDao.findById(id));
	}

	@Override
	public JsonResult<Role> updateRole(Role role) {
		roleDao.updateRole(role);
		roleDao.deleteRoleModules(role.getRole_id());
		List<Integer> moduleIds = role.getModuleIds();
		for (Integer moduleId : moduleIds) {
			Map<String, Object> roleModules = new HashMap<String, Object>();
			roleModules.put("role_id", role.getRole_id());
			roleModules.put("module_id", moduleId);
			roleDao.saveRoleModules(roleModules);
		}
		return new JsonResult<>(0, "修改角色成功", role);
	}

	@Override
	public JsonResult<?> deleteRole(int id) {
		roleDao.deleteRoleModules(id);
		roleDao.deleteRole(id);
		return new JsonResult<>(0, "删除角色成功", null);
	}
}
