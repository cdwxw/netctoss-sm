package wxw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

//import wxw.model.base.Report;
import wxw.model.page.ReportPage;
//import wxw.service.ReportService;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/report")
// 1、将变量放入session；2、方法调用时匹配传参
@SessionAttributes("reportPage")
public class ReportController extends BaseController {

//	@Resource
//	private ReportService reportService;

	@RequestMapping("/findReport.do")
	public String find(ReportPage page, Model model) {
//		JsonResult<List<Report>> json = reportService.findByPage(page);
//		model.addAttribute("reports", json.getData());
//		model.addAttribute("reportPage", page);
		return "report/report_list";
	}

//	@RequestMapping("/toAddReport.do")
//	public String toAdd() {
//		return "report/report_add";
//	}
//
//	@RequestMapping("/addReport.do")
//	public String add(Report report, Model model) {
//		reportService.save(report);
//		return "redirect:findReport.do";
//	}
//
//	@RequestMapping("/toUpdateReport.do")
//	public String toUpdate(@RequestParam("report_id") int id, Model model) {
//		JsonResult<Report> json = reportService.findById(id);
//		model.addAttribute("report", json.getData());
//		return "report/report_modi";
//	}
//
//	@RequestMapping("/updateReport.do")
//	public String update(Report report, Model model) {
//		reportService.update(report);
//		return "redirect:findReport.do";
//	}
//
//	@RequestMapping("/deleteReport.do")
//	public String delete(@RequestParam("report_id") int id, Model model) {
//		reportService.delete(id);
//		return "redirect:findReport.do";
//	}

}
