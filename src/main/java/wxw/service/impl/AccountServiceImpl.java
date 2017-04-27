package wxw.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wxw.dao.AccountDao;
import wxw.dao.ServiceDao;
import wxw.model.base.Account;
import wxw.model.page.AccountPage;
import wxw.service.AccountService;
import wxw.util.JsonResult;

@Service
public class AccountServiceImpl implements AccountService {

	@Resource
	private AccountDao accountDao;
	@Resource
	private ServiceDao serviceDao;

	@Override
	public JsonResult<List<Account>> findByPage(AccountPage page) {
		page.setRows(accountDao.findRows(page));
		return new JsonResult<>(accountDao.findByPage(page));
	}

	@Override
	public JsonResult<Account> updateStart(int id) {
		// Account account = accountDao.findById(id);
		// 避免再次数据库查询，使用new对象
		Account account = new Account();
		account.setAccount_id(id);
		account.setStatus("0");
		accountDao.updateStatus(account);
		return new JsonResult<>(0, "开通账务账号成功!", account);
	}

	@Override
	public JsonResult<Account> updatePause(int id) {
		// 暂停其下属的业务账号
		serviceDao.updatePauseByAccount(id);
		// 暂停账务账号
		Account account = new Account();
		account.setAccount_id(id);
		account.setStatus("1");
		accountDao.updateStatus(account);
		return new JsonResult<>(0, "暂停账务账号成功!", account);
	}

	@Override
	public JsonResult<Account> updateDelete(int id) {
		// 删除其下属的业务账号
		serviceDao.updateDeleteByAccount(id);
		// 删除账务账号
		Account account = new Account();
		account.setAccount_id(id);
		account.setStatus("2");
		accountDao.updateStatus(account);
		return new JsonResult<>(0, "删除账务账号成功!", account);
	}

	@Override
	public JsonResult<Account> save(Account account) {
		account.setStatus("0");
		account.setCreate_date(new Timestamp(System.currentTimeMillis()));
		accountDao.save(account);
		return new JsonResult<>(0, "新增账务账号成功!", account);
	}

	@Override
	public JsonResult<Account> findByIdcardNo(String idcardNo) {
		return new JsonResult<>(accountDao.findByIdcardNo(idcardNo));
	}

	@Override
	public JsonResult<Account> findById(int id) {
		return new JsonResult<>(accountDao.findById(id));
	}

	@Override
	public JsonResult<Account> update(Account account) {
		accountDao.update(account);
		return new JsonResult<>(0, "修改账务账号成功!", account);
	}

}
