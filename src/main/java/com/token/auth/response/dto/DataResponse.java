package com.token.auth.response.dto;

public class DataResponse {

	private String status;
	private String message;
	private Object data;

	public DataResponse() {
		super();
	}

	public DataResponse(String status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}
