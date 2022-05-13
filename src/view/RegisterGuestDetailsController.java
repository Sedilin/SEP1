package view;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.BookingModelManager;
import model.Guest;

public class RegisterGuestDetailsController
{

  private BookingModelManager modelManager; //helping with all of the methods from java
  private Region root; //the base of xml file
  private ViewHandler viewHandler; //send info from different tabs


  @FXML private Button addGuest;
  @FXML private TextField name;
  @FXML private TextField surname;
  @FXML private TextField phoneNumber;
  @FXML private TextField ID;
  @FXML private TextField nationality;
  @FXML private TextField city;
  @FXML private TextField country;
  @FXML private TextField postalCode;
  @FXML private CheckBox smoking;
  @FXML private TextField birthday;
  @FXML private TextField arrival;
  @FXML private TextField departure;
  @FXML private TextField save;
  @FXML private Button checkIn;

  private Guest currentGuest;

  //taking info from this classes
  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.root = root;
  }

  public Region getRoot()
  {
    return root;
  }

  public void button(ActionEvent e)
  {
    viewHandler.openView("MainPage");
  }
}
