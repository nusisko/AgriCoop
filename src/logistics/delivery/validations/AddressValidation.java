package logistics.delivery.validations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class AddressValidation {

    /**
     * Private constructor to prevent instantiation
     */
    private AddressValidation() {
        throw new AssertionError("AddressValidation class should not be instantiated.");
    }

    public static List<Double> searchForCoordinates(String province, String city) {
        List<Double> addressCoordinates = new Vector<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/listado-longitud-latitud-municipios-espana.csv"))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String csvProvince = parts[0];
                    String csvCity = parts[1];
                    double latitude = Double.parseDouble(parts[2]);
                    double longitude = Double.parseDouble(parts[3]);
                    double altitude = Double.parseDouble(parts[4]);


                    if (csvProvince.equals(province) && csvCity.equals(city)) {
                        System.out.println("Found Address: " + csvCity + ", " + csvProvince + " - Lat: " + latitude + ", Long: " + longitude);
                        addressCoordinates.add(latitude);
                        addressCoordinates.add(longitude);
                        addressCoordinates.add(altitude);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressCoordinates;
    }

    public static double calculateDistance(Double latitudeOrigin, Double longitudeOrigin, Double altitudeOrigin, Double latitudeDestination, Double longitudeDestination, Double altitudeDestination) {
        double RADIUS_EARTH = 6369.174461501855; // Earth radius in kilometers (at latitude 40 ~ Spain)

        double latA = Math.toRadians(latitudeOrigin);
        double latB = Math.toRadians(latitudeDestination);
        double lonA = Math.toRadians(longitudeOrigin);
        double lonB = Math.toRadians(longitudeDestination);

        double deltaLat = latB - latA;
        double deltaLon = lonB - lonA;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(latA) * Math.cos(latB)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double centralAngle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        Double greatCircleDistance = RADIUS_EARTH * centralAngle;

        return Math.sqrt(Math.pow((altitudeDestination - altitudeOrigin)/1000, 2) + Math.pow(greatCircleDistance, 2));

    }


}
