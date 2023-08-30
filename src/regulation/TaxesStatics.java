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
    private static float ivaRate = .21f;
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
}
