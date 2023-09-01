package billing;

import java.util.List;

public interface IBillable {

    /**
     * Gets the collection of the farmer's bills
     * @return the collection of the farmer's bills
     */
    public List<Bill> getSales();

    /**
     * Adds a bill to the farmer's sales
     * @param bill the bill to add
     */
    public void addSale(Bill bill);
}
