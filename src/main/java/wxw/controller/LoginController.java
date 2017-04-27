package wxw.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wxw.model.base.Admin;
import wxw.service.AdminService;
import wxw.service.LoginService;
import wxw.util.ImageUtil;
import wxw.util.JsonResult;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Resource
	private LoginService loginService;

	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "main/login";
	}

	@RequestMapping("/cookieLogin.do")
	@ResponseBody
	public JsonResult<Admin> cookieLogin(
			String adminCode, 
			String password, 
			String valiCode, 
			HttpSession session, 
			HttpServletResponse resp,
			HttpServletRequest req) {
		return loginService.cookieLogin(
				adminCode, password, valiCode, session, resp, req);
	}

	@RequestMapping("/sessionLogin.do")
	@ResponseBody
	public JsonResult<Admin> sessionLogin(
			String adminCode, 
			String password, 
			String valiCode, 
			HttpSession session) {
		return loginService.sessionLogin(
				adminCode, password, valiCode, session);
	}

	@RequestMapping("/createImage.do")
	public void createImage(HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, BufferedImage> imageMap = ImageUtil.createImage();
		String code = imageMap.keySet().iterator().next();

		// 保存code到服务端session
		session.setAttribute("sessionImageCode", code);
		System.out.println("sessionImageCode:" + session.getAttribute("sessionImageCode"));

		// 将图片写出响应
		BufferedImage image = imageMap.get(code);
		response.setContentType("image/jpeg");
		OutputStream ops = response.getOutputStream();
		ImageIO.write(image, "jpeg", ops);
		ops.close();
	}

	@RequestMapping("/toIndex.do")
	public String toIndex() {
		return "main/index";
	}

	@RequestMapping("/nopower.do")
	public String nopower() {
		return "main/nopower";
	}

	@RequestMapping("/error.do")
	public String error() {
		return "main/error";
	}

}
