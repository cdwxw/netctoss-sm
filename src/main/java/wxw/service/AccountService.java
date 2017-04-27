package wxw.service;

import java.util.List;

import wxw.model.base.Account;
import wxw.model.page.AccountPage;
import wxw.util.JsonResult;

public interface AccountService {

	JsonResult<List<Account>> findByPage(AccountPage page);

	JsonResult<Account> updateStart(int id);

	JsonResult<Account> updatePause(int id);

	JsonResult<Account> updateDelete(int id);

	JsonResult<Account> save(Account account);

	JsonResult<Account> findByIdcardNo(String idcardNo);

	JsonResult<Account> findById(int id);

	JsonResult<Account> update(Account account);

}
