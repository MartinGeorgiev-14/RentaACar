package helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalCalculator {

    public BigDecimalCalculator() {
    }

    public BigDecimal subtractByBigDecimal (BigDecimal bd, BigDecimal number){
        BigDecimal result = new BigDecimal(String.valueOf(bd.subtract(number)));
        return roundUp(result, 2);
    }

    public BigDecimal multiplyByInt(BigDecimal bd, int number){
        BigDecimal result = new BigDecimal(String.valueOf(bd.multiply(BigDecimal.valueOf(number))));
        return roundUp(result, 2);
    }

    public BigDecimal multiply(BigDecimal bd, BigDecimal number){
        BigDecimal result = new BigDecimal(String.valueOf(bd.multiply(number)));
        return roundUp(result, 2);
    }

    public BigDecimal divisionByBigDecimal(BigDecimal bd, BigDecimal number){
        BigDecimal result = new BigDecimal(String.valueOf(bd.divide(number)));
        return roundUp(result, 2);
    }

    public BigDecimal subtractByPercent(BigDecimal bd, BigDecimal percent){
        BigDecimal result = bd.multiply(percent);

        return roundUp(bd.subtract(result), 2 );
    }

    public BigDecimal percentageOf(BigDecimal bd, BigDecimal percent){
        BigDecimal decimalPercentage = percent.divide(new BigDecimal("100"));
        BigDecimal result = decimalPercentage.multiply(bd);

        return roundUp(result, 2);
    }

    public BigDecimal findValueByPercent(BigDecimal bd, BigDecimal percent){
        BigDecimal result = bd.multiply(percent);
        return roundUp(result, 2);
    }

    public BigDecimal addByPercent(BigDecimal bd, BigDecimal percent){
        BigDecimal result = new BigDecimal(String.valueOf(bd.multiply(new BigDecimal(String.valueOf(percent)))));
        return roundUp(bd.add(result), 2);
    }

    public BigDecimal add(BigDecimal bd, BigDecimal number){
        BigDecimal result = bd.add(number);
        return roundUp(result, 2);
    }

    private BigDecimal roundUp(BigDecimal bd, int number){
        return bd.setScale(number, RoundingMode.HALF_UP);
    }
}
