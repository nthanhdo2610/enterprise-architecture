package cs544.auth.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

// import cs544.auth.user.UserRepository;
// import cs544.auth.user.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired private UserRepository repo;
	
//	@Test
	public void testCreateUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("password");
		//Login api test curl
		//curl -v -H "Content-Type: application/json" -d "{\"email\":\"user@miu.edu\", \"password\":\"password\"}" localhost:8080/auth/

		//valid api test curl
		//curl localhost:8080/auth/?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2LG5hbUBjb2RlamF2YS5uZXQiLCJpc3MiOiJDb2RlSmF2YSIsImlhdCI6MTY4NzAzMDI2MywiZXhwIjoxNjg3MTE2NjYzfQ.bmH2h_3RVM15KdRMXWslRxLgWs7_MHFh82H1aRkM0VzHrBlAkyF3TXz36DI8yI_42S11S8HZoTd-xRnGKqhd8w | json
		User newUser = new User("user@miu.edu", password, "admin");
		
		User savedUser = repo.save(newUser);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
}
