package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員用コントローラークラス
 * @author igayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 従業員一覧ページへ遷移させるメソッド
	 * @param model　従業員リストを入れるスコープ
	 * @return　従業員一覧ページ
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList",employeeList);
		return "employee/list";
		
	}
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		
		return new UpdateEmployeeForm();
		
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		
		model.addAttribute("employee",employeeService.showDetail(Integer.parseInt(id)));
		
		return "employee/detail";
	}
	
	/**
	 * 従業員の扶養人数を更新するメソッド
	 * @param form 更新したい従業員のフォームオブジェクト
	 * @return 従業員一覧ページへの遷移
	 */
	@RequestMapping("/update")
	public String update(String id,String dependentsCount) {
		
		Employee employee = new Employee();
		
		employee.setId(Integer.parseInt(id));
		
		employee = employeeService.showDetail(employee.getId());
		employee.setDependentsCount(Integer.parseInt(dependentsCount));
		employeeService.update(employee);
		
		return "redirect:/employee/showList";
		
	}
	

}
