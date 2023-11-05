package com.dru.care.app.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchEmployeeDepDto {
	private Integer empDepId;
	private Integer depId;
	private String depNm;

}
