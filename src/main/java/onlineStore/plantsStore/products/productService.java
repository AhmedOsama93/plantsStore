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
    public void deleteProduct(long id){
        product p =productRepository.getById(id);
        productRepository.delete(p);
    }
    public SeasonStat[] getSeasonStat(){
        SeasonStat s1 = new SeasonStat();
        s1.season="summer";
        s1.productNo=productRepository.findproductsByseason(s1.season).size();

        SeasonStat s2 = new SeasonStat();
        s2.season="winter";
        s2.productNo=productRepository.findproductsByseason(s2.season).size();

        SeasonStat s3 = new SeasonStat();
        s3.season="autumn";
        s3.productNo=productRepository.findproductsByseason(s3.season).size();

        SeasonStat s4 = new SeasonStat();
        s4.season="spring";
        s4.productNo=productRepository.findproductsByseason(s4.season).size();
        SeasonStat[] arr ={s1,s2,s3,s4};
        return arr;
    }
    public void addNewProduct(product product){
        product product1 = productRepository.findproductsByName(product.getName());
        if(product1!=null){
            throw new IllegalStateException("product name already exists");
        }else {
            System.out.println(product);
            if(product.getSeason()==null||product.getSeason().equals(",")||product.getSeason().equals("")){
                product.setSeason(null);
            }
            if(product.getSoil()==null||product.getSoil().equals(",")||product.getSoil().equals("")){
                product.setSoil(null);
            }
            productRepository.save(product);
        }
        System.out.println(product);
    }

    public void editProduct(product product){
        productRepository.save(product);
    }
}
