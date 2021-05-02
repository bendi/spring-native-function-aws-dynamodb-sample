import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.bedkowski.spring.lambda.Application;
import pl.bedkowski.spring.lambda.model.ProductInfo;
import pl.bedkowski.spring.lambda.repository.ProductInfoRepository;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
  "amazon.dynamodb.endpoint=http://localhost:8000/", 
  "amazon.dynamodb.accesskey=test1",
  "amazon.dynamodb.secretkey=test231" })
@TestInstance(Lifecycle.PER_CLASS)
public class ProductInfoRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private ProductInfoRepository repository;

    private static final String EXPECTED_COST = "20";
    private static final String EXPECTED_PRICE = "50";

    @BeforeAll
    public void createTable() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        var tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductInfo.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
    }

    @BeforeEach
    public void setup() throws Exception {
        dynamoDBMapper.batchDelete(repository.findAll());
    }

    @Test
    public void givenItemWithExpectedCost_whenRunFindAll_thenItemIsFound() { 
        ProductInfo productInfo = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
        repository.save(productInfo);
        var result = new ArrayList<ProductInfo>();
        repository.findAll().spliterator().forEachRemaining(result::add);

        assertThat(result.size(), is(greaterThan(0)));
        assertThat(result.get(0).getCost(), is(equalTo(EXPECTED_COST))); 
    }
}
