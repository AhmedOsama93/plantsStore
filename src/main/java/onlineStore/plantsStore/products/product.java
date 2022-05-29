package onlineStore.plantsStore.products;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class product implements Comparable<product>, Serializable {
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
    private String season;
    private String soil;
    private boolean active = true;
    private String Photo;
    private boolean indoor ;
    private String description;
    private int numOrderd=0;

    public int getNumOrderd() {
        return numOrderd;
    }
    public void incNumOrderd(int num){
        numOrderd+=num;
    }
    public void setNumOrderd(int numOrderd) {
        this.numOrderd = numOrderd;
    }
    public void decquantityAvailable(int num){
        quantityAvailable-=num;
    }

    @Override
    public boolean equals(Object obj)
    {
//        if(this == obj)
//        if(obj == null || obj.getClass()!= this.getClass())
//            return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        product p = (product) obj;
        return (p.id == this.id);
    }
    @Override
    public int hashCode()
    {
        return (int) this.id;
    }

    public product(String name, String category, int quantityAvailable, double price, String season, String soil, String photo, boolean indoor, String description) {
        this.name = name;
        this.category = category;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.season = season;
        this.soil = soil;
        Photo = photo;
        this.indoor = indoor;
        this.description = description;
    }
    public product(String name, String category, int quantityAvailable, double price, String season, String soil, boolean indoor, String description) {
        this.name = name;
        this.category = category;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.season = season;
        this.soil = soil;
        this.indoor = indoor;
        this.description = description;
    }

    public product() {

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public String getPhoto() {
        return Photo;
    }

    public boolean isIndoor() {
        return indoor;
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

    public void setSeason(String season) {
        this.season = season;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getSeason() {
        return season;
    }

    public String getSoil() {
        return soil;
    }

    public product(long id, String name, String category, int quantityAvailable, double price, String season, String soil, boolean active) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.season = season;
        this.soil = soil;
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


    @Override
    public int compareTo(product o) {
        return o.getNumOrderd()-this.getNumOrderd();
    }
}
