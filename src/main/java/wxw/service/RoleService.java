package wxw.service;

import java.util.List;

import wxw.model.base.Module;
import wxw.model.base.Role;
import wxw.model.page.RolePage;
import wxw.util.JsonResult;

public interface RoleService {

	JsonResult<List<Role>> findByPage(RolePage page);

	JsonResult<List<Module>> findAllModules();

	JsonResult<Role> saveRole(Role role);

	JsonResult<Role> findByName(String name);

	JsonResult<Role> findById(int id);

	JsonResult<Role> updateRole(Role role);

	JsonResult<?> deleteRole(int id);

}
