package model;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class that manages other classes
 * @author Lukasz
 * @version 1.1
 */
public class Hotel
{
    private String fileNameRoom;
    private String fileNameBookings;
    private String fileNameGuests;

    /**
     *
      * @param fileNameBookings name of the file that contains information about Bookings of the hotel
     * @param fileNameGuests name of the file that contains information about Guests of the hotel
     * @param fileNameRoom name of the file that contains information about Rooms of the hotel
     */
    public Hotel(String fileNameBookings, String fileNameGuests, String fileNameRoom)
    {
        this.fileNameBookings = fileNameBookings;
        this.fileNameGuests = fileNameGuests;
        this.fileNameRoom = fileNameRoom;
    }

    /**
     *Gets the list of room objects from the file that contains information about RoomList
     * @return a list of room objects
     */
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
     * Adds a booking object to the file that contains information about BookingList
     * @param booking booking object to be added
     */
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

    /**
     * Gets a list of booking objects from the file that contains information about BookingList
     * @return a list of booking objects
     */
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

    /**
     * Adds a guest to the file that contains information about GuestList
     * @param guest guest object to be added
     */
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

    /**
     * Gets the list of guest objects from the file containing information about GuestList
     * @return a list of guest objects
     */
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

    /**
     *Sets booking variable's value checkedIn to true and writes it to the file than contains information about booking list
     * @param booking booking which checkedIn value is changed and meant to be written in the file
     */
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

    /**
     * Changes Sets booking variable's value checkedOut to true and writes it to the file than contains information about booking list
     * @param booking booking which checkedOut value is changed and meant to be written in the file
     * @return the variable total price of the booking object
     */
    public double checkOut(Booking booking)
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
        return bookings.getBooking(booking).getTotalPrice();
    }

}
