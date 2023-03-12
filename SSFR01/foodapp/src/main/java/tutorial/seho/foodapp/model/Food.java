package tutorial.seho.foodapp.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Food {

    @NotNull(message = "Please include a food name!") //note no semicolon
    @NotBlank(message = "Please include a food name!")
    @Size(min = 2, message = "Name must be at least 3 characters long")
    private String name;

    @NotNull(message = "Please include a price!")
    @DecimalMin(value = "0.01" , message =  "Price must be more than 0!")
    private BigDecimal price;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Food(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
    
    public Food() {
    }
    
    @Override
    public String toString() {
        return "Food [name=" + name + ", price=" + price + "]";
    }

}
