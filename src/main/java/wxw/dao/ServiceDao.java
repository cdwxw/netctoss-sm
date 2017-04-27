package wxw.dao;

import java.util.List;
import java.util.Map;

import wxw.model.base.ServiceVO;
import wxw.model.page.Page;

public interface ServiceDao {

	List<Map<String, Object>> findByPage(Page page);// 关联查询:使用Map封装另2张表的关联字段

	int findRows(Page page);

	ServiceVO findById(int id);

	void updateStatus(ServiceVO service);

	int updatePauseByAccount(int accountId);

	int updateDeleteByAccount(int accountId);

	void save(ServiceVO service);

	void update(ServiceVO service);

}
