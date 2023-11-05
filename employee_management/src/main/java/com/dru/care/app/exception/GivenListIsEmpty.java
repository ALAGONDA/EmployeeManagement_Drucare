package com.dru.care.app.exception;

@SuppressWarnings("serial")
public class GivenListIsEmpty extends RuntimeException {

	public GivenListIsEmpty(String msg) {
		super(msg);
	}
}
