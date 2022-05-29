package onlineStore.plantsStore.products;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class productController {
    private final productService productService;

    @Autowired
    public productController(productService productService){
        this.productService=productService;
    }

    @GetMapping(path="admin/getProductCount")
    public ResponseEntity<Integer>getProductCount(){
        return ResponseEntity.ok().body(productService.getProductCount());
    }
    @GetMapping(path="visitor/getProduct")
    public ResponseEntity<List<product>> getProduct(){
        return ResponseEntity.ok().body(productService.getProducts());
    }
    @GetMapping(path="user/getProductByID/{id}")
    public ResponseEntity<product> getProductById(@PathVariable long id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
    @GetMapping(path="user/getTopProduct")
    public ResponseEntity<List<product>> getTopProduct(){
        return ResponseEntity.ok().body(productService.getTopProducts());
    }
    @GetMapping(path="admin/productSeasonStat" )
    public ResponseEntity<SeasonStat[]> productSeasonStat(){
        return ResponseEntity.ok().body(productService.getSeasonStat());
    }
 //   @GetMapping(path="admin/productSeasonStat" )

    @PostMapping(path="admin/addNewProduct" )
    public ResponseEntity<?> addNewProduct(@RequestBody  product product){
        productService.addNewProduct(product);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="admin/deleteProduct/{id}")
    public ResponseEntity<?>deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="admin/editProduct")
    public ResponseEntity<?> editProduct(@RequestBody product product) {
        productService.editProduct(product);
        return ResponseEntity.ok().build();
    }

}
@Data
class SeasonStat{
    String season;
    int productNo;
}