package main;

import model.Hotel;
import model.Room;
import model.BookingModelManager;
/**
 * A class containing Room information.
 * @author Christopher
 * @version 1.0
 */
public class LoadInitialData {
    public static void main(String[] args)
    {
        BookingModelManager bookingModelManager = new BookingModelManager("hotel.bin");
        Hotel hotel = bookingModelManager.load();
        hotel.getAllRooms().addRoom(new Room(305, false, "Single Bedroom Suite", 259.00, 2));
        hotel.getAllRooms().addRoom(new Room(304, false, "Single Bedroom Suite", 259.00, 2));
        hotel.getAllRooms().addRoom(new Room(303, false, "Single Bedroom Suite", 259.00, 2));
        //Constructor

        /**
         * 5 parameter constructor for individual room types.
         * @param roomNumber the room number.
         * @param isSmoking the room includes a smoking option.
         * @param type the room type.
         * @param Price the room price per night.
         * @param capacity the capacity of the room.
         */

        hotel.getAllRooms().addRoom(new Room(302, false, "2-Bedroom Suite", 339.00, 4));
        /**
         * 5 parameter constructor for individual room types.
         * @param roomNumber the room number.
         * @param isSmoking the room includes a smoking option.
         * @param type the room type.
         * @param Price the room price per night.
         * @param capacity the capacity of the room.
         */

        hotel.getAllRooms().addRoom(new Room(301, false, "3-Bedroom Suite", 399.00, 6));
        /**
         * 5 parameter constructor for individual room types.
         * @param roomNumber the room number.
         * @param isSmoking the room includes a smoking option.
         * @param type the room type.
         * @param Price the room price per night.
         * @param capacity the capacity of the room.
         */

        hotel.getAllRooms().addRoom(new Room(219, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(218, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(217, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(216, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(215, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(214, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(213, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(212, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(211, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(210, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(209, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(208, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(207, false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(206, false, "Double Room-King Size", 169.00,2));

        /**
         * 5 parameter constructor for individual room types.
         * @param roomNumber the room number.
         * @param isSmoking the room includes a smoking option.
         * @param type the room type.
         * @param Price the room price per night.
         * @param capacity the capacity of the room.
         */

        hotel.getAllRooms().addRoom(new Room(205, false, "Double Room-Twin", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(204, false, "Double Room-Twin", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(203, false, "Double Room-Twin", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(202, false, "Double Room-Twin", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(201, false, "Double Room-Twin", 169.00, 2));

        /**
         * 5 parameter constructor for individual room types.
         * @param roomNumber the room number.
         * @param isSmoking the room includes a smoking option.
         * @param type the room type.
         * @param Price the room price per night.
         * @param capacity the capacity of the room.
         */

        hotel.getAllRooms().addRoom(new Room(118,false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(117,false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(116,false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(115,false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(114,false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(113,false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(112,false, "Double Room-King Size", 169.00, 2));
        hotel.getAllRooms().addRoom(new Room(111,false, "Double Room-King Size", 169.00, 2));

        /**
         * 5 parameter constructor for individual room types.
         * @param roomNumber the room number.
         * @param isSmoking the room includes a smoking option.
         * @param type the room type.
         * @param Price the room price per night.
         * @param capacity the capacity of the room.
         */

        hotel.getAllRooms().addRoom(new Room(110,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(109,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(108,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(107,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(106,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(105,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(104,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(103,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(102,false, "Single Room", 129.00, 2));
        hotel.getAllRooms().addRoom(new Room(101,false, "Single Room", 129.00, 2));

        /**
         * 5 parameter constructor for individual room types.
         * @param roomNumber the room number.
         * @param isSmoking the room includes a smoking option.
         * @param type the room type.
         * @param Price the room price per night.
         * @param capacity the capacity of the room.
         */

        bookingModelManager.save(hotel);
    }
}
