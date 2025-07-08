package value_objects;

import java.util.Objects;

public class Address {

    private String country;
    private String city;
    private String street;
    private String buildingNumber;

    public Address(String country,
                   String city,
                   String street,
                   String buildingNumber)
    {
        this.country = country;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(buildingNumber, address.buildingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, buildingNumber);
    }

    @Override
    public String toString() {
        return  this.buildingNumber + ' ' + this.street + ", " + this.city + ", " + this.country;
    }
}
