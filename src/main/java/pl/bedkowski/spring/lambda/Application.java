package pl.bedkowski.spring.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.bedkowski.spring.lambda.config.DynamoDbConfigProps;

@SpringBootApplication
@ConfigurationPropertiesScan("pl.bedkowski.spring.lambda.config")
// limitation of native-image generation
// please see https://github.com/spring-projects-experimental/spring-native/issues/716
@EnableConfigurationProperties(DynamoDbConfigProps.class)
public class Application {

	/*
	 * You need this main method or explicit <start-class>example.FunctionConfiguration</start-class>
	 * in the POM to ensure boot plug-in makes the correct entry
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}