package wxw.service;

import java.util.List;

import wxw.model.base.Cost;
import wxw.model.page.CostPage;
import wxw.util.JsonResult;

public interface CostService {

	JsonResult<List<Cost>> findAll();

	JsonResult<Cost> save(Cost cost);

	JsonResult<Cost> findById(int id);

	JsonResult<Cost> update(Cost cost);

	JsonResult<?> delete(int id);

	JsonResult<List<Cost>> findByPage(CostPage page);
	
}
