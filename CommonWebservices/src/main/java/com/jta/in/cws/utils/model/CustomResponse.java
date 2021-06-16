package com.jta.in.cws.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

/**
 * The Class CustomResponse.
 */
public class CustomResponse {

	/** The response status. */
	private String responseStatus;

	/** The message. */
	@JsonInclude(Include.NON_NULL)
	private Message message;

	/** The response. */
	private Object response;

	/**
	 * Gets the response status.
	 * @return : the response status
	 */
	public String getResponseStatus() {
		return responseStatus;
	}

	/**
	 * Sets the response status.
	 * @param responseStatus : the new response status
	 */
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	/**
	 * Gets the message.
	 * @return :the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * @param message : the new message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * Gets the response.
	 * @return the response
	 */
	public Object getResponse() {
		return response;
	}

	/**
	 * Sets the response.
	 * @param response the new response
	 *
	 */
	public void setResponse(Object response) {
		this.response = response;
	}

	/**
	 * Instantiates a new custom response.
	 */
	public CustomResponse() {
		super();
	}

	/**
	 * Instantiates a new custom response.
	 * @param responseStatus : the response status
	 * @param message :  the message
	 * @param response : the response
	 */
	public CustomResponse(String responseStatus, Message message,
			Object response) {
		this.responseStatus = responseStatus;
		this.message = message;
		this.response = response;

	}

	/**
	 * Instantiates a new custom response.
	 * @param responseStatus : the response status
	 * @param message : the message
	 *
	 */
	public CustomResponse(String responseStatus, Message message) {
		this.responseStatus = responseStatus;
		this.message = message;
	}

	/**
	 * Instantiates a new custom response.
	 * @param responseStatus : the response status
	 * @param response  : the response
	 */
	public CustomResponse(String responseStatus, Object response) {
		this.responseStatus = responseStatus;
		this.response = response;
	}
}
