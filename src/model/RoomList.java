package model;

import java.util.ArrayList;
/**
 * A class containing RoomList information.
 * @author Lukasz
 * @version 1.2
 */
public class RoomList {
    public ArrayList<Room> rooms;

    /**
     * No-argument constructor initializing the array of Rooms.
     */
    public RoomList()
    {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room)
    {
        rooms.add(room);
    }

    public void removeRoom(Room room)
    {
        rooms.remove(room);
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
    public RoomList getRoomsType(String type)
    {
        RoomList temp = new RoomList();
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                temp.addRoom(room);
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
     * Compares the two lists of RoomList.
     * @param obj object to be compared.
     * @return true if the two objects are equal and false otherwise.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof RoomList))
        {
            return false;
        }
        else
        {
            RoomList other = (RoomList) obj;
            return rooms.equals(other.rooms);
        }
    }
}
