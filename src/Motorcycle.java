import java.math.BigDecimal;

public class Motorcycle extends Vehicle{

    public Motorcycle(String brand, String model, BigDecimal value) {
        super(brand, model, value, new BigDecimal("15"), new BigDecimal("0.02"), new BigDecimal("5"));
    }
}
