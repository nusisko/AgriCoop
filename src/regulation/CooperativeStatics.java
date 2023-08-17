package regulation;

public class CooperativeStatics {
    private static float smallProducerMaxExtension = 5.f;

    /**
     * Private constructor to prevent instantiation
     */
    private CooperativeStatics() {
        throw new AssertionError("CooperativeConstants class should not be instantiated.");
    }

    public static void updateSmallProducerMaxExtension(float newLimit) {
        if (newLimit < 0) {
            throw new IllegalArgumentException("Invalid value: " + newLimit + "\n Enter a positive value.");
        }
        smallProducerMaxExtension = newLimit;
    }

    public static float getSmallProducerMaxExtension() {
        return smallProducerMaxExtension;
    }
}

