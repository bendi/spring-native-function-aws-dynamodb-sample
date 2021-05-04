package pl.bedkowski.spring.lambda.repository;

import org.springframework.stereotype.Component;
import pl.bedkowski.spring.lambda.model.ProductInfo;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.*;

@Component
public class ProductInfoRepository  {

    private static final TableSchema<ProductInfo> SCHEMA;

    static {
//         BeanTableSchema is buggy on graalvm: https://github.com/aws/aws-sdk-java-v2/issues/2445
//         building static
//        SCHEMA = TableSchema.fromBean(ProductInfo.class);
        SCHEMA = tableSchema();
    }

    private final DynamoDbTable<ProductInfo> dynamoDbTable;

    public ProductInfoRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        dynamoDbTable = dynamoDbEnhancedClient.table("ProductInfo", SCHEMA);
    }

    public ProductInfo findById(String keyVal) {
        var key= Key.builder().partitionValue(keyVal).build();

        return dynamoDbTable.getItem(key);
    }

    private static TableSchema<ProductInfo> tableSchema() {
        return StaticTableSchema.builder(ProductInfo.class)
                         .newItemSupplier(ProductInfo::new)
                         .addAttribute(
                                 String.class,
                                 (a) -> a.name("id")
                                         .getter(ProductInfo::getId)
                                         .setter(ProductInfo::setId)
                                         .tags(primaryPartitionKey())
                         )
                         .addAttribute(
                                 String.class,
                                 (a) -> a.name("msrp")
                                         .getter(ProductInfo::getMsrp)
                                         .setter(ProductInfo::setMsrp)
                         )
                         .addAttribute(
                                 String.class,
                                 (a) -> a.name("cost")
                                         .getter(ProductInfo::getCost)
                                         .setter(ProductInfo::setCost)
                         )
                         .build();
    }
}