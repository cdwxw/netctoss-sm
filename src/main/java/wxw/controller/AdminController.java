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

import wxw.model.base.Admin;
import wxw.model.base.Module;
import wxw.model.base.Role;
import wxw.model.page.AdminPage;
import wxw.service.AdminService;
import wxw.service.RoleService;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminPage")
public class AdminController {

	@Resource
	private AdminService adminService;

	@Resource
	private RoleService roleService;

	@RequestMapping("/findAdmin.do")
	public String find(AdminPage page, Model model) {
		JsonResult<List<Admin>> aJson = adminService.findByPage(page);
		model.addAttribute("admins", aJson.getData());
		JsonResult<List<Module>> mJson = roleService.findAllModules();
		model.addAttribute("modules", mJson.getData());
		model.addAttribute("adminPage", page);
		return "admin/admin_list";
	}

	@RequestMapping("/resetPassword.do")
	@ResponseBody
	public JsonResult<Map<String, Object>> resetPassword(@RequestParam("ids") String ids) {
		return adminService.updatePassword(ids);
	}

	@RequestMapping("/toAddAdmin.do")
	public String toAdd(Model model) {
		JsonResult<List<Role>> json = adminService.findAllRoles();
		model.addAttribute("roles", json.getData());
		return "admin/admin_add";
	}

	@RequestMapping("/addAdmin.do")
	public String add(Admin admin, Model model) {
		adminService.saveAdmin(admin);
		return "redirect:findAdmin.do";
	}

	@RequestMapping("/checkAdminCode.do")
	@ResponseBody
	public JsonResult<Admin> checkAdminCode(String adminCode) {
		return adminService.findByCode(adminCode);
	}

	@RequestMapping("/toUpdateAdmin.do")
	public String toUpdate(@RequestParam("admin_id") int id, Model model) {
		JsonResult<List<Role>> rJson = adminService.findAllRoles();
		model.addAttribute("roles", rJson.getData());
		JsonResult<Admin> aJson = adminService.findById(id);
		model.addAttribute("admin", aJson.getData());
		return "admin/admin_modi";
	}

	@RequestMapping("/updateAdmin.do")
	public String update(Admin admin) {
		adminService.updateAdmin(admin);
		return "redirect:findAdmin.do";
	}

	@RequestMapping("/deleteAdmin.do")
	@ResponseBody
	public JsonResult<?> delete(@RequestParam("admin_id") int id) {
		return adminService.deleteAdmin(id);
	}

}
