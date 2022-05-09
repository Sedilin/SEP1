package model;

import java.util.ArrayList;
/**
 * A class containing for the booking object.
 * @author Gabriela and Lukasz
 * @version 1.3
 * */
public class Booking {
    private double totalPrice;
    private boolean hasPaid;
    private GuestList guestList;
    private Room room;
    private Date arrivalDate;
    private Date departureDate;

    //Constructor

    /**
     * 5 parameter constructor initializing the current booking.
     * @param room the room.
     * @param guest the guest.
     * @param arrivalDate the arrival date.
     * @param departureDate the departure date.
     * @param hasPaid the information if the guest paid.
     */
    public Booking (Room room, Guest guest, Date arrivalDate, Date departureDate, boolean hasPaid)
    {
        guestList = new GuestList();
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.hasPaid = hasPaid;
        this.room = room;
        guestList.addGuest(guest);
        totalPrice = arrivalDate.daysInBetween(departureDate) * room.getPrice();
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
    public Guest getGuest(String phoneNumber)
    {
        return guestList.searchGuestByPhoneNumber(phoneNumber);
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

    //Has Paid

    /**
     * Gets information if the guest has paid for the Booking.
     * @return the information if the guest has paid for the Booking.
     */
    public boolean hasPaid()
    {
        return hasPaid;
    }

    /**
     * Sets the information if the guest has paid for the Booking.
     * @param hasPaid the information if the guest has paid for the Booking.
     */
    public void setHasPaid(boolean hasPaid)
    {
        this.hasPaid = hasPaid;
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
        return totalPrice;
    }

    /**
     * A toString method to print out each of the elements from the Booking class.
     * @return all objects from the Booking.
     */
    public String toString()
    {
        return guestList.getMainGuest().toString()+" "+room.getRoomNumber()+" "+arrivalDate+" "+departureDate+" "+hasPaid;
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
               && departureDate.equals(other.departureDate) && hasPaid == hasPaid();
    }
}
