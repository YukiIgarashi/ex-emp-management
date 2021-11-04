package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
	/**
	 * 従業員情報を全件取得するメソッド
	 */
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> showList() {
		
		return employeeRepository.findAll();
		
	}
	
	/**
	 * 従業員情報をIDで１件検索し、情報を取得するメソッド
	 * @param id 検索したい従業員のID
	 * @return 従業員情報を持ったオブジェクト
	 */
	public Employee showDetail(Integer id) {
		
		return employeeRepository.load(id);
		
	}

}
