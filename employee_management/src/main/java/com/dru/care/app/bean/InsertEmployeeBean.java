package com.dru.care.app.bean;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All attributes of employee data")
public class InsertEmployeeBean {

	@JsonIgnore
	@ApiModelProperty(value = "Each Employee Have Unique EmpId", dataType = "java.lang.Long", required = false)
	private Long empId;
	@ApiModelProperty(dataType = "java.lang.Integer", required = false)
	private Integer orgId;
	@ApiModelProperty(value = "Employee First Name", dataType = "java.lang.String", required = true, example = "Konda")
	private String firstNm;
	@ApiModelProperty(value = "Employee Middle Name", dataType = "java.lang.String", required = false, example = "ZZZ")
    private String middleNm;
	@ApiModelProperty(value = "Employee Last Name", dataType = "java.lang.String", required = true, example = "Raghu")
    private String lastNm;
	private String address;
	private Integer pincode;
	private String gender;
	private String branch;
	@ApiModelProperty(value = "Employee Email Id", dataType = "java.lang.String", required = true, example = "konda@gmail.com")
	private String emailId;
	private String companyNm;
	private String empDesignation;
	@ApiModelProperty(value = "Employee Phone Number", dataType = "java.lang.Long", required = true, example = "8374241485")
    private Long phoneNo;
	private Double empSalary;
	private Boolean isActive;
	private List<EmployeeDeptBean> listOfEmpDeptBean;

}
