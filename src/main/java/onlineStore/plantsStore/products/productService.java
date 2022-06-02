package onlineStore.plantsStore.products;

import onlineStore.plantsStore.orders.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class productService {
    private final productRepository productRepository;
    private final orderService orderService;
    @Autowired
    public productService(productRepository productRepository, onlineStore.plantsStore.orders.orderService orderService){
        this.productRepository=productRepository;
        this.orderService = orderService;
    }

    public List<product> getActiveProducts(){
        return productRepository.getActiveProducts();
    }
    public List<product> getAllProducts(){
        return productRepository.findAll();
    }
    public int getProductCount(){
        return productRepository.findAll().size();
    }

    public product getActiveProductById(long id){
        return productRepository.findActiveProductsByID(id);
    }
    public product getActiveProductByName(String name){
        return productRepository.findActiveProductsByName(name);
    }
    public product getByIdForAdmin(long id){
        return productRepository.findproductsByID(id);
    }

    public List<product> getTopProducts(){
        List<product> p1 = productRepository.findAll();
        Collections.sort(p1);
        List<product> p2 = new ArrayList<>() ;
        int size;
        if(p1.size()>10)
            size = 10;
        else
            size=p1.size();
        for (int i = 0; i <size ; i++) {
            p2.add(p1.get(i));
        }
        return p2;
    }

    public void deActivateProduct(long id){
        product p =productRepository.getById(id);
        p.setActive(false);
        productRepository.save(p);
    }
    public void ActivateProduct(long id){
        product p =productRepository.getById(id);
        p.setActive(true);
        productRepository.save(p);
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

        SeasonStat s5 = new SeasonStat();
        s5.season="all year";
        s5.productNo=productRepository.findproductsByseason(s5.season).size();

        SeasonStat[] arr ={s1,s2,s3,s4,s5};
        return arr;
    }
    public void addNewProduct(product product){
        product product1 = productRepository.findproductsByName(product.getName());
        if(product1!=null){
            throw new IllegalStateException("product name already exists");
        }else {
            if(product.getSeason()==null||product.getSeason().equals(",")||product.getSeason().equals("")){
                product.setSeason(null);
            }
            if(product.getSoil()==null||product.getSoil().equals(",")||product.getSoil().equals("")){
                product.setSoil(null);
            }
            product.setActive(true);
            productRepository.save(product);
        }
    }

    public void editProduct(product product){
            if(product.getSeason()==null||product.getSeason().equals(",")||product.getSeason().equals("")){
                product.setSeason(null);
            }
            if(product.getSoil()==null||product.getSoil().equals(",")||product.getSoil().equals("")){
                product.setSoil(null);
            }
            productRepository.save(product);
    }
    public List<Double>aboutProductPrice(){
        List<product>products=productRepository.findAll();
        List<Double>prices = new ArrayList<>();
        List<Double>aboutProductPrice=new ArrayList<>();
        Double sum = 0.0;
        Double More200 = 0.0, less200=0.0;
        for (product p:products) {
            sum+=p.getPrice();
            if (p.getPrice()>200){
                More200++;
            }
            else {
                less200++;
            }
            prices.add(p.getPrice());
        }
        Double mn = Collections.min(prices);
        Double mx = Collections.max(prices);
        Double avg = sum/products.size();
        aboutProductPrice.add(mn);
        aboutProductPrice.add(avg);
        aboutProductPrice.add(mx);
        aboutProductPrice.add(More200);
        aboutProductPrice.add(less200);
        List<Double> data2 = new ArrayList<>();
        data2=orderService.aboutOrders();
        aboutProductPrice.addAll(data2);
        return aboutProductPrice;
    }
}
