package model;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class that manages other classes
 * @author Lukasz and Gabriela
 * @version 1.2
 */
public class Hotel
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
        RoomList availableRooms = getAllRooms();
        BookingList allBookings = getAllBookings();
        allBookings = allBookings.getRoomsOfOneType(roomType);
        availableRooms = availableRooms.getRoomsType(roomType);

        for (Booking booking : allBookings.getAllBooking())
        {
            if ((arrivalDate.isAfter(booking.getArrivalDate()) && arrivalDate.isBefore(booking.getDepartureDate())) ||
                    (departureDate.isAfter(booking.getArrivalDate()) && departureDate.isBefore(booking.getDepartureDate())) ||
                    arrivalDate.equals(booking.getArrivalDate()) || departureDate.equals(booking.getDepartureDate()))
            {
                availableRooms.removeRoom(booking.getRoom());
            }
        }
        return availableRooms;
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
     * Changes Sets booking variable's value checkedOut to true and writes it to the file than contains information about booking list
     * @param booking booking which checkedOut value is changed
     * @return the variable total price of the booking object
     */
    public double checkOut(Booking booking)
    {
        bookings.getBooking(booking).setCheckedOut();
        return bookings.getBooking(booking).getTotalPrice();
    }

}
