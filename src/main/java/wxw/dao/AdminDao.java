package wxw.dao;

import java.util.List;
import java.util.Map;

import wxw.model.base.Admin;
import wxw.model.base.Module;
import wxw.model.page.Page;

public interface AdminDao {

	List<Admin> findByPage(Page page);

	int findRows(Page page);

	/**
	 * Map中包含2个值，管理员ID集合、默认密码 
	 * key 					value 
	 * ids 					List<Integer> 
	 * defaultPassword 		String
	 */
	void updatePassword(Map<String, Object> param);

	void saveAdmin(Admin admin);

	void saveAdminRoles(Map<String, Object> adminRoles);

	Admin findByCode(String adminCode);

	Admin findById(int id);

	void updateAdmin(Admin admin);

	void deleteAdminRoles(int adminId);

	void deleteAdmin(int id);

	void updateToken(Admin admin);// 设置用户登录口令

	List<Module> findModulesByAdmin(int adminId);// 获取用户拥有的全部权限

}
