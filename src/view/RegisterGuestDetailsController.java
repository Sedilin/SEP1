package view;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    }
    else if (e.getSource() == saveButton)
    {
      Hotel hotel = modelManager.load();
      Guest guest = new Guest(firstName.getText(), lastName.getText(), phoneNumber.getText());
      if (hotel.getAllGuests().getGuest(guest) == null)
      {

      }
      guest.setId(ID.getText());
      guest.setNationality(nationality.getText());
      guest.setAddress(new Address(postalCode.getText(), city.getText(), country.getText()));
      String[] tempArr = birthday.getText().split("/");
      Date birthday = new Date(Integer.parseInt(tempArr[0]),Integer.parseInt(tempArr[1]), Integer.parseInt(tempArr[2]));
      guest.setBirthday(birthday);
      hotel.addGuest(guest);
      modelManager.save(hotel);
      System.out.println(modelManager.load().getAllGuests());
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
