package com.dru.care.app.bean;



import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchEmployeeDto {

	private Integer empId;
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
	private List<FetchEmployeeDepDto> employeeDeptBeans;

}
