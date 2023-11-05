package com.dru.care.app.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertPatientBean {

	private String firstNm;
	private String lastNm;
	private String address;
	private Integer age;
	private Long phoneNo;
	private String gender;

}
