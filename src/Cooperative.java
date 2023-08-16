import Regulation.CooperativeStatics;

public class Cooperative {
    public static void main(String[] args) {
        CooperativeStatics.updateSmallProducerMaxExtension(1000.f);
        System.out.println(CooperativeStatics.getSmallProducerMaxExtension());
    }
    }
