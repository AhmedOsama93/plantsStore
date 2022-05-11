package onlineStore.plantsStore.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class userConfig {

    @Bean
    CommandLineRunner commandLineRunner(usersRepository repository){
        return args->{
            users abdo = new users(
                    1,
                    "abdo",
                    "admin",
                    "admin1@gmail.com",
                    "123",
                    true,
                    true
            );
            repository.saveAll(List.of(abdo));
        };
    }
}
