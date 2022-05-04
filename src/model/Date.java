package model;
/**
 * A class containing for the date object
 * @author Gabriela
 * @version 1.0
 * */

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;



public class Date
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
   * @return true or false
   */
  public boolean isLeapYear()

  {
    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   *
   * @return
   */
  public int daysInMonth()
  {

    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10 || month == 12)
    {
      return 31;
    }

    else if (month == 2)
    {
      if (isLeapYear())
      {
        return 29;
      }
      else
      {
        return 28;
      }
    }

    else if (month == 4 || month == 6 || month == 9 || month == 11)
    {
      return 30;
    }

    else
    {
      return -1;
    }
  }


  public static Date today()
  {
    LocalDate currentDate = LocalDate.now();
    int day = currentDate.getDayOfMonth();
    int month = currentDate.getMonthValue();
    int year = currentDate.getYear();

    return new Date(day,month,year);
  }

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

  public String toString ()
  {
    return day + "." + month + "." + year;
  }

  public boolean isBefore(Date obj)
  {
    if (year <= obj.year && month <= obj.month && day <= obj.day)
    {
      return true;
    }
    else
      return false;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Date))
    {
      return false;
    }
    Date other = (Date)  obj;
    return year ==other.year && month == other.month && day == other.day;
  }

  public Date copy()
  {
    return new Date(day,month,year);
  }
}
