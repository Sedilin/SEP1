package model;

import java.util.Arrays;
/**
 * A class containing Guest information.
 * @author Lukasz
 * @version 1.0
 */
public class RoomList {
    private Room[] rooms;
    /**
     * No-argument constructor initializing the array of Rooms.
     */
    public RoomList()
    {
        rooms = new Room[42];
    }
    /**
     * Goes through the array searching for specific room number in the array of Rooms.
     * @param roomNumber the room number we are searching for.
     * @return if true the room with specific room number, otherwise returns null.
     */
    public Room findByRoomNumber(int roomNumber)
    {
        for (Room room : rooms)
        {
            if (room.getRoomNumber() == roomNumber)
            {
                return room;
            }
        }
        return null;
    }
    /**
     * Goes through the array of rooms, searches for given
     * @param type the type we want to search for.
     * @return the array of Rooms with specific criteria.
     */
    //We have only 4 types of rooms (single, double, suite, king bedroom).
    public Room[] getRoomsType(String type)
    {
        Room[] temp = new Room[30];
        int position = 0;
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                temp[position] = room;
                position++;
            }
        }
        return temp;
    }
    /**
     * A toString method to print out each of the elements from the RoomList class.
     * @return all objects from the RoomList.
     */
    public String toString()
    {
        String info = "";

        for (Room room : rooms) {
            info += room + "\n";
        }
        return info;
    }
    /**
     * Compares Room list, if it is possible to smoke, type of the room and price of the object of two RoomList.
     * @param obj object to be compared.
     * @return true if the two objects are equal and false otherwise.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof RoomList other))
        {
            return false;
        }
        else
        {
            return Arrays.equals(rooms, other.rooms);
        }
    }
}
