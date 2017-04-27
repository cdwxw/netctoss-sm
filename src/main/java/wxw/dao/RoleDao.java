package wxw.dao;

import java.util.List;
import java.util.Map;

import wxw.model.base.Module;
import wxw.model.base.Role;
import wxw.model.page.Page;

public interface RoleDao {

	List<Role> findByPage(Page page);

	int findRows(Page page);

	List<Module> findAllModules();

	void saveRole(Role role);

	void saveRoleModules(Map<String, Object> roleModules);

	Role findByName(String name);

	Role findById(int id);

	void updateRole(Role role);

	void deleteRoleModules(int roleId);

	void deleteRole(int roleId);

}
