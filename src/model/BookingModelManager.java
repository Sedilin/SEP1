package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BookingModelManager {
    private String fileName;

    public BookingModelManager(String fileName)
    {
        this.fileName = fileName;
    }
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
