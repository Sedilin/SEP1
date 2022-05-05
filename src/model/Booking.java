package model;

import java.util.ArrayList;

public class Booking {
    private double totalPrice;
    private boolean hasPaid;
    private GuestList guestList = new GuestList();
    private RoomList roomList = new RoomList();
    private Date arrivalDate;
    private Date departureDate;
    private Room room;
    private Guest guest;

    //Constructor
    public Booking (int roomNumber, String phoneNumber, Date arrivalDate, Date departureDate, boolean hasPaid)
    {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.hasPaid = hasPaid;
        this.room = roomList.findByRoomNumber(roomNumber);
        this.guest = guestList.searchGuestByPhoneNumber(phoneNumber);
        totalPrice = arrivalDate.daysInBetween(departureDate) * room.getPrice();
    }
    //Guest
    public Guest getGuest()
    {
        return guest;
    }
    public void editGuestDetails(Guest guest) {
        this.guest = guest;
    }

    //Arrival date
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }

    //Departure date
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
    public Date getDepartureDate() {
        return departureDate;
    }

    //Room
    public void changeRoomNumber(int newRoomNumber)
    {
        room = roomList.findByRoomNumber(newRoomNumber);
    }
    public Room getRoom() {
        return room;
    }

    //Has Paid
    public boolean hasPaid()
    {
        return hasPaid;
    }
    public void setHasPaid(boolean hasPaid)
    {
        this.hasPaid = hasPaid;
    }

    //Price
    public void applyDiscount(int discount)
    {
        totalPrice *= discount;
    }
    public double getTotalPrice()
    {
        return totalPrice;
    }
}
