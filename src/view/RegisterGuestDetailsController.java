package view;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.*;

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

  //taking info from this classes
  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.root = root;
    reset();
  }
  public void initialize()
  {
    modelManager = new BookingModelManager("hotel.bin");
  }

  public Region getRoot()
  {
    return root;
  }
  public void reset()
  {
    updateRegisterGuestDetails();
  }

  public void handleForRegisterGuestDetails(ActionEvent e)
  {
    if (e.getSource() == checkIn)
    {
      count = 0;
      Hotel hotel = modelManager.load();
      hotel.getAllBookings().getBooking(viewHandler.getMainPageController().bookingListTable.getSelectionModel().getSelectedItem()).setCheckedIn();
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
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The additional guest has been added. \nNumber of guests created for the booking: " + count);
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
    arrival.getEditor().clear();
    departure.getEditor().clear();
    smoking.setSelected(false);
  }
}
