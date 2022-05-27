package onlineStore.plantsStore.users;
import lombok.Data;
import onlineStore.plantsStore.Role.Role;
import onlineStore.plantsStore.products.product;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@Table
public class users implements Serializable {

    @SequenceGenerator(
            name="user_sequence",
            sequenceName="user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "user_sequence"
    )

    private long id;
    private String fName;
    private String lName;
    @Id
    private String username;
    private String password;
    @ManyToMany
    private Set<product> cart;
    private String verifyCode;
    private String phoneNo;
    private String address1;
    private String address2;
    private String city;
    private boolean completeContactInfo = false;


    public String getVerifyCode() {
        return verifyCode;
    }

    public boolean isContactInfoComplete(){
        if(phoneNo!=null&&address1!=null&&city!=null){
            completeContactInfo=true;
            return true;
        }
        completeContactInfo = false;
        return false;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @ManyToMany(fetch =EAGER)
    private Collection<Role> roles=new ArrayList<>();

    private boolean isAdmin=false;
    private boolean isSeller=false;
    private int Active = 0;
    //0->new user not active(cuz verify code)
    //1->active
    //2-> susbended (by admin)
    private int numOfOrders=0;

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            if(obj == null || obj.getClass()!= this.getClass())
                return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        users p = (users) obj;
        return (p.id == this.id);
    }
    @Override
    public int hashCode()
    {
        return (int) this.id;
    }

    public users() {
        
    }
    public int NewOrderCount(){
        numOfOrders++;
        return numOfOrders;
    }
    public long getId() {
        return id;
    }

    public void setActive(int active) {
        Active = active;
    }

    public int getActive() {
        return Active;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<product> getCart() {
        return cart;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public int getNumOfOrders() {
        return numOfOrders;
    }

    public void setCart(Set<product> cart) {
        this.cart = cart;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setNumOfOrders(int numOfOrders) {
        this.numOfOrders = numOfOrders;
    }


    public users(String fName, String lName, String username, String password) {
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
    }

    public users(long id, String fName, String lName, String username, String password, boolean isAdmin, int Active) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.Active = Active;
    }

    @Override
    public String toString() {
        return "users{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isActive=" + Active +
                '}';
    }
}
