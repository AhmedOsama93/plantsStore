package onlineStore.plantsStore.products;

import onlineStore.plantsStore.users.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productService {
    private final productRepository productRepository;

    @Autowired
    public productService(productRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<product> getProducts(){
        return productRepository.findAll();
    }

    public void addNewProduct(product product){
        Optional<product> productOptional = productRepository.findproductsByName(product.getName());
        if(productOptional.isPresent()){
            throw new IllegalStateException("product name already exists");
        }else {
            productRepository.save(product);
        }
        System.out.println(product);
    }

}
