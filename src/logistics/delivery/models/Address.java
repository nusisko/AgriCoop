package logistics.delivery.models;

import logistics.delivery.validations.AddressValidation;

import java.util.List;

public class Address {
    private String province;
    private String city;
    private String street;
    private int number;
    private String directionExtras;
    private double latitude;
    private double longitude;
    private double altitude;

    public Address(String province, String city, String street, int number, String directionExtras) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.number = number;
        this.directionExtras = directionExtras;
        List<Double> coordinates  = AddressValidation.searchForCoordinates(province, city);
        this.latitude = coordinates.get(0);
        this.longitude = coordinates.get(1);
        this.altitude = coordinates.get(2);
        this.latitude = 0;
    }



    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDirectionExtras() {
        return directionExtras;
    }

    public void setDirectionExtras(String directionExtras) {
        this.directionExtras = directionExtras;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}