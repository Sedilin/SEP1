package model;

import java.util.ArrayList;

public class Booking {
    private double totalPrice;
    private boolean hasPaid;
    private GuestList guestList;
    private Room room;
    private Date arrivalDate;
    private Date departureDate;

    //Constructor
    public Booking (Room room, Guest guest, Date arrivalDate, Date departureDate, boolean hasPaid)
    {
        guestList = new GuestList();
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.hasPaid = hasPaid;
        this.room = room;
        guestList.addGuest(guest);
        totalPrice = arrivalDate.daysInBetween(departureDate) * room.getPrice();
    }
    //Guest
    public void addGuest(Guest guest)
    {
        guestList.addGuest(guest);
    }
    public Guest getGuest(String phoneNumber)
    {
        return guestList.searchGuestByPhoneNumber(phoneNumber);
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
    public void changeRoom(Room room)
    {
        this.room = room;
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
    public void applyDiscount(double discount)
    {
        totalPrice -= totalPrice * (discount/100);
    }
    public double getTotalPrice()
    {
        return totalPrice;
    }

    public String toString()
    {
        return guestList.getMainGuest().toString()+" "+room.getRoomNumber()+" "+arrivalDate+" "+departureDate+" "+hasPaid;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Booking other))
        {
            return false;
        }
        return guestList.equals(other.guestList) && room.equals(other.room) && arrivalDate.equals(other.arrivalDate)
               && departureDate.equals(other.departureDate) && hasPaid == hasPaid();
    }
}
