import helpers.BigDecimalCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Invoice {
    private Customer customer;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
    private int rentDays;
    private int usedDays;
    private int remainingDays;
    private BigDecimal rentalCost;
    private BigDecimal insuranceCost;
    private BigDecimal rentalCostPerDay;
    private BigDecimal earlyReturnRentCost;
    private BigDecimal insurancePerDay;
    private BigDecimal initialInsurancePerDay;
    private BigDecimal insuranceDiscountPerDay;
    private BigDecimal insuranceIncreasePerDay;
    private BigDecimal earlyReturnInsuranceCost;
    private BigDecimal total;

    private BigDecimalCalculator bdCalculator;

    public Invoice(Customer customer, LocalDate startDate, LocalDate endDate, LocalDate returnDate,  Vehicle vehicle, BigDecimalCalculator bdCalculator) {
        this.customer = customer;
        this.endDate = endDate;
        this.returnDate = returnDate;
        this.startDate = startDate;
        this.vehicle = vehicle;
        this.bdCalculator = bdCalculator;
        this.rentalCostPerDay = new BigDecimal("0");
        this.earlyReturnRentCost = new BigDecimal("0");
        this.insurancePerDay = new BigDecimal("0");
        this.initialInsurancePerDay = new BigDecimal("0");
        this.insuranceDiscountPerDay = new BigDecimal("0");
        this.insuranceIncreasePerDay = new BigDecimal("0");
        this.earlyReturnInsuranceCost = new BigDecimal("0");
        this.total = new BigDecimal("0");
    }

    private void calcRentDays(){
        this.rentDays = (int) ChronoUnit.DAYS.between(this.startDate, this.endDate);
    }

    private void calcUsedDays() { this.usedDays = (int) ChronoUnit.DAYS.between(this.startDate, this.returnDate); }

    private void calcRemainingDays() {this.remainingDays = this.rentDays - this.usedDays;}

    private void calcTotal() { this.total = bdCalculator.add(this.rentalCost, this.insuranceCost);}

    private void calcNormalRentalCost(){
        if(this.rentDays > 7){

            BigDecimal dailyCost = bdCalculator.subtractByBigDecimal(vehicle.getDailyCost(), vehicle.getDiscountDaily());
            BigDecimal discountedCost = bdCalculator.divisionByBigDecimal(dailyCost, new BigDecimal("2"));
            BigDecimal totalDiscount = bdCalculator.multiplyByInt(discountedCost, this.remainingDays);

            this.rentalCost = bdCalculator.multiplyByInt(dailyCost, this.usedDays);
            this.rentalCost = this.rentalCost.add(totalDiscount);
            this.rentalCostPerDay = dailyCost;
            this.earlyReturnRentCost = totalDiscount;
        }
        else{
            this.rentalCost = bdCalculator.multiplyByInt(vehicle.getDailyCost(), this.usedDays);
            this.rentalCost = this.rentalCost.add(bdCalculator.multiplyByInt(vehicle.getDailyCost(), this.remainingDays));
            this.rentalCostPerDay = vehicle.getDailyCost();
        }
    }

    private void calcInsuranceCostOfVehicle(){
    this.initialInsurancePerDay = bdCalculator.percentageOf(vehicle.getValue(), vehicle.getInsurancePercent());
    this.insurancePerDay = this.initialInsurancePerDay;
    this.earlyReturnInsuranceCost = bdCalculator.multiplyByInt(this.insurancePerDay, this.remainingDays);
    this.insuranceCost = bdCalculator.multiplyByInt(insurancePerDay, this.usedDays);

        if(vehicle instanceof Car){
            Car car = (Car) vehicle;

            if(car.getSafetyRating() >= 4){
                BigDecimal discounted = bdCalculator.findValueByPercent(this.initialInsurancePerDay, new BigDecimal("0.15"));
                this.insuranceDiscountPerDay = discounted;
                this.insurancePerDay = this.initialInsurancePerDay.subtract(discounted);

                this.earlyReturnInsuranceCost = bdCalculator.multiplyByInt(this.insurancePerDay, this.remainingDays);
                this.insuranceCost = bdCalculator.multiplyByInt(insurancePerDay, this.usedDays);
            }
        }
        else if(vehicle instanceof Motorcycle){
            if(customer.getAge() < 25){
                BigDecimal increased = bdCalculator.findValueByPercent(this.initialInsurancePerDay, new BigDecimal("0.20"));
                this.insuranceIncreasePerDay = increased;
                this.insurancePerDay = this.initialInsurancePerDay.add(increased);

                this.earlyReturnInsuranceCost = bdCalculator.multiplyByInt(this.insurancePerDay, this.remainingDays);
                this.insuranceCost = bdCalculator.multiplyByInt(this.insurancePerDay, this.usedDays);
            }
        }
        else{
            this.initialInsurancePerDay = bdCalculator.percentageOf(vehicle.getValue(), new BigDecimal("0.03"));

            if(customer.getExperienceYears() > 5){
                BigDecimal discounted = bdCalculator.findValueByPercent(this.initialInsurancePerDay, new BigDecimal("0.15"));
                this.insuranceDiscountPerDay = discounted;
                this.insurancePerDay = this.initialInsurancePerDay.subtract(discounted);

                this.earlyReturnInsuranceCost = bdCalculator.multiplyByInt(this.insurancePerDay, this.remainingDays);
                this.insuranceCost = bdCalculator.multiplyByInt(insurancePerDay, this.usedDays);
            }
        }
    }

    public void calcEverything(){
        calcRentDays();
        calcUsedDays();
        calcRemainingDays();
        calcNormalRentalCost();
        calcInsuranceCostOfVehicle();
        calcTotal();
    }

    public BigDecimalCalculator getBdCalculator() {
        return bdCalculator;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getEarlyReturnInsuranceCost() {
        return earlyReturnInsuranceCost;
    }

    public BigDecimal getEarlyReturnRentCost() {
        return earlyReturnRentCost;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getInitialInsurancePerDay() {
        return initialInsurancePerDay;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public BigDecimal getInsuranceDiscountPerDay() {
        return insuranceDiscountPerDay;
    }

    public BigDecimal getInsurancePerDay() {
        return insurancePerDay;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public BigDecimal getRentalCost() {
        return rentalCost;
    }

    public BigDecimal getRentalCostPerDay() {
        return rentalCostPerDay;
    }

    public int getRentDays() {
        return rentDays;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getUsedDays() {
        return usedDays;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getInsuranceIncreasePerDay() {
        return insuranceIncreasePerDay;
    }
}
