package model;

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
        if (!bookings.contains(booking))
        bookings.add(booking);
        else {
            bookings.remove(booking);
            bookings.add(booking);
        }
    }

    public void removeBooking(Booking booking)
    {
        bookings.remove(booking);
    }

    /**
     * Gets last booking from booking list
     * @return
     */
    public Booking getLastBooking()
    {
        return bookings.get(bookings.size()-1);
    }

    /**
     * Finds a booking by phone number.
     * @param phoneNumber the phone number to search for the booking.
     * @return the booking that has been found by the phone number, if booking does not exist it does not return anything.
     */
    public BookingList findBookingsByPhoneNumber(String phoneNumber)
    {
        BookingList bookingList = new BookingList();
        for (Booking booking : bookings) {
            if (booking.getMainGuestForBooking().getPhoneNumber().equals(phoneNumber)) {
                bookingList.addBooking(booking);
            }
        }
        return bookingList;
    }

    /**
     * Gets room objects of one type from bookings
     * @param type type of room
     * @return a room object
     */
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

    /**
     * Gets a specific booking from bookings
     * @param booking booking to be returned
     * @return the booking from bookings
     */
    public Booking getBooking(Booking booking)
    {
        for (Booking value : bookings) {
            if (value.equals(booking)) {
                return value;
            }
        }
        return null;
    }

    /**
     * Gets a list of bookings which isCheckedIn value is true
     * @return a list of checked in bookings
     */
    public BookingList getCheckedInBookings()
    {
        BookingList checkedInBookings = new BookingList();
        for (int i = 0; i < bookings.size(); i++)
        {
            if (bookings.get(i).isCheckedIn() && !(bookings.get(i).isCheckedOut()))
            {
                checkedInBookings.addBooking(bookings.get(i));
            }
        }
        return checkedInBookings;
    }

    /**
     * Gets a list of bookings which isCheckedIn values is false
     * @return bookings which are not checked in
     */
    public BookingList getNotCheckedInBookings()
    {
        BookingList notCheckedInBookings = new BookingList();
        for (int i = 0; i < bookings.size(); i++)
        {
            if (!(bookings.get(i).isCheckedIn()) && !(bookings.get(i).isCheckedOut()))
            {
                notCheckedInBookings.addBooking(bookings.get(i));
            }
        }
        return notCheckedInBookings;
    }

    /**
     * Gets a booking by its index in the booking list
     * @param index index of the booking to be returned
     * @return booking of a certain index
     */
    public Booking getBookingByIndex(int index)
    {
        return bookings.get(index);
    }

    /**
     * Gets the number of bookings registered on the same phone number
     * @param phoneNumber phone number of a guest
     * @return number of bookings registered on the same phone number
     */
    public int getNumberOfBookingsByPhoneNumber(String phoneNumber)
    {
        int count = 0;
        for (int i = 0; i < bookings.size(); i++)
        {
            if(bookings.get(i).getMainGuestForBooking().getPhoneNumber().equals(phoneNumber))
            {
                count ++;
            }
        }
        return count;
    }

    /**
     * Gets the size of bookings
     * @return size of bookings
     */
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
