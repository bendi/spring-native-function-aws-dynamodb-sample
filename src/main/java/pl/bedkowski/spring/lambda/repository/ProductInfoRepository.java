package pl.bedkowski.spring.lambda.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bedkowski.spring.lambda.model.ProductInfo;

import java.util.Optional;

@EnableScan
public interface ProductInfoRepository extends CrudRepository<ProductInfo, String> {
    
    Optional<ProductInfo> findById(String id);
}