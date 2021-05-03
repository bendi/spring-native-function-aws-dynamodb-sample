package pl.bedkowski.spring.lambda.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class ProductInfo {
    private String id;
    private String msrp;
    private String cost;

    public ProductInfo(String expectedCost, String expectedPrice) {
        this.cost = expectedCost;
        this.msrp = expectedPrice;
    }

    public ProductInfo(){}

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @DynamoDbAttribute("msrp")
    public String getMsrp() {
        return msrp;
    }

    @DynamoDbAttribute("cost")
    public String getCost() {
        return cost;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}