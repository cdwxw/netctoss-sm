package daotest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wxw.dao.AccountDao;
import wxw.dao.AdminDao;
import wxw.dao.CostDao;
import wxw.dao.RoleDao;
import wxw.dao.ServiceDao;
import wxw.model.base.Account;
import wxw.model.base.Admin;
import wxw.model.base.Cost;
import wxw.model.base.Role;
import wxw.model.base.ServiceVO;
import wxw.model.page.AccountPage;
import wxw.model.page.AdminPage;
import wxw.model.page.CostPage;
import wxw.model.page.RolePage;
import wxw.model.page.ServicePage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestDao {

	// // 测试MyBatis配置
	// ApplicationContext ctx;
	// @Before
	// public void init() {
	// ctx = new ClassPathXmlApplicationContext("classpath:spring.xml", "classpath:spring-mybatis.xml");
	// }
	// @Test
	// public void testMapperScanner() {
	// Object obj = ctx.getBean("mapperScanner");
	// System.out.println(obj);
	// }

	@Resource
	private CostDao costDao;
	@Resource
	private AccountDao accountDao;
	@Resource
	private ServiceDao serviceDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private AdminDao adminDao;

	@Test
	public void testAdminDao() {
		/*
		 * findByCode
		 * 
		 * updateToken
		 */
		// Admin admin = adminDao.findByCode("admin");
		// admin.setToken("wxw123");
		// adminDao.updateToken(admin);
		// System.out.println(admin.getAdmin_id() + "|" + admin.getAdmin_code() + "|" + admin.getToken());

		/*
		 * findByPage
		 */
		AdminPage page = new AdminPage();
		page.setRoleName("");
		List<Admin> list = adminDao.findByPage(page);
		for (Admin a : list) {
			System.out.println(a.getAdmin_code());
			List<Role> roles = a.getRoles();
			for (Role r : roles) {
				System.out.println(r.getName());
			}
			System.out.println("-----------");
		}
		System.out.println(adminDao.findRows(page));

		/*
		 * updatePassword
		 */
		Map<String, Object> map = new HashMap<>();
		List<Integer> ids = new ArrayList<>();
		ids.add(1005);
		ids.add(1006);
		map.put("ids", ids);
		map.put("defaultPassword", "abc");
		adminDao.updatePassword(map);

		/*
		 * saveAdmin
		 */
		Admin admin = new Admin();
		admin.setAdmin_code("ddd");
		admin.setPassword("ccc");
		admin.setName("CCC");
		adminDao.saveAdmin(admin);
		System.out.println(admin.getAdmin_id());

		/*
		 * saveAdminRoles
		 */
		Map<String, Object> adminRoles = new HashMap<>();
		adminRoles.put("admin_id", admin.getAdmin_id());
		adminRoles.put("role_id", 1);
		adminDao.saveAdminRoles(adminRoles);

		/*
		 * findByCode
		 */
		System.out.println(adminDao.findByCode("admin").getEmail());
	}

	// @Test
	public void testCostDao() {
		/*
		 * findAll()
		 */
		List<Cost> list = costDao.findAll();
		for (Cost c : list) {
			System.out.println(c.getCost_id() + "|" + c.getName() + "|" + c.getCreatime());
		}

		/*
		 * save()
		 */
		Cost cost = new Cost();
		cost.setName("测试5");
		cost.setCost_type("1");
		cost.setCreatime(new Timestamp(System.currentTimeMillis()));
		costDao.save(cost);
		System.out.println(cost.getCost_id());

		/*
		 * findById()
		 */
		cost = costDao.findById(1);
		System.out.println(cost.getCost_id() + "|" + cost.getName() + "|" + cost.getCreatime());

		/*
		 * 
		 */
		cost = costDao.findById(19);
		cost.setName("测试C");
		costDao.update(cost);

		/*
		 * findByPage()
		 */
		CostPage page = new CostPage();
		list = costDao.findByPage(page);
		for (Cost c : list) {
			System.out.println(c.getCost_id() + "|" + c.getName() + "|" + c.getCreatime());
		}

		/*
		 * findRows()
		 */
		System.out.println(costDao.findRows());

	}

	// @Test
	public void testAccountDao() {
		/*
		 * findByPage()
		 */
		AccountPage page = new AccountPage();
		page.setReal_name("guojing");
		List<Account> list = accountDao.findByPage(page);
		for (Account a : list) {
			System.out.println(a.getAccount_id() + "|" + a.getLogin_name() + "|" + a.getCreate_date());
		}

		/*
		 * updateStatus()
		 */
		Account account = new Account();
		account.setAccount_id(1020);
		account.setStatus("0");
		accountDao.updateStatus(account);

		/*
		 * save()
		 */
		account = new Account();
		account.setLogin_name("测试1");
		account.setLogin_passwd("123");
		account.setIdcard_no("1231231231231232");
		account.setReal_name("asdf");
		account.setTelephone("123123");
		account.setCreate_date(new Timestamp(System.currentTimeMillis()));
		accountDao.save(account);

		/*
		 * Account findByIdcardNo(String idcardNo);
		 */
		account = accountDao.findByIdcardNo("123456198701230123");
		System.out.println(account);

		/*
		 * Account findById(int id);
		 */
		account = accountDao.findById(1024);
		System.out.println(account);

		/*
		 * Integer update(Account account);
		 */
		account = accountDao.findById(1024);
		account.setReal_name("ccc");
		account.setRecommender_id(1021);
		accountDao.update(account);
	}

	// @Test
	public void testServiceDao() {
		/*
		 * findByPage
		 */
		ServicePage page = new ServicePage();
		List<Map<String, Object>> list = serviceDao.findByPage(page);
		System.out.println(list);
		System.out.println(list.get(0).keySet());

		/*
		 * findRows
		 */
		System.out.println(serviceDao.findRows(page));

		/*
		 * findById
		 */
		System.out.println(serviceDao.findById(2008));

		/*
		 * updateStatus
		 */
		ServiceVO service = new ServiceVO();
		service.setService_id(2008);
		service.setStatus("1");
		serviceDao.updateStatus(service);

		/*
		 * updatePauseByAccount
		 */
		System.out.println(serviceDao.updatePauseByAccount(1010));

		/*
		 * save
		 */
		service.setService_id(2009);
		service.setAccount_id(1010);
		service.setUnix_host("192.168.0.100");
		service.setOs_username("qq");
		service.setLogin_passwd("qq");
		service.setCost_id(1);
		// serviceDao.save(service);

		/*
		 * update
		 */
		serviceDao.update(service);
	}

	// @Test
	public void testRoleDao() {
		/*
		 * findByPage
		 */
		RolePage page = new RolePage();
		System.out.println(roleDao.findByPage(page));
		System.out.println(roleDao.findRows(page));

		/*
		 * findById
		 */
		Role role = roleDao.findById(2);
		System.out.println(role.getModuleIds() + "|" + role.getModules());
	}

}
