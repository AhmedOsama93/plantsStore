package onlineStore.plantsStore.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import onlineStore.plantsStore.Role.Role;
import onlineStore.plantsStore.SendEmail.sendVerifyCode;
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
    public int getUsersCount(){
        return  usersRepository.findAll().size();
    }

    public boolean isAdmin(String username){
        users u=usersRepository.findusersByEmail(username);
        if(u.isAdmin()){
            return true;
        }
        return false;
    }
    public boolean isUser(String username){
        users u=usersRepository.findusersByEmail(username);
        if(u.getActive()==1){
            return true;
        }
        return false;
    }
    public void addRoleToUser(String email,String roleName){
        users user=usersRepository.findusersByEmail(email);
        if(roleName.equals("ROLE_ADMIN")){
            user.setAdmin(true);
            user.setActive(1);
        }
        else if (roleName.equals("ROLE_USER"))
            user.setActive(1);
        Role role=roleRepo.findByName(roleName);
        if(!user.getRoles().contains(role)){
                user.setActive(1);
            user.getRoles().add(role);
            usersRepository.save(user);
        }
    }

    public void suspendUser(String email){
        users  user = usersRepository.findusersByEmail(email);
        Role r = roleRepo.findByName("ROLE_USER");
        if(user.getActive()==1){
            user.setActive(2);
            user.getRoles().remove(r);
        }
//        else if(user.getActive()==2){
//            user.setActive(1);
//            user.getRoles().add(r);
//        }
        usersRepository.save(user);
    }
    public void activateUser(String email){
        users  user = usersRepository.findusersByEmail(email);
        Role r = roleRepo.findByName("ROLE_USER");
        if(user.getActive()==2||user.getActive()==0){
            user.setActive(1);
            user.getRoles().add(r);
        }
        usersRepository.save(user);
    }
    public users getUserData(String username){
        return usersRepository.findusersByEmail(username);
    }
    public boolean isEmailTaken(String username){
        if(usersRepository.findusersByEmail(username)!=null)
            return true;
        return false;
    }
    public users addNewUser(users user) {
        users user1 = usersRepository.findusersByEmail(user.getUsername());

        if(user1!=null){
            if (user1.getActive()!=0){
                throw new IllegalStateException("email is suspended");
            }
            throw new IllegalStateException("email is taken");
        }else {
            sendVerifyCode vc = new sendVerifyCode();
            String verifyCode = vc.generateVerifyCode();
            vc.sendCode(user.getUsername(),verifyCode);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(null);
            user.setSeller(false);
            user.setActive(0);
            user.setAdmin(false);
            user.setVerifyCode(verifyCode);
            return usersRepository.save(user);

        }
    }
    public users addNewUserForAdmin(users user) {
        users user1 = usersRepository.findusersByEmail(user.getUsername());

        if(user1!=null){
            if (user1.getActive()!=0){
                throw new IllegalStateException("email is suspended");
            }
            throw new IllegalStateException("email is taken");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(null);
            user.setSeller(false);
            user.setActive(1);
            user.setAdmin(false);
            return usersRepository.save(user);
        }
    }
    public void forgetPasswordRequest(String email){
        users user1 = usersRepository.findusersByEmail(email);
        if(user1==null){
            throw new IllegalStateException("no such user");
        }
        sendVerifyCode vc = new sendVerifyCode();
        String verifyCode = vc.generateVerifyCode();
        vc.sendCode(email,verifyCode);
        user1.setVerifyCodePassword(verifyCode);
    }

    public void changeForgetPassword(String code,String newPass1,String newPass2){
        try {
            users user1 = usersRepository.findusersByVCP(code);
            if(user1!=null && user1.getVerifyCodePassword().equals(code) && newPass1.equals(newPass2)){
                user1.setPassword(passwordEncoder.encode(newPass1));
                user1.setVerifyCodePassword(null);
                usersRepository.save(user1);
            }
            else {
                throw new IllegalStateException("wrong code");
            }
        }
        catch (Exception e){
          //  throw new IllegalStateException(e.getMessage());
        }
    }
    public void editUserForUser(String username,users user){
        users user1 = usersRepository.findusersByEmail(username);
        if(user.getfName()!=null){
            user1.setfName(user.getfName());
        }
        if(user.getlName()!=null){
            user1.setlName(user.getlName());
        }
        if(user.getPhoneNo()!=null){
            user1.setPhoneNo(user.getPhoneNo());
        }
        if(user.getAddress1()!=null){
            user1.setAddress1(user.getAddress1());
        }
        if(user.getAddress2()!=null){
            user1.setAddress2(user.getAddress2());
        }
        if(user.getCity()!=null){
            user1.setCity(user.getCity());
        }
    }
    public void verifyCode(String code){
        users user1 = usersRepository.findusersByVC(code);
        if(user1!=null&&user1.getVerifyCode().equals(code)){
            user1.setActive(1);
            user1.setVerifyCode(null);
            Role r = roleRepo.findByName("ROLE_USER");
            user1.getRoles().add(r);
            usersRepository.save(user1);
        }else {
            throw new IllegalStateException("Wrong Verify Code");
        }
    }

    public void editUserForAdmins(users user){
        usersRepository.save(user);
    }


}
