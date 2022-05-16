package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing GuestList information.
 * @author Gabriela and Lukasz
 * @version 1.1
 */
public class GuestList implements Serializable
{
  private ArrayList<Guest> guests;

  /**
   * An empty constructor that initializes the array list of guests
   */
  public GuestList()
  {
    guests = new ArrayList<>();
  }

  /**
   * Adds object of type Guest to the array list of guests
   * @param guest guest to be added
   */
  public void addGuest(Guest guest)
  {
    if (!guests.contains(guest))
    guests.add(guest);
  }
  public void addGuests(GuestList newGuests)
  {
    for (int i = 0; i < newGuests.size(); i++) {
      guests.add(newGuests.getGuest(i));
    }
  }

  /**
   * Removes a guest from the array list of guests
   * @param guest guest to be removed
   */
  public void removeGuest(Guest guest)
  {
    for (int i = 0; i < guests.size(); i++)
    {
      if(guests.get(i).equals(guest))
      {
        guests.remove(i);
      }
    }
  }

  public Guest getGuest(int index)
  {
    return guests.get(index);
  }
  /**
   * Gets a Guest object by phone number from list of guests
   * @param phoneNumber phone number of guest
   * @return a guest
   */
  public Guest searchGuestByPhoneNumber(String phoneNumber)
  {
    for (Guest guest : guests)
    {

      if (guest.getPhoneNumber().equals(phoneNumber))
      {
        return guest;
      }
    }
    return null;
  }

  /**
   * Gets the size of the guest list.
   * @return the number of guests in the list.
   */
  public int size()
  {
    return guests.size();
  }
  public Guest getMainGuest()
  {
    return guests.get(0);
  }

  /**
   * Returns a string representation of the guest list
   * @return all guests
   */
  public String toString()
  {
    String allGuests = "";
    {
      for (Guest guest : guests)
      {
        allGuests += guest + "\n";
      }
    }
    return allGuests;
  }

  /**
   * Compares the two lists of guests
   * @param obj object to be compared
   * @return true if the two lists are equal and false if they are not
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof GuestList other))
    {
      return false;
    }
    else
    {
      return guests.equals(other.guests);
    }

  }
}
