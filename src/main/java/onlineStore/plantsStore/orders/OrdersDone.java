package onlineStore.plantsStore.orders;

import lombok.Data;

import java.time.LocalDateTime;

@Data
class OrdersDone {
    String Pname;
    String fname;
    LocalDateTime orderDate;
    long quantity;
    double price;
}
