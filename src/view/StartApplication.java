package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BookingModelManager;

import java.io.IOException;

/**
 * Initializes the modelManager and viewHandler
 * @author Lukasz
 */
public class  StartApplication extends Application
{
  /**
   * Initializes modelManager and view handles in the given stage
   * @param window a stage
   * @throws Exception indicates a failure in launching the application
   */
  public void start(Stage window) throws Exception
  {
    BookingModelManager modelManager = new BookingModelManager("hotel.bin");
    ViewHandler viewHandler = new ViewHandler(modelManager);
    window.setResizable(false);
    viewHandler.start(window);
  }
}
