package com.dru.care.app.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.collections4.CollectionUtils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dru.care.app.bean.DeleteEmployeeReq;
import com.dru.care.app.bean.DeleteEmployeeReqs;
import com.dru.care.app.bean.EmployeeDeptBean;
import com.dru.care.app.bean.FetchDepartmentDto;
import com.dru.care.app.bean.FetchEmployeeDepDto;
import com.dru.care.app.bean.FetchEmployeeDto;
import com.dru.care.app.bean.FetchEmployeeReq;
import com.dru.care.app.bean.InsertEmployeeBean;
import com.dru.care.app.bean.InsertEmployeeReqs;
import com.dru.care.app.bean.InsertPatientBean;
import com.dru.care.app.bean.UpdateEmployeeBean;
import com.dru.care.app.bean.InsertEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReqs;
import com.dru.care.app.dao.EmployeeBeanDao;
import com.dru.care.app.dao.ExportEmployeeDetailsToExcel;
import com.dru.care.app.exception.GivenListIsEmpty;
import com.dru.care.app.exception.InvalidExcelFormate;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class EmployeeBeanServiceImpl implements EmployeeBeanService {

	@Autowired
	private EmployeeBeanDao employeeBeanDao;

	@Override
	public Boolean insertEmployeeBeanUsingMap(InsertEmployeeReq insertEmployeeReq) {

		return employeeBeanDao.insertEmployeeBeanUsingMap(insertEmployeeReq);
	}

	@Override
	public Boolean insertEmployeeBeanUsingMapSqlParameter(InsertEmployeeReq insertEmployeeReq) {

		return employeeBeanDao.insertEmployeeBeanUsingMapSqlParameter(insertEmployeeReq);
	}

	@Override
	public Boolean insertEmployeeBeanUsingBeanProperty(InsertEmployeeReq insertEmployeeReq) {

		return employeeBeanDao.insertEmployeeBeanUsingBeanProperty(insertEmployeeReq);
	}

	@Override
	public Boolean updateEmployeeBeanUsingMap(UpdateEmployeeReq updateEmployeeReq) {
		return employeeBeanDao.updateEmployeeBeanUsingMap(updateEmployeeReq);
	}

	@Override
	public Boolean updateEmployeeBeanUsingMapSqlParameter(UpdateEmployeeReq updateEmployeeReq) {
		return employeeBeanDao.updateEmployeeBeanUsingMapSqlParameter(updateEmployeeReq);
	}

	@Override
	public Boolean updateEmployeeBeanUsingBeanProperty(UpdateEmployeeReq updateEmployeeReq) {
		return employeeBeanDao.updateEmployeeBeanUsingBeanProperty(updateEmployeeReq);
	}

	@Override
	public Boolean softDeleteEmployeeBeanUsingMap(DeleteEmployeeReq deleteEmployeeReq) {
		return employeeBeanDao.softDeleteEmployeeBeanUsingMap(deleteEmployeeReq);
	}

	@Override
	public Boolean softDeleteEmployeeBeanUsingMapSqlParameter(DeleteEmployeeReq deleteEmployeeReq) {
		return employeeBeanDao.softDeleteEmployeeBeanUsingMapSqlParameter(deleteEmployeeReq);
	}

	@Override
	public Boolean softDeleteEmployeeBeanUsingBeanParameter(DeleteEmployeeReq deleteEmployeeReq) {
		return employeeBeanDao.softDeleteEmployeeBeanUsingBeanParameter(deleteEmployeeReq);
	}

	@Override
	public Boolean insertEmployeeBeanUsingBatchWithCreateBatch(InsertEmployeeReqs insertEmployeeReq) {

		List<InsertEmployeeBean> listInsertUpdateEmployeeBeans = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(insertEmployeeReq.getInsertEmployeeBeans())) {
			for (InsertEmployeeBean insertEmployeeBean : insertEmployeeReq.getInsertEmployeeBeans()) {
				insertEmployeeBean.setOrgId(insertEmployeeReq.getOrgId());
				listInsertUpdateEmployeeBeans.add(insertEmployeeBean);
			}
		}
		Boolean insertFlag = false;
		if (CollectionUtils.isNotEmpty(listInsertUpdateEmployeeBeans)) {
			insertFlag = employeeBeanDao.insertEmployeeBeanUsingBatchWithCreateBatch(listInsertUpdateEmployeeBeans);
		}
		return insertFlag;
	}

	@Override
	public Boolean updateEmployeeBeanUsingBatchWithCreateBatch(UpdateEmployeeReqs updateEmployeeReqs) {

		List<UpdateEmployeeBean> listInsertUpdateEmployeeBeans = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(updateEmployeeReqs.getUpdateEmployeeBeans())) {
			for (UpdateEmployeeBean updateEmployeeBean : updateEmployeeReqs.getUpdateEmployeeBeans()) {
				updateEmployeeReqs.setOrgId(updateEmployeeBean.getOrgId());
				listInsertUpdateEmployeeBeans.add(updateEmployeeBean);
			}
		}
		Boolean insertFlag = false;
		if (CollectionUtils.isNotEmpty(listInsertUpdateEmployeeBeans)) {
			insertFlag = employeeBeanDao.updateEmployeeBeanUsingBatchWithCreateBatch(listInsertUpdateEmployeeBeans);
		}
		return insertFlag;
	}

	@Override
	public Boolean softDeleteEmployeeBeanUsingBatchWithCreateBatch(DeleteEmployeeReqs deleteEmployeeReqs) {

		return employeeBeanDao.softDeleteEmployeeBeanUsingBatchWithCreateBatch(deleteEmployeeReqs);
	}

	@Override
	public Boolean insertEmployeeBeanUsingBatchWithMapSqlParameter(InsertEmployeeReqs listEmployeeReqs) {
		List<InsertEmployeeBean> listInsertUpdateEmployeeBeans = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(listEmployeeReqs.getInsertEmployeeBeans())) {
			for (InsertEmployeeBean insertEmployeeBean : listEmployeeReqs.getInsertEmployeeBeans()) {
				insertEmployeeBean.setOrgId(listEmployeeReqs.getOrgId());
				listInsertUpdateEmployeeBeans.add(insertEmployeeBean);
			}
		}
		Boolean insertFlag = false;
		if (CollectionUtils.isNotEmpty(listInsertUpdateEmployeeBeans)) {
			insertFlag = employeeBeanDao.insertEmployeeBeanUsingBatchWithMapSqlParameter(listInsertUpdateEmployeeBeans);
		}
		return insertFlag;
	}

	@Override
	public Boolean updateEmployeeBeanUsingBatchWithMapSqlParameter(UpdateEmployeeReqs updateEmployeeReqs) {
		return employeeBeanDao.updateEmployeeBeanUsingBatchWithMapSqlParameter(updateEmployeeReqs);
	}

	@Override
	public Boolean softDeleteEmployeeBeanUsingBatchWithMapSqlParameter(DeleteEmployeeReqs deleteEmployeeReqs) {
		return employeeBeanDao.softDeleteEmployeeBeanUsingBatchWithMapSqlParameter(deleteEmployeeReqs);
	}

	@Override
	public Boolean insertEmployeeBeanUsingBatchWithMap(InsertEmployeeReqs listEmployeeReqs) {
		List<InsertEmployeeBean> listInsertUpdateEmployeeBeans = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(listEmployeeReqs.getInsertEmployeeBeans())) {
			for (InsertEmployeeBean insertEmployeeBean : listEmployeeReqs.getInsertEmployeeBeans()) {
				insertEmployeeBean.setOrgId(listEmployeeReqs.getOrgId());
				listInsertUpdateEmployeeBeans.add(insertEmployeeBean);
			}
		}
		Boolean insertFlag = false;
		if (CollectionUtils.isNotEmpty(listInsertUpdateEmployeeBeans)) {
			insertFlag = employeeBeanDao.insertEmployeeBeanUsingBatchWithMap(listInsertUpdateEmployeeBeans);
		}
		return insertFlag;
	}

	@Override
	public Boolean updateEmployeeBeanUsingBatchWithMap(UpdateEmployeeReqs updateEmployeeReqs) {
		return employeeBeanDao.updateEmployeeBeanUsingBatchWithMap(updateEmployeeReqs);
	}

	@Override
	public Boolean softDeleteEmployeeBeanUsingBatchWithMap(DeleteEmployeeReqs deleteEmployeeReqs) {
		return employeeBeanDao.softDeleteEmployeeBeanUsingBatchWithMap(deleteEmployeeReqs);
	}

	@Override
	public Boolean insertEmployeeBeanUsingBatch(InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanDao.insertEmployeeBeanUsingBatch(insertEmployeeReq);
	}

	@Override
	public Boolean updateEmployeeBeanUsingBatch(UpdateEmployeeReqs updateEmployeeReqs) {

		return employeeBeanDao.updateEmployeeBeanUsingBatch(updateEmployeeReqs);
	}

	@Override
	public Boolean softDeleteEmployeeBeanUsingBatch(DeleteEmployeeReqs deleteEmployeeReqs) {

		return employeeBeanDao.softDeleteEmployeeBeanUsingBatch(deleteEmployeeReqs);
	}

	/*
	 * Using Single EndPoint for insert and update employee details using
	 * NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean insertAndUpdateEmployee(InsertEmployeeReqs insertEmployeeReq) {
		List<InsertEmployeeBean> saveList = new ArrayList<>();
		List<InsertEmployeeBean> updateList = new ArrayList<>();

		// Here I am Checking given list have empty or null
		if (CollectionUtils.isNotEmpty(insertEmployeeReq.getInsertEmployeeBeans())) {
			for (InsertEmployeeBean insertEmployeeBean : insertEmployeeReq.getInsertEmployeeBeans()) {
				// Here i am setting orgId inside InsertUpdateEmployeeBean Object
				insertEmployeeBean.setOrgId(insertEmployeeReq.getOrgId());
				// Here if empId is null then we can insert otherwise we can update the data
				if (insertEmployeeBean.getEmpId() == null) {
					saveList.add(insertEmployeeBean);
				} else {
					updateList.add(insertEmployeeBean);
				}
			}
		}

		Boolean insertFlag = false;
		Boolean updateFlag = false;

		// Here I am Checking given saveList have empty or null
		if (CollectionUtils.isNotEmpty(saveList)) {
			insertFlag = employeeBeanDao.insertEmployee(saveList);
		}

		// Here I am Checking given updateList have empty or null
		if (CollectionUtils.isNotEmpty(updateList)) {
			updateFlag = employeeBeanDao.updateEmployee(updateList);
		}

		return insertFlag || updateFlag;
	}

	@Override
	public Boolean insertEmployeeUsingJdbcTemplate(InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanDao.insertEmployeeUsingJdbcTemplate(insertEmployeeReq);
	}

	@Override
	public Boolean insertEmployeeUsingNamed(InsertEmployeeReqs insertEmployeeReq) {
		List<InsertEmployeeBean> listInsertUpdateEmployeeBeans = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(insertEmployeeReq.getInsertEmployeeBeans())) {
			for (InsertEmployeeBean insertEmployeeBean : insertEmployeeReq.getInsertEmployeeBeans()) {
				insertEmployeeBean.setOrgId(insertEmployeeReq.getOrgId());
				listInsertUpdateEmployeeBeans.add(insertEmployeeBean);
			}
		}
		Boolean insertFlag = false;
		if (CollectionUtils.isNotEmpty(listInsertUpdateEmployeeBeans)) {
			insertFlag = employeeBeanDao.insertEmployeeUsingNamedParameter(listInsertUpdateEmployeeBeans);
		}
		return insertFlag;

	}

	@Override
	public Boolean updateEmployeeUsingJdbcTemplate(UpdateEmployeeReqs updateEmployeeReqs) {
		return employeeBeanDao.updateEmployeeUsingJdbcTemplate(updateEmployeeReqs);
	}

	/*
	 * Insert Employee Details With KeyHolder Using JdbcTemplate
	 */
	@Override
	public Boolean insertEmployeeWithKeyHolderUsingJdbc(InsertEmployeeReq insertEmployeeReq) {

		return employeeBeanDao.insertEmployeeWithKeyHolderUsingJdbc(insertEmployeeReq);
	}

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate
	 */
	@Override
	public Boolean insertEmployeeWithKeyHolderUsingNamed(InsertEmployeeReq insertEmployeeReq) {

		return employeeBeanDao.insertEmployeeWithKeyHolderUsingNamed(insertEmployeeReq);
	}

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate with
	 * Batch Update
	 */
	@Override
	public Boolean insertEmployeeUsingKeyHolderWithBatch(InsertEmployeeReqs insertEmployeeReq) {
		List<InsertEmployeeBean> listInsertEmployeeBeans = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(insertEmployeeReq.getInsertEmployeeBeans())) {
			for (InsertEmployeeBean insertEmployeeBean : insertEmployeeReq.getInsertEmployeeBeans()) {
				insertEmployeeBean.setOrgId(insertEmployeeReq.getOrgId());
				listInsertEmployeeBeans.add(insertEmployeeBean);
			}
		}
		Boolean insertFlag = false;
		if (CollectionUtils.isNotEmpty(listInsertEmployeeBeans)) {
			insertFlag = employeeBeanDao.insertEmployeeUsingKeyHolderWithBatch(listInsertEmployeeBeans);
		}
		return insertFlag;
	}

	/*
	 * Insert Employee Details With KeyHolder Using JdbcTemplate with Batch Update
	 */
	@Override
	public Boolean insertEmployeeUsingKeyHolder(InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanDao.insertEmployeeUsingKeyHolder(insertEmployeeReq);
	}

	/*
	 * fetching employee details Using QueryForObject With NamedParameter
	 * JdbcTemplate
	 */
	@Override
	public FetchEmployeeDto fecthEmployeeDetailsUsingQueryForObjectWithNamed(FetchEmployeeReq fetchEmployeeReq) {
		FetchEmployeeReq fecthEmployeeDetailsObj = employeeBeanDao
				.fecthEmployeeDetailsUsingQueryForObjectWithNamed(fetchEmployeeReq);
		FetchEmployeeDto employeeBeanResponse = new FetchEmployeeDto();
		employeeBeanResponse.setEmpId(fecthEmployeeDetailsObj.getEmpId());

		employeeBeanResponse.setFirstNm(fecthEmployeeDetailsObj.getFirstNm());
		employeeBeanResponse.setMiddleNm(fecthEmployeeDetailsObj.getMiddleNm());
		employeeBeanResponse.setLastNm(fecthEmployeeDetailsObj.getLastNm());
		employeeBeanResponse.setAddress(fecthEmployeeDetailsObj.getAddress());
		employeeBeanResponse.setPincode(fecthEmployeeDetailsObj.getPincode());
		employeeBeanResponse.setGender(fecthEmployeeDetailsObj.getGender());
		employeeBeanResponse.setBranch(fecthEmployeeDetailsObj.getBranch());
		employeeBeanResponse.setEmailId(fecthEmployeeDetailsObj.getEmailId());
		employeeBeanResponse.setCompanyNm(fecthEmployeeDetailsObj.getCompanyNm());
		employeeBeanResponse.setEmpDesignation(fecthEmployeeDetailsObj.getEmpDesignation());
		employeeBeanResponse.setPhoneNo(fecthEmployeeDetailsObj.getPhoneNo());
		employeeBeanResponse.setEmpSalary(fecthEmployeeDetailsObj.getEmpSalary());
		return employeeBeanResponse;
	}

	/*
	 * fetching employee details Using QueryForList With NamedParameter JdbcTemplate
	 */
	@Override
	public List<FetchEmployeeDto> fecthEmployeeDetailsUsingQueryForListWithNamed(FetchEmployeeReq fetchEmployeeReq) {

		List<Map<String, Object>> fecthEmployeeDetailsObj = employeeBeanDao
				.fecthEmployeeDetailsUsingQueryForListWithNamed(fetchEmployeeReq);

		List<FetchEmployeeDto> list = new ArrayList<>();
		for (Map<String, Object> map : fecthEmployeeDetailsObj) {
			FetchEmployeeDto employeeBeanResponse = new FetchEmployeeDto();
			employeeBeanResponse.setEmpId((Integer) map.get("emp_id"));

			employeeBeanResponse.setFirstNm((String) map.get("first_nm"));
			employeeBeanResponse.setMiddleNm((String) map.get("middle_nm"));
			employeeBeanResponse.setLastNm((String) map.get("last_nm"));
			employeeBeanResponse.setAddress((String) map.get("address"));
			employeeBeanResponse.setPincode((Integer) map.get("pincode"));
			employeeBeanResponse.setGender((String) map.get("gender"));
			employeeBeanResponse.setBranch((String) map.get("branch"));
			employeeBeanResponse.setEmailId((String) map.get("email_id"));
			employeeBeanResponse.setCompanyNm((String) map.get("company_nm"));
			employeeBeanResponse.setEmpDesignation((String) map.get("emp_designation"));
			employeeBeanResponse.setPhoneNo((Long) map.get("phone_no"));
			employeeBeanResponse.setEmpSalary((BigDecimal) map.get("emp_salary"));
			list.add(employeeBeanResponse);
		}

		return list;
	}

	/*
	 * fetching employee details Using QueryForMap With NamedParameter JdbcTemplate
	 */
	@Override
	public FetchEmployeeDto fecthEmployeeDetailsUsingQueryForMapWithNamed(FetchEmployeeReq fetchEmployeeReq) {
		Map<String, Object> fecthEmployeeDetailsObj = employeeBeanDao
				.fecthEmployeeDetailsUsingQueryForMapWithNamed(fetchEmployeeReq);
		FetchEmployeeDto employeeBeanResponse = new FetchEmployeeDto();
		employeeBeanResponse.setEmpId((Integer) fecthEmployeeDetailsObj.get("emp_id"));
		employeeBeanResponse.setFirstNm((String) fecthEmployeeDetailsObj.get("first_nm"));
		employeeBeanResponse.setMiddleNm((String) fecthEmployeeDetailsObj.get("middle_nm"));
		employeeBeanResponse.setLastNm((String) fecthEmployeeDetailsObj.get("last_nm"));
		employeeBeanResponse.setAddress((String) fecthEmployeeDetailsObj.get("address"));
		employeeBeanResponse.setPincode((Integer) fecthEmployeeDetailsObj.get("pincode"));
		employeeBeanResponse.setGender((String) fecthEmployeeDetailsObj.get("gender"));
		employeeBeanResponse.setBranch((String) fecthEmployeeDetailsObj.get("branch"));
		employeeBeanResponse.setEmailId((String) fecthEmployeeDetailsObj.get("email_id"));
		employeeBeanResponse.setCompanyNm((String) fecthEmployeeDetailsObj.get("company_nm"));
		employeeBeanResponse.setEmpDesignation((String) fecthEmployeeDetailsObj.get("emp_designation"));
		employeeBeanResponse.setPhoneNo((Long) fecthEmployeeDetailsObj.get("phone_no"));
		employeeBeanResponse.setEmpSalary((BigDecimal) fecthEmployeeDetailsObj.get("emp_salary"));
		return employeeBeanResponse;
	}

	/*
	 * fetching employee details Using Query With NamedParameter JdbcTemplate
	 */
	@Override
	public List<FetchEmployeeDto> fecthEmployeeDetailsUsingQuery(FetchEmployeeReq fetchEmployeeReq) {

		List<FetchEmployeeReq> fecthEmployeeDetailsObj = employeeBeanDao
				.fecthEmployeeDetailsUsingQuery(fetchEmployeeReq);
		List<FetchEmployeeDto> list = new ArrayList<>();
		for (FetchEmployeeReq fetchEmployeeReqObj : fecthEmployeeDetailsObj) {
			FetchEmployeeDto employeeBeanResponse = new FetchEmployeeDto();
			employeeBeanResponse.setEmpId(fetchEmployeeReqObj.getEmpId());
			employeeBeanResponse.setFirstNm(fetchEmployeeReqObj.getFirstNm());
			employeeBeanResponse.setMiddleNm(fetchEmployeeReqObj.getMiddleNm());
			employeeBeanResponse.setLastNm(fetchEmployeeReqObj.getLastNm());
			employeeBeanResponse.setAddress(fetchEmployeeReqObj.getAddress());
			employeeBeanResponse.setPincode(fetchEmployeeReqObj.getPincode());
			employeeBeanResponse.setGender(fetchEmployeeReqObj.getGender());
			employeeBeanResponse.setBranch(fetchEmployeeReqObj.getBranch());
			employeeBeanResponse.setEmailId(fetchEmployeeReqObj.getEmailId());
			employeeBeanResponse.setCompanyNm(fetchEmployeeReqObj.getCompanyNm());
			employeeBeanResponse.setEmpDesignation(fetchEmployeeReqObj.getEmpDesignation());
			employeeBeanResponse.setPhoneNo(fetchEmployeeReqObj.getPhoneNo());
			employeeBeanResponse.setEmpSalary(fetchEmployeeReqObj.getEmpSalary());
			list.add(employeeBeanResponse);

		}
		return list;
	}

	/*
	 * fetching employee details Using DynamicQuery With NamedParameter JdbcTemplate
	 */
	@Override
	public List<FetchEmployeeDto> fecthEmployeeDetailsUsingDynamicQuery(FetchEmployeeReq fetchEmployeeReq) {
		List<Map<String, Object>> fecthEmployeeDetailsObj = employeeBeanDao
				.fecthEmployeeDetailsUsingDynamicQuery(fetchEmployeeReq);
		List<FetchEmployeeDto> list = new ArrayList<>();
		for (Map<String, Object> map : fecthEmployeeDetailsObj) {
			FetchEmployeeDto employeeBeanResponse = new FetchEmployeeDto();
			employeeBeanResponse.setEmpId((Integer) map.get("emp_id"));
			employeeBeanResponse.setFirstNm((String) map.get("first_nm"));
			employeeBeanResponse.setMiddleNm((String) map.get("middle_nm"));
			employeeBeanResponse.setLastNm((String) map.get("last_nm"));
			employeeBeanResponse.setAddress((String) map.get("address"));
			employeeBeanResponse.setPincode((Integer) map.get("pincode"));
			employeeBeanResponse.setGender((String) map.get("gender"));
			employeeBeanResponse.setBranch((String) map.get("branch"));
			employeeBeanResponse.setEmailId((String) map.get("email_id"));
			employeeBeanResponse.setCompanyNm((String) map.get("company_nm"));
			employeeBeanResponse.setEmpDesignation((String) map.get("emp_designation"));
			employeeBeanResponse.setPhoneNo((Long) map.get("phone_no"));
			employeeBeanResponse.setEmpSalary((BigDecimal) map.get("emp_salary"));
			list.add(employeeBeanResponse);
		}

		return list;

	}

	/*
	 * Using Single EndPoint for insert and update employee details with KeyHolder
	 * using NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean insertAndUpdateEmployeeWithDepartment(InsertEmployeeReqs insertEmployeeReqs) {
		Long empId = null;
		Boolean inserted = false;
		Boolean updated = false;
		if (CollectionUtils.isNotEmpty(insertEmployeeReqs.getInsertEmployeeBeans())) {
			for (InsertEmployeeBean insertEmployeeBeanObj : insertEmployeeReqs.getInsertEmployeeBeans()) {
				List<EmployeeDeptBean> saveEmpDeptList = new ArrayList<>();
				List<EmployeeDeptBean> updateEmpDeptList = new ArrayList<>();

				insertEmployeeBeanObj.setOrgId(insertEmployeeReqs.getOrgId());
				if (insertEmployeeBeanObj.getEmpId() == null) {
					empId = employeeBeanDao.insertEmployee(insertEmployeeBeanObj);
					if (empId != null) {
						inserted = true;
					}
				} else {
					updated = employeeBeanDao.updateEmployee(insertEmployeeBeanObj);
				}

				if (CollectionUtils.isNotEmpty(insertEmployeeBeanObj.getListOfEmpDeptBean())) {
					for (EmployeeDeptBean employeeDeptBeanObj : insertEmployeeBeanObj.getListOfEmpDeptBean()) {
						employeeDeptBeanObj.setOrgId(insertEmployeeBeanObj.getOrgId());
						if (employeeDeptBeanObj.getEmpDepId() == null) {
							saveEmpDeptList.add(employeeDeptBeanObj);
						} else {
							updateEmpDeptList.add(employeeDeptBeanObj);
						}
					}
				}

				if (CollectionUtils.isNotEmpty(saveEmpDeptList)) {
					inserted = employeeBeanDao.insertEmpDept(empId, saveEmpDeptList);

				}
				if (CollectionUtils.isNotEmpty(updateEmpDeptList)) {
					updated = employeeBeanDao.updateEmpDept(updateEmpDeptList);

				}

			}
		}
		return inserted || updated;
	}

	/*
	 * fetching particular employee department details Using NamedParameter
	 * JdbcTemplate
	 */
	@Override
	public List<FetchEmployeeDto> fecthEmployeeWithDepartmentDetailsUsingQuery(FetchEmployeeReq fetchEmployeeReq) {
		List<Map<String, Object>> fecthEmployeeWithDepartmentObj = employeeBeanDao
				.fecthEmployeeWithDepartmentDetailsUsingQuery(fetchEmployeeReq);
		if (CollectionUtils.isNotEmpty(fecthEmployeeWithDepartmentObj)) {

			int count = 0;
			List<FetchEmployeeDepDto> empDept = null;
			FetchEmployeeDto fetchEmployeeDto = null;
			List<FetchEmployeeDto> empList = new ArrayList<>();
			Set<Integer> set = new HashSet<>();
			for (Map<String, Object> row : fecthEmployeeWithDepartmentObj) {
				// count++;
				if (set.add((Integer) row.get("emp_id"))) {
					if (fetchEmployeeDto != null) {
						fetchEmployeeDto.setEmployeeDeptBeans(empDept);
						empList.add(fetchEmployeeDto);
					}
					fetchEmployeeDto = new FetchEmployeeDto();
					fetchEmployeeDto.setEmpId((Integer) row.get("emp_id"));

					fetchEmployeeDto.setFirstNm((String) row.get("first_nm"));
					fetchEmployeeDto.setMiddleNm((String) row.get("middle_nm"));
					fetchEmployeeDto.setLastNm((String) row.get("last_nm"));
					fetchEmployeeDto.setAddress((String) row.get("address"));
					fetchEmployeeDto.setPincode((Integer) row.get("pincode"));
					fetchEmployeeDto.setGender((String) row.get("gender"));
					fetchEmployeeDto.setBranch((String) row.get("branch"));
					fetchEmployeeDto.setEmailId((String) row.get("email_id"));
					fetchEmployeeDto.setCompanyNm((String) row.get("company_nm"));
					fetchEmployeeDto.setEmpDesignation((String) row.get("emp_designation"));
					fetchEmployeeDto.setPhoneNo((Long) row.get("phone_no"));
					fetchEmployeeDto.setEmpSalary((BigDecimal) row.get("emp_salary"));
					empDept = new ArrayList<>();

				}
				FetchEmployeeDepDto employeeDeptBean = new FetchEmployeeDepDto();
				employeeDeptBean.setDepId((Integer) row.get("dep_id"));
				employeeDeptBean.setDepNm((String) row.get("dep_nm"));
				employeeDeptBean.setEmpDepId((Integer) row.get("emp_dep_id"));
				empDept.add(employeeDeptBean);
				count++;

			}
			// fetching for last row
			if (count == fecthEmployeeWithDepartmentObj.size()) {
				if (fetchEmployeeDto != null) {
					fetchEmployeeDto.setEmployeeDeptBeans(empDept);
					empList.add(fetchEmployeeDto);
				}
			}
			return empList;
		}
		throw new GivenListIsEmpty("Empty List...!!!");

	}

	@Override
	public List<FetchDepartmentDto> fetchDepartmentWiseGenderCount(FetchEmployeeReq fetchEmployeeReq) {
		List<Map<String, Object>> fetchGenderWithDepartmentDetails = employeeBeanDao
				.fetchDepartmentWiseGenderCount(fetchEmployeeReq);
		if (CollectionUtils.isNotEmpty(fetchGenderWithDepartmentDetails)) {
			Map<Integer, FetchDepartmentDto> fetchDepMap = new LinkedHashMap<>();
			fetchGenderWithDepartmentDetails.forEach(row -> {
				Integer depId = (Integer) row.get("dep_id");
				if (!fetchDepMap.containsKey(depId)) {
					FetchDepartmentDto fetchDepartmentDtoObj = new FetchDepartmentDto();
					fetchDepartmentDtoObj.setDepId(depId);
					fetchDepartmentDtoObj.setDepNm((String) row.get("dep_nm"));
					fetchDepMap.put(depId, fetchDepartmentDtoObj);
				}
				FetchDepartmentDto fetchDepartmentDtoObj = fetchDepMap.get(depId);
				Map<String, Long> map = fetchDepartmentDtoObj.getGenderCountDetails();
				if (map == null) {
					map = new LinkedHashMap<>();
					fetchDepartmentDtoObj.setGenderCountDetails(map);
				}
				String gender = (String) row.get("gender");
				Long countGender = (Long) row.get("gender_count");
				map.put(gender, countGender);
			});
			return new ArrayList<>(fetchDepMap.values());

		}
		throw new GivenListIsEmpty("Empty List...!!!");
	}

	@Override
	public byte[] generateEmployeePdfReport() throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		document.open();

		Font f = FontFactory.getFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14.00f);
		f.setStyle(Font.BOLD);

		PdfContentByte para = writer.getDirectContent();
		para.rectangle(document.left(), document.bottom(), document.right() - document.left(),
				document.top() - document.bottom());
		para.stroke();

		PdfPTable table = new PdfPTable(2);
		Paragraph paraForOrgDetails = new Paragraph();
		paraForOrgDetails.setAlignment(Element.ALIGN_LEFT);
		paraForOrgDetails.add(new Phrase("Mamatha Hospital", f));
		paraForOrgDetails.add(Chunk.NEWLINE);
		paraForOrgDetails.add(new Phrase("plot 2-37/1,Madhapur"));
		paraForOrgDetails.add(Chunk.NEWLINE);
		paraForOrgDetails.add(new Phrase("Hyderabad Telangana India-500025"));
		paraForOrgDetails.add(Chunk.NEWLINE);
		paraForOrgDetails.add(new Phrase("Contact No : 8374241485"));
		paraForOrgDetails.add(Chunk.NEWLINE);
		paraForOrgDetails.add(new Phrase("GST : 32APTGT1752B277"));

		Paragraph paraForPatientDetails = new Paragraph();

		paraForPatientDetails.setAlignment(Element.ALIGN_LEFT);
		paraForPatientDetails.add(new Phrase("Bill To : ", f));
		paraForPatientDetails.add(Chunk.NEWLINE);
		paraForPatientDetails.add(new Phrase("Mr.Raghu | 25 Years | Male", f));
		paraForPatientDetails.add(Chunk.NEWLINE);
		paraForPatientDetails.add(new Phrase("UHID : P00003725", f));
		paraForPatientDetails.add(Chunk.NEWLINE);
		paraForPatientDetails.add(new Phrase("Address :", f));
		paraForPatientDetails.add(Chunk.NEWLINE);
		paraForPatientDetails.add(new Phrase("Warangal Urban Hanumakonda Telangana 506006"));

		PdfPCell cellForOrg = new PdfPCell();
		cellForOrg.setBorder(0);
		cellForOrg.addElement(paraForOrgDetails);
		PdfPCell cellForPatient = new PdfPCell();
		cellForPatient.setBorder(0);
		cellForPatient.addElement(paraForPatientDetails);

		table.addCell(cellForOrg);
		table.addCell(cellForPatient);
		table.setTotalWidth(document.getPageSize().getWidth() - 70);
		table.writeSelectedRows(0, -1, document.left() - 1, document.top() - 35, para);

		PdfPTable table1 = new PdfPTable(6);

		PdfPCell departmentCell = new PdfPCell(new Phrase("Department", f));
		// departmentCell.setBorder(0);
		PdfPCell ashCell = new PdfPCell(new Phrase("#", f));
		// ashCell.setBorder(0);

		PdfPCell serviceNameCell = new PdfPCell(new Phrase("Service Name", f));
		// serviceNameCell.setBorder(0);

		PdfPCell quantityCell = new PdfPCell(new Phrase("Qty", f));
		// quantityCell.setBorder(0);

		PdfPCell serviceAmtCell = new PdfPCell(new Phrase("Service Amt", f));
		PdfPCell netAmtCell = new PdfPCell(new Phrase("Net Amt", f));
		// serviceAmtCell.setBorder(0);

		table1.addCell(departmentCell);
		table1.addCell(ashCell);
		table1.addCell(serviceNameCell);
		table1.addCell(quantityCell);
		table1.addCell(serviceAmtCell);
		table1.addCell(netAmtCell);

		table1.setHeaderRows(6);

		table1.setTotalWidth(document.getPageSize().getWidth() - 70);

		String[] data = { "Cardiology", "1",
				"1. Admission fees, \n2. 10 minute Apgar Colour, \n3.Hydroxyindolean panel(5)- 24 hours Urine", "1.00",
				"200.00", "200.00" };

		for (String cellData : data) {
			PdfPCell cell = new PdfPCell(new Paragraph(cellData));
			table1.addCell(cell);
		}

		table1.writeSelectedRows(0, -1, document.left() - 1, document.top() - table.getTotalHeight() - 45, para);

		PdfPTable addInfoTable = new PdfPTable(3);

		// Create PdfPCell for "Total"
		PdfPCell billModeLabelCell = new PdfPCell(new Phrase("Totals :  ", f));
		PdfPCell servicebBillModeValueCell = new PdfPCell(new Phrase("200.00", f));
		PdfPCell netBillModeValueCell = new PdfPCell(new Phrase("200.00", f));
		billModeLabelCell.setBorder(0);
		servicebBillModeValueCell.setBorder(0);
		netBillModeValueCell.setBorder(0);
		servicebBillModeValueCell.setPaddingTop(35);
		netBillModeValueCell.setPaddingTop(35);
		billModeLabelCell.setPaddingTop(35);
		netBillModeValueCell.setPaddingLeft(130);
		servicebBillModeValueCell.setPaddingLeft(130);
		billModeLabelCell.setPaddingLeft(125);

		// Add the cells to the additionalInfoTable
		addInfoTable.addCell(billModeLabelCell);
		addInfoTable.addCell(servicebBillModeValueCell);
		addInfoTable.addCell(netBillModeValueCell);
		// Set the total width of the additionalInfoTable
		addInfoTable.setTotalWidth(document.getPageSize().getWidth() - 70);

		// Write the additionalInfoTable to the document
		addInfoTable.writeSelectedRows(0, -1, document.left() - 1,
				document.top() - addInfoTable.getTotalHeight() - 45 - table1.getTotalHeight() - 20, para);

		PdfPTable additionalInfoTable = new PdfPTable(2);

		// Create PdfPCell for "Paid Payment Mode"
		PdfPCell paymentModeLabelCell = new PdfPCell(new Phrase("Paid Payment Mode  ", f));
		PdfPCell paymentModeValueCell = new PdfPCell(new Phrase(" :Cash 200.00/- RS."));
		paymentModeLabelCell.setBorder(0);
		paymentModeValueCell.setBorder(0);
		paymentModeLabelCell.setPaddingRight(120);
		paymentModeLabelCell.setPaddingTop(50);
		paymentModeValueCell.setPaddingTop(50);

		// Create PdfPCell for "Bill Amount in Words"
		PdfPCell billAmountInWordsLabelCell = new PdfPCell(new Phrase("Bill Amount in Words ", f));
		PdfPCell billAmountInWordsValueCell = new PdfPCell(new Phrase(" :Two Hundred Rupees Only."));
		billAmountInWordsLabelCell.setBorder(0);
		billAmountInWordsValueCell.setBorder(0);
		billAmountInWordsLabelCell.setPaddingRight(120);
		billAmountInWordsLabelCell.setPaddingTop(8);
		billAmountInWordsValueCell.setPaddingTop(8);

		PdfPCell mesgInWordsLabelCell = new PdfPCell(new Phrase("#Received Rs 200.00/- From Mr.Raghu With Thanks.."));
		mesgInWordsLabelCell.setBorder(0);
		mesgInWordsLabelCell.setColspan(2); // Spanning the cell across 2 columns
		mesgInWordsLabelCell.setPaddingTop(8);

		// Add the cells to the additionalInfoTable
		additionalInfoTable.addCell(paymentModeLabelCell);
		additionalInfoTable.addCell(paymentModeValueCell);
		additionalInfoTable.addCell(billAmountInWordsLabelCell);
		additionalInfoTable.addCell(billAmountInWordsValueCell);
		additionalInfoTable.addCell(mesgInWordsLabelCell); // Add the cell to the table

		// Set the total width of the additionalInfoTable
		additionalInfoTable.setTotalWidth(document.getPageSize().getWidth() - 70);

		// Write the additionalInfoTable to the document
		additionalInfoTable.writeSelectedRows(0, -1, document.left() - 1,
				document.top() - additionalInfoTable.getTotalHeight() - 45 - table1.getTotalHeight() - 20, para);

		document.close();
		return out.toByteArray();

	}

	@Override
	public Boolean insertEmployeeDetailsThroughExcelFile(MultipartFile file, String fileFormate) throws IOException {
		Boolean insertedFlag = false;
		if (StringUtils.getFilenameExtension(file.getOriginalFilename()).equals(fileFormate)) {
			List<String> requiredFields = Arrays.asList("FirstName", "Address", "LastName", "Age", "Gender",
					"PhoneNumber");
			List<InsertPatientBean> list = new ArrayList<>();
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			// for headers
			XSSFRow headers = workbook.getSheetAt(0).getRow(0);
			for (int j = 0; j < headers.getLastCellNum(); j++) {
				if (!requiredFields.contains(headers.getCell(j).getStringCellValue())) {
					// custom exception
					System.out.println("Not preseneted.." + headers.getCell(j).getStringCellValue());
				}
			}

			for (int index = 1; index < sheet.getPhysicalNumberOfRows(); index++) {
				// skip S.No i,e row.getCell(0)
				InsertPatientBean insertPatientReq = new InsertPatientBean();
				XSSFRow row = sheet.getRow(index);
				insertPatientReq.setFirstNm(row.getCell(1).getStringCellValue());
				insertPatientReq.setLastNm(row.getCell(2).getStringCellValue());
				insertPatientReq.setAddress(row.getCell(3).getStringCellValue());
				Integer age = (int) row.getCell(4).getNumericCellValue();
				insertPatientReq.setAge(age);
				Long phoneNo = ((Double) row.getCell(6).getNumericCellValue()).longValue();
				insertPatientReq.setPhoneNo(phoneNo);
				insertPatientReq.setGender(row.getCell(5).getStringCellValue());
				list.add(insertPatientReq);
			}
			System.out.println("Hello :: " + list);

			if (CollectionUtils.isNotEmpty(list)) {
				insertedFlag = employeeBeanDao.insertEmployeeDetails(list);
			}
			workbook.close();
			return insertedFlag;
		}
		throw new InvalidExcelFormate("Please Give Proper Formate for excel...!!!!");

	}

	@Override
	public ByteArrayInputStream employeeDetailsExportToExcelFile() throws IOException {
		List<Map<String, Object>> list = employeeBeanDao.employeeDetailsExportToExcelFile();
		if (CollectionUtils.isNotEmpty(list)) {
			return ExportEmployeeDetailsToExcel.exportEmployeeDetailsToExcel(list);
		}
		throw new GivenListIsEmpty("Empty List...!!!");

	}

}
