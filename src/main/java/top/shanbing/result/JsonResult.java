package top.shanbing.result;


//@ApiModel：用对象来接收参数
//@ApiModel
public class JsonResult<T> {
	//@ApiProperty：用对象接收参数时，描述对象的一个字段
	//@ApiModelProperty("返回状态，是否成功")
	private int result;

	//@ApiModelProperty("返回信息")
	private String msg;

	//@ApiModelProperty(value = "返回的数据")
	private T data;
	
	public JsonResult(){
	}
	
	public JsonResult(T data){
		this.result = 1;
		this.msg = "success";
		this.data = data;
	}
	
	public JsonResult(String msg, T data){
		this.result = 1;
		this.msg = msg;
		this.data = data;
	}

	public JsonResult(int result, String msg){
		this.result = result;
		this.msg = msg;
	}
	
	public JsonResult(int result, String msg, T data){
		this.result = result;
		this.msg = msg;
		this.data = data;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
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
		return "JsonResult [result=" + result + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
