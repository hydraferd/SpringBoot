package com.example.nds.Payload;

public class ApiResponse {
	 int status;
	 String errorMessage;
	 String message;
	 Object data;
	 
 public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

public ApiResponse(int status, String errorMessage, String message, Object data) {
		this.status=status;
		this.errorMessage=errorMessage;
		this.message=message;
		this.data=data;
	}

}
