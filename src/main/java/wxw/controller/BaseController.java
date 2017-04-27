package wxw.controller;

import java.sql.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import wxw.util.DateEditor;

/**
 * 所有Controller的父类，用于封装一些共用的逻辑或算法
 * 
 * @author wxw
 *
 */
public class BaseController {

	// Spring在请求开始时（调用Controller方法前）先调用该方法
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

}
