package main;

import model.Hotel;
import model.Room;
import model.BookingModelManager;

public class LoadInitialData {
    public static void main(String[] args)
    {
        BookingModelManager bookingModelManager = new BookingModelManager("hotel.bin");
        Hotel hotel = bookingModelManager.load();
        hotel.getAllRooms().addRoom(new Room());
        bookingModelManager.save(hotel);
    }
}
