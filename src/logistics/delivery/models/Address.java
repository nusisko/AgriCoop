package logistics.delivery.models;

public class Address {
    private String community;
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


        this.latitude = 0;
    }
}