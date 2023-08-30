package billing;

import production.models.Product;
import regulation.CooperativeStatics;
import regulation.TaxesStatics;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {

    private static int count = 0;
    private final int billNumber;
    private final Date date;

    private final String sender;
    private final String receiver;

    private final Product product;
    private final float productPrice;
    private final float productQuantity;


    private final float amountBase;
    private final float amountIVA;
    private final float amountTotal;

    private final String description;

    public Bill(String receiver, Product product, float amount, String description) {
        this.billNumber = count++;
        this.date = new Date();
        this.sender = TaxesStatics.getFiscalCooperativeName();
        this.receiver = receiver;
        this.product = new Product(product);
        this.productPrice = product.getPrice();
        this.productQuantity = amount;
        this.amountBase = amount * productPrice;
        this.amountIVA = amount * TaxesStatics.getIvaRate();
        this.amountTotal = amountBase + amountIVA;
        this.description = description;
    }


    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sb = "*************************************************\n" +
                "**************************************************\n" +
                "                       INVOICE\n" +
                "**************************************************\n" +
                "  Bill Number: " + billNumber + "\n" +
                "  Date: " + dateFormat.format(date) + "\n" +
                "  Sender: " + sender + "\n" +
                "  Receiver: " + receiver + "\n" +
                "**************************************************\n" +
                "  Product: " + product.getName() + "\n" +
                String.format("%-25s %15.2f %s\n", "  Product price:", productPrice, " €/Kg") +
                String.format("%-25s %15.2f %s\n", "  Product quantity:", productQuantity, " Kg") +
                "**************************************************\n" +
                String.format("%-25s %15.2f %s\n", "  Amount Base:", amountBase, " €") +
                String.format("%-25s %15.2f %s\n", "  Amount IVA:", amountIVA, " €") +
                String.format("%-25s %15.2f %s\n", "  Amount Total:", amountTotal, " €") +
                "**************************************************\n" +
                "  Description:\n  " + description + "\n" +
                "**************************************************\n" +
                "**************************************************\n";

        return sb;
    }
}
