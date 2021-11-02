package jp.co.sample.repository;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
	
	/**
	 * 取得したデータをAdministratorドメインに変換するRowMapper作成
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
		= new BeanPropertyRowMapper<>(Administrator.class);
	
	
	/**
	 * クエリ―用Template作成
	 */
	private NamedParameterJdbcTemplate templete;
	
	public void insert(Administrator administrator) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		String sql ="INSERT INTO administrators(name,mailAddress,password) "
				+ "VALUES(:name,:mailAddress,:password);";
		
		templete.update(sql, param);
		
	}
	
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		
		String sql ="SELECT id,name,mailAddress,password "
				+ "FROM administrators WHERE mailAddress=:mailAddress AND password=:password;";
		
		SqlParameterSource param = new 
				MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		
		Administrator administrator = templete.queryForObject(sql,param,ADMINISTRATOR_ROW_MAPPER);
		
		if(administrator == null) {
			return null;
		}
		
		return administrator;
		
	}
	

}
