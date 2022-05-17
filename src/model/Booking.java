package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing for the booking object.
 * @author Gabriela and Lukasz
 * @version 1.4
 * */
public class Booking implements Serializable
{
    private double totalPrice;
    private GuestList guestList;
    private Room room;
    private Date arrivalDate;
    private Date departureDate;
    private boolean isCheckedIn;
    private boolean isCheckedOut;

    //Constructor

    /**
     * 3 parameter constructor initializing the current booking.
     * @param room the room.
     * @param arrivalDate the arrival date.
     * @param departureDate the departure date.
     */
    public Booking (Room room, Date arrivalDate, Date departureDate)
    {
        guestList = new GuestList();
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.room = room;
        totalPrice = room.getPrice();
        isCheckedIn = false;
        isCheckedOut = false;
    }
    public String getGuestName() {
        return guestList.getMainGuest().getFirstName() + " " +  guestList.getMainGuest().getLastName();
    }
    public String getPhoneNumber() {
        return guestList.getMainGuest().getPhoneNumber();
    }
    public int getRoomNumber() {
        return room.getRoomNumber();
    }

    //Guest
    /**
     * Adds a guest to the guest list in the Booking.
     * @param guest the guest.
     */
    public void addGuest(Guest guest)
    {
        guestList.addGuest(guest);
    }
    /**
     * Gets the guest from the guest list in the booking.
     * @return guest from guest list.
     */
    public Guest getMainGuestForBooking()
    {
        return guestList.getMainGuest();
    }

    public GuestList getAllGuests()
    {
        return guestList;
    }
    //Arrival date
    /**
     * Sets an arrival date in the Booking.
     * @param arrivalDate the arrival date.
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Gets the arrival date of the Booking.
     * @return the arrival date.
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    //Departure date
    /**
     * Sets the departure date of the Booking.
     * @param departureDate the departure date.
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Gets the departure date from the Booking.
     * @return the departure date from the Booking.
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    //Room
    /**
     * Changes a value of the Room in the Booking
     * @param room the new room.
     */
    public void changeRoom(Room room)
    {
        this.room = room;
    }

    /**
     * Gets the room from the Booking.
     * @return the room from the Booking.
     */
    public Room getRoom() {
        return room;
    }

    //Check-In
    /**
     * Gets information if the guest is checked in.
     * @return the information if the guest is checked in.
     */
    public boolean isCheckedIn()
    {
        return isCheckedIn;
    }

    /**
     * Sets the information if the guest is checked in to true.
     */
    public void setCheckedIn()
    {
        isCheckedIn = true;
    }

    //Check-Out
    /**
     * Gets information if the guest is checked out.
     * @return the information if the guest is checked out.
     */
    public boolean isCheckedOut()
    {
        return isCheckedOut;
    }

    /**
     * Sets the information if the guest is checked out to true.
     */
    public void setCheckedOut()
    {
        isCheckedOut = true;
    }

    //Price
    /**
     * Applies the discount to the total price for the Booking.
     * @param discount the discount.
     */
    public void applyDiscount(double discount)
    {
        totalPrice -= totalPrice * (discount/100);
    }

    /**
     * Gets the total price of the Booking.
     * @return the total price of the Booking.
     */
    public double getTotalPrice()
    {
        return totalPrice * arrivalDate.daysInBetween(departureDate);
    }

    /**
     * A toString method to print out each of the elements from the Booking class.
     * @return all objects from the Booking.
     */
    public String toString()
    {
        return guestList.toString() +" "+room.getRoomNumber() + " " + arrivalDate + " " + departureDate + " " + isCheckedIn + " " + isCheckedOut;
    }

    /**
     * Compares all the variables of the object of two Bookings.
     * @param obj object to be compared.
     * @return true if the two objects are equal and false otherwise.
     */
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Booking other))
        {
            return false;
        }
        return guestList.equals(other.guestList) && room.equals(other.room) && arrivalDate.equals(other.arrivalDate)
               && departureDate.equals(other.departureDate) && isCheckedOut == other.isCheckedOut && isCheckedIn == other.isCheckedIn;
    }
}
