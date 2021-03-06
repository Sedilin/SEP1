package model;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * A class containing for the date object.
 * @author Gabriela and Lukasz
 * @version 1.1
 * */
public class Date implements Serializable
{
  private int day;
  private int month;
  private int year;

  /**
   * no argument constructor initializing the current date
   */
  public Date()
  {
    Date temp = today();
    day = temp.day;
    month = temp.month;
    year = temp.year;
  }

  /**
   * 3 argument constructor initializing the date
   * @param day the day
   * @param month the month
   * @param year the year
   */

  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Gets the day from the Date class
   * @return date from Date class
   */
  public int getDay()
  {
    return day;
  }

  /**
   * Sets a day to the Date class
   * @param day day of the date object
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   * Gets the month form the Date class
   * @return month from the Date class
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * Sets the month to the Date class
   * @param month month of Date class
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   * Gets the year from the Date class
   * @return year from the Date class
   */
  public int getYear()
  {
    return year;
  }

  /**
   * Sets the year to the Date class
   * @param year year of the Date class
   */
  public void setYear(int year)
  {
    this.year = year;
  }

  /**
   * Verifies if the year from Date class is a leap year
   * @return true if the year from Date is a leap year and false if it is not
   */
  public boolean isLeapYear()

  {
    return year % 4 == 0 && year % 100 != 0 || year % 400 == 0; // O(8)
  }

  /**
   * Gets the number of days in a month of date object
   * @return number of days in a month
   */
  //this method returns the number of days in a month(30 for even months, 31 for odd, and 29 or 28 for february(depends on isLeapYear true or false))
  public int daysInMonth()
  {

    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10 || month == 12) // O(13)
    {
      return 31; // O(1)
    }

    else if (month == 2) // O(1)
    {
      if (isLeapYear()) //O(8)
      {
        return 29; // O(1)
      }
      else
      {
        return 28; // O(1)
      }
    }

    else if (month == 4 || month == 6 || month == 9 || month == 11) // O(7)
    {
      return 30; // O(1)
    }

    else
    {
      return -1; // O(1)
    }
    // The method returns the number of days in given month
    // T(n) = 13 + 1 + 1 + 8 + 1 + 1 + 7 + 1 + 1 = 34
    // Ignoring constants and coefficient we get T(n) = O(1)
  }

  /**
   * Gets the current date
   * @return a Date object of current date
   */
  public static Date today()
  {
    LocalDate currentDate = LocalDate.now();
    int day = currentDate.getDayOfMonth();
    int month = currentDate.getMonthValue();
    int year = currentDate.getYear();

    return new Date(day,month,year);
  }

  /**
   * Increases the day of date object with one day
   */
  public void nextDay()
  {
    day++;

    if (day > daysInMonth())
    {
      day = 1;
      month++;
      if (month > 12)
      {
        month = 1;
        year++;
      }
    }
  }

  /**
   * Returns a number of days in between two dates.
   * @param departureDate the date of departure.
   * @return the number of days that has passed from arrival till departure date.
   */
  public int daysInBetween(Date departureDate)
  {
    Date arrivalDate = new Date(day, month, year);
    int count = 0;

    for (Date i = arrivalDate; !i.equals(departureDate); i.nextDay())
    {
      count++;
    }
//    do {
//      count++;
//      arrivalDate.nextDay();
//    }
//    while (!(arrivalDate.equals(departureDate)));
      return count;
  }
  /**
   * Returns a string representation of the date
   * @return a string representation of the date : format "day.month.year"
   */
  public String toString ()
  {
    return day + "/" + month + "/" + year;
  }

  /**
   * Verifies the date from Date class is before another date object
   * @param obj object of type Date
   * @return true if the date from Date class is before the second object of type Date
   */
  public boolean isBefore(Date obj)
  {
    return year <= obj.year && month <= obj.month && day <= obj.day;
  }

  /**
   * Verifies if the date from Date class is after another date object
   * @param obj object of type Date
   * @return true if the date from Date class is after the second object of type Date
   */
  public boolean isAfter(Date obj)
  {
    return year > obj.year && month > obj.month && day > obj.day;
  }

  /**
   * Compares the date of the two Date objects
   * @param obj compared object
   * @return true if the two objects are equal and false if they aare not
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Date other))
    {
      return false;
    }
    return year == other.year && month == other.month && day == other.day;
  }

  /**
   * Copies the Date class
   * @return a copy of the Date class
   */
  public Date copy()
  {
    return new Date(day,month,year);
  }
}
