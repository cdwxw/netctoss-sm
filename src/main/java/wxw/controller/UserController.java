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

//import wxw.model.base.User;
import wxw.model.base.Module;
import wxw.model.base.Role;
//import wxw.model.page.UserPage;
//import wxw.service.UserService;
import wxw.service.RoleService;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/user")
@SessionAttributes("userPage")
public class UserController {

//	@Resource
//	private UserService userService;
//
//	@Resource
//	private RoleService roleService;

	@RequestMapping("/toInformation.do")
	public String toInformation() {
		return "user/user_info";
	}

	@RequestMapping("/toPassword.do")
	public String toPassword() {
		return "user/user_modi_pwd";
	}

//	@RequestMapping("/findUser.do")
//	public String find(UserPage page, Model model) {
//		JsonResult<List<User>> aJson = userService.findByPage(page);
//		model.addAttribute("users", aJson.getData());
//		JsonResult<List<Module>> mJson = roleService.findAllModules();
//		model.addAttribute("modules", mJson.getData());
//		model.addAttribute("userPage", page);
//		return "user/user_list";
//	}
//
//	@RequestMapping("/resetPassword.do")
//	@ResponseBody
//	public JsonResult<Map<String, Object>> resetPassword(@RequestParam("ids") String ids) {
//		return userService.updatePassword(ids);
//	}
//
//	@RequestMapping("/toAddUser.do")
//	public String toAdd(Model model) {
//		JsonResult<List<Role>> json = userService.findAllRoles();
//		model.addAttribute("roles", json.getData());
//		return "user/user_add";
//	}
//
//	@RequestMapping("/addUser.do")
//	public String add(User user, Model model) {
//		userService.saveUser(user);
//		return "redirect:findUser.do";
//	}
//
//	@RequestMapping("/checkUserCode.do")
//	@ResponseBody
//	public JsonResult<User> checkUserCode(String userCode) {
//		return userService.findByCode(userCode);
//	}
//
//	@RequestMapping("/toUpdateUser.do")
//	public String toUpdate(@RequestParam("user_id") int id, Model model) {
//		JsonResult<List<Role>> rJson = userService.findAllRoles();
//		model.addAttribute("roles", rJson.getData());
//		JsonResult<User> aJson = userService.findById(id);
//		model.addAttribute("user", aJson.getData());
//		return "user/user_modi";
//	}
//
//	@RequestMapping("/updateUser.do")
//	public String update(User user) {
//		userService.updateUser(user);
//		return "redirect:findUser.do";
//	}
//
//	@RequestMapping("/deleteUser.do")
//	@ResponseBody
//	public JsonResult<?> delete(@RequestParam("user_id") int id) {
//		return userService.deleteUser(id);
//	}

}
