package com.dru.care.app.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = EmployeeIdNotExist.class)
	public ExceptionResponse employeeIdNotExist(EmployeeIdNotExist employeeIdNotExist) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMsg(employeeIdNotExist.getMessage());
		return exceptionResponse;

	}
	
	@ExceptionHandler(value = OrgIdNotExist.class)
	public ExceptionResponse orgIdNotExist(OrgIdNotExist orgIdNotExist) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMsg(orgIdNotExist.getMessage());
		return exceptionResponse;

	}
	
	@ExceptionHandler(value = GivenListIsEmpty.class)
	public ExceptionResponse givenListIsEmpty(GivenListIsEmpty givenListIsEmpty) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMsg(givenListIsEmpty.getMessage());
		return exceptionResponse;

	}
	
	@ExceptionHandler(value = InvalidExcelFormate.class)
	public ExceptionResponse invalidExcelFormate(InvalidExcelFormate invalidExcelFormate) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMsg(invalidExcelFormate.getMessage());
		return exceptionResponse;

	}
}
