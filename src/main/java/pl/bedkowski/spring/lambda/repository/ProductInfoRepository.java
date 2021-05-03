package pl.bedkowski.spring.lambda.repository;

import org.springframework.stereotype.Component;
import pl.bedkowski.spring.lambda.model.ProductInfo;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.util.HashMap;
import java.util.Optional;

@Component
public class ProductInfoRepository  {

    private final DynamoDbClient dynamoDbClient;

    public ProductInfoRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public Optional<ProductInfo> findById(String keyVal) {
        var keyToGet = new HashMap<String, AttributeValue>();

        keyToGet.put("id", AttributeValue.builder()
                                        .s(keyVal).build());

        var request = GetItemRequest.builder()
                                    .key(keyToGet)
                                    .tableName("ProductInfo")
                                    .build();

        var returnedItem = dynamoDbClient.getItem(request).item();
        return Optional.empty();
    }
}