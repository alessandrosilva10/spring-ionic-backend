package br.com.alessandro.alga.exceptionhandler;

public class CustomError {
	private String userMessage;
	private String developerMessage;
	
	public CustomError(String userMessage, String developerMessage) {
		super();
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
	}
	
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
}
