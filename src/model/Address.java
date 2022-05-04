package model;
/**
 * A class containing Address information.
 * @author Lukasz
 * @version 1.0
 */
public class Address {
    private String postcode;
    private String city;
    private String country;

    /**
     * 3 argument constructor initializing the Address.
     * @param postcode the postcode.
     * @param city the city.
     * @param country the country.
     */
    public Address(String postcode, String city, String country)
    {
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    /**
     * Gets a country from the Address class.
     * @return the country from the Address class.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets a city from the Address class.
     * @return city from the Address class.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets a postcode from the Address class.
     * @return postcode from the Address class.
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets a country to the Address.
     * @param country the country to set the object to.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets a city to the Address.
     * @param city the city to set the object to.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets a postcode to the Address.
     * @param postcode the postcode to set the object to.
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * A toString method to print out each of the elements from the Address class.
     * @return all objects from the Address.
     */
    public String toString()
    {
        return postcode + " " + city + " " + country;
    }

    /**
     * Compares the city, postcode and country of the object of two Addresses.
     * @param obj object to be compared.
     * @return true if the two objects are equal and false otherwise.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Address other))
        {
            return false;
        }
        else
        {
            return postcode.equals(other.postcode) && city.equals(other.city) && country.equals(other.country);
        }
    }
}
