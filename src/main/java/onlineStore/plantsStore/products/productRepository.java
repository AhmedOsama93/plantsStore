package onlineStore.plantsStore.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface productRepository extends JpaRepository<product,Long> {
    @Query("select p from product p where p.name=?1")
    product findproductsByName(String name);
    @Query("select p from product p where p.active=true")
    List<product>  getActiveProducts();
    @Query("select p from product p where p.id=?1")
    product findproductsByID(long id);
    @Query("select p from product p where p.id=?1 and p.active=TRUE")
    product findActiveProductsByID(long id);

    @Query("select p from product p where p.season=?1")
    List<product> findproductsByseason(String season);
}
