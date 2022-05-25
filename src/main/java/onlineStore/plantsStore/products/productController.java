package onlineStore.plantsStore.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productController {
    private final productService productService;

    @Autowired
    public productController(productService productService){
        this.productService=productService;
    }

    @GetMapping(path="user/getProduct")
    public List<product> getProduct(){return productService.getProducts();}

    @PostMapping(path="admin/addNewProduct" )
    public ResponseEntity<?> addNewProduct(@RequestBody  product product){
        productService.addNewProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="admin/editProduct")
    public void editProduct(@RequestBody product product) {
        productService.editProduct(product);
    }

}
