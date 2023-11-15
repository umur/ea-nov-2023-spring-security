package edu.miu.jwtsecurity.repository;

import edu.miu.jwtsecurity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Integer> {
}
