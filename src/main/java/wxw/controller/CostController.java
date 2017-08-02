package wxw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import wxw.model.base.Cost;
import wxw.model.page.CostPage;
import wxw.service.CostService;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/cost")
// ②将ModelMap中属性名为costPage的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes("costPage")
public class CostController extends BaseController {

	@Resource
	private CostService costService;

	@RequestMapping("/findCost.do")
	public String find(CostPage page, Model model) {
		JsonResult<List<Cost>> json = costService.findByPage(page);
		model.addAttribute("costs", json.getData());
		// ①向ModelMap中添加一个属性
		model.addAttribute("costPage", page);
		return "cost/cost_list";
	}

	// @RequestMapping("/findCost.do")
	// public String find(Model model) {
	// JsonResult<List<Cost>> json = costService.findAll();
	// model.addAttribute("costs", json.getData());
	// return "cost/cost_list";
	// }

	@RequestMapping("/toAddCost.do")
	public String toAdd() {
		return "cost/cost_add";
	}

	@RequestMapping("/addCost.do")
	public String add(Cost cost, Model model) {
		costService.save(cost);
		return "redirect:findCost.do";
	}

	@RequestMapping("/toUpdateCost.do")
	public String toUpdate(@RequestParam("cost_id") int id, Model model) {
		JsonResult<Cost> json = costService.findById(id);
		model.addAttribute("cost", json.getData());
		return "cost/cost_modi";
	}

	@RequestMapping("/updateCost.do")
	public String update(Cost cost, Model model) {
		costService.update(cost);
		return "redirect:findCost.do";
	}

	@RequestMapping("/deleteCost.do")
	public String delete(@RequestParam("cost_id") int id, Model model) {
		costService.delete(id);
		return "redirect:findCost.do";
	}

}
