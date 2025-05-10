package manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "manager.repository")
//@EntityScan(basePackages = "manager.entity")
public class HotelManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagerApplication.class, args);
	}

}
