package wxw.service;

import java.util.List;
import java.util.Map;

import wxw.model.base.Admin;
import wxw.model.base.Role;
import wxw.model.page.AdminPage;
import wxw.util.JsonResult;

public interface AdminService {

	JsonResult<List<Admin>> findByPage(AdminPage page);

	JsonResult<Map<String, Object>> updatePassword(String ids);

	JsonResult<List<Role>> findAllRoles();

	JsonResult<Admin> saveAdmin(Admin admin);

	JsonResult<Admin> findByCode(String adminCode);

	JsonResult<Admin> findById(int id);

	JsonResult<Admin> updateAdmin(Admin admin);

	JsonResult<?> deleteAdmin(int id);

}
