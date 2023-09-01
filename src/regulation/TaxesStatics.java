package regulation;

import billing.Bill;

/**
 * Class that represents the fiscal statics values of the cooperative
 *
 * @version 1.0.0
 * @see Bill
 */
public class TaxesStatics {

    /**
     * Standard rate of IVA - (Impuesto de Valor Añadido)
     */
    private static float ivaRate = .1f;
    /**
     * Tax applied to distributors
     */
    private static float taxDistributors = .5f;
    /**
     * Tax applied to final customers
     */
    private static float taxFinalCustomer = .15f;
    /**
     * Fiscal name of the cooperative
     */
    private static String fiscalCooperativeName = "Cooperativa Agraria S.A.";

    /**
     * Private constructor to prevent instantiation
     *
     * @throws AssertionError when called
     */
    private TaxesStatics() {
        throw new AssertionError("TaxesStatics" + " class should not be instantiated.");
    }

    /**
     * Returns the fiscal name of the cooperative
     *
     * @return the fiscal name of the cooperative
     */
    public static String getFiscalCooperativeName() {
        return fiscalCooperativeName;
    }

    /**
     * Updates the fiscal name of the cooperative
     *
     * @param newFiscalName the new fiscal name of the cooperative
     * @throws IllegalArgumentException if the new fiscal name is empty
     */
    public static void setFiscalCooperativeName(String newFiscalName) {
        if (newFiscalName.isEmpty()) {
            throw new IllegalArgumentException("Invalid name: " + newFiscalName + "\n Do not enter an empty fiscal name.");
        }
        fiscalCooperativeName = newFiscalName;
    }

    /**
     * Returns the standard rate of IVA
     *
     * @return the standard rate of IVA
     */
    public static float getIvaRate() {
        return ivaRate;
    }

    /**
     * Updates the standard rate of IVA
     *
     * @param newIvaRate the new standard rate of IVA
     * @throws IllegalArgumentException if the new IVA rate is not between 0 and 1
     */
    public static void setIvaRate(float newIvaRate) {
        if (0 > newIvaRate || newIvaRate > 1) {
            throw new IllegalArgumentException("Invalid IVA rate: " + newIvaRate + "\n Has to represent a percentage between 0 and 1.");
        }
        ivaRate = newIvaRate;
    }

    /**
     * Returns the tax applied to distributors
     *
     * @return tax to  distributors
     */
    public static float getTaxDistributors() {
        return taxDistributors;
    }

    /**
     * Updates the tax applied to distributors
     *
     * @param newTaxDistributors the tax applied to distributors
     * @throws IllegalArgumentException if the new tax is not between 0 and 1
     */
    public static void setTaxDistributors(float newTaxDistributors) {
        if (0 > newTaxDistributors || newTaxDistributors > 1) {
            throw new IllegalArgumentException("Invalid tax rate: " + newTaxDistributors + "\n Has to represent a percentage between 0 and 1.");
        }
        taxDistributors = newTaxDistributors;
    }

    /**
     * Returns the tax applied to customers
     *
     * @return tax to customers
     */
    public static float getTaxFinalCustomer() {
        return taxFinalCustomer;
    }

    /**
     * Updates the tax applied to final customers
     *
     * @param newTaxFinalCustomers the tax applied to final customers
     * @throws IllegalArgumentException if the new tax is not between 0 and 1
     */
    public static void setTaxFinalCustomer(float newTaxFinalCustomers) {
        if (0 > newTaxFinalCustomers || newTaxFinalCustomers > 1) {
            throw new IllegalArgumentException("Invalid tax rate: " + newTaxFinalCustomers + "\n Has to represent a percentage between 0 and 1.");
        }
        taxFinalCustomer = newTaxFinalCustomers;
    }
}
