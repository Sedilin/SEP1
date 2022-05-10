package model;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Hotel {
    private String fileNameRoom;
    private String fileNameBookings;
    private String fileNameGuests;

    public Hotel(String fileNameBookings, String fileNameGuests, String fileNameRoom)
    {
        this.fileNameBookings = fileNameBookings;
        this.fileNameGuests = fileNameGuests;
        this.fileNameRoom = fileNameRoom;
    }

    public RoomList getAllRooms()
    {
        RoomList allRooms = new RoomList();

        try {
            allRooms = (RoomList) MyFileHandler.readFromBinaryFile(fileNameRoom);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found");
        }
        return allRooms;
    }
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
    public void addBooking (Booking booking)
    {
        BookingList bookings = getAllBookings();
        bookings.addBooking(booking);
        try {
            MyFileHandler.writeToBinaryFile(fileNameBookings, bookings);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
    }
    public BookingList getAllBookings()
    {
        BookingList allBookings = new BookingList();

        try {
            allBookings = (BookingList) MyFileHandler.readFromBinaryFile(fileNameBookings);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found");
        }
        return allBookings;
    }
    public void addGuest(Guest guest)
    {
        GuestList guests = getAllGuests();
        guests.addGuest(guest);
        try {
            MyFileHandler.writeToBinaryFile(fileNameGuests, guests);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
    }
    public GuestList getAllGuests()
    {
        GuestList allGuests = new GuestList();

        try {
            allGuests = (GuestList) MyFileHandler.readFromBinaryFile(fileNameGuests);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found");
        }
        return allGuests;
    }
    public Guest findGuestByPhoneNumber (String phoneNumber)
    {
        GuestList allGuests = getAllGuests();
        return allGuests.searchGuestByPhoneNumber(phoneNumber);
    }
    public void checkIn(Booking booking)
    {
        BookingList bookings = getAllBookings();
        bookings.getBooking(booking).setCheckedIn();
        try {
            MyFileHandler.writeToBinaryFile(fileNameBookings, bookings);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
    }
    public void checkOut(Booking booking)
    {
        BookingList bookings = getAllBookings();
        bookings.getBooking(booking).setCheckedOut();
        try {
            MyFileHandler.writeToBinaryFile(fileNameBookings, bookings);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
    }

}
