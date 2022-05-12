package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BookingModelManager;

public class StartApplication extends Application
{
  public void start(Stage window) throws Exception
  {
    BookingModelManager modelManager = new BookingModelManager("hotel.bin");
    ViewHandler viewHandler = new ViewHandler(modelManager);
    viewHandler.start(window);
  }
}
