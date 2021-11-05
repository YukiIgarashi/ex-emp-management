package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	
	/**
	 * 管理者新規登録のメソッド
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		
		administratorRepository.insert(administrator);
		
	}
	
	/**
	 * ログインする管理者のオブジェクトを呼び出すメソッド
	 * @param mailAddress　ログインしたい管理者のメールアドレス
	 * @param password　ログインしたい管理者のパスワード
	 * @return
	 */
	public Administrator login(String mailAddress,String password) {
		
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
	
}
