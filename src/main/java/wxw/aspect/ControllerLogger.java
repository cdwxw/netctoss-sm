package wxw.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 测试环绕增强的方面组件
 * 
 * @author wxw
 *
 */
@Component
@Aspect
public class ControllerLogger {

	/**
	 * 环绕通知对方法的要求： 1、public方法 2、返回Object 3、参数是ProceedingJoinPoint
	 * 
	 * 环绕通知类型的AOP，Spring会将目标组件方法的调用权交给方面组件，通过参数ProceedingJoinPoint调用目标组件方法
	 * 
	 * @param p
	 * @return
	 * @throws Throwable
	 */
	@Around("within(wxw.controller..*)")
	public Object clogger(ProceedingJoinPoint p) throws Throwable {

		String className = p.getTarget().getClass().getName();
		String methodName = p.getSignature().getName();

		System.out.println("------->AOP调用:" + className + "." + methodName + "()");
		Object obj = p.proceed();// 调用目标组件

		return obj;
	}

}
