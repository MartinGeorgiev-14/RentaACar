import java.math.BigDecimal;

public abstract class Vehicle {
    private String brand;
    private String model;
    private BigDecimal value;
    private BigDecimal dailyCost;
    private BigDecimal insurancePercent;
    private BigDecimal discountDaily;

    public Vehicle(String brand, String model,  BigDecimal value, BigDecimal dailyCost, BigDecimal insurancePercent, BigDecimal discountDaily) {
        this.brand = brand;
        this.dailyCost = dailyCost;
        this.insurancePercent = insurancePercent;
        this.model = model;
        this.value = value;
        this.discountDaily = discountDaily;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public BigDecimal getInsurancePercent() {
        return insurancePercent;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getDiscountDaily() {
        return discountDaily;
    }
}

