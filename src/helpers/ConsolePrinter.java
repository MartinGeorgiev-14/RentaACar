package helpers;

public class ConsolePrinter extends Printer{

    public ConsolePrinter() {
    }

    @Override
    public void print(String string) {
        System.out.println(string);
    }
}
