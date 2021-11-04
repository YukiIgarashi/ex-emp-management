package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
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

}
