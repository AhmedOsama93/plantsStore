package onlineStore.plantsStore;

import onlineStore.plantsStore.users.userService;
import onlineStore.plantsStore.users.users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@Configuration
public class PlantsStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantsStoreApplication.class, args);

	}
	@Bean
	CommandLineRunner MaincommandLineRunner(userService userService){
		return args -> {
			//take care to change it in each run
//			userService.addNewUser(
//					new users(
//                    5,
//                    "abdo2",
//                    "admin2",
//                    "admin5",
//                    "123",
//                    true,
//                    true
//            )
//			);
//
//
//			userService.addRoleToUser("admin5", "ROLE_ADMIN");
//			userService.addRoleToUser("admin5", "ROLE_USER");
			//userService.addRoleToUser("admin", "ROLE_ADMIN");
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
