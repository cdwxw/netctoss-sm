package wxw.util;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 若页面上生日字段为空或格式不对，点击保存会报400错误：Spring MVC "The request sent by the client was syntactically incorrect ()"
 * 参见：http://blog.csdn.net/yiluoak_47/article/details/10821747
 * 原因：Spring在调用当前方法时，会自动将表单参数注入到account对象中，当发现表单中有birthdate字段，
 * 而account对象中也有birthdate属性时，Spring会自动调用一个日期类型转换器（类），将表单中的字符串
 * 数据转换成对象中的日期类型。该转换器对日期的处理逻辑不完善，如果表单中的日期为null或格式不对会报错。
 * 需要重写日期转换器，将表单中的日期（yyyy-MM-dd）字符串转成java.sql.Date
 * 
 * @author wxw
 *
 */
public class DateEditor extends PropertyEditorSupport {
	
	private String pattern = "yyyy-MM-dd";
	
	public DateEditor() {
		
	}
	
	public DateEditor(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public void setAsText(String text) 
			throws IllegalArgumentException {
		if(text == null || text.length() == 0) {
			setValue(null);
		} else {
			SimpleDateFormat sf = new SimpleDateFormat(this.pattern);
			String dateStr = sf.format(Date.valueOf(text));
			setValue(Date.valueOf(dateStr));
		}
	}

}
