package jp.co.sample.controller;

import java.beans.Beans;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録用コントローラー
 * @author igayuki
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	/**
	 * 管理者登録用サービス
	 */
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * 管理者登録用フォーム自動生成
	 * @return　管理者登録用フォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		
		return new InsertAdministratorForm();
		
	}
	
	/**
	 * 管理者登録画面へ遷移
	 * @return　管理者登録画面URL
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		
		return "administrator/insert";
		
	}
	
	/**
	 * 管理者登録を行うメソッド
	 * @param form パラメーターを含むフォーム
	 * @return　ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		
		administratorService.insert(administrator);
		
		return "redirect:/";
		
	}
	
	/**
	 * ログイン用フォーム自動生成
	 * @return
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		
		return new LoginForm();
		
	}
	
	/**
	 * ログイン画面へ遷移
	 * @return
	 */
	@RequestMapping("/")
	public String toLogin() {
		
		return "administrator/login";
		
	}
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		
		if(administrator == null) {
			String message = "メールアドレスまたはパスワードが不正です。";
			model.addAttribute("message",message);
			return "forward:/";
			
		}
		
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
		
	}
	
	/**
	 * 管理者ログアウト用メソッド
	 * @return ログイン画面への遷移
	 */
	@RequestMapping("/logout")
	public String logout() {
		
		session.invalidate();
		return "redirect:/";
		
	}
	
	
	
	

}
