package model;

import java.util.ArrayList;

public class Booking {
    private double totalPrice;
    private boolean hasPaid;
    private GuestList guestList;
    private RoomList roomList;
    private Date arrivalDate;
    private Date departureDate;
    private Room room;
    private Guest guest;

    public Booking (Room room, Date arrivalDate, Date departureDate, boolean hasPaid, Guest guest)
    {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.hasPaid = hasPaid;
        this.room = room;
        this.guest = guest;
        totalPrice = arrivalDate.daysInBetween(departureDate) * room.getPrice();
    }


}
