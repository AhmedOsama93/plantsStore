package onlineStore.plantsStore.Role;

import onlineStore.plantsStore.users.users;
import onlineStore.plantsStore.users.usersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class roleConfig {
    @Bean
    CommandLineRunner rolecommandLineRunner(roleRepo repository){
        return args->{
            Role role=new Role(1,"ROLE_USER");
            Role role2=new Role(2,"ROLE_ADMIN");
            repository.save(role);
            repository.save(role2);
        };
    }
}
