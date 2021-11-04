package jp.co.sample.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者情報検索・登録用レポジトリ
 * @author igayu
 *
 */
@Repository
public class AdministratorRepository {
	
	/**
	 * 取得したデータをAdministratorドメインに変換するRowMapper作成
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
		= new BeanPropertyRowMapper<>(Administrator.class);
	
	@Autowired
	private NamedParameterJdbcTemplate templete;
	
	/**
	 * 管理者登録メソッド
	 * @param administrator　管理者オブジェクト
	 */
	public void insert(Administrator administrator) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		String sql ="INSERT INTO administrators(name,mail_address,password) "
				+ "VALUES(:name,:mailAddress,:password);";
		
		templete.update(sql, param);
		
	}
	
	/**
	 * @param mailAddress　管理者のメールアドレス
	 * @param password　管理者のパスワード
	 * @return
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		
		String sql ="SELECT id,name,mail_address,password "
				+ "FROM administrators WHERE mail_address=:mailAddress AND password=:password;";
		
		SqlParameterSource param = new 
				MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		
		List<Administrator> administratorList = templete.query(sql, param,ADMINISTRATOR_ROW_MAPPER);
		
		if(administratorList.size() == 0) {
			return null;
		}
		
		return administratorList.get(0);
		
	}
	

}
