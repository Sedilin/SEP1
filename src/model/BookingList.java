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
    public void addBooking(Booking booking)
    {
        bookings.add(booking);
    }
    public BookingList findBookings(String phoneNumber)
    {
        BookingList bookingList = new BookingList();
        for (Booking booking : bookings) {
            if (booking.getGuest().getPhoneNumber().equals(phoneNumber)) {
                bookingList.addBooking(booking);
            }
        }
        return bookingList;
    }
}
