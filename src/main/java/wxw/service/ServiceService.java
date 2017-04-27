package wxw.service;

import java.util.List;
import java.util.Map;

import wxw.model.base.ServiceVO;
import wxw.model.page.ServicePage;
import wxw.util.JsonResult;

public interface ServiceService {

	JsonResult<List<Map<String, Object>>> findByPage(ServicePage page);

	JsonResult<ServiceVO> updateStart(int id);

	JsonResult<ServiceVO> updatePause(int id);

	JsonResult<ServiceVO> updateDelete(int id);

	JsonResult<ServiceVO> save(ServiceVO service);

	JsonResult<ServiceVO> findById(int id);
	
	JsonResult<ServiceVO> update(ServiceVO service);

}
