package view;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.*;

/**
 * Controller for the registerGuestDetails
 * @author Lukasz, Diana, Gabriela
 */
public class RegisterGuestDetailsController
{

  private BookingModelManager modelManager; //helping with all of the methods from java
  private Region root; //the base of xml file
  private ViewHandler viewHandler; //send info from different tabs

  @FXML private Button addGuest;
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField phoneNumber;
  @FXML private TextField ID;
  @FXML private TextField nationality;
  @FXML private TextField city;
  @FXML private TextField country;
  @FXML private TextField postalCode;
  @FXML private CheckBox smoking;
  @FXML private TextField birthday;
  @FXML private DatePicker arrival;
  @FXML private DatePicker departure;
  @FXML private Button saveButton;
  @FXML private Button checkIn;
  @FXML private Button importGuestDetails;

  private Guest currentGuest;
  private int count = 0;
  Guest additionalGuest;

  /**
   * Called from the view handler class to set up the view handler, modelManager and root layout
   * @param viewHandler viewHandler
   * @param modelManager modelManager
   * @param root root layout
   */
  //taking info from this classes
  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.root = root;
    reset();
  }

  /**
   * Returns root layout
   * @return root layout
   */
  public Region getRoot()
  {
    return root;
  }

  /**
   * Clears all the fields and sets smoking to false
   */
  public void reset()
  {
    updateRegisterGuestDetails();
  }

  /**
   * A method that handles events for checkIn, addGuest, importGuestDetails and save buttons
   * @param e an action event
   */
  public void handleForRegisterGuestDetails(ActionEvent e)
  {
    if (e.getSource() == checkIn)
    {
      count = 0;
      Hotel hotel = modelManager.load();
      Booking temp = hotel.getAllBookings().getBooking(viewHandler.getMainPageController().bookingListTable.getSelectionModel().getSelectedItem());

      if (smoking.isSelected())
      {
        temp.getRoom().setSmoking(true);
      }
      temp.setCheckedIn();
      modelManager.save(hotel);
      viewHandler.openView("MainPage");
    }
    else if (e.getSource() == addGuest)
    {
      reset();
    }
    else if (e.getSource() == importGuestDetails)
    {
      currentGuest = viewHandler.getMainPageController().bookingListTable.getSelectionModel().getSelectedItem().getMainGuestForBooking();
      firstName.setText(currentGuest.getFirstName());
      lastName.setText(currentGuest.getLastName());
      phoneNumber.setText(currentGuest.getPhoneNumber());
      if (currentGuest.getId() != null)
      {
        ID.setText(currentGuest.getId());
        nationality.setText(currentGuest.getNationality());
        city.setText(currentGuest.getAddress().getCity());
        country.setText(currentGuest.getAddress().getCountry());
        postalCode.setText(currentGuest.getAddress().getPostcode());
        birthday.setText(currentGuest.getBirthday().toString());
      }
      if(viewHandler.getMainPageController().bookingListTable.getSelectionModel().getSelectedItem().getRoom().getRoomNumber() == 301 || viewHandler.getMainPageController().bookingListTable.getSelectionModel().getSelectedItem().getRoom().getRoomNumber() == 302)
      {
        smoking.setDisable(false);
      }
      else
      {
        smoking.setDisable(true);
      }
    }
    else if (e.getSource() == saveButton)
    {
      if (count==0)
      {
        Hotel hotel = modelManager.load();
        currentGuest.setId(ID.getText());
        currentGuest.setNationality(nationality.getText());
        currentGuest.setAddress(new Address(postalCode.getText(), city.getText(), country.getText()));
        String[] tempArr = birthday.getText().split("/");
        Date birthday = new Date(Integer.parseInt(tempArr[0]),Integer.parseInt(tempArr[1]), Integer.parseInt(tempArr[2]));
        currentGuest.setBirthday(birthday);
        hotel.addGuest(currentGuest);
        modelManager.save(hotel);
        count++;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The main guest has been added.");
        alert.setHeaderText(null);
        alert.showAndWait();

      }
      else if (count <= viewHandler.getMainPageController().bookingListTable.getSelectionModel().getSelectedItem().getRoom().getCapacity())
        {
          Hotel hotel = modelManager.load();
          additionalGuest = new Guest(firstName.getText(), lastName.getText(), phoneNumber.getText());
          additionalGuest.setId(ID.getText());
          additionalGuest.setNationality(nationality.getText());
          additionalGuest.setAddress(new Address(postalCode.getText(), city.getText(), country.getText()));
          String[] tempArr = birthday.getText().split("/");
          Date birthday = new Date(Integer.parseInt(tempArr[0]),Integer.parseInt(tempArr[1]), Integer.parseInt(tempArr[2]));
          additionalGuest.setBirthday(birthday);
          hotel.addGuest(additionalGuest);
          modelManager.save(hotel);
          count++;
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The additional guest has been added. \n Number of guests created for the booking: " + count);
          alert.setHeaderText(null);
          alert.showAndWait();
        }
      else
        {
          Alert alert = new Alert(Alert.AlertType.ERROR, "The room has reached its max capacity!");
          alert.setHeaderText(null);
          alert.showAndWait();
        }

      }
    }

  /**
   * Clears all the fields and sets smoking to false
   */
  private void updateRegisterGuestDetails()
  {
    firstName.clear();
    lastName.clear();
    phoneNumber.clear();
    ID.clear();
    nationality.clear();
    city.clear();
    country.clear();
    postalCode.clear();
    birthday.clear();
    smoking.setSelected(false);
  }
}
