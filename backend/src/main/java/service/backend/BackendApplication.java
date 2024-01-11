package service.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.backend.model.Collection;
import service.backend.service.AccountService;
import service.backend.service.UserService;

import java.util.List;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

//		UserService userService = new UserService();
//		System.out.println(userService.getUserByEmail("alice.johnson@example.com"));
//		userService.loadUsersFromJsonFile2();

		AccountService accountService = new AccountService();

		List<Collection> collections1 = accountService.getUsersCollectionsByEmail("john.doe@example.com");

		System.out.println(collections1);

		Collection col = new Collection();
		System.out.println(col.generateUniqueId());
	}

}
