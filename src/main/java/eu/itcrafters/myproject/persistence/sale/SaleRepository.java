package eu.itcrafters.myproject.persistence.sale;

import eu.itcrafters.myproject.persistence.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
  @Query("select s from Sale s where s.product = :product")
  Optional<Sale> findSaleBy(Product product);
}