package wxw.service.impl;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import wxw.dao.AdminDao;
import wxw.dao.RoleDao;
import wxw.model.base.Admin;
import wxw.model.base.Role;
import wxw.model.page.AdminPage;
import wxw.model.page.RolePage;
import wxw.service.AdminService;
import wxw.util.JsonResult;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDao adminDao;
	@Resource
	private RoleDao roleDao;

	@Override
	public JsonResult<List<Admin>> findByPage(AdminPage page) {
		page.setRows(adminDao.findRows(page));
		return new JsonResult<>(adminDao.findByPage(page));
	}

	@Override
	public JsonResult<Map<String, Object>> updatePassword(String ids) {
		Map<String, Object> param = new HashMap<>();
		param.put("ids", buildIdList(ids));
		param.put("defaultPassword", "123");
		adminDao.updatePassword(param);
		return new JsonResult<>(0, "密码重置成功", param);
	}

	private List<Integer> buildIdList(String ids) {
		if (ids == null || ids.length() == 0)
			return null;
		List<Integer> list = new ArrayList<>();
		String[] idsArray = ids.split(",");
		for (String id : idsArray) {
			list.add(Integer.valueOf(id));
		}
		return list;
	}

	@Override
	public JsonResult<List<Role>> findAllRoles() {
		RolePage page = new RolePage();
		page.setRowsPerPage(10000);
		return new JsonResult<>(roleDao.findByPage(page));
	}

	@Override
	public JsonResult<Admin> saveAdmin(Admin admin) {
		admin.setEnrolldate(new Timestamp(System.currentTimeMillis()));
		adminDao.saveAdmin(admin);
		List<Integer> roleIds = admin.getRoleIds();
		for (Integer roleId : roleIds) {
			Map<String, Object> adminRoles = new HashMap<String, Object>();
			adminRoles.put("admin_id", admin.getAdmin_id());
			adminRoles.put("role_id", roleId);
			adminDao.saveAdminRoles(adminRoles);
		}
		return new JsonResult<>(0, "新增管理员成功", admin);
	}

	@Override
	public JsonResult<Admin> findByCode(String name) {
		return new JsonResult<>(adminDao.findByCode(name));
	}

	@Override
	public JsonResult<Admin> findById(int id) {
		return new JsonResult<>(adminDao.findById(id));
	}

	@Override
	public JsonResult<Admin> updateAdmin(Admin admin) {
		adminDao.updateAdmin(admin);
		adminDao.deleteAdminRoles(admin.getAdmin_id());
		List<Integer> roleIds = admin.getRoleIds();
		for (Integer roleId : roleIds) {
			Map<String, Object> adminRoles = new HashMap<String, Object>();
			adminRoles.put("admin_id", admin.getAdmin_id());
			adminRoles.put("role_id", roleId);
			adminDao.saveAdminRoles(adminRoles);
		}
		return new JsonResult<>(0, "修改管理员成功", admin);
	}

	@Override
	public JsonResult<?> deleteAdmin(int id) {
		adminDao.deleteAdminRoles(id);
		adminDao.deleteAdmin(id);
		return new JsonResult<>(0, "删除管理员成功", null);
	}

}
