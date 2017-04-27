package wxw.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import wxw.dao.AdminDao;
import wxw.model.base.Admin;
import wxw.model.base.Module;
import wxw.service.LoginService;
import wxw.util.JsonResult;

@Service
public class LoginServiceImpl implements LoginService {
	public final static int SUCCESS = 0;
	public final static int ADMINCODE_ERROR = 1;
	public final static int PASSWORD_ERROR = 2;
	public final static int VALICODE_ERROR = 3;

	@Resource
	private AdminDao adminDao;

	@Override
	public JsonResult<Admin> cookieLogin(
			String adminCode, 
			String password, 
			String valiCode, 
			HttpSession session, 
			HttpServletResponse resp,
			HttpServletRequest req) {

		Thread t = Thread.currentThread();
		System.out.println(("cookieLogin.do:" + t.getName() + "," + t.getId()));

		// 校验验证码
		String sessionImageCode = (String) session.getAttribute("sessionImageCode");
		if (valiCode == null || !valiCode.equalsIgnoreCase(sessionImageCode)) {
			return new JsonResult<Admin>(VALICODE_ERROR, "验证码错误.", null);
		}
		
		// 校验用户
		Admin admin = adminDao.findByCode(adminCode);
		if (admin == null) {
			return new JsonResult<>(ADMINCODE_ERROR, "账号不存在.", null);
		} else if (!admin.getPassword().equals(password)) {
			return new JsonResult<>(PASSWORD_ERROR, "密码错误.", null);
		} else {
			// 登陆时随机生成token，并保存至数据库
			String token = UUID.randomUUID().toString();
			admin.setToken(token);
			adminDao.updateToken(admin);
			
			// 下发token至客户端cookie，登陆检查用
			Cookie cookie = new Cookie("token", admin.getAdmin_id() + "," + token);
			cookie.setPath("/");
			resp.addCookie(cookie);
			
			// 保存用户权限至session，权限认证用
			List<Module> modules = adminDao.findModulesByAdmin(admin.getAdmin_id());
			session.setAttribute("allModules", modules);

			return new JsonResult<Admin>(admin);
		}
	}

	@Override
	public JsonResult<Admin> sessionLogin(
			String adminCode, 
			String password,
			String valiCode,
			HttpSession session) {

		Thread t = Thread.currentThread();
		System.out.println(("sessionLogin.do:" + t.getName() + "," + t.getId()));

		// 校验验证码
		String sessionImageCode = (String) session.getAttribute("sessionImageCode");
		System.out.println("valiCode:"+valiCode);
		System.out.println("sessionImageCode:"+sessionImageCode);
		if (valiCode == null || !valiCode.equalsIgnoreCase(sessionImageCode)) {
			return new JsonResult<Admin>(VALICODE_ERROR, "验证码错误.", null);
		}
		
		// 校验用户
		Admin admin = adminDao.findByCode(adminCode);
		if (admin == null) {
			return new JsonResult<>(ADMINCODE_ERROR, "账号不存在.", null);
		} else if (!admin.getPassword().equals(password)) {
			return new JsonResult<>(PASSWORD_ERROR, "密码错误.", null);
		} else {
			// 保存用户对象至session，登陆检查用
			session.setAttribute("admin", admin);
			
			// 保存用户权限至session，权限认证用
			List<Module> modules = adminDao.findModulesByAdmin(admin.getAdmin_id());
			session.setAttribute("allModules", modules);

			return new JsonResult<Admin>(admin);
		}
	}

	@Override
	public boolean checkToken(String id, String token) {
		Admin admin = adminDao.findById(Integer.parseInt(id));
		return token.equals(admin.getToken());
	}

}
