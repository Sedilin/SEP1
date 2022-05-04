package model;
/**
 * A class containing Guest information.
 * @author Lukasz
 * @version 1.0
 */
public class Room {
    private int roomNumber;
    private boolean isSmoking;
    private String type;
    private double price;

    /**
     * 4 argument constructor initializing the Room variables.
     * @param roomNumber the room number.
     * @param isSmoking the information if you can smoke in the room.
     * @param type the type of the room.
     * @param price the price.
     */
    public Room (int roomNumber, boolean isSmoking, String type, double price)
    {
        this.roomNumber = roomNumber;
        this.isSmoking = isSmoking;
        this.type = type;
        this.price = price;
    }
    /**
     * Gets a room number from the Room class.
     * @return the room number from the Room class.
     */
    public int getRoomNumber() {
        return roomNumber;
    }
    /**
     * Sets the room number to the Room.
     * @param roomNumber the room number to set the object to.
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    /**
     * Gets an information if you can smoke in the room from the Room class.
     * @return the information if you can smoke in the room from the Room class.
     */
    public boolean isSmoking() {
        return isSmoking;
    }
    /**
     * Sets the information about smoking in the room to the Room.
     * @param isSmoking the information about smoking in the room to set the object to.
     */
    public void setSmoking(boolean isSmoking) {
        this.isSmoking = isSmoking;
    }
    /**
     * Gets a type from the Room class.
     * @return the type from the Room class.
     */
    public String getType() {
        return type;
    }
    /**
     * Sets the type to the Room.
     * @param type the type to set the object to.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Gets a price from the Room class.
     * @return the price from the Room class.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Sets the price to the Room.
     * @param price the price to set the object to.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * A toString method to print out each of the elements from the Room class.
     * @return all objects from the Room.
     */
    public String toString()
    {
        return roomNumber + " " + isSmoking + " " + type + " " + price;
    }
    /**
     * Compares room number, if it is possible to smoke, type of the room and price of the object of two Rooms.
     * @param obj object to be compared.
     * @return true if the two objects are equal and false otherwise.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Room other))
        {
            return false;
        }
        else
        {
            return roomNumber == other.roomNumber && isSmoking == other.isSmoking && type.equals(other.type) &&
                    price == other.price;
        }
    }
}
