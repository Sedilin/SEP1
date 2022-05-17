package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.Booking;
import model.BookingModelManager;
import model.Hotel;

import static java.lang.Double.parseDouble;

public class CheckOut

{
  @FXML private Text guestName;
  @FXML private Text phoneNumber;
  @FXML private Text arrivalDate;
  @FXML private Text departureDate;
  @FXML private TextField discount;
  @FXML private Text totalPrice;
  @FXML private Button importButton;
  @FXML private Button payAndCheckIn;

  private ViewHandler viewHandler;
  private BookingModelManager modelManager;
  private Region root;

  Booking currentBooking;
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
    updateCheckOutPage();
  }

  public void updateCheckOutPage() {
    guestName.setText("Guest name");
    phoneNumber.setText("Phone number");
    arrivalDate.setText("Arrival date");
    departureDate.setText("Departure date");
    discount.clear();
    totalPrice.setText("");
  }
  public void importButton()
  {
    reset();
    currentBooking = viewHandler.getMainPageController().checkoutListTable.getSelectionModel().getSelectedItem();
    String price = String.valueOf(currentBooking.getTotalPrice());
    guestName.setText(currentBooking.getGuestName());
    phoneNumber.setText(currentBooking.getPhoneNumber());
    arrivalDate.setText(currentBooking.getArrivalDate().toString());
    departureDate.setText(currentBooking.getDepartureDate().toString());
    totalPrice.setText(price);
    System.out.println(currentBooking);
  }
  public void discount() {

    if (discount.getText() != null)
    {
      currentBooking.applyDiscount(parseDouble(discount.getText()));
      totalPrice.setText(String.valueOf(Math.floor(currentBooking.getTotalPrice())));
      System.out.println(currentBooking);
    }
  }
  public void payAndCheckInButton()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The amount to pay for " + currentBooking.getArrivalDate().daysInBetween(currentBooking.getDepartureDate()) + " days: " + currentBooking.getTotalPrice() + " $");
    alert.setHeaderText(null);
    alert.showAndWait();
    currentBooking.setCheckedOut();
    System.out.println(currentBooking);
    Hotel hotel = modelManager.load();
    hotel.addBooking(currentBooking);
    modelManager.save(hotel);
    viewHandler.openView("MainPage");
  }
}
