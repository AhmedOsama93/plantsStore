package onlineStore.plantsStore.products;

import javax.persistence.*;

@Entity
@Table
public class product {
    @Id
    @SequenceGenerator(
            name="product_sequence",
            sequenceName="product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "product_sequence"
    )
    private long id;
    private String name;
    private String category;
    private int quantityAvailable=0;
    private double price;
    private boolean active = false;

    public product() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public double getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public product(long id, String name, String category, int quantityAvailable, double price, boolean active) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.active = active;
    }

    @Override
    public String toString() {
        return "product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", quantityAvailable=" + quantityAvailable +
                ", price=" + price +
                ", active=" + active +
                '}';
    }
}
