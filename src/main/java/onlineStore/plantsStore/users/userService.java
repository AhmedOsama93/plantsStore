package onlineStore.plantsStore.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userService {

    private final usersRepository usersRepository;

    @Autowired
    public userService(onlineStore.plantsStore.users.usersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<users> getUsers(){
        return  usersRepository.findAll();
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

    public void addNewUser(users user) {
        users user1 = usersRepository.findusersByEmail(user.getEmail());

        if(user1!=null){
            throw new IllegalStateException("email is taken");
        }else {
            usersRepository.save(user);
        }
    }
}
