package model;

public class Hotel {
    private GuestList guestList;
    private BookingList bookingList;
    private RoomList roomList;

    public Hotel(GuestList guestList, BookingList bookingList, RoomList roomList)
    {
        this.guestList = guestList;
        this.bookingList = bookingList;
        this.roomList = roomList;
    }

    public RoomList getAllRoom()
    {
        return roomList;
    }
    public RoomList getAvailableRooms(Date arrivalDate, Date departureDate, String roomType)
    {
        RoomList availableRooms = new RoomList();
        BookingList bookingListOfType = bookingList.getRoomsOfOneType(roomType);

        for (int i = 0; i < bookingListOfType.size(); i++)
        {
            if ((!arrivalDate.isAfter(bookingListOfType.getAllBooking().get(i).getArrivalDate()) &&
            !arrivalDate.isBefore(bookingListOfType.getAllBooking().get(i).getDepartureDate())) ||
                    (!departureDate.isAfter(bookingListOfType.getAllBooking().get(i).getArrivalDate()) &&
                    !departureDate.isBefore(bookingListOfType.getAllBooking().get(i).getDepartureDate())))
            {
                availableRooms.addRoom(bookingListOfType.getAllBooking().get(i).getRoom());
            }
        }
        return availableRooms;
    }
    public void addBooking (Booking booking)
    {
        bookingList.addBooking(booking);
    }
    public BookingList getAllBookings()
    {
        return bookingList;
    }
    public void addGuest(Guest guest)
    {
        guestList.addGuest(guest);
    }
    public GuestList getAllGuests()
    {
        return guestList;
    }
    public Guest findGuestByPhoneNumber (String phoneNumber)
    {
        return guestList.searchGuestByPhoneNumber(phoneNumber);
    }
//    public void checkIn(Booking booking)
//    {
//        booking
//
//
//    }
}
