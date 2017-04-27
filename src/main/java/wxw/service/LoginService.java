package wxw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wxw.model.base.Admin;
import wxw.util.JsonResult;

public interface LoginService {

	JsonResult<Admin> cookieLogin(
			String adminCode, 
			String password, 
			String valiCode, 
			HttpSession session, 
			HttpServletResponse resp,
			HttpServletRequest req);

	JsonResult<Admin> sessionLogin(
			String adminCode, 
			String password, 
			String valiCode,
			HttpSession session);

	boolean checkToken(String id, String token);// cookie登陆时用

}
