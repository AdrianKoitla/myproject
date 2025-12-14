package eu.itcrafters.myproject.persistence.producttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {


    @Query("select p from ProductType p where upper(p.typeName) = upper(:typeName)")
    Optional<ProductType> findProductTypeBy(String typeName);
}