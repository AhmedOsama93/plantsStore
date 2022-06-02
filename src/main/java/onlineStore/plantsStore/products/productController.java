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
        return ResponseEntity.ok().body(productService.getActiveProducts());
    }
    @GetMapping(path="admin/getAllProduct")
    public ResponseEntity<List<product>> getAllProduct(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
    @GetMapping(path="user/getProductByID/{id}")
    public ResponseEntity<product> getProductById(@PathVariable long id){
        return ResponseEntity.ok().body(productService.getActiveProductById(id));
    }
    @GetMapping(path="user/getProductByName/{name}")
    public ResponseEntity<product> getProductByName(@PathVariable String name){
        return ResponseEntity.ok().body(productService.getActiveProductByName(name));
    }
    @GetMapping(path="admin/getProductByIdForAdmin/{id}")
    public ResponseEntity<product> getProductByIdForAdmin(@PathVariable long id){
        return ResponseEntity.ok().body(productService.getByIdForAdmin(id));
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
    public ResponseEntity<?> addNewProduct(product product){
        productService.addNewProduct(product);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="admin/deActivateProduct/{id}")
    public ResponseEntity<?>deActivateProduct(@PathVariable long id){
        productService.deActivateProduct(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="admin/ActivateProduct/{id}")
    public ResponseEntity<?>activateProduct(@PathVariable long id){
        productService.ActivateProduct(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="admin/editProduct")
    public ResponseEntity<?> editProduct( product product) {
        productService.editProduct(product);
        return ResponseEntity.ok().build();
    }
    @GetMapping(path="admin/aboutProductPrice")
    public ResponseEntity<List<Double>> aboutProductPrice(){
        return ResponseEntity.ok().body(productService.aboutProductPrice());
    }
}
@Data
class SeasonStat{
    String season;
    int productNo;
}
