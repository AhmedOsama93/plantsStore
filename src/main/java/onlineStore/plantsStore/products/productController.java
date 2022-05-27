package onlineStore.plantsStore.products;


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

    @GetMapping(path="user/getProduct")
    public ResponseEntity<List<product>> getProduct(){

        return ResponseEntity.ok().body(productService.getProducts());
    }

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
