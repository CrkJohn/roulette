package technical.test.massiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MassivApplication {

	public static void main(String[] args) {
		SpringApplication.run(MassivApplication.class, args);
	}

}
