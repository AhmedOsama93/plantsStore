package onlineStore.plantsStore.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import onlineStore.plantsStore.Role.Role;
import onlineStore.plantsStore.Role.roleRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class userService {

    private final usersRepository usersRepository;
    private final onlineStore.plantsStore.Role.roleRepo roleRepo;

    public List<users> getUsers(){
        return  usersRepository.findAll();
    }

    public void addRoleToUser(String email,String roleName){
        users user=usersRepository.findusersByEmail(email);
        Role role=roleRepo.findByName(roleName);
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
        users user1 = usersRepository.findusersByEmail(user.getEmail());

        if(user1!=null){
            throw new IllegalStateException("email is taken");
        }else {
            return usersRepository.save(user);
        }
    }

    public void editUserForAdmins(users user){
        usersRepository.save(user);
    }
}
