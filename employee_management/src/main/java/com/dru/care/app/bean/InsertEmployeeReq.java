package com.dru.care.app.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertEmployeeReq {
	
	private Integer orgId;
	private String firstNm;
	private String lastNm;
	private String middleNm;
	private Integer pincode;
	private String gender;
	private String branch;
	private String address;
	private String emailId;
	private String companyNm;
	private String empDesignation;
	private Long phoneNo;
	private Double empSalary;
	
	
}
