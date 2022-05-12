package onlineStore.plantsStore.products;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class productConfig {
    @Bean
    CommandLineRunner commandLineRunner2(productRepository repository){
        return args->{
            product p1 = new product(
                    1,
                    "plant1",
                    "category1",
                    20,
                    50,
                    true
            );
            repository.saveAll(List.of(p1));
        };
    }
    @Bean
    public WebMvcConfigurer corsConfigurer2() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }
        };
    }
}
