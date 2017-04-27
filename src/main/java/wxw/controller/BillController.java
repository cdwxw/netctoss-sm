package wxw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import wxw.model.base.Bill;
import wxw.model.page.BillPage;
//import wxw.service.BillService;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/bill")
// 1、将变量放入session；2、方法调用时匹配传参
@SessionAttributes("billPage")
public class BillController extends BaseController {

//	@Resource
//	private BillService billService;

	@RequestMapping("/findBill.do")
	public String find(BillPage page, Model model) {
//		JsonResult<List<Bill>> json = billService.findByPage(page);
//		model.addAttribute("bills", json.getData());
//		model.addAttribute("billPage", page);
		return "bill/bill_list";
	}

//	@RequestMapping("/toAddBill.do")
//	public String toAdd() {
//		return "bill/bill_add";
//	}
//
//	@RequestMapping("/addBill.do")
//	public String add(Bill bill, Model model) {
//		billService.save(bill);
//		return "redirect:findBill.do";
//	}
//
//	@RequestMapping("/toUpdateBill.do")
//	public String toUpdate(@RequestParam("bill_id") int id, Model model) {
//		JsonResult<Bill> json = billService.findById(id);
//		model.addAttribute("bill", json.getData());
//		return "bill/bill_modi";
//	}
//
//	@RequestMapping("/updateBill.do")
//	public String update(Bill bill, Model model) {
//		billService.update(bill);
//		return "redirect:findBill.do";
//	}
//
//	@RequestMapping("/deleteBill.do")
//	public String delete(@RequestParam("bill_id") int id, Model model) {
//		billService.delete(id);
//		return "redirect:findBill.do";
//	}

}
