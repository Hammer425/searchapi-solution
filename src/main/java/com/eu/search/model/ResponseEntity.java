package com.eu.search.model;

public class ResponseEntity<T> {

	private T data;
	private boolean status;
	private String message;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ResponseEntity error(String message) {
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.setMessage(message);
		responseEntity.setStatus(false);
		return responseEntity;
	}

	public static <T> ResponseEntity success(T data) {
		ResponseEntity responseEntity = new ResponseEntity<T>();
		responseEntity.setData(data);
		responseEntity.setStatus(true);
		return responseEntity;
	}

}
