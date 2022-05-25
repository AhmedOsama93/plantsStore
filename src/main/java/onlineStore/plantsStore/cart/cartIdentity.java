package onlineStore.plantsStore.cart;

import lombok.AllArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@AllArgsConstructor
public class cartIdentity implements Serializable {
    private   String username;
    private long productID;

    public cartIdentity() {
    }


    @Override
    public boolean equals(Object obj)
    {
//        if(this == obj)
//            if(obj == null || obj.getClass()!= this.getClass())
//                return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        cartIdentity p = (cartIdentity) obj;
        return (p.username == this.username && p.productID == this.productID);
    }
    @Override
    public int hashCode()
    {
        return (int) this.username.hashCode() + (int) this.productID;
    }
}
