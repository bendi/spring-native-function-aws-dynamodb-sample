package pl.bedkowski.spring.lambda.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "amazon.dynamodb")
@ConstructorBinding
public class DynamoDbConfigProps {

    private final String endpoint;

    private final String accesskey;

    private final String secretkey;

    public DynamoDbConfigProps(String endpoint, String accesskey, String secretkey) {
        this.endpoint = endpoint;
        this.accesskey = accesskey;
        this.secretkey = secretkey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public String getSecretkey() {
        return secretkey;
    }
}
