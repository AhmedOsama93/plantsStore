package onlineStore.plantsStore.cart;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class cartIdentity implements Serializable {
    private   long userId;
    private long productID;

    public cartIdentity() {
    }

    public cartIdentity(long userId, long productID) {
        this.userId = userId;
        this.productID = productID;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            if(obj == null || obj.getClass()!= this.getClass())
                return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        cartIdentity p = (cartIdentity) obj;
        return (p.userId == this.userId && p.productID == this.productID);
    }
    @Override
    public int hashCode()
    {
        return (int) this.userId;
    }
}
