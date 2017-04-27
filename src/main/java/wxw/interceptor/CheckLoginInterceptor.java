package wxw.interceptor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import wxw.model.base.Admin;
import wxw.service.LoginService;

/**
 * 登陆检查拦截器，在用户访问除登陆之外的功能时，判断用户是否已经登陆成功，否则踢回登陆页面
 * 
 * @author wxw
 *
 */
public class CheckLoginInterceptor implements HandlerInterceptor {

	@Resource
	private LoginService loginService;

	/**
	 * 请求结束时最终调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	/**
	 * Controller之后调用
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("1<--登陆检查拦截器.");
	}

	/**
	 * Controller之前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		System.out.println("------------------------------------------------------------");
		System.out.println("1-->登陆检查拦截器.");

		return sessionCheck(req, resp);
//		return cookieCheck(req, resp);
	}

	/**
	 * session检查
	 */
	private boolean sessionCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Admin admin = (Admin) req.getSession().getAttribute("admin");
		if (admin == null) {
			resp.sendRedirect(req.getContextPath() + "/login/toLogin.do");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * cookie检查
	 */
	private boolean cookieCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (!checkLogin(req)) {
			resp.sendRedirect(req.getContextPath() + "/login/toLogin.do");
			return false;
		} else {
			return true;
		}
	}

	// 检查是否登陆
	private boolean checkLogin(HttpServletRequest req) {
		String value = getCookie(req, "token");
		if (value == null) {
			return false;
		}
		String[] data = value.split(",");
		if (data.length != 2) {
			return false;
		}
		String loginUserId = data[0];
		String token = data[1];
		return loginService.checkToken(loginUserId, token);
	}

	// 获取Cookie值
	private String getCookie(HttpServletRequest req, String name) {
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
