package wxw.dao;

import java.util.List;

import wxw.model.base.Account;
import wxw.model.page.Page;

public interface AccountDao {

	List<Account> findByPage(Page page);

	int findRows(Page page);

	void updateStatus(Account account);

	void save(Account account);

	Account findByIdcardNo(String idcardNo);// 新增时用于查询出推荐人

	Account findById(int id);// 修改时用于查询出推荐人身份证号

	void update(Account account);

}
