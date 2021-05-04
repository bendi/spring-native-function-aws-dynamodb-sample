package pl.bedkowski.spring.lambda.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import software.amazon.awssdk.regions.Region;

import java.net.URI;

@ConfigurationProperties(prefix = "amazon.dynamodb")
@ConstructorBinding
public class DynamoDbConfigProps {

    private final URI endpoint;

    private final String accesskey;

    private final String secretkey;

    private final Region region;

    public DynamoDbConfigProps(URI endpoint, String accesskey, String secretkey, Region region) {
        this.endpoint = endpoint;
        this.accesskey = accesskey;
        this.secretkey = secretkey;
        this.region = region;
    }

    public URI getEndpoint() {
        return endpoint;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public Region getRegion() {
        return region;
    }
}
