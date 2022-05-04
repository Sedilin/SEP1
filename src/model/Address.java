package model;

public class Address {
    private String postcode;
    private String city;
    private String country;

    public Address(String postcode, String city, String country)
    {
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String toString()
    {
        return postcode + " " + city + " " + country;
    }
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
