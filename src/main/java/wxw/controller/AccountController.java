package wxw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import wxw.model.base.Account;
import wxw.model.page.AccountPage;
import wxw.service.AccountService;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/account")
@SessionAttributes("accountPage")
public class AccountController extends BaseController {

	@Resource
	private AccountService accountService;

	@RequestMapping("/findAccount.do")
	public String find(AccountPage page, Model model) {
		JsonResult<List<Account>> json = accountService.findByPage(page);
		model.addAttribute("accounts", json.getData());
		model.addAttribute("accountPage", page);
		return "account/account_list";
	}

	@RequestMapping("/startAccount.do")
	@ResponseBody
	public JsonResult<Account> updateStart(@RequestParam("account_id") int id) {
		return accountService.updateStart(id);
	}

	@RequestMapping("/pauseAccount.do")
	@ResponseBody
	public JsonResult<Account> updatePause(@RequestParam("account_id") int id) {
		return accountService.updatePause(id);
	}

	@RequestMapping("/deleteAccount.do")
	@ResponseBody
	public JsonResult<Account> updateDelete(@RequestParam("account_id") int id) {
		return accountService.updateDelete(id);
	}

	@RequestMapping("/toAddAccount.do")
	public String toAdd() {
		return "account/account_add";
	}

	@RequestMapping("/addAccount.do")
	public String add(Account account) {
		accountService.save(account);
		return "redirect:findAccount.do";
	}

	@RequestMapping("/searchAccount.do")
	@ResponseBody
	public JsonResult<Account> searchAccount(String idcardNo) {
		return accountService.findByIdcardNo(idcardNo);
	}

	@RequestMapping("/toUpdateAccount.do")
	public String toUpdate(@RequestParam("account_id") int id, Model model) {
		JsonResult<Account> json = accountService.findById(id);
		model.addAttribute("account", json.getData());
		return "account/account_modi";
	}

	@RequestMapping("/updateAccount.do")
	public String update(Account account) {
		accountService.update(account);
		return "redirect:findAccount.do";
	}

}
