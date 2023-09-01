import customer.CustomerType;
import customer.Customer;
import logistics.delivery.models.Address;
import logistics.delivery.models.TransportProvider;
import logistics.delivery.models.Tariff;
import logistics.warehouse.models.Stock;
import producer.models.BigFarmer;
import producer.models.FederatedFarmer;
import producer.models.SmallFarmer;
import production.models.Crop;
import production.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Cooperative {
    public static void main(String[] args) {

        //CREATION OF PRODUCTS
        Product betazoidZabuFruit = new Product("Fruta zabu de Betazed", 6, 0.2f, true);
        Product vulcanWheat = new Product("Trigo vulcano", 2, 0.2f, false);
        Product andorianTubers = new Product("Tubérculos andorianos", 4, 0.2f, true);
        Product rigelianKiBaratan = new Product("Ki Baratan rigeliano", 4, 0.2f, false);
        Product rigelianFleckFlowers = new Product("Flores Fleck regalianas", 2, 0.2f, true);
        Product bolianTomatoes = new Product("Tomates bolianos", 3, 0.2f, false);


        //CREATION OF CROPS
        Crop rikerCrop1 = new Crop(rigelianKiBaratan, 1f);
        Crop rikerCrop2 = new Crop(andorianTubers, 2f);
        Crop rikerCrop3 = new Crop(rigelianFleckFlowers, 2f);
        ArrayList<Crop> rikerProduction = new ArrayList<>(Arrays.asList(rikerCrop1, rikerCrop2, rikerCrop3));

        Crop deanaCrop1 = new Crop(betazoidZabuFruit, 2f);
        Crop deanaCrop2 = new Crop(rigelianKiBaratan, 1f);
        ArrayList<Crop> deanaProduction = new ArrayList<>(Arrays.asList(deanaCrop1, deanaCrop2));

        Crop spokCrop1 = new Crop(vulcanWheat, 3.5f);
        Crop spokCrop2 = new Crop(andorianTubers, 1f);
        ArrayList<Crop> spokProduction = new ArrayList<>(Arrays.asList(spokCrop1, spokCrop2));

        Crop dataCrop1 = new Crop(rigelianKiBaratan, 1f);
        Crop dataCrop2 = new Crop(bolianTomatoes, 2f);
        ArrayList<Crop> dataProduction = new ArrayList<>(Arrays.asList(dataCrop1, dataCrop2));

        Crop worfCrop1 = new Crop(andorianTubers, 1f);
        Crop worfCrop2 = new Crop(bolianTomatoes, 4f);
        ArrayList<Crop> worfProduction = new ArrayList<>(Arrays.asList(worfCrop1, worfCrop2));

        Crop fleetCrop1 = new Crop(rigelianFleckFlowers, 5.1f);
        Crop fleetCrop2 = new Crop(bolianTomatoes, 7f);
        Crop fleetCrop3 = new Crop(rigelianKiBaratan, 4f);
        Crop fleetCrop4 = new Crop(andorianTubers, 3.1f);

        ArrayList<Crop> fleetProduction = new ArrayList<Crop>(Arrays.asList(fleetCrop1, fleetCrop2, fleetCrop3, fleetCrop4));

        // CREATION OF FARMERS
        SmallFarmer data = new SmallFarmer("Data", dataProduction);
        SmallFarmer deana = new SmallFarmer("Deana", deanaProduction);
        SmallFarmer riker = new SmallFarmer("Riker", rikerProduction);
        SmallFarmer spok = new SmallFarmer("Spok", spokProduction);
        SmallFarmer worf = new SmallFarmer("Worf", worfProduction);
        BigFarmer fleet = new BigFarmer("Fleet", fleetProduction);

        // CREATION OD FEDERATED FARMER
        HashSet<SmallFarmer> andorianTubersAssociates = new HashSet<>(Arrays.asList(riker, spok, worf));
        FederatedFarmer andorianTubersFederatedFarmer = new FederatedFarmer("Andorian Tubers", andorianTubers, andorianTubersAssociates);
        andorianTubersFederatedFarmer.addCrop(rikerCrop2, riker);
        andorianTubersFederatedFarmer.addCrop(spokCrop2, spok);
        andorianTubersFederatedFarmer.addCrop(worfCrop1, worf);

        // HARVESTING CROPS

        andorianTubersFederatedFarmer.harvestToStock(worfCrop1);
        riker.harvestToStock(rikerCrop3);
        fleet.harvestToStock(fleetCrop1);
        fleet.harvestToStock(fleetCrop2);
        worf.harvestToStock(worfCrop2);
        deana.harvestToStock(deanaCrop1);

        fleet.harvestToStock(fleetCrop4);
        andorianTubersFederatedFarmer.harvestToStock(spokCrop2);
        andorianTubersFederatedFarmer.harvestToStock(rikerCrop2);

        // CREATION OF CUSTOMER
        Address testAddress = new Address("Sevilla", "Sevilla", "Calle lunares", 5, "");
        Customer customerTest = new Customer("test", testAddress, CustomerType.DISTRIBUTOR);

        // CREATION OF TRANSPORT PROVIDER
        Tariff economicTariff = new Tariff("EconomicTariff", 0.02, 0.07, CustomerType.DISTRIBUTOR);
        TransportProvider shipmentsFerengi = new TransportProvider("shipmentsFerengi", new ArrayList<Tariff>(List.of(economicTariff)));

        // PURCHASES
        customerTest.purchase(betazoidZabuFruit, 1f, shipmentsFerengi, economicTariff);
        customerTest.purchase(andorianTubers, 7f, shipmentsFerengi, economicTariff);

        // MANAGE ORDERS
        Stock.manageLastOrder();
        Stock.manageLastOrder();


        //SHOW BILLS OF (FARMERS - TRANSPORT PROVIDER - CUSTOMER)
        System.out.println(deana.getSales());
        System.out.println(fleet.getSales());
        System.out.println(andorianTubersFederatedFarmer.getSales());
        System.out.println(customerTest.getSales());
        System.out.println(shipmentsFerengi.getSales());




    }
}