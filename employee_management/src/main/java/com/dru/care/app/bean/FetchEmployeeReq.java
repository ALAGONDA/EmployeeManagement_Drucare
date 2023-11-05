package com.dru.care.app.bean;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchEmployeeReq {

	@ApiModelProperty(dataType = "java.lang.Integer", required = true)
    private Integer empId;
	@ApiModelProperty(dataType = "java.lang.Integer", required = true)
    private Integer orgId;
	private String firstNm;
	private String middleNm;
	private String lastNm;
	private String address;
	private Integer pincode;
	private String gender;
	private String branch;
	private String emailId;
	private String companyNm;
	private String empDesignation;
	private Long phoneNo;
	private BigDecimal empSalary;
	private BigDecimal minSalary;
	private BigDecimal maxSalary;
	@ApiModelProperty(dataType = "java.lang.Integer", required = true)
    private Integer pageNo;
	private String empNm;
	private String depNm;
	
	
	

}
