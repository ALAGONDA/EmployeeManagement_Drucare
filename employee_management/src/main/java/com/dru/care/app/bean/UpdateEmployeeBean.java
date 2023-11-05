package com.dru.care.app.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeBean {
	
	@ApiModelProperty(dataType = "java.lang.Long", required = true)
    private Long empId;
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
	private Double empSalary;
	private Boolean isActive;

}
