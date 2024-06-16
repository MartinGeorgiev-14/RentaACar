import java.math.BigDecimal;
import helpers.Printer;

public class ConsoleInvoicePrinter extends InvoicePrinter {

    protected Printer cp;

    public ConsoleInvoicePrinter(Printer cp) {
        this.cp = cp;
    }

    @Override
    void printInvoice(Invoice invoice) {
        StringBuilder sb = new StringBuilder();

        appendStartEnd(sb);
        appendLine(sb, "Date", invoice.getStartDate());
        appendLine(sb, "Customer name", invoice.getCustomer().getFirstName() + " " + invoice.getCustomer().getLastName());
        appendLine(sb, "Rented Vehicle", invoice.getVehicle().getBrand() + " " + invoice.getVehicle().getModel());
        appendEmptyRow(sb);
        appendLine(sb, "Reservation start date", invoice.getStartDate());
        appendLine(sb, "Reservation end date", invoice.getEndDate());
        appendLine(sb, "Reserved rental days", invoice.getRentDays() + " days");
        appendEmptyRow(sb);
        appendLine(sb, "Actual return date", invoice.getReturnDate());
        appendLine(sb, "Actual rental days", invoice.getUsedDays() + " days");
        appendEmptyRow(sb);
        appendMoneyLine(sb, "Rental cost per day", invoice.getRentalCostPerDay());
        if((invoice.getInsuranceDiscountPerDay().compareTo(BigDecimal.ZERO) > 0) || (invoice.getInsuranceIncreasePerDay().compareTo(BigDecimal.ZERO) > 0)){
            appendMoneyLine(sb, "Initial insurance per day", invoice.getInitialInsurancePerDay());
        }
        appendMoneyLine(sb, "Insurance discount per day", invoice.getInsuranceDiscountPerDay());
        appendMoneyLine(sb, "Insurance increase per day", invoice.getInsuranceIncreasePerDay());
        appendMoneyLine(sb, "Insurance per day", invoice.getInsurancePerDay());
        appendEmptyRow(sb);
        appendMoneyLine(sb, "Early return discount for rent", invoice.getEarlyReturnRentCost());
        appendMoneyLine(sb, "Early return discount for insurance", invoice.getEarlyReturnInsuranceCost());
        if((invoice.getEarlyReturnRentCost().compareTo(BigDecimal.ZERO) > 0) || (invoice.getEarlyReturnInsuranceCost().compareTo(BigDecimal.ZERO) > 0)){
          appendEmptyRow(sb);
        }
        appendMoneyLine(sb, "Total rent", invoice.getRentalCost());
        appendMoneyLine(sb, "Total insurance", invoice.getInsuranceCost());
        appendMoneyLine(sb, "Total", invoice.getTotal());
        appendStartEnd(sb);

        cp.print(sb.toString());
    }

    private void appendLine(StringBuilder sb, String label, Object value){
        if(value != null){
            sb.append(label).append(": ").append(value).append("\n");
        }
    }

    private void appendMoneyLine(StringBuilder sb, String label, BigDecimal value){
        if(value != null && value.compareTo(BigDecimal.ZERO) > 0){
            sb.append(label).append(": $").append(value).append("\n");
        }
    }

    private void appendStartEnd(StringBuilder sb){
        sb.append("XXXXXXXXX").append("\n");
    }

    private void appendEmptyRow(StringBuilder sb){
        sb.append("\n");
    }
}
