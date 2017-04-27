package wxw.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import wxw.model.base.Module;

/**
 * 权限检查拦截器，判断用户是否具有访问当前模块的权限，若没有踢至无权限页面
 * 
 * @author wxw
 *
 */
public class CheckModuleInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("\t\t3<--权限检查拦截器...");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		System.out.println("\t\t3-->权限检查拦截器...");

		// 获取用户当前正在访问的模块
		int currentModuleId = (int) req.getSession().getAttribute("currentModuleId");
		// 获取用户具有权限的所有模块
		@SuppressWarnings("unchecked")
		List<Module> modules = (List<Module>) req.getSession().getAttribute("allModules");
		for (Module m : modules) {
			if (m.getModule_id() == currentModuleId) {
				return true;
			}
		}
		resp.sendRedirect(req.getContextPath() + "/login/nopower.do");
		return false;
	}

}
