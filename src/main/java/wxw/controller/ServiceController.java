package wxw.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import wxw.model.base.Account;
import wxw.model.base.Cost;
import wxw.model.base.ServiceVO;
import wxw.model.page.ServicePage;
import wxw.service.AccountService;
import wxw.service.CostService;
import wxw.service.ServiceService;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/service")
@SessionAttributes("servicePage")
public class ServiceController extends BaseController {

	@Resource
	private ServiceService serviceService;
	@Resource
	private CostService costService;
	@Resource
	private AccountService accountService;

	@RequestMapping("/findService.do")
	public String find(ServicePage page, Model model) {
		JsonResult<List<Map<String, Object>>> json = serviceService.findByPage(page);
		model.addAttribute("services", json.getData());
		model.addAttribute("servicePage", page);
		return "service/service_list";
	}

	@RequestMapping("/startService.do")
	@ResponseBody
	public JsonResult<ServiceVO> updateStart(@RequestParam("service_id") int id) {
		return serviceService.updateStart(id);
	}

	@RequestMapping("/pauseService.do")
	@ResponseBody
	public JsonResult<ServiceVO> updatePause(@RequestParam("service_id") int id) {
		return serviceService.updatePause(id);
	}

	@RequestMapping("/deleteService.do")
	@ResponseBody
	public JsonResult<ServiceVO> updateDelete(@RequestParam("service_id") int id) {
		return serviceService.updateDelete(id);
	}

	@RequestMapping("/toAddService.do")
	public String toAdd(Model model) {
		JsonResult<List<Cost>> json = costService.findAll();
		model.addAttribute("costs", json.getData());
		return "service/service_add";
	}

	@RequestMapping("/findAccount.do")
	@ResponseBody
	public JsonResult<Account> findAccount(@RequestParam("idcardNo") String idcardNo) {
		return accountService.findByIdcardNo(idcardNo);
	}

	@RequestMapping("/addService.do")
	public String add(ServiceVO service) {
		serviceService.save(service);
		return "redirect:findService.do";
	}

	@RequestMapping("/toUpdateService.do")
	public String toUpdate(@RequestParam("service_id") int id, Model model) {
		JsonResult<List<Cost>> json = costService.findAll();
		model.addAttribute("costs", json.getData());
		JsonResult<ServiceVO> sJson = serviceService.findById(id);
		model.addAttribute("service", sJson.getData());
		return "service/service_modi";
	}

	@RequestMapping("/updateService.do")
	public String update(ServiceVO service) {
		serviceService.update(service);
		return "redirect:findService.do";
	}

}
