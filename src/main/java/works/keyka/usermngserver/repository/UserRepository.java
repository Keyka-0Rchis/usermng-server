package works.keyka.usermngserver.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import works.keyka.usermngserver.domain.UserData;


@Repository
@RequiredArgsConstructor
public class UserRepository {
	
	private final JdbcTemplate jdbcTemplate;
	public void insert(UserData userData) {
		String sql = "INSERT INTO user_data (user_name, email, password, delete_flag) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql,
			userData.getUserName(),
			userData.getEmail(),
			userData.getPassword(),
			userData.isDeleteFlag()
			);
    }
}
