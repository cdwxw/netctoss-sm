package wxw.aspect;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import wxw.model.base.Admin;

/**
 * 记录异常日志
 * 
 * @author wxw
 *
 */
@Component
@Aspect
public class ExceptionLogger {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ExceptionLogger.class);

	@Resource
	private HttpServletRequest req;

	@Around("within(wxw.controller..*)")
	public Object exLogger(ProceedingJoinPoint p) {
		Object obj = null;
		try {
			obj = p.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			// 记录日志
			StringBuffer sb = new StringBuffer();
			Admin admin = (Admin) req.getSession().getAttribute("admin");
			if (admin != null) {
				String adminCode = admin.getAdmin_code();
				String ip = req.getRemoteHost();
				String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String className = p.getTarget().getClass().getName();
				String methodName = p.getSignature().getName();
				sb.append("用户[").append(adminCode)
					.append("],IP[").append(ip)
					.append("],在[").append(now)
					.append("],调用[").append(className)
					.append(".").append(methodName)
					.append("()]时,发生如下异常:\n");
			}

			StackTraceElement[] elems = e.getStackTrace();
			for (StackTraceElement elem : elems) {
				sb.append("\t").append(elem.toString()).append("\n");
			}
			logger.info(sb.toString());

			// 记录完日志后，将异常抛出，由ExceptionResolver继续处理异常
			throw new RuntimeException(e);
		}
		// 返回目标组件
		return obj;
	}

}
