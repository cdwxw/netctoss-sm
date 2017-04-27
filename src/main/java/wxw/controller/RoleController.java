package wxw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import wxw.model.base.Module;
import wxw.model.base.Role;
import wxw.model.page.RolePage;
import wxw.service.RoleService;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/role")
@SessionAttributes("rolePage")
public class RoleController {

	@Resource
	private RoleService roleService;

	@RequestMapping("/findRole.do")
	public String find(RolePage page, Model model) {
		JsonResult<List<Role>> json = roleService.findByPage(page);
		model.addAttribute("roles", json.getData());
		model.addAttribute("rolePage", page);
		return "role/role_list";
	}

	@RequestMapping("/toAddRole.do")
	public String toAdd(Model model) {
		JsonResult<List<Module>> json = roleService.findAllModules();
		model.addAttribute("modules", json.getData());
		return "role/role_add";
	}

	@RequestMapping("/addRole.do")
	public String add(Role role) {
		roleService.saveRole(role);
		return "redirect:findRole.do";
	}

	@RequestMapping("/checkRoleName.do")
	@ResponseBody
	public JsonResult<Role> checkName(String name) {
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		return roleService.findByName(name);
	}

	@RequestMapping("/toUpdateRole.do")
	public String toUpdate(@RequestParam("role_id") int id, Model model) {
		JsonResult<List<Module>> mJson = roleService.findAllModules();
		model.addAttribute("modules", mJson.getData());
		JsonResult<Role> rJson = roleService.findById(id);
		model.addAttribute("role", rJson.getData());
		return "role/role_modi";
	}

	@RequestMapping("/updateRole.do")
	public String update(Role role) {
		roleService.updateRole(role);
		return "redirect:findRole.do";
	}

	@RequestMapping("/deleteRole.do")
	@ResponseBody
	public JsonResult<?> delete(@RequestParam("role_id") int id) {
		return roleService.deleteRole(id);
	}

}
