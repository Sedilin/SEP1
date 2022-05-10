package model;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing for the BookingList object.
 * @author Gabriela and Lukasz
 * @version 1.0
 * */
public class BookingList implements Serializable
{
    private ArrayList<Booking> bookings;

    /**
     * No-argument constructor initializing the array of Rooms.
     */
    public BookingList()
    {
        bookings = new ArrayList<>();
    }

    /**
     * Gets all the bookings from the list.
     * @return the bookings in the BookingList.
     */
    public ArrayList<Booking> getAllBooking()
    {
        return bookings;
    }

    /**
     * Adds a booking to the BookingList object.
     * @param booking the booking to add to the booking list.
     */
    public void addBooking(Booking booking)
    {
        bookings.add(booking);
    }

    /**
     * Finds a booking by phone number.
     * @param phoneNumber the phone number to search the booking by.
     * @return the booking that has been found by the phone number, if booking does not exist it does not return anything.
     */
    public BookingList findBookingsByPhoneNumber(String phoneNumber)
    {
        BookingList bookingList = new BookingList();
        for (Booking booking : bookings) {
            if (booking.getGuest().getPhoneNumber().equals(phoneNumber)) {
                bookingList.addBooking(booking);
            }
        }
        return bookingList;
    }

    public BookingList getRoomsOfOneType(String type)
    {
        BookingList bookingsOfType = new BookingList();
        for (Booking booking : bookings) {
            if (booking.getRoom().getType().equals(type)) {
                bookingsOfType.addBooking(booking);
            }
        }
        return bookingsOfType;
    }

    public Booking getBooking(Booking booking)
    {
        for (Booking value : bookings) {
            if (value.equals(booking)) {
                return value;
            }
        }
        return null;
    }

    public int size()
    {
        return bookings.size();
    }

    /**
     * A toString method to print out each of the elements from the BookingList class.
     * @return all objects from the BookingList.
     */
    public String toString()
    {
        String infoBookings = "";
        for (Booking booking : bookings)
        {
            infoBookings += booking.toString() + "\n";
        }
        return infoBookings;
    }

    /**
     * Compares the two lists of BookingList
     * @param obj object to be compared.
     * @return true if the two objects are equal and false otherwise.
     */
    public boolean equals(Object obj)
    {
        if(!(obj instanceof BookingList other))
        {
            return false;
        }
        return bookings.equals(other.bookings);
    }

}
