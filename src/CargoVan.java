import java.math.BigDecimal;

public class CargoVan extends Vehicle{
    public CargoVan(String brand, String model, BigDecimal value) {
        super(brand, model, value, new BigDecimal("50"), new BigDecimal("0.03"), new BigDecimal("10"));
    }
}
