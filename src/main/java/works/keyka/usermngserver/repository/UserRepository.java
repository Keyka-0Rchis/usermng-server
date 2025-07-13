package works.keyka.usermngserver.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import works.keyka.usermngserver.domain.UserData;


@Repository
@RequiredArgsConstructor
public class UserRepository {
	
	//JdbcTamplateのメソッドを使うためにインスタンス化
	private final JdbcTemplate jdbcTemplate;
	public void insert(UserData userData) {
		//一旦？でinsert intoして
		String sql = "INSERT INTO user_data (user_name, email, password, delete_flag) VALUES (?, ?, ?, ?)";
		//実際の値で上書きってこと？
		jdbcTemplate.update(sql,
			userData.getUserName(),
			userData.getEmail(),
			userData.getPassword(),
			userData.isDeleteFlag()
			);
    }
	
	public boolean isExistEmail(String email) {
		String sql = "SELECT COUNT(*) FROM user_data WHERE email = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class,email);
		return count != null && count > 0;
	}
}
