package jp.co.sample.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * 従業員情報検索・更新用レポジトリ
 * @author igayu
 *
 */
@Repository
public class EmployeeRepository {
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
	= new BeanPropertyRowMapper<>(Employee.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 従業員情報（全件）検索用メソッド
	 * @return
	 */
	public List<Employee> findAll() {
		
		String sql ="SELECT * FROM employees"
				+ " ORDER BY hire_date DESC;";
		
		List<Employee> employeeList = template.query(sql,EMPLOYEE_ROW_MAPPER);
		
		if(employeeList.size() == 0) {
			List<Employee> zeroOfSizeOfEmployeeList = new ArrayList<>();
			return zeroOfSizeOfEmployeeList;
		}
		return employeeList;
		
	}
	
	/**
	 * 従業員情報（ID指定）検索用メソッド
	 * @param id　従業員ID
	 * @return
	 */
	public Employee load(Integer id) {
		
		String sql = "SELECT * FROM employees WHERE id=:id;";
		
		SqlParameterSource param = new 
				MapSqlParameterSource().addValue("id", id);
		
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
		
	}
	
	/**
	 * 従業員情報更新用メソッド
	 * @param employee　更新する従業員オブジェクト
	 */
	public void update(Employee employee) {
		String sql = "UPDATE employees SET name=:name,image=:image,gender=:gender"
				+ ",hire_date=:hire_date,mail_address=:mail_address"
				+ ",zip_code=:zip_code,address=:address,telephone=:telephone"
				+ ",salary=:salary,characteristics=:characteristics,dependents_count=:dependents_count"
				+ " WHERE id=:id;";
				
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		
		template.update(sql,param);
		
		
	}
	

}
