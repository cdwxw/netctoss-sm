package wxw.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wxw.dao.CostDao;
import wxw.model.base.Cost;
import wxw.model.page.CostPage;
import wxw.service.CostService;
import wxw.util.JsonResult;

@Service
public class CostServiceImpl implements CostService {
	
	@Resource
	private CostDao costDao;

	@Override
	public JsonResult<List<Cost>> findAll() {
		return new JsonResult<>(costDao.findAll());
	}

	@Override
	public JsonResult<Cost> save(Cost cost) {
		cost.setStatus("1");
		cost.setCreatime(new Timestamp(System.currentTimeMillis()));
		costDao.save(cost);
		return new JsonResult<>(0, "新增资费成功!", cost);
	}

	@Override
	public JsonResult<Cost> findById(int id) {
		return new JsonResult<>(costDao.findById(id));
	}

	@Override
	public JsonResult<Cost> update(Cost cost) {
		costDao.update(cost);
		return new JsonResult<>(0, "更新资费成功!", cost);
	}

	@Override
	public JsonResult<?> delete(int id) {
		costDao.delete(id);
		return new JsonResult<>(0, "删除资费成功!", null);
	}

	@Override
	public JsonResult<List<Cost>> findByPage(CostPage page) {
		page.setRows(costDao.findRows());
		return new JsonResult<>(costDao.findByPage(page));
	}

}
