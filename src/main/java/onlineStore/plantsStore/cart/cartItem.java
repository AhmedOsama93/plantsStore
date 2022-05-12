package onlineStore.plantsStore.cart;

public class cartItem {
    public long productID;
    public int quantity;

    public cartItem() {
    }

    public cartItem(long productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }
}
