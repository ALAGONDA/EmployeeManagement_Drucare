package com.dru.care.app.bean;


import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchDepartmentDto {
	
	private Integer depId;
	private String depNm;
	private Map<String,Long> genderCountDetails;

}
