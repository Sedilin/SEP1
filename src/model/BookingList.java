package model;

import java.awt.print.Book;
import java.util.ArrayList;

public class BookingList {
    private ArrayList<Booking> bookings;

    public BookingList()
    {
        bookings = new ArrayList<>();
    }

    public ArrayList<Booking> getAllBooking()
    {
        return bookings;
    }

    public void addBooking(Booking booking)
    {
        bookings.add(booking);
    }

    public BookingList findBookingsByPhoneNumber(String phoneNumber)
    {
        BookingList bookingList = new BookingList();
        for (Booking booking : bookings) {
            if (booking.getGuest(phoneNumber)!=null) {
                bookingList.addBooking(booking);
            }
        }
        return bookingList;
    }

    public String toString()
    {
        String infoBookings = "";
        for (Booking booking : bookings)
        {
            infoBookings += booking.toString() + "\n";
        }
        return infoBookings;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof BookingList other))
        {
            return false;
        }
        return bookings.equals(other.bookings);
    }

}
