package onlineStore.plantsStore.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productController {
    private final productService productService;

    @Autowired
    public productController(productService productService){
        this.productService=productService;
    }

    @GetMapping(path="api/product/getProduct")
    public List<product> getProduct(){return productService.getProducts();}

    @PostMapping(path="api/product/addNewProduct" )
    public void addNewProduct(@ModelAttribute  product product){
        productService.addNewProduct(product);
    }

    @PostMapping(path="api/product/editProduct")
    public void editProduct(@RequestBody product product) {
        productService.editProduct(product);
    }

}
