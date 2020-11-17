package technical.test.massiv.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * MongoConfig represents the configuration to the database
 *
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Value("${mongodb.name}")
	private String MONGODB_NAME;

	@Value("${mongodb.uri}")
	private String URI;

	@Value("${mongodb.port}")
	private Integer PORT;

	@Override
	protected String getDatabaseName() {

		return MONGODB_NAME;
	}

	@Bean
	@Override
	public MongoClient mongoClient() {

		String uriDataBase = String.format(URI,PORT);
		ConnectionString connectionString = new ConnectionString(uriDataBase);
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
																	 .applyConnectionString(connectionString)
																	 .build();

		return MongoClients.create(mongoClientSettings);
	}

}
