package wxw.dao;

import java.util.List;

import wxw.model.base.Cost;
import wxw.model.page.Page;

public interface CostDao {

	List<Cost> findAll();

	void save(Cost cost);

	Cost findById(int id);

	void update(Cost cost);

	void delete(int id);

	List<Cost> findByPage(Page page);

	int findRows();

}
