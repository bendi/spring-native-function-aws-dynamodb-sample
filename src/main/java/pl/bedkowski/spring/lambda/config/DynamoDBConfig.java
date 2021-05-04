package pl.bedkowski.spring.lambda.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {
    private final DynamoDbConfigProps dynamoDbConfigProps;

    public DynamoDBConfig(DynamoDbConfigProps dynamoDbConfigProps) {
        this.dynamoDbConfigProps = dynamoDbConfigProps;
    }

    AwsCredentials awsCredentials() {
        return AwsBasicCredentials.create(dynamoDbConfigProps.getAccesskey(), dynamoDbConfigProps.getSecretkey());
    }
    DynamoDbClient dynamoDbClient(AwsCredentials awsCredentials) {
        return DynamoDbClient.builder().credentialsProvider(() -> awsCredentials).endpointOverride(dynamoDbConfigProps.getEndpoint()).region(dynamoDbConfigProps.getRegion()).build();
    }

    @Bean
    DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient(awsCredentials())).build();
    }
}