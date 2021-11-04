package jp.co.sample.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.domain.Employee;
import jp.co.sample.repository.AdministratorRepository;
import jp.co.sample.repository.EmployeeRepository;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	EmployeeRepository emRepository;
	
	@Autowired
	AdministratorRepository adRepository;
	
	@RequestMapping("")
	private String employeeTest() {
		/*
		//findAllテスト
		List<Employee> employeeList = emRepository.findAll();
		for(Employee employee : employeeList) {
			System.out.println(employee);
		}
		
		//loadテスト
		System.out.println( emRepository.load(1));
		
		//updateテスト
		
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("test");
		employee.setImage("test");
		employee.setGender("test");
		employee.setHireDate(new Date());
		employee.setMailAddress("demo3@sample.com");
		employee.setZipCode("test");
		employee.setAddress("test");
		employee.setTelephone("test");
		employee.setSalary(0);
		employee.setCharacteristics("test");
		employee.setDependentsCount(0);
		
		emRepository.update(employee) ;
		*/
		/*
		Administrator administrator = new Administrator();
		administrator.setMailAddress("test@sample.com");
		administrator.setName("test");
		administrator.setPassword("test");
		adRepository.insert(administrator);
		
		System.out.println(adRepository.findByMailAddressAndPassword("iga@sample.com","testtest"));
		*/
		return "test";
		
	}

}
