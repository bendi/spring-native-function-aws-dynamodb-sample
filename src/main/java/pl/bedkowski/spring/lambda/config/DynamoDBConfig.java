package pl.bedkowski.spring.lambda.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {
    private final DynamoDbConfigProps dynamoDbConfigProps;

    public DynamoDBConfig(DynamoDbConfigProps dynamoDbConfigProps) {
        this.dynamoDbConfigProps = dynamoDbConfigProps;
    }

    @Bean
    DynamoDbClient dynamoDbClient() {
        var region = Region.US_EAST_1;
        return DynamoDbClient.builder().region(region).build();
    }
}