package onlineStore.plantsStore.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/product")
public class productController {
    private final productService productService;

    @Autowired
    public productController(productService productService){
        this.productService=productService;
    }

    @GetMapping
    public List<product> getProduct(){return productService.getProducts();}

    @PostMapping
    public void addNewProduct(@RequestBody product product){
        productService.addNewProduct(product);
    }
}
