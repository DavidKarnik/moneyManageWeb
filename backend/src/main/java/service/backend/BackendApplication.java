package service.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.backend.service.UserService;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

//		UserService userService = new UserService();
//		System.out.println(userService.getUserByEmail("alice.johnson@example.com"));
//		userService.loadUsersFromJsonFile2();
	}

}
