import helpers.BigDecimalCalculator;
import helpers.ConsolePrinter;
import helpers.Printer;

import javax.imageio.metadata.IIOMetadataNode;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BigDecimalCalculator bdc = new BigDecimalCalculator();
        Printer cp1 = new ConsolePrinter();
        InvoicePrinter ip1 = new ConsoleInvoicePrinter(cp1);

        Vehicle c1 = new Car("Mitsubishi", "Mirage", new BigDecimal("15000"), 3);
        Customer cus1 = new Customer(20, "John", "Doe", 2);
        Invoice i1 = new Invoice(cus1, LocalDate.of(2024, 06, 3), LocalDate.of(2024, 6, 13),
                LocalDate.of(2024, 6, 13), c1, bdc);

        Vehicle c2 = new Motorcycle("Triumph", "Tiger Sport 660", new BigDecimal("10000"));
        Customer cus2 = new Customer(20, "Mary", "Jhonson", 2);
        Invoice i2 = new Invoice(cus2, LocalDate.of(2024, 6, 3), LocalDate.of(2024, 6, 13),
                LocalDate.of(2024, 06, 13), c2, bdc);

        Vehicle c3 = new CargoVan("Citroen", "Jumper", new BigDecimal("20000"));
        Customer cus3 = new Customer(30, "John", "Markson", 8);
        Invoice i3 = new Invoice(cus3, LocalDate.of(2024,6,3), LocalDate.of(2024,6,18),
                LocalDate.of(2024, 6, 13), c3, bdc);

        i1.calcEverything();
        ip1.printInvoice(i1);

        i2.calcEverything();
        ip1.printInvoice(i2);

        i3.calcEverything();
        ip1.printInvoice(i3);

    }
}