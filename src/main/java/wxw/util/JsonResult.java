package wxw.util;

import java.io.Serializable;

/**
 * 用于封装服务器到客户端的Json返回值
 */
public class JsonResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int SUCCESS=0;
	public static final int ERROR=1;
	
	private int status;
	private String msg = "";
	private T data;
	
	public JsonResult() {
		status = SUCCESS;
	}
	//为了方便,重载n个构造器
	public JsonResult(
		int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public JsonResult(String errorMsg){
		this(ERROR, errorMsg, null);
	}
	public JsonResult(T data){
		this(SUCCESS, "", data);
	}
	public JsonResult(int status){
		this(status, "", null);
	}
	public JsonResult(Throwable e){
		this(ERROR, e.getMessage(), null);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "JsonResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
}