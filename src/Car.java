import java.math.BigDecimal;

public class Car extends Vehicle {
    private int safetyRating;

    public Car(String brand, String model, BigDecimal value, int safetyRating) {
        super(brand, model, value, new BigDecimal("20"), new BigDecimal("0.01"), new BigDecimal("5"));
        this.safetyRating = safetyRating;
    }

    public int getSafetyRating() {
        return safetyRating;
    }
}
