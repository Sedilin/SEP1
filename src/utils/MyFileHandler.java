package utils;

import java.io.*;
import java.util.ArrayList;

import parser.ParserException;
import parser.XmlJsonParser;

public class MyFileHandler
{
   /**
    *Writes the given object to a file with the given file name
    * @param fileName file name
    * @param obj object to be written in the file
    * @throws FileNotFoundException an exception which means that the binary file was not found
    * @throws IOException an exception which indicates a failure in writing to the file
    */
   // Writes the given object to a file with the given file name
   public static void writeToBinaryFile(String fileName, Object obj) throws FileNotFoundException, IOException
   {
      ObjectOutputStream writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName);
         writeToFile = new ObjectOutputStream(fileOutStream);

         writeToFile.writeObject(obj);
      }
      finally
      {
         if (writeToFile != null)
         {
            try
            {
               writeToFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }
   }

   /**
    * Reads the first object from the file with the given file name and returns it
    * @param fileName file name
    * @return the first object from the file
    * @throws FileNotFoundException indicates that the binary file was not found
    * @throws IOException indicates a failure in reading from the file
    * @throws ClassNotFoundException indicates a mismatch of classes from the file and computer
    */
   // Reads the first object from the file with the given file name and returns it.
   // Whoever calls the method will need to cast it from type Object to its actual type
   public static Object readFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      Object obj = null;
      ObjectInputStream readFromFile = null;
      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new ObjectInputStream(fileInStream);
         try
         {
            obj = readFromFile.readObject();
         }
         catch (EOFException eof)
         {
           //Done reading
         }
      }
      finally
      {
         if (readFromFile != null)
         {
            try
            {
               readFromFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }
      return obj;
   }

   /**
    * Creates the XML file of available rooms
    * @param list that should be written in the xml file
    */
   public static void createXMLFile(Object list) {
      XmlJsonParser parser = new XmlJsonParser();
      try {
         parser.toXml(list, "Availability.xml");
      } catch (ParserException e) {
         e.printStackTrace();
      }
   }
 }
