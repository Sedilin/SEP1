package model;

import java.io.Serializable;

/**
 * A class containing Guest information.
 * @author Lukasz
 * @version 1.0
 */
public class Guest implements Serializable
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String id;
    private String title;
    private String nationality;
    private boolean frequentGuest;
    private Address address;
    private Date birthday;

    /**
     * 3 argument constructor initializing the Guest variables.
     * @param firstName the first name.
     * @param lastName the last name.
     * @param phoneNumber the phone number.
     */
    public Guest(String firstName, String lastName, String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        id = null;
        title = null;
        nationality = null;
        frequentGuest = false;
        address = null;
        birthday = null;
    }

    /**
     * Gets a first name from the Guest class.
     * @return the first name from the Guest class.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name to the Guest.
     * @param firstName the first name to set the object to.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Gets a last name from the Guest class.
     * @return the last name from the Guest class.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the last name to the Guest.
     * @param lastName the last name to set the object to.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Gets a phone number from the Guest class.
     * @return the phone number from the Guest class.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Sets the phone number to the Guest.
     * @param phoneNumber the phone number to set the object to.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Gets an id from the Guest class.
     * @return the id from the Guest class.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the id to the Guest.
     * @param id the id to set the object to.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Gets a title from the Guest class.
     * @return the title from the Guest class.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title to the Guest.
     * @param title the id to set the object to.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Gets a nationality from the Guest class.
     * @return the nationality from the Guest class.
     */
    public String getNationality() {
        return nationality;
    }
    /**
     * Sets the nationality to the Guest.
     * @param nationality the nationality to set the object to.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    /**
     * Gets an information is guest a frequent visitor from the Guest class.
     * @return the information if the guest is frequent from the Guest class.
     */
    public boolean isFrequentGuest() {
        return frequentGuest;
    }
    /**
     * Sets the information if the guest is frequent to the Guest.
     * @param frequentGuest the information if the guest is frequent to set the object.
     */
    public void setFrequentGuest(boolean frequentGuest) {
        this.frequentGuest = frequentGuest;
    }
    /**
     * Gets an address from the Guest class.
     * @return the address from the Guest class.
     */
    public Address getAddress() {
        return address;
    }
    /**
     * Sets the address to the Guest.
     * @param address the nationality to set the object to.
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    /**
     * Gets a birthday from the Guest class.
     * @return the birthday from the Guest class.
     */
    public Date getBirthday() {
        return birthday.copy();
    }
    /**
     * Sets the birthday to the Guest.
     * @param birthday the nationality to set the object to.
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday.copy();
    }
    /**
     * A toString method to print out each of the elements from the Guest class.
     * @return all objects from the Guest.
     */
    public String toString()
    {
        return firstName + " " + lastName + " " + phoneNumber + " " + id + " " + address + " " +birthday;
    }
    /**
     * Compares all the variables of the object of two Guests.
     * @param obj object to be compared.
     * @return true if the two objects are equal and false otherwise.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Guest other))
        {
            return false;
        }
        else
        {
            return firstName.equals(other.firstName) && lastName.equals(other.lastName) &&
                    phoneNumber.equals(other.phoneNumber);
        }
    }
}
