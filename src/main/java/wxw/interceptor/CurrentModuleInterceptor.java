package wxw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 判断当前模块拦截器，用来判断用户当前访问的模块
 * 
 * @author wxw
 *
 */
public class CurrentModuleInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("\t2<--当前模块拦截器..");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		System.out.println("\t2-->当前模块拦截器..");

		String uri = req.getRequestURI();
		// 根据rui判断用户当前访问的模块
		int currentModuleId = 0;// 默认为0是主页
		if (uri.contains("role")) {
			currentModuleId = 1;
		} else if (uri.contains("admin")) {
			currentModuleId = 2;
		} else if (uri.contains("cost")) {
			currentModuleId = 3;
		} else if (uri.contains("account")) {
			currentModuleId = 4;
		} else if (uri.contains("service")) {
			currentModuleId = 5;
		} else if (uri.contains("bill")) {
			currentModuleId = 6;
		} else if (uri.contains("report")) {
			currentModuleId = 7;
		} else if (uri.contains("toInformation")) {
			currentModuleId = 8;
		} else if (uri.contains("toPassword")) {
			currentModuleId = 9;
		}
		req.getSession().setAttribute("currentModuleId", currentModuleId);
		return true;
	}

}
