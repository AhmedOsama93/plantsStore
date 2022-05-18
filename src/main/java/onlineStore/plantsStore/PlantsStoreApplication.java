package onlineStore.plantsStore;

import onlineStore.plantsStore.users.userService;
import onlineStore.plantsStore.users.users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication

public class PlantsStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantsStoreApplication.class, args);


	}
}
