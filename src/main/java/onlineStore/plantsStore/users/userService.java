package onlineStore.plantsStore.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import onlineStore.plantsStore.Role.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class userService  implements UserDetailsService {

    private final usersRepository usersRepository;
    private final onlineStore.plantsStore.Role.roleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        users user = usersRepository.findusersByEmail(email);
        if(user==null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else {
            log.info("user found:{}",email);
        }
        Collection<SimpleGrantedAuthority>authorities=new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    public List<users> getUsers(){
        return  usersRepository.findAll();
    }


    public void addRoleToUser(String email,String roleName){
        users user=usersRepository.findusersByEmail(email);
        Role role=roleRepo.findByName(roleName);
        if(!user.getRoles().contains(role)){
            user.getRoles().add(role);
            usersRepository.save(user);
        }
    }

    public void suspendUser(String email){
        users  user = usersRepository.findusersByEmail(email);
        if(user.isActive()==false){
            user.setActive(true);
        }
        else{
            user.setActive(false);
        }
        usersRepository.save(user);
    }

    public users addNewUser(users user) {
        users user1 = usersRepository.findusersByEmail(user.getUsername());

        if(user1!=null){
            throw new IllegalStateException("email is taken");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(null);
            user.setSeller(false);
            user.setActive(false);
            user.setAdmin(false);
            return usersRepository.save(user);
        }
    }

    public void editUserForAdmins(users user){
        usersRepository.save(user);
    }


}
