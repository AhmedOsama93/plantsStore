package onlineStore.plantsStore.cart;

import onlineStore.plantsStore.products.product;
import onlineStore.plantsStore.users.users;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class cart {
  @EmbeddedId
  cartIdentity id;

  int quantity;

  public cart() {
  }

  public cart(cartIdentity id, int quantity) {
    this.id = id;
    this.quantity = quantity;
  }

  public cartIdentity getId() {
    return id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setId(cartIdentity id) {
    this.id = id;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
