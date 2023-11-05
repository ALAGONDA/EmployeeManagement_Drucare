package com.dru.care.app.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dru.care.app.bean.DeleteEmployeeReq;
import com.dru.care.app.bean.DeleteEmployeeReqs;
import com.dru.care.app.bean.FetchDepartmentDto;
import com.dru.care.app.bean.FetchEmployeeDto;
import com.dru.care.app.bean.FetchEmployeeReq;
import com.dru.care.app.bean.InsertEmployeeReqs;
import com.dru.care.app.bean.InsertEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReqs;
import com.dru.care.app.service.EmployeeBeanService;
import com.dru.care.app.service.EmployeeBeanServiceImpl;
import com.itextpdf.text.DocumentException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses({ @ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 401, message = "UNAUTHORIZED"),
		@ApiResponse(code = 403, message = "FORBIDDEN"), @ApiResponse(code = 404, message = "RESOURCE_NOT_FOUND"),
		@ApiResponse(code = 405, message = "METHOD_NOT_ALLOWED"),
		@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR") })
@RestController
public class EmployeeBeanController {

	/*
	 * Autowired annotation used for automatic dependency injection.
	 */
	@Autowired
	private EmployeeBeanService employeeBeanService;

	/*
	 * Using Single EndPoint for insert and update employee details with KeyHolder
	 * using NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Insert and Update Employee With Department Details ", response = Boolean.class)
	@PostMapping(value = "/insertAndUpdateEmployeeWithDepartment")
	public Boolean insertAndUpdateEmployeeWithDepartment(@RequestBody InsertEmployeeReqs insertEmployeeReqs) {

		return employeeBeanService.insertAndUpdateEmployeeWithDepartment(insertEmployeeReqs);
	}

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate with
	 * Batch Update
	 */
	@ApiOperation(value = "Insert Employee details Using KeyHolder With Batch", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeUsingKeyHolderWithBatch")
	public Boolean insertEmployeeUsingKeyHolderWithBatch(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanService.insertEmployeeUsingKeyHolderWithBatch(insertEmployeeReq);
	}

	/*
	 * Insert Employee Details With KeyHolder Using JdbcTemplate with Batch Update
	 */
	@ApiOperation(value = "Insert Employee details Using KeyHolder", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeUsingKeyHolder")
	public Boolean insertEmployeeUsingKeyHolder(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanService.insertEmployeeUsingKeyHolder(insertEmployeeReq);
	}

	/*
	 * Insert Employee Details With KeyHolder Using JdbcTemplate
	 */
	@ApiOperation(value = "Insert Employee details Using KeyHolder With JdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeWithKeyHolderUsingJdbc")
	public Boolean insertEmployeeWithKeyHolderUsingJdbc(@RequestBody InsertEmployeeReq insertEmployeeReq) {
		return employeeBeanService.insertEmployeeWithKeyHolderUsingJdbc(insertEmployeeReq);
	}

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate
	 */
	@ApiOperation(value = "Insert Employee Using KeyHolder With NamedParameter JdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeWithKeyHolderUsingNamed")
	public Boolean insertEmployeeWithKeyHolderUsingNamed(@RequestBody InsertEmployeeReq insertEmployeeReq) {
		return employeeBeanService.insertEmployeeWithKeyHolderUsingNamed(insertEmployeeReq);
	}

	/*
	 * Using Single EndPoint for insert and update employee details using
	 * NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Insert And Update Employee Using NamedParameter JdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/insertAndUpdateEmployee")
	public Boolean insertAndUpdateEmployee(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanService.insertAndUpdateEmployee(insertEmployeeReq);
	}

	/*
	 * insert employee details using JdbcTemplate with using orgId
	 */
	@ApiOperation(value = "Insert Employee Using JdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/insertEmployeUsingJdbcTemplate")
	public Boolean insertEmployeUsingJdbcTemplate(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanService.insertEmployeeUsingJdbcTemplate(insertEmployeeReq);
	}

	/*
	 * insert employee details using NamedParameterJdbcTemplate with using orgId
	 */
	@ApiOperation(value = "Insert Employee Using NamedParameter JdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeUsingNamed")
	public Boolean insertEmployeeUsingNamed(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanService.insertEmployeeUsingNamed(insertEmployeeReq);
	}

	/*
	 * update employee details using JdbcTemplate with using orgId
	 */
	@ApiOperation(value = "Update Employee Using JdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/updateEmployeeUsingJdbcTemplate")
	public Boolean updateEmployeeUsingJdbcTemplate(@RequestBody UpdateEmployeeReqs updateEmployeeReqs) {
		return employeeBeanService.updateEmployeeUsingJdbcTemplate(updateEmployeeReqs);
	}

	/*
	 * insert employee with BatchUpdate using JdbcTemplate
	 */
	@ApiOperation(value = "Insert Employee Using JdbcTemplate With BatchUpdate", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeBeanUsingBatch")
	public Boolean insertEmployeeBeanUsingBatch(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanService.insertEmployeeBeanUsingBatch(insertEmployeeReq);
	}

	/*
	 * update employee with BatchUpdate using JdbcTemplate
	 */
	@ApiOperation(value = "Update Employee details Endpoint", response = Boolean.class)
	@PostMapping(value = "/updateEmployeeBeanUsingBatch")
	public Boolean updateEmployeeBeanUsingBatch(@RequestBody UpdateEmployeeReqs updateEmployeeReqs) {
		return employeeBeanService.updateEmployeeBeanUsingBatch(updateEmployeeReqs);
	}

	/*
	 * soft delete employee with BatchUpdate using JdbcTemplate
	 */
	@PostMapping(value = "/softDeleteEmployeeBeanUsingBatch")
	@ApiOperation(value = "Soft Delete Employee details Endpoint", response = Boolean.class)
	public Boolean softDeleteEmployeeBeanUsingBatch(@RequestBody DeleteEmployeeReqs deleteEmployeeReqs) {
		return employeeBeanService.softDeleteEmployeeBeanUsingBatch(deleteEmployeeReqs);
	}

	/*
	 * insert employee using BatchUpdate with CreateBatch method
	 */
	@ApiOperation(value = "Insert Employee details Using BatchUpdate with CreateBatch", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeBeanUsingBatchWithCreateBatch")
	public Boolean insertEmployeeBeanUsingBatchWithCreateBatch(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		return employeeBeanService.insertEmployeeBeanUsingBatchWithCreateBatch(insertEmployeeReq);
	}

	/*
	 * insert employee using BatchUpdate with MapSqlParameterSource
	 */
	@ApiOperation(value = "Insert  Employee details Using BatchUpdate with MapSqlParameterSource", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeBeanUsingBatchWithMapSqlParameter")
	public Boolean insertEmployeeBeanUsingBatchWithMapSqlParameter(@RequestBody InsertEmployeeReqs listEmployeeReqs) {
		return employeeBeanService.insertEmployeeBeanUsingBatchWithMapSqlParameter(listEmployeeReqs);
	}

	/*
	 * insert employee using BatchUpdate with Map
	 */
	@ApiOperation(value = "Insert Employee details Using BatchUpdate with Map ", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeBeanUsingBatchWithMap")
	public Boolean insertEmployeeBeanUsingBatchWithMap(@RequestBody InsertEmployeeReqs listEmployeeReqs) {
		return employeeBeanService.insertEmployeeBeanUsingBatchWithMap(listEmployeeReqs);
	}

	/*
	 * update employee using BatchUpdate with CreateBatch method
	 */
	@ApiOperation(value = "Update Employee details Using BatchUpdate with CreateBatch ", response = Boolean.class)
	@PostMapping(value = "/updateEmployeeBeanUsingBatchWithCreateBatch")
	public Boolean updateEmployeeBeanUsingBatchWithCreateBatch(@RequestBody UpdateEmployeeReqs updateEmployeeReqs) {
		return employeeBeanService.updateEmployeeBeanUsingBatchWithCreateBatch(updateEmployeeReqs);
	}

	/*
	 * update employee using BatchUpdate with MapSqlParameterSource
	 */
	@ApiOperation(value = "Update Employee details Using BatchUpdate with MapSqlParameterSource ", response = Boolean.class)
	@PostMapping(value = "/updateEmployeeBeanUsingBatchWithMapSqlParameter")
	public Boolean updateEmployeeBeanUsingBatchWithMapSqlParameter(@RequestBody UpdateEmployeeReqs updateEmployeeReqs) {
		return employeeBeanService.updateEmployeeBeanUsingBatchWithMapSqlParameter(updateEmployeeReqs);
	}

	/*
	 * update employee using BatchUpdate with Map
	 */
	@ApiOperation(value = "Update Employee details Using BatchUpdate with Map ", response = Boolean.class)
	@PostMapping(value = "/updateEmployeeBeanUsingBatchWithMap")
	public Boolean updateEmployeeBeanUsingBatchWithMap(@RequestBody UpdateEmployeeReqs updateEmployeeReqs) {
		return employeeBeanService.updateEmployeeBeanUsingBatchWithMap(updateEmployeeReqs);
	}

	/*
	 * soft delete employee using BatchUpdate with CreateBatch method
	 */
	@ApiOperation(value = "Soft Delete Employee details Endpoint", response = Boolean.class)
	@PostMapping(value = "/softDeleteEmployeeBeanUsingBatchWithCreateBatch")
	public Boolean softDeleteEmployeeBeanUsingBatchWithCreateBatch(@RequestBody DeleteEmployeeReqs deleteEmployeeReqs) {
		return employeeBeanService.softDeleteEmployeeBeanUsingBatchWithCreateBatch(deleteEmployeeReqs);
	}

	/*
	 * soft delete employee using BatchUpdate with MapSqlParameterSource
	 */
	@ApiOperation(value = "Soft Delete Employee details Endpoint", response = Boolean.class)
	@PostMapping(value = "/softDeleteEmployeeBeanUsingBatchWithMapSqlParameter")
	public Boolean softDeleteEmployeeBeanUsingBatchWithMapSqlParameter(
			@RequestBody DeleteEmployeeReqs deleteEmployeeReqs) {
		return employeeBeanService.softDeleteEmployeeBeanUsingBatchWithMapSqlParameter(deleteEmployeeReqs);
	}

	/*
	 * soft delete employee using BatchUpdate with Map
	 */
	@ApiOperation(value = "Soft Delete Employee details Endpoint", response = Boolean.class)
	@PostMapping(value = "/softDeleteEmployeeBeanUsingBatchWithMap")
	public Boolean softDeleteEmployeeBeanUsingBatchWithMap(@RequestBody DeleteEmployeeReqs deleteEmployeeReqs) {
		return employeeBeanService.softDeleteEmployeeBeanUsingBatchWithMap(deleteEmployeeReqs);
	}

	/*
	 * insert employee using Map with NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Insert Employee details Using Map with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeBeanUsingMap")
	public Boolean insertEmployeeBeanUsingMap(@RequestBody InsertEmployeeReq insertEmployeeReq) {
		return employeeBeanService.insertEmployeeBeanUsingMap(insertEmployeeReq);
	}

	/*
	 * insert employee using MapSqlParameterSource with NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Insert Employee details Using MapSqlParameterSource with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeBeanUsingMapSqlParameter")
	public Boolean insertEmployeeBeanUsingMapSqlParameter(@RequestBody InsertEmployeeReq insertEmployeeReq) {
		return employeeBeanService.insertEmployeeBeanUsingMapSqlParameter(insertEmployeeReq);
	}

	/*
	 * insert employee using BeanPropertySqlParameterSource with
	 * NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Insert Employee details Using BeanPropertySqlParameterSource with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeBeanUsingBeanProperty")
	public Boolean insertEmployeeBeanUsingBeanProperty(@RequestBody InsertEmployeeReq insertEmployeeBean) {
		return employeeBeanService.insertEmployeeBeanUsingBeanProperty(insertEmployeeBean);
	}

	/*
	 * update employee using Map with NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Update Employee details Using Map with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/updateEmployeeBeanUsingMap")
	public Boolean updateEmployeeBeanUsingMap(@RequestBody UpdateEmployeeReq updateEmployeeReq) {
		return employeeBeanService.updateEmployeeBeanUsingMap(updateEmployeeReq);
	}

	/*
	 * update employee using MapSqlParameterSource with NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Update Employee details Using MapSqlParameterSource with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/updateEmployeeBeanUsingMapSqlParameter")
	public Boolean updateEmployeeBeanUsingMapSqlParameter(@RequestBody UpdateEmployeeReq updateEmployeeReq) {
		return employeeBeanService.updateEmployeeBeanUsingMapSqlParameter(updateEmployeeReq);
	}

	/*
	 * update employee using BeanPropertySqlParameterSource with
	 * NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Update Employee details Using BeanPropertySqlParameterSource with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/updateEmployeeBeanUsingBeanProperty")
	public Boolean updateEmployeeBeanUsingBeanProperty(@RequestBody UpdateEmployeeReq updateEmployeeReq) {
		return employeeBeanService.updateEmployeeBeanUsingBeanProperty(updateEmployeeReq);
	}

	/*
	 * soft delete employee using Map with NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Soft Delete Employee details Using Map with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/softDeleteEmployeeBeanUsingMap")
	public Boolean softDeleteEmployeeBeanUsingMap(@RequestBody DeleteEmployeeReq deleteEmployeeReq) {
		return employeeBeanService.softDeleteEmployeeBeanUsingMap(deleteEmployeeReq);
	}

	/*
	 * soft delete employee using MapSqlParameterSource with
	 * NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Soft Delete Employee details Using MapSqlParameterSource with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/softDeleteEmployeeBeanUsingMapSqlParameter")
	public Boolean softDeleteEmployeeBeanUsingMapSqlParameter(@RequestBody DeleteEmployeeReq deleteEmployeeReq) {
		return employeeBeanService.softDeleteEmployeeBeanUsingMapSqlParameter(deleteEmployeeReq);
	}

	/*
	 * soft delete employee using BeanPropertySqlParameter with
	 * NamedParameterJdbcTemplate
	 */
	@ApiOperation(value = "Soft Delete Employee details Using BeanPropertySqlParameter with NamedParameterJdbcTemplate", response = Boolean.class)
	@PostMapping(value = "/softDeleteEmployeeBeanUsingBeanParameter")
	public Boolean softDeleteEmployeeBeanUsingBeanParameter(@RequestBody DeleteEmployeeReq deleteEmployeeReq) {
		return employeeBeanService.softDeleteEmployeeBeanUsingBeanParameter(deleteEmployeeReq);
	}

	/*
	 * fetching employee details Using QueryForObject With NamedParameter
	 * JdbcTemplate
	 */
	@ApiOperation(value = "Fetching Employee Details Using QueryForObject With NamedParameter JdbcTemplate ", response = FetchEmployeeDto.class)
	@PostMapping(value = "/fecthEmployeeDetailsUsingQueryForObjectWithNamed")
	public FetchEmployeeDto fecthEmployeeDetailsUsingQueryForObjectWithNamed(
			@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return employeeBeanService.fecthEmployeeDetailsUsingQueryForObjectWithNamed(fetchEmployeeReq);
	}

	/*
	 * fetching employee details Using QueryForMap With NamedParameter JdbcTemplate
	 */
	@ApiOperation(value = "Fetching Employee Details QueryForMap With NamedParameter JdbcTemplate", response = FetchEmployeeDto.class)
	@PostMapping(value = "/fecthEmployeeDetailsUsingQueryForMapWithNamed")
	public FetchEmployeeDto fecthEmployeeDetailsUsingQueryForMapWithNamed(
			@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return employeeBeanService.fecthEmployeeDetailsUsingQueryForMapWithNamed(fetchEmployeeReq);
	}

	/*
	 * fetching employee details Using QueryForList With NamedParameter JdbcTemplate
	 */
	@ApiOperation(value = "Fetching Employee Details Using QueryForList With NamedParameter JdbcTemplate ", response = FetchEmployeeDto.class, responseContainer = "List")
	@PostMapping(value = "/fecthEmployeeDetailsUsingQueryForListWithNamed")
	public List<FetchEmployeeDto> fecthEmployeeDetailsUsingQueryForListWithNamed(
			@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return employeeBeanService.fecthEmployeeDetailsUsingQueryForListWithNamed(fetchEmployeeReq);
	}

	/*
	 * fetching employee details Using Query With NamedParameter JdbcTemplate
	 */
	@ApiOperation(value = "Fetching Employee Details Using Query With NamedParameter JdbcTemplate", response = FetchEmployeeDto.class, responseContainer = "List")
	@PostMapping(value = "/fecthEmployeeDetailsUsingQuery")
	public List<FetchEmployeeDto> fecthEmployeeDetailsUsingQuery(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return employeeBeanService.fecthEmployeeDetailsUsingQuery(fetchEmployeeReq);
	}

	/*
	 * fetching employee details Using DynamicQuery With NamedParameter JdbcTemplate
	 */
	@ApiOperation(value = "Fetching Employee Details Using Dynamic Query", response = FetchEmployeeDto.class, responseContainer = "List")
	@PostMapping(value = "/fecthEmployeeDetailsUsingDynamicQuery")
	public List<FetchEmployeeDto> fecthEmployeeDetailsUsingDynamicQuery(
			@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return employeeBeanService.fecthEmployeeDetailsUsingDynamicQuery(fetchEmployeeReq);
	}

	/*
	 * fetching particular employee department details Using NamedParameter
	 * JdbcTemplate
	 */
	@ApiOperation(value = "Fetching Employee With Department Details Using Query", response = FetchEmployeeDto.class, responseContainer = "List")
	@PostMapping(value = "/fecthEmployeeWithDepartmentDetailsUsingQuery")
	public List<FetchEmployeeDto> fecthEmployeeWithDepartmentDetailsUsingQuery(
			@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return employeeBeanService.fecthEmployeeWithDepartmentDetailsUsingQuery(fetchEmployeeReq);
	}

	@ApiOperation(value = "Fetching Department Wise Gender Count Details Using Query", response = FetchDepartmentDto.class, responseContainer = "List")
	@PostMapping(value = "/fetchDepartmentWiseGenderCount")
	public List<FetchDepartmentDto> fetchDepartmentWiseGenderCount(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return employeeBeanService.fetchDepartmentWiseGenderCount(fetchEmployeeReq);
	}

	@ApiOperation(value = "Generate Employee Pdf Details", response = ResponseEntity.class, responseContainer = "ByteArray")
    @PostMapping(value = "/generateEmployeePdfReport")
	public ResponseEntity<byte[]> generateEmployeePdfReport() throws DocumentException, IOException {
		byte[] stream = employeeBeanService.generateEmployeePdfReport();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=EmployeeReport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(stream);
	}

	@ApiOperation(value = "Insert Employee Details Through Excel File To DataBase", response = Boolean.class)
	@PostMapping(value = "/insertEmployeeDetailsThroughExcelFile")
	public Boolean insertEmployeeDetailsThroughExcelFile(@RequestParam("file") MultipartFile files,
			@RequestParam("fileFormate") String fileFormate) throws IOException {
		return employeeBeanService.insertEmployeeDetailsThroughExcelFile(files, fileFormate);
	}
    
	@ApiOperation(value = "Employee Details Export To Excel File", response = ResponseEntity.class,responseContainer = "Resource")
    @PostMapping(value = "/employeeDetailsExportToExcelFile")
	public ResponseEntity<Resource> employeeDetailsExportToExcelFile() throws IOException {
		ByteArrayInputStream stream = employeeBeanService.employeeDetailsExportToExcelFile();
		InputStreamResource resource=new InputStreamResource(stream);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=PatientReport.xlsx");
		return ResponseEntity.ok().headers(headers)
				.contentType(
						MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(resource);

	}

}
