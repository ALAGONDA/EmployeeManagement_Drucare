package com.dru.care.app.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dru.care.app.bean.DeleteEmployeeBean;
import com.dru.care.app.bean.DeleteEmployeeReq;
import com.dru.care.app.bean.DeleteEmployeeReqs;
import com.dru.care.app.bean.EmployeeDeptBean;
import com.dru.care.app.bean.FetchEmployeeReq;
import com.dru.care.app.bean.InsertEmployeeBean;
import com.dru.care.app.bean.InsertEmployeeReqs;
import com.dru.care.app.bean.InsertPatientBean;
import com.dru.care.app.bean.UpdateEmployeeBean;
import com.dru.care.app.bean.InsertEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReq;
import com.dru.care.app.bean.UpdateEmployeeReqs;
import com.dru.care.app.controller.EmployeeBeanController;
import com.dru.care.app.exception.EmployeeIdNotExist;
import com.dru.care.app.exception.GivenListIsEmpty;

@Repository
public class EmployeeBeanDaoImpl implements EmployeeBeanDao {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(EmployeeBeanController.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/*
	 * Below Three Queries for NamedParameter JdbcTempalte
	 */
	@Value("${queries.queryForInsertEmployeeDetails}")
	private String queryForInsertEmployeeDetails;

	@Value("${queries.queryForUpdateEmployeeDetails}")
	private String queryForUpdateEmployeeDetails;

	@Value("${queries.queryForSoftDeleteEmployeeDetails}")
	private String queryForSoftDeleteEmployeeDetails;

	@Value("${queries.queryForFetchEmployeeDetails}")
	private String queryForFetchEmployeeDetails;

	@Value("${queries.queryForFetchEmployeeDetailsUsingList}")
	private String queryForFetchEmployeeDetailsUsingList;

	@Value("${queries.queryForFetchEmployeeWithDepartmentDetails}")
	private String queryForFetchEmployeeWithDepartmentDetails;

	@Value("${queries.queryForFetchGenderWithDepartmentWiseCountDetails}")
	private String queryForFetchGenderWithDepartmentWiseCountDetails;

	@Value("${queries.queryForInsertPatientDetails}")
	private String queryForInsertPatientDetails;
	
	@Value("${queries.queryForFetchPatientDetails}")
	private String queryForFetchPatientDetails ;

	/*
	 * Below Five Queries for JdbcTempalte
	 */
	@Value("${queries.queryForInsertEmployeeInfo}")
	private String queryForInsertEmployeeInfo;

	@Value("${queries.queryForInsertEmpDepXref}")
	private String queryForInsertEmpDepXref;

	@Value("${queries.queryForUpdateEmpDepXref}")
	private String queryForUpdateEmpDepXref;

	@Value("${queries.queryForUpdateEmployeeInfo}")
	private String queryForUpdateEmployeeInfo;

	@Value("${queries.queryForSoftDeleteEmployeeInfo}")
	private String queryForSoftDeleteEmployeeInfo;

	/*
	 * insert employee using Map with NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean insertEmployeeBeanUsingMap(InsertEmployeeReq insertEmployeeReq) {

		int update = 0;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("orgId", insertEmployeeReq.getOrgId());
			map.put("firstNm", insertEmployeeReq.getFirstNm());
			map.put("lastNm", insertEmployeeReq.getLastNm());
			map.put("address", insertEmployeeReq.getAddress());
			map.put("phoneNo", insertEmployeeReq.getPhoneNo());
			map.put("emailId", insertEmployeeReq.getEmailId());
			map.put("empSalary", insertEmployeeReq.getEmpSalary());
			map.put("companyNm", insertEmployeeReq.getCompanyNm());
			map.put("empDesignation", insertEmployeeReq.getEmpDesignation());
			map.put("middleNm", insertEmployeeReq.getMiddleNm());
			map.put("pincode", insertEmployeeReq.getPincode());
			map.put("gender", insertEmployeeReq.getGender());
			map.put("branch", insertEmployeeReq.getBranch());
			update = namedParameterJdbcTemplate.update(queryForInsertEmployeeDetails, map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;

	}

	/*
	 * insert employee using MapSqlParameterSource with NamedParameterJdbcTemplate
	 */

	@Override
	public Boolean insertEmployeeBeanUsingMapSqlParameter(InsertEmployeeReq insertEmployeeReq) {
		int update = 0;
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("orgId", insertEmployeeReq.getOrgId());
			parameters.addValue("firstNm", insertEmployeeReq.getFirstNm());
			parameters.addValue("lastNm", insertEmployeeReq.getLastNm());
			parameters.addValue("address", insertEmployeeReq.getAddress());
			parameters.addValue("phoneNo", insertEmployeeReq.getPhoneNo());
			parameters.addValue("emailId", insertEmployeeReq.getEmailId());
			parameters.addValue("empSalary", insertEmployeeReq.getEmpSalary());
			parameters.addValue("companyNm", insertEmployeeReq.getCompanyNm());
			parameters.addValue("empDesignation", insertEmployeeReq.getEmpDesignation());
			parameters.addValue("middleNm", insertEmployeeReq.getMiddleNm());
			parameters.addValue("pincode", insertEmployeeReq.getPincode());
			parameters.addValue("gender", insertEmployeeReq.getGender());
			parameters.addValue("branch", insertEmployeeReq.getBranch());
			update = namedParameterJdbcTemplate.update(queryForInsertEmployeeDetails, parameters);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;
	}

	/*
	 * insert employee using BeanPropertySqlParameterSource with
	 * NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean insertEmployeeBeanUsingBeanProperty(InsertEmployeeReq insertEmployeeReq) {
		int update = 0;
		try {
			update = namedParameterJdbcTemplate.update(queryForInsertEmployeeDetails,
					new BeanPropertySqlParameterSource(insertEmployeeReq));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return update > 0;
	}

	/*
	 * update employee using Map with NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean updateEmployeeBeanUsingMap(UpdateEmployeeReq updateEmployeeReq) {
		int update = 0;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("firstNm", updateEmployeeReq.getFirstNm());
			map.put("lastNm", updateEmployeeReq.getLastNm());
			map.put("address", updateEmployeeReq.getAddress());
			map.put("phoneNo", updateEmployeeReq.getPhoneNo());
			map.put("emailId", updateEmployeeReq.getEmailId());
			map.put("empSalary", updateEmployeeReq.getEmpSalary());
			map.put("companyNm", updateEmployeeReq.getCompanyNm());
			map.put("empDesignation", updateEmployeeReq.getEmpDesignation());
			map.put("middleNm", updateEmployeeReq.getMiddleNm());
			map.put("pincode", updateEmployeeReq.getPincode());
			map.put("gender", updateEmployeeReq.getGender());
			map.put("branch", updateEmployeeReq.getBranch());
			map.put("orgId", updateEmployeeReq.getOrgId());
			map.put("empId", updateEmployeeReq.getEmpId());
			update = namedParameterJdbcTemplate.update(queryForUpdateEmployeeDetails, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return update > 0;
	}

	/*
	 * update employee using MapSqlParameterSource with NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean updateEmployeeBeanUsingMapSqlParameter(UpdateEmployeeReq updateEmployeeReq) {
		int update = 0;
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("firstNm", updateEmployeeReq.getFirstNm());
			parameters.addValue("lastNm", updateEmployeeReq.getLastNm());
			parameters.addValue("address", updateEmployeeReq.getAddress());
			parameters.addValue("phoneNo", updateEmployeeReq.getPhoneNo());
			parameters.addValue("emailId", updateEmployeeReq.getEmailId());
			parameters.addValue("empSalary", updateEmployeeReq.getEmpSalary());
			parameters.addValue("companyNm", updateEmployeeReq.getCompanyNm());
			parameters.addValue("empDesignation", updateEmployeeReq.getEmpDesignation());
			parameters.addValue("middleNm", updateEmployeeReq.getMiddleNm());
			parameters.addValue("pincode", updateEmployeeReq.getPincode());
			parameters.addValue("gender", updateEmployeeReq.getGender());
			parameters.addValue("branch", updateEmployeeReq.getBranch());
			parameters.addValue("orgId", updateEmployeeReq.getOrgId());
			parameters.addValue("empId", updateEmployeeReq.getEmpId());
			update = namedParameterJdbcTemplate.update(queryForUpdateEmployeeDetails, parameters);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return update > 0;
	}

	/*
	 * update employee using BeanPropertySqlParameterSource with
	 * NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean updateEmployeeBeanUsingBeanProperty(UpdateEmployeeReq updateEmployeeReq) {
		int update = 0;
		try {
			update = namedParameterJdbcTemplate.update(queryForUpdateEmployeeDetails,
					new BeanPropertySqlParameterSource(updateEmployeeReq));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;

	}

	/*
	 * soft delete employee using Map with NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean softDeleteEmployeeBeanUsingMap(DeleteEmployeeReq deleteEmployeeReq) {
		int update = 0;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("empId", deleteEmployeeReq.getEmpId());
			update = namedParameterJdbcTemplate.update(queryForSoftDeleteEmployeeDetails, map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;
	}

	/*
	 * soft delete employee using MapSqlParameter with NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean softDeleteEmployeeBeanUsingMapSqlParameter(DeleteEmployeeReq deleteEmployeeReq) {
		int update = 0;
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("empId", deleteEmployeeReq.getEmpId());
			update = namedParameterJdbcTemplate.update(queryForSoftDeleteEmployeeDetails, parameters);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;
	}

	/*
	 * soft delete employee using BeanPropertySqlParameterSource with
	 * NamedParameterJdbcTemplate
	 */
	@Override
	public Boolean softDeleteEmployeeBeanUsingBeanParameter(DeleteEmployeeReq deleteEmployeeReq) {
		int update = 0;
		try {
			update = namedParameterJdbcTemplate.update(queryForSoftDeleteEmployeeDetails,
					new BeanPropertySqlParameterSource(deleteEmployeeReq));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;
	}

	/*
	 * insert employee using BatchUpdate with CreateBatch method
	 */
	@Override
	public Boolean insertEmployeeBeanUsingBatchWithCreateBatch(List<InsertEmployeeBean> listInsertUpdateEmployeeBeans) {
		int[] batchUpdate = null;
		try {
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployeeDetails,
					SqlParameterSourceUtils.createBatch(listInsertUpdateEmployeeBeans));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listInsertUpdateEmployeeBeans.size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * update employee using BatchUpdate with CreateBatch method
	 */
	@Override
	public Boolean updateEmployeeBeanUsingBatchWithCreateBatch(List<UpdateEmployeeBean> listUpdateEmployeeBeans) {
		int[] batchUpdate = null;
		try {

			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForUpdateEmployeeDetails,
					SqlParameterSourceUtils.createBatch(listUpdateEmployeeBeans));
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * If batchUpdate is not null, return the length of the array
		 * (batchUpdate.length). If batchUpdate is null, return 0.
		 */
		return listUpdateEmployeeBeans.size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * soft delete employee using BatchUpdate with CreateBatch method
	 */
	@Override
	public Boolean softDeleteEmployeeBeanUsingBatchWithCreateBatch(DeleteEmployeeReqs deleteEmployeeReqs) {
		int[] batchUpdate = null;
		List<DeleteEmployeeBean> deleteEmployeeBeans = new ArrayList<>();
		try {
			for (DeleteEmployeeBean deleteEmployeeBean : deleteEmployeeReqs.getDeleteEmployeeBeans()) {
				deleteEmployeeBean.setOrgId(deleteEmployeeReqs.getOrgId());
				deleteEmployeeBeans.add(deleteEmployeeBean);
			}
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForSoftDeleteEmployeeDetails,
					SqlParameterSourceUtils.createBatch(deleteEmployeeBeans));
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * If batchUpdate is not null, return the length of the array
		 * (batchUpdate.length). If batchUpdate is null, return 0.
		 */
		return deleteEmployeeReqs.getDeleteEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * insert employee using BatchUpdate with MapSqlParameterSource
	 */
	@Override
	public Boolean insertEmployeeBeanUsingBatchWithMapSqlParameter(List<InsertEmployeeBean> listEmployeeReqs) {
		MapSqlParameterSource[] mapSqlParameterSource = new MapSqlParameterSource[listEmployeeReqs.size()];
		int i = 0;
		int[] batchUpdate = null;
		try {
			for (InsertEmployeeBean insertEmployeebean : listEmployeeReqs) {
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				parameters.addValue("firstNm", insertEmployeebean.getFirstNm());
				parameters.addValue("lastNm", insertEmployeebean.getLastNm());
				parameters.addValue("address", insertEmployeebean.getAddress());
				parameters.addValue("phoneNo", insertEmployeebean.getPhoneNo());
				parameters.addValue("emailId", insertEmployeebean.getEmailId());
				parameters.addValue("empSalary", insertEmployeebean.getEmpSalary());
				parameters.addValue("companyNm", insertEmployeebean.getCompanyNm());
				parameters.addValue("empDesignation", insertEmployeebean.getEmpDesignation());
				parameters.addValue("middleNm", insertEmployeebean.getMiddleNm());
				parameters.addValue("pincode", insertEmployeebean.getPincode());
				parameters.addValue("gender", insertEmployeebean.getGender());
				parameters.addValue("branch", insertEmployeebean.getBranch());
				mapSqlParameterSource[i] = parameters;
				i++;

			}
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployeeDetails, mapSqlParameterSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmployeeReqs.size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	/*
	 * update employee using BatchUpdate with MapSqlParameterSource
	 */
	@Override
	public Boolean updateEmployeeBeanUsingBatchWithMapSqlParameter(UpdateEmployeeReqs updateEmployeeReqs) {
		MapSqlParameterSource[] mapSqlParameterSource = new MapSqlParameterSource[updateEmployeeReqs
				.getUpdateEmployeeBeans().size()];
		int i = 0;
		int[] batchUpdate = null;
		try {
			for (UpdateEmployeeBean updateEmployeeReq : updateEmployeeReqs.getUpdateEmployeeBeans()) {
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				parameters.addValue("firstNm", updateEmployeeReq.getFirstNm());
				parameters.addValue("lastNm", updateEmployeeReq.getLastNm());
				parameters.addValue("address", updateEmployeeReq.getAddress());
				parameters.addValue("phoneNo", updateEmployeeReq.getPhoneNo());
				parameters.addValue("emailId", updateEmployeeReq.getEmailId());
				parameters.addValue("empSalary", updateEmployeeReq.getEmpSalary());
				parameters.addValue("companyNm", updateEmployeeReq.getCompanyNm());
				parameters.addValue("empDesignation", updateEmployeeReq.getEmpDesignation());
				parameters.addValue("middleNm", updateEmployeeReq.getMiddleNm());
				parameters.addValue("pincode", updateEmployeeReq.getPincode());
				parameters.addValue("gender", updateEmployeeReq.getGender());
				parameters.addValue("branch", updateEmployeeReq.getBranch());
				parameters.addValue("orgId", updateEmployeeReqs.getOrgId());
				parameters.addValue("empId", updateEmployeeReq.getEmpId());
				mapSqlParameterSource[i] = parameters;
				i++;

			}
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForUpdateEmployeeDetails, mapSqlParameterSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateEmployeeReqs.getUpdateEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * soft delete employee using BatchUpdate with MapSqlParameterSource
	 */
	@Override
	public Boolean softDeleteEmployeeBeanUsingBatchWithMapSqlParameter(DeleteEmployeeReqs deleteEmployeeReqs) {
		MapSqlParameterSource[] mapSqlParameterSource = new MapSqlParameterSource[deleteEmployeeReqs
				.getDeleteEmployeeBeans().size()];
		int i = 0;
		int[] batchUpdate = null;
		try {
			for (DeleteEmployeeBean deleteEmployeebean : deleteEmployeeReqs.getDeleteEmployeeBeans()) {
				deleteEmployeebean.setOrgId(deleteEmployeeReqs.getOrgId());
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				parameters.addValue("orgId", deleteEmployeebean.getOrgId());
				parameters.addValue("empId", deleteEmployeebean.getEmpId());
				mapSqlParameterSource[i] = parameters;
				i++;

			}
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForSoftDeleteEmployeeDetails,
					mapSqlParameterSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteEmployeeReqs.getDeleteEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	/*
	 * insert employee using BatchUpdate with Map
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertEmployeeBeanUsingBatchWithMap(List<InsertEmployeeBean> listEmployeeReqs) {
		List<Map<String, Object>> listOfMap = new ArrayList<>();
		int[] batchUpdate = null;
		int i = 0;
		try {
			for (InsertEmployeeBean insertEmployeeBean : listEmployeeReqs) {
				Map<String, Object> map = new HashMap<>();
				map.put("firstNm", insertEmployeeBean.getFirstNm());
				map.put("lastNm", insertEmployeeBean.getLastNm());
				map.put("address", insertEmployeeBean.getAddress());
				map.put("phoneNo", insertEmployeeBean.getPhoneNo());
				map.put("emailId", insertEmployeeBean.getEmailId());
				map.put("empSalary", insertEmployeeBean.getEmpSalary());
				map.put("companyNm", insertEmployeeBean.getCompanyNm());
				map.put("empDesignation", insertEmployeeBean.getEmpDesignation());
				map.put("middleNm", insertEmployeeBean.getMiddleNm());
				map.put("pincode", insertEmployeeBean.getPincode());
				map.put("gender", insertEmployeeBean.getGender());
				map.put("branch", insertEmployeeBean.getBranch());
				listOfMap.add(map);
				i++;

			}
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployeeDetails,
					listOfMap.toArray(new Map[i]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmployeeReqs.size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * update employee using BatchUpdate with Map
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean updateEmployeeBeanUsingBatchWithMap(UpdateEmployeeReqs updateEmployeeReqs) {
		List<Map<String, Object>> listOfMap = new ArrayList<>();
		int[] batchUpdate = null;
		int i = 0;
		try {
			for (UpdateEmployeeBean updateEmployeeReq : updateEmployeeReqs.getUpdateEmployeeBeans()) {
				Map<String, Object> map = new HashMap<>();
				map.put("firstNm", updateEmployeeReq.getFirstNm());
				map.put("lastNm", updateEmployeeReq.getLastNm());
				map.put("address", updateEmployeeReq.getAddress());
				map.put("phoneNo", updateEmployeeReq.getPhoneNo());
				map.put("emailId", updateEmployeeReq.getEmailId());
				map.put("empSalary", updateEmployeeReq.getEmpSalary());
				map.put("companyNm", updateEmployeeReq.getCompanyNm());
				map.put("empDesignation", updateEmployeeReq.getEmpDesignation());
				map.put("middleNm", updateEmployeeReq.getMiddleNm());
				map.put("pincode", updateEmployeeReq.getPincode());
				map.put("gender", updateEmployeeReq.getGender());
				map.put("branch", updateEmployeeReq.getBranch());
				map.put("empId", updateEmployeeReq.getEmpId());
				map.put("orgId", updateEmployeeReq.getOrgId());
				listOfMap.add(map);
				i++;

			}
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForUpdateEmployeeDetails,
					listOfMap.toArray(new Map[i]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateEmployeeReqs.getUpdateEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * soft delete employee using BatchUpdate with Map
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean softDeleteEmployeeBeanUsingBatchWithMap(DeleteEmployeeReqs deleteEmployeeReqs) {
		List<Map<String, Object>> listOfMap = new ArrayList<>();
		int[] batchUpdate = null;
		int i = 0;
		try {
			for (DeleteEmployeeBean deleteEmployeebean : deleteEmployeeReqs.getDeleteEmployeeBeans()) {
				Map<String, Object> map = new HashMap<>();
				map.put("orgId", deleteEmployeebean.getOrgId());
				map.put("empId", deleteEmployeebean.getEmpId());
				listOfMap.add(map);
				i++;

			}
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForSoftDeleteEmployeeDetails,
					listOfMap.toArray(new Map[i]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteEmployeeReqs.getDeleteEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * insert employee with BatchUpdate using JdbcTemplate
	 */
	@Override
	public Boolean insertEmployeeBeanUsingBatch(InsertEmployeeReqs insertEmployeeReq) {
		int[] batchUpdate = null;
		try {
			batchUpdate = jdbcTemplate.batchUpdate(queryForInsertEmployeeInfo, new BatchPreparedStatementSetter() {

				@SuppressWarnings("unused")
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					InsertEmployeeBean insertEmployeeBean = insertEmployeeReq.getInsertEmployeeBeans().get(i);
					ps.setInt(1, insertEmployeeReq.getOrgId());
					ps.setString(2, insertEmployeeBean.getFirstNm());
					if (insertEmployeeBean != null) {
						ps.setString(3, insertEmployeeBean.getMiddleNm());
					} else {
						ps.setNull(3, java.sql.Types.VARCHAR);
					}
					ps.setString(4, insertEmployeeBean.getLastNm());
					ps.setString(5, insertEmployeeBean.getAddress());
					ps.setInt(6, insertEmployeeBean.getPincode());
					ps.setString(7, insertEmployeeBean.getGender());
					if (insertEmployeeBean != null) {
						ps.setString(8, insertEmployeeBean.getBranch());
					} else {
						ps.setNull(8, java.sql.Types.VARCHAR);
					}
					ps.setLong(9, insertEmployeeBean.getPhoneNo());
					if (insertEmployeeBean != null) {
						ps.setString(10, insertEmployeeBean.getEmailId());
					} else {
						ps.setNull(10, java.sql.Types.VARCHAR);
					}
					ps.setDouble(11, insertEmployeeBean.getEmpSalary());
					ps.setString(12, insertEmployeeBean.getCompanyNm());
					ps.setString(13, insertEmployeeBean.getEmpDesignation());

				}

				@Override
				public int getBatchSize() {

					return insertEmployeeReq.getInsertEmployeeBeans().size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertEmployeeReq.getInsertEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	/*
	 * update employee with BatchUpdate using JdbcTemplate
	 */
	@Override
	public Boolean updateEmployeeBeanUsingBatch(UpdateEmployeeReqs updateEmployeeReqs) {
		int[] batchUpdate = null;
		try {
			batchUpdate = jdbcTemplate.batchUpdate(queryForUpdateEmployeeDetails, new BatchPreparedStatementSetter() {

				@SuppressWarnings("unused")
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					UpdateEmployeeBean updateEmployeebean = updateEmployeeReqs.getUpdateEmployeeBeans().get(i);
					ps.setString(1, updateEmployeebean.getFirstNm());
					if (updateEmployeebean != null) {
						ps.setString(2, updateEmployeebean.getMiddleNm());
					} else {
						ps.setNull(2, java.sql.Types.VARCHAR);
					}
					ps.setString(3, updateEmployeebean.getLastNm());
					ps.setString(4, updateEmployeebean.getAddress());
					ps.setInt(5, updateEmployeebean.getPincode());
					ps.setString(6, updateEmployeebean.getGender());
					if (updateEmployeebean != null) {
						ps.setString(7, updateEmployeebean.getBranch());
					} else {
						ps.setNull(7, java.sql.Types.VARCHAR);
					}

					ps.setLong(8, updateEmployeebean.getPhoneNo());
					if (updateEmployeebean != null) {
						ps.setString(9, updateEmployeebean.getEmailId());
					} else {
						ps.setNull(9, java.sql.Types.VARCHAR);
					}

					ps.setDouble(10, updateEmployeebean.getEmpSalary());
					ps.setString(11, updateEmployeebean.getCompanyNm());
					ps.setString(12, updateEmployeebean.getEmpDesignation());
					ps.setLong(13, updateEmployeebean.getOrgId());
					ps.setLong(14, updateEmployeebean.getEmpId());
				}

				@Override
				public int getBatchSize() {

					return updateEmployeeReqs.getUpdateEmployeeBeans().size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateEmployeeReqs.getUpdateEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * soft delete employee with BatchUpdate using JdbcTemplate
	 */
	@Override
	public Boolean softDeleteEmployeeBeanUsingBatch(DeleteEmployeeReqs deleteEmployeeReqs) {
		int[] batchUpdate = null;
		try {
			batchUpdate = jdbcTemplate.batchUpdate(queryForSoftDeleteEmployeeDetails,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							DeleteEmployeeBean deleteEmployeebean = deleteEmployeeReqs.getDeleteEmployeeBeans().get(i);
							ps.setInt(1, deleteEmployeebean.getOrgId());
							ps.setLong(2, deleteEmployeebean.getEmpId());

						}

						@Override
						public int getBatchSize() {

							return deleteEmployeeReqs.getDeleteEmployeeBeans().size();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteEmployeeReqs.getDeleteEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	/*
	 * Using Single EndPoint for insert employee details using NamedParameter
	 * JdbcTemplate
	 */
	@Override
	public Boolean insertEmployee(List<InsertEmployeeBean> saveList) {

		int[] batchUpdate = null;
		try {
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployeeDetails,
					SqlParameterSourceUtils.createBatch(saveList.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveList.size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	/*
	 * Using Single EndPoint for update employee details using NamedParameter
	 * JdbcTemplate
	 */
	@Override
	public Boolean updateEmployee(List<InsertEmployeeBean> updateList) {
		int[] batchUpdate = null;
		try {
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForUpdateEmployeeDetails,
					SqlParameterSourceUtils.createBatch(updateList.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateList.size() == (batchUpdate != null ? batchUpdate.length : 0);

	}

	/*
	 * insert employee details using JdbcTemplate with using orgId
	 */
	@Override
	public Boolean insertEmployeeUsingJdbcTemplate(InsertEmployeeReqs insertEmployeeReq) {
		int[] batchUpdate = null;
		try {
			batchUpdate = jdbcTemplate.batchUpdate(queryForInsertEmployeeInfo, new BatchPreparedStatementSetter() {

				@SuppressWarnings("unused")
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					InsertEmployeeBean insertEmployeeBean = insertEmployeeReq.getInsertEmployeeBeans().get(i);

					ps.setInt(1, insertEmployeeReq.getOrgId());
					ps.setString(2, insertEmployeeBean.getFirstNm());
					if (insertEmployeeBean != null) {
						ps.setString(3, insertEmployeeBean.getMiddleNm());
					} else {
						ps.setNull(3, java.sql.Types.VARCHAR);
					}
					ps.setString(4, insertEmployeeBean.getLastNm());
					ps.setString(5, insertEmployeeBean.getAddress());
					ps.setInt(6, insertEmployeeBean.getPincode());
					ps.setString(7, insertEmployeeBean.getGender());
					if (StringUtils.isNotBlank(insertEmployeeBean.getBranch())) {
						ps.setString(8, insertEmployeeBean.getBranch());
					} else {
						ps.setNull(8, java.sql.Types.VARCHAR);
					}
					ps.setLong(9, insertEmployeeBean.getPhoneNo());
					if (insertEmployeeBean != null) {
						ps.setString(10, insertEmployeeBean.getEmailId());
					} else {
						ps.setNull(10, java.sql.Types.VARCHAR);
					}
					ps.setDouble(11, insertEmployeeBean.getEmpSalary());
					ps.setString(12, insertEmployeeBean.getCompanyNm());
					ps.setString(13, insertEmployeeBean.getEmpDesignation());

				}

				@Override
				public int getBatchSize() {
					return insertEmployeeReq.getInsertEmployeeBeans().size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return insertEmployeeReq.getInsertEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	/*
	 * insert employee details using NamedParameterJdbcTemplate with using orgId
	 */
	@Override
	public Boolean insertEmployeeUsingNamedParameter(List<InsertEmployeeBean> listInsertUpdateEmployeeBeans) {
		int[] batchUpdate = null;
		try {
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployeeDetails,
					SqlParameterSourceUtils.createBatch(listInsertUpdateEmployeeBeans.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listInsertUpdateEmployeeBeans.size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	/*
	 * update employee details using JdbcTemplate with using orgId
	 */
	@Override
	public Boolean updateEmployeeUsingJdbcTemplate(UpdateEmployeeReqs updateEmployeeReqs) {
		int[] batchUpdate = null;
		try {
			batchUpdate = jdbcTemplate.batchUpdate(queryForUpdateEmployeeInfo, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					UpdateEmployeeBean updateEmployeeBean = updateEmployeeReqs.getUpdateEmployeeBeans().get(i);

					ps.setString(1, updateEmployeeBean.getFirstNm());

					ps.setString(2, updateEmployeeBean.getMiddleNm());

					ps.setString(3, updateEmployeeBean.getLastNm());
					ps.setString(4, updateEmployeeBean.getAddress());
					ps.setInt(5, updateEmployeeBean.getPincode());
					ps.setString(6, updateEmployeeBean.getGender());

					ps.setString(7, updateEmployeeBean.getBranch());

					ps.setLong(8, updateEmployeeBean.getPhoneNo());

					ps.setString(9, updateEmployeeBean.getEmailId());

					ps.setDouble(10, updateEmployeeBean.getEmpSalary());
					ps.setString(11, updateEmployeeBean.getCompanyNm());
					ps.setString(12, updateEmployeeBean.getEmpDesignation());
					ps.setInt(13, updateEmployeeBean.getOrgId());
					ps.setLong(14, updateEmployeeBean.getEmpId());

				}

				@Override
				public int getBatchSize() {
					return updateEmployeeReqs.getUpdateEmployeeBeans().size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return updateEmployeeReqs.getUpdateEmployeeBeans().size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	/*
	 * Insert Employee Details With KeyHolder Using JdbcTemplate
	 */
	@SuppressWarnings("unused")
	@Override
	public Boolean insertEmployeeWithKeyHolderUsingJdbc(InsertEmployeeReq insertEmployeeReq) {
		int update = 0;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {

			update = jdbcTemplate.update(connection -> {
				PreparedStatement preparedStatement = connection.prepareStatement(queryForInsertEmployeeInfo,
						new String[] { "emp_id" });
				preparedStatement.setInt(1, insertEmployeeReq.getOrgId());
				preparedStatement.setString(2, insertEmployeeReq.getFirstNm());
				if (insertEmployeeReq != null) {
					preparedStatement.setString(3, insertEmployeeReq.getMiddleNm());
				} else {
					preparedStatement.setNull(3, java.sql.Types.VARCHAR);
				}
				preparedStatement.setString(4, insertEmployeeReq.getLastNm());
				preparedStatement.setString(5, insertEmployeeReq.getAddress());
				preparedStatement.setInt(6, insertEmployeeReq.getPincode());
				preparedStatement.setString(7, insertEmployeeReq.getGender());
				if (StringUtils.isNotBlank(insertEmployeeReq.getBranch())) {
					preparedStatement.setString(8, insertEmployeeReq.getBranch());
				} else {
					preparedStatement.setNull(8, java.sql.Types.VARCHAR);
				}
				preparedStatement.setLong(9, insertEmployeeReq.getPhoneNo());
				if (insertEmployeeReq != null) {
					preparedStatement.setString(10, insertEmployeeReq.getEmailId());
				} else {
					preparedStatement.setNull(10, java.sql.Types.VARCHAR);
				}
				preparedStatement.setDouble(11, insertEmployeeReq.getEmpSalary());
				preparedStatement.setString(12, insertEmployeeReq.getCompanyNm());
				preparedStatement.setString(13, insertEmployeeReq.getEmpDesignation());
				return preparedStatement;
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Result :: " + keyHolder.getKey().longValue());
		return update > 0;
	}

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate
	 */
	@Override
	public Boolean insertEmployeeWithKeyHolderUsingNamed(InsertEmployeeReq insertEmployeeReq) {
		int update = 0;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("orgId", insertEmployeeReq.getOrgId());
			parameters.addValue("firstNm", insertEmployeeReq.getFirstNm());
			parameters.addValue("lastNm", insertEmployeeReq.getLastNm());
			parameters.addValue("address", insertEmployeeReq.getAddress());
			parameters.addValue("phoneNo", insertEmployeeReq.getPhoneNo());
			parameters.addValue("emailId", insertEmployeeReq.getEmailId());
			parameters.addValue("empSalary", insertEmployeeReq.getEmpSalary());
			parameters.addValue("companyNm", insertEmployeeReq.getCompanyNm());
			parameters.addValue("empDesignation", insertEmployeeReq.getEmpDesignation());
			parameters.addValue("middleNm", insertEmployeeReq.getMiddleNm());
			parameters.addValue("pincode", insertEmployeeReq.getPincode());
			parameters.addValue("gender", insertEmployeeReq.getGender());
			parameters.addValue("branch", insertEmployeeReq.getBranch());
			update = namedParameterJdbcTemplate.update(queryForInsertEmployeeDetails, parameters, keyHolder,
					new String[] { "emp_id" });
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Result :: " + keyHolder.getKey().longValue());
		return update > 0;
	}

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate with
	 * Batch Update
	 */
	@Override
	public Boolean insertEmployeeUsingKeyHolderWithBatch(List<InsertEmployeeBean> listInsertEmployeeBeans) {
		int update = 0;
		List<Long> generatedKeysList = new ArrayList<>();
		try {
			for (InsertEmployeeBean insertEmployeeBean : listInsertEmployeeBeans) {
				KeyHolder keyHolder = new GeneratedKeyHolder();
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				parameters.addValue("orgId", insertEmployeeBean.getOrgId());
				parameters.addValue("firstNm", insertEmployeeBean.getFirstNm());
				parameters.addValue("lastNm", insertEmployeeBean.getLastNm());
				parameters.addValue("address", insertEmployeeBean.getAddress());
				parameters.addValue("phoneNo", insertEmployeeBean.getPhoneNo());
				parameters.addValue("emailId", insertEmployeeBean.getEmailId());
				parameters.addValue("empSalary", insertEmployeeBean.getEmpSalary());
				parameters.addValue("companyNm", insertEmployeeBean.getCompanyNm());
				parameters.addValue("empDesignation", insertEmployeeBean.getEmpDesignation());
				parameters.addValue("middleNm", insertEmployeeBean.getMiddleNm());
				parameters.addValue("pincode", insertEmployeeBean.getPincode());
				parameters.addValue("gender", insertEmployeeBean.getGender());
				parameters.addValue("branch", insertEmployeeBean.getBranch());
				update = update + namedParameterJdbcTemplate.update(queryForInsertEmployeeDetails, parameters,
						keyHolder, new String[] { "emp_id" });

				Number number = keyHolder.getKey();
				if (number != null) {
					generatedKeysList.add(number.longValue());
				}

			}
			System.out.println("Result :: " + generatedKeysList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update == listInsertEmployeeBeans.size();
	}

	/*
	 * Insert Employee Details With KeyHolder Using NamedParameter JdbcTemplate with
	 * Batch Update
	 */
	@SuppressWarnings("unused")
	@Override
	public Boolean insertEmployeeUsingKeyHolder(InsertEmployeeReqs insertEmployeeReq) {
		int update = 0;
		List<Long> generatedKeysList = new ArrayList<>();
		try {
			for (InsertEmployeeBean insertEmployeeBean : insertEmployeeReq.getInsertEmployeeBeans()) {
				KeyHolder keyHolder = new GeneratedKeyHolder();
				update = update + jdbcTemplate.update(connection -> {
					PreparedStatement preparedStatement = connection.prepareStatement(queryForInsertEmployeeInfo,
							new String[] { "emp_id" });
					preparedStatement.setInt(1, insertEmployeeReq.getOrgId());
					preparedStatement.setString(2, insertEmployeeBean.getFirstNm());
					if (insertEmployeeBean != null) {
						preparedStatement.setString(3, insertEmployeeBean.getMiddleNm());
					} else {
						preparedStatement.setNull(3, java.sql.Types.VARCHAR);
					}
					preparedStatement.setString(4, insertEmployeeBean.getLastNm());
					preparedStatement.setString(5, insertEmployeeBean.getAddress());
					preparedStatement.setInt(6, insertEmployeeBean.getPincode());
					preparedStatement.setString(7, insertEmployeeBean.getGender());
					if (StringUtils.isNotBlank(insertEmployeeBean.getBranch())) {
						preparedStatement.setString(8, insertEmployeeBean.getBranch());
					} else {
						preparedStatement.setNull(8, java.sql.Types.VARCHAR);
					}
					preparedStatement.setLong(9, insertEmployeeBean.getPhoneNo());
					if (insertEmployeeBean != null) {
						preparedStatement.setString(10, insertEmployeeBean.getEmailId());
					} else {
						preparedStatement.setNull(10, java.sql.Types.VARCHAR);
					}
					preparedStatement.setDouble(11, insertEmployeeBean.getEmpSalary());
					preparedStatement.setString(12, insertEmployeeBean.getCompanyNm());
					preparedStatement.setString(13, insertEmployeeBean.getEmpDesignation());
					return preparedStatement;
				}, keyHolder);
				Number number = keyHolder.getKey();
				if (number != null) {
					generatedKeysList.add(number.longValue());
				}

			}

			System.out.println("Result2 :: " + generatedKeysList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update == insertEmployeeReq.getInsertEmployeeBeans().size();
	}

	/*
	 * fetching employee details Using QueryForObject With NamedParameter
	 * JdbcTemplate
	 */
	@Override
	public FetchEmployeeReq fecthEmployeeDetailsUsingQueryForObjectWithNamed(FetchEmployeeReq fetchEmployeeReq) {

		try {
			return namedParameterJdbcTemplate.queryForObject(queryForFetchEmployeeDetails,
					new BeanPropertySqlParameterSource(fetchEmployeeReq),
					new BeanPropertyRowMapper<>(FetchEmployeeReq.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new EmployeeIdNotExist("Please Given Valid Employee Id...!!!!");
	}

	/*
	 * fetching employee details Using QueryForMap With NamedParameter JdbcTemplate
	 */
	@Override
	public Map<String, Object> fecthEmployeeDetailsUsingQueryForMapWithNamed(FetchEmployeeReq fetchEmployeeReq) {
		try {
			return namedParameterJdbcTemplate.queryForMap(queryForFetchEmployeeDetails,
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new EmployeeIdNotExist("Please Given Valid Employee Id...!!!!");

	}

	/*
	 * fetching employee details Using QueryForList With NamedParameter JdbcTemplate
	 */
	@Override
	public List<Map<String, Object>> fecthEmployeeDetailsUsingQueryForListWithNamed(FetchEmployeeReq fetchEmployeeReq) {

		try {

			return namedParameterJdbcTemplate.queryForList(queryForFetchEmployeeDetailsUsingList,
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new GivenListIsEmpty("Empty List....!!!!");

	}

	/*
	 * fetching employee details Using Query With NamedParameter JdbcTemplate
	 */
	@Override
	public List<FetchEmployeeReq> fecthEmployeeDetailsUsingQuery(FetchEmployeeReq fetchEmployeeReq) {
		try {
			return namedParameterJdbcTemplate.query(queryForFetchEmployeeDetailsUsingList,
					new BeanPropertySqlParameterSource(fetchEmployeeReq),
					new BeanPropertyRowMapper<FetchEmployeeReq>(FetchEmployeeReq.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new GivenListIsEmpty("Empty List....!!!!");
	}

	/*
	 * fetching employee details Using DynamicQuery With NamedParameter JdbcTemplate
	 */
	@Override
	public List<Map<String, Object>> fecthEmployeeDetailsUsingDynamicQuery(FetchEmployeeReq fetchEmployeeReq) {

		try {
			StringBuilder builder = new StringBuilder(queryForFetchEmployeeDetailsUsingList);
			if (StringUtils.isNotBlank(fetchEmployeeReq.getEmpNm())) {
				// iLIKE for case-insensitive string comparison.
				builder.append(" AND Concat(first_nm,' ',middle_nm,' ',last_nm) ilike '%'||:empNm||'%' ");
			}

			if (StringUtils.isNotBlank(fetchEmployeeReq.getGender())) {
				// iLIKE for case-insensitive string comparison.
				builder.append(" AND gender ilike '%'||:gender||'%' ");
			}

			if (fetchEmployeeReq.getMinSalary() != null && fetchEmployeeReq.getMaxSalary() != null) {
				builder.append(" AND emp_salary BETWEEN :minSalary AND :maxSalary ");
			} else if (fetchEmployeeReq.getMinSalary() != null) {
				builder.append(" AND emp_salary <= :minSalary ");
			} else if (fetchEmployeeReq.getMaxSalary() != null) {
				builder.append(" AND emp_salary >= :maxSalary ");
			}

			builder.append(" ORDER BY first_nm");
			if (fetchEmployeeReq.getPageNo() != null) {
				builder.append(" limit 2 offset (:pageNo*2) ");
			}
			return namedParameterJdbcTemplate.queryForList(builder.toString(),
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new GivenListIsEmpty("Empty List....!!!!");

	}

	@Override
	public Long insertEmployee(InsertEmployeeBean insertEmployeeBeanObj) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			namedParameterJdbcTemplate.update(queryForInsertEmployeeDetails,
					new BeanPropertySqlParameterSource(insertEmployeeBeanObj), keyHolder, new String[] { "emp_id" });
		} catch (Exception e) {
			e.printStackTrace();
		}
		Number number = keyHolder.getKey();
		return number.longValue();
	}

	@Override
	public Boolean updateEmployee(InsertEmployeeBean insertEmployeeBeanObj) {
		int update = 0;
		try {
			update = namedParameterJdbcTemplate.update(queryForUpdateEmployeeDetails,
					new BeanPropertySqlParameterSource(insertEmployeeBeanObj));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update > 0;
	}

	@Override
	public Boolean insertEmpDept(Long empId, List<EmployeeDeptBean> saveEmpDeptList) {
		int[] update = null;
		try {
			update = jdbcTemplate.batchUpdate(queryForInsertEmpDepXref, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					EmployeeDeptBean employeeDeptBeanObj = saveEmpDeptList.get(i);
					ps.setLong(1, employeeDeptBeanObj.getOrgId());
					ps.setLong(2, empId);
					ps.setLong(3, employeeDeptBeanObj.getDepId());
				}

				@Override
				public int getBatchSize() {

					return saveEmpDeptList.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveEmpDeptList.size() == (update != null ? update.length : 0);
	}

	@Override
	public Boolean updateEmpDept(List<EmployeeDeptBean> updateEmpDeptList) {
		int[] update = null;
		try {
			update = jdbcTemplate.batchUpdate(queryForUpdateEmpDepXref, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					EmployeeDeptBean employeeDeptBeanObj = updateEmpDeptList.get(i);
					ps.setLong(1, employeeDeptBeanObj.getDepId());
					ps.setLong(2, employeeDeptBeanObj.getOrgId());
					ps.setLong(3, employeeDeptBeanObj.getEmpDepId());
				}

				@Override
				public int getBatchSize() {

					return updateEmpDeptList.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateEmpDeptList.size() == (update != null ? update.length : 0);
	}

	/*
	 * fetching particular employee department details Using NamedParameter
	 * JdbcTemplate
	 */
	@Override
	public List<Map<String, Object>> fecthEmployeeWithDepartmentDetailsUsingQuery(FetchEmployeeReq fetchEmployeeReq) {
		logger.info("Info Message...!!!");
		try {
			logger.warn("Warn Message...!!!");
			return namedParameterJdbcTemplate.queryForList(queryForFetchEmployeeWithDepartmentDetails,
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Message...!!!");
		}
		logger.debug("Debug Message...!!");
		throw new GivenListIsEmpty("Empty List....!!!!");

	}

	@Override
	public List<Map<String, Object>> fetchDepartmentWiseGenderCount(FetchEmployeeReq fetchEmployeeReq) {

		try {

			return namedParameterJdbcTemplate.queryForList(queryForFetchGenderWithDepartmentWiseCountDetails,
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
		} catch (Exception e) {
			e.printStackTrace();

		}

		throw new GivenListIsEmpty("Empty List....!!!!");

	}

	@Override
	public Boolean insertEmployeeDetails(List<InsertPatientBean> list) {
		int[] batchUpdate = null;
		try {
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForInsertPatientDetails,
					SqlParameterSourceUtils.createBatch(list.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.size() == (batchUpdate != null ? batchUpdate.length : 0);
	}

	@Override
	public List<Map<String, Object>> employeeDetailsExportToExcelFile() {
		try {
			return jdbcTemplate.queryForList(queryForFetchPatientDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new GivenListIsEmpty("Empty List....!!!!");
	}

}
