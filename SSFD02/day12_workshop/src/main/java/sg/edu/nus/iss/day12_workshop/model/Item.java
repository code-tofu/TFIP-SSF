package sg.edu.nus.iss.day12_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String itemName;
    private Integer quantity;
}
