package regulation;

public enum TaxesConstants {
    IVA(.21f);

    private final float rate;

    private TaxesConstants(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }
}
