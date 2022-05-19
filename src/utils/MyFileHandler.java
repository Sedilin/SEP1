package utils;

import java.io.*;
import java.util.ArrayList;

public class MyFileHandler
{
   /**
    *Writes the given object to a file with the given file name
    * @param fileName file name
    * @param obj object to be written in the file
    * @throws FileNotFoundException an exception which means that the binary file was not found
    * @throws IOException an exception which indicates a failure in input operations
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
    * Writes the objects from the given array to a file with the given file name
    * @param fileName file name
    * @param objs object from the given array
    * @throws FileNotFoundException an exception which means that the binary file was not found
    * @throws IOException an exception which indicates a failure in input operations
    */
   // Writes the objects in the given array to a file with the given file name
   public static void writeArrayToBinaryFile(String fileName, Object[] objs) throws FileNotFoundException, IOException
   {
      ObjectOutputStream writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName);
         writeToFile = new ObjectOutputStream(fileOutStream);

         for (Object obj : objs) {
            writeToFile.writeObject(obj);
         }
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
    * @throws FileNotFoundException an exception which means that the binary file was not found
    * @throws IOException an exception which indicates a failure in output operations
    * @throws ClassNotFoundException
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
    * Reads all objects from the file with the given file name and returns it as an Object[].
    * @param fileName file name
    * @return returns an array of all objects read from binary file
    * @throws FileNotFoundException an exception which means that the binary file was not found
    * @throws IOException an exception which indicates a failure in output operations
    * @throws ClassNotFoundException an exception that indicates a failure in finding the class in
    */
   // Reads all objects from the file with the given file name and returns it as an Object[].
   // Whoever calls the method will need to cast the Objects to their actual type
   public static Object[] readArrayFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      ArrayList<Object> objs = new ArrayList<>();

      ObjectInputStream readFromFile = null;
      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new ObjectInputStream(fileInStream);
         while (true)
         {
            try
            {
               objs.add(readFromFile.readObject());
            }
            catch (EOFException eof)
            {
              //Done reading
               break;
            }
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

      return objs.toArray();
   }
 }
