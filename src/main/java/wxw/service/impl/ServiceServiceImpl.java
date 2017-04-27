package wxw.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wxw.dao.AccountDao;
import wxw.dao.ServiceDao;
import wxw.model.base.Account;
import wxw.model.base.ServiceVO;
import wxw.model.page.ServicePage;
import wxw.service.ServiceService;
import wxw.util.JsonResult;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Resource
	private ServiceDao serviceDao;
	@Resource
	private AccountDao accountDao;

	@Override
	public JsonResult<List<Map<String, Object>>> findByPage(ServicePage page) {
		page.setRows(serviceDao.findRows(page));
		return new JsonResult<>(serviceDao.findByPage(page));
	}

	@Override
	public JsonResult<ServiceVO> updateStart(int id) {
		// 需要通过service找到account 此处需要通过数据库查询，不能使用new对象
		ServiceVO service = serviceDao.findById(id);
		// 判断对应的账务账号是否处于开通态
		Account account = accountDao.findById(service.getAccount_id());
		if (!account.getStatus().equals("0")) {
			return new JsonResult<>("账务账号没有开通，不允许开通当前业务账号!");
		}
		service.setStatus("0");
		serviceDao.updateStatus(service);
		return new JsonResult<>(0, "开通业务账号成功!", service);
	}

	@Override
	public JsonResult<ServiceVO> updatePause(int id) {
		// ServiceVO service = serviceDao.findById(id);
		// 避免再次数据库查询，使用new对象
		ServiceVO service = new ServiceVO();
		service.setService_id(id);
		service.setStatus("1");
		serviceDao.updateStatus(service);
		return new JsonResult<>(0, "暂停业务账号成功!", service);
	}

	@Override
	public JsonResult<ServiceVO> updateDelete(int id) {
		ServiceVO service = new ServiceVO();
		service.setService_id(id);
		service.setStatus("2");
		serviceDao.updateStatus(service);
		return new JsonResult<>(0, "删除业务账号成功!", service);
	}

	@Override
	public JsonResult<ServiceVO> save(ServiceVO service) {
		service.setStatus("0");
		service.setCreate_date(new Timestamp(System.currentTimeMillis()));
		serviceDao.save(service);
		return new JsonResult<>(0, "新增业务账号成功!", service);
	}

	@Override
	public JsonResult<ServiceVO> findById(int id) {
		return new JsonResult<>(serviceDao.findById(id));
	}

	@Override
	public JsonResult<ServiceVO> update(ServiceVO service) {
		service.setCreate_date(new Timestamp(System.currentTimeMillis()));
		serviceDao.update(service);
		return new JsonResult<>(0, "更新业务账号成功!", service);
	}

}
