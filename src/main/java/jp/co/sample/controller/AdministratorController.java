package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministratorForm;
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
	
	

}
