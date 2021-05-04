package pl.bedkowski.spring.lambda;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.bedkowski.spring.lambda.repository.ProductInfoRepository;

import java.util.function.Function;

@Component("uppercase")
public class UpperCaseFunction implements Function<String, String> {

    private static final Logger log = LogManager.getLogger();

    private final ProductInfoRepository productInfoRepository;

    public UpperCaseFunction(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }

    @Override
    public String apply(String s) {
        var product = productInfoRepository.findById("1");
        log.info("Fetched product: {}", product.getMsrp());
        return s.toUpperCase();
    }
}
