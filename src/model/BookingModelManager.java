package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BookingModelManager {
    private String fileName;

    /**
     * 1 argument constructor
     * @param fileName file name
     */
    public BookingModelManager(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Loads data from the binary file and to the hotel object
     * @return returns a hotel object
     */
    public Hotel load()
    {
        Hotel hotel = new Hotel();
        try
        {
            hotel = (Hotel) MyFileHandler.readFromBinaryFile(fileName);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found");
        }
        return hotel;
    }

    /**
     * Saves data to the binary file from the hotel object
     * @param hotel hotel object
     */
    public void save(Hotel hotel)
    {
        try
        {
            MyFileHandler.writeToBinaryFile(fileName, hotel);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
    }
}
