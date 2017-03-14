package model;

/**
 * Created by SergLion on 06.03.2017.
 */
public class Adress extends Entity<Integer> {


    private String town;
    private String street;
    private String house;
    private int flat;

    public Adress() {
    }

    public Adress(String town, String street, String house, int flat) {
        this.town = town;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat=" + flat +
                "} " + super.toString();
    }
}
