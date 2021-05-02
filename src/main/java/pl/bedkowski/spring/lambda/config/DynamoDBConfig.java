package pl.bedkowski.spring.lambda.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.apache.commons.lang3.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "pl.bedkowski.spring.lambda.repository")
public class DynamoDBConfig {
    private final DynamoDbConfigProps dynamoDbConfigProps;

    public DynamoDBConfig(DynamoDbConfigProps dynamoDbConfigProps) {
        this.dynamoDbConfigProps = dynamoDbConfigProps;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        var amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        
        if (!StringUtils.isEmpty(dynamoDbConfigProps.getEndpoint())) {
            amazonDynamoDB.setEndpoint(dynamoDbConfigProps.getEndpoint());
        }
        
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
          dynamoDbConfigProps.getAccesskey(), dynamoDbConfigProps.getSecretkey());
    }
}