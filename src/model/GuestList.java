package model;

import java.util.ArrayList;

public class GuestList {
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
    guests.add(guest);
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
