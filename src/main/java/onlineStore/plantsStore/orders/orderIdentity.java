package onlineStore.plantsStore.orders;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class orderIdentity implements Serializable {
    private   long userId;
    private long productID;
    private int orderNo;
    @Override
    public boolean equals(Object obj)
    {
//        if(this == obj)
//            if(obj == null || obj.getClass()!= this.getClass())
//                return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        orderIdentity o = (orderIdentity) obj;
        return (o.userId == this.userId && o.productID == this.productID &&o.orderNo==this.orderNo);
    }
    @Override
    public int hashCode()
    {
        return (int) this.userId+(int) this.productID+(int) this.orderNo;
    }
}
