package onlineStore.plantsStore.users;
import onlineStore.plantsStore.products.product;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
public class users implements Serializable {
    @Id
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
    private String email;
    private String password;
    @ManyToMany
    private Set<product> cart;

    private boolean isAdmin=false;
    private boolean isSeller=false;
    private boolean isActive=false;

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

    public long getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isActive() {
        return isActive;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public users(String fName, String lName, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public users(long id, String fName, String lName, String email, String password, boolean isAdmin, boolean isActive) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "users{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isActive=" + isActive +
                '}';
    }
}
