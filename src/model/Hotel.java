package model;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

/**
 * A class that manages other classes
 * @author Lukasz and Gabriela
 * @version 1.2
 */
public class Hotel implements Serializable
{
    private RoomList rooms;
    private BookingList bookings;
    private GuestList guests;

    /**
     * No-argument constructor.
     */
    public Hotel()
    {
        rooms = new RoomList();
        bookings = new BookingList();
        guests = new GuestList();
    }

    /**
     *Gets the list of room objects from the file that contains information about RoomList
     * @return a list of room objects
     */
    public RoomList getAllRooms()
    {
        return rooms;
    }

    /**
     * Gets a list of rooms available room objects in certain a range of time
     * @param arrivalDate arrival date
     * @param departureDate departure date
     * @param roomType type of room
     * @return a list of room objects
     */
    public RoomList getAvailableRooms(Date arrivalDate, Date departureDate, String roomType)
    {

      //We initialize a roomList object and a bookingList object

        RoomList availableRooms = getAllRooms(); // O(1)
        BookingList allBookings = getAllBookings(); // O(1)

     // We check if the roomType is not null and assign the according bookings to allBookings and according rooms to availableRooms
        if (roomType != null) // This comparison will be done once
        {
            allBookings = allBookings.getRoomsOfOneType(roomType); // O(n)
            availableRooms = availableRooms.getRoomsType(roomType); // O(n)
        }

        for (Booking booking : allBookings.getAllBooking())  // This loop will run n times
        {
            if ((arrivalDate.isAfter(booking.getArrivalDate()) && arrivalDate.isBefore(booking.getDepartureDate())) || // O(4)
                    (departureDate.isAfter(booking.getArrivalDate()) && departureDate.isBefore(booking.getDepartureDate())) || // O(4)
                    arrivalDate.equals(booking.getArrivalDate()) || departureDate.equals(booking.getDepartureDate())) // O(4)
            {
                availableRooms.removeRoom(booking.getRoom()); // O(n) + O(1)
            }
        }
        return availableRooms; // O(1)

      /*

      We loop through all bookings and check if the arrival and departure dates don't overlap
      T(n) = 1 + 1 +  1 + n + n + n(4 + 4 + 4 + n + 1) + 1 = 4 + 15n + n^2, so ignoring constants and coefficients
      we get T(n) = O(n^2)
      We chose this method due to its complexity
       */
    }


  /**
   * Gets a booking by the number it was registered on from a booking list
   * @param phoneNumber phone number of the main guest of the booking
   * @return a booking
   */
  public BookingList findBookingsByPhoneNumber(String phoneNumber) {
        return bookings.findBookingsByPhoneNumber(phoneNumber);
   }

    /**
     * Adds a booking object to bookings
     * @param booking booking object to be added
     */
    public void addBooking (Booking booking)
    {
        bookings.addBooking(booking);
    }

    /**
     * Gets a list of booking objects from bookings
     * @return a list of booking objects
     */
    public BookingList getAllBookings()
    {
        return  bookings;
    }

    /**
     * Adds a guest to guests
     * @param guest guest object to be added
     */
    public void addGuest(Guest guest)
    {
        guests.addGuest(guest);
    }

    /**
     * Gets the list of guest objects from guests
     * @return a list of guest objects
     */
    public GuestList getAllGuests()
    {
        return guests;
    }

  /**
   * Gets a guest by the phone number
   * @param phoneNumber phone number
   * @return a guest
   */
    public Guest findGuestByPhoneNumber (String phoneNumber)
    {
        GuestList allGuests = getAllGuests();
        return allGuests.searchGuestByPhoneNumber(phoneNumber);
    }

    /**
     *Sets booking variable's value checkedIn to true and writes it to the file than contains information about booking list
     * @param booking booking which checkedIn value is changed
     */
    public void checkIn(Booking booking)
    {
        bookings.getBooking(booking).setCheckedIn();
    }

    /**
     * Sets booking variable's value checkedOut to true and writes it to the file than contains information about booking list
     * @param booking booking which checkedOut value is changed
     * @return the variable total price of the booking object
     */
    public double checkOut(Booking booking)
    {
        bookings.getBooking(booking).setCheckedOut(); // O(1)
        return bookings.getBooking(booking).getTotalPrice(); // O()
    }

  /**
   * A toString method to print out the fields of a hotel
   * @return a string representation of Hotel object
   */
  public String toString()
    {
        return guests.toString() + " " + bookings.toString() + " " + rooms.toString();
    }

}
