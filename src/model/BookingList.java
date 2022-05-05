package model;

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
}
