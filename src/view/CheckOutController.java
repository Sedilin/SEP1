package view;

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

public class CheckOutController

{
  @FXML private Text guestName;
  @FXML private Text phoneNumber;
  @FXML private Text arrivalDate;
  @FXML private Text departureDate;
  @FXML private Text totalPrice;
  @FXML private TextField discount;
  @FXML private TextField cleaningFee;
  @FXML private Button importButton;
  @FXML private Button payAndCheckIn;

  private ViewHandler viewHandler;
  private BookingModelManager modelManager;
  private Region root;

  /**
   * Initializes viewHandler object, model manager object and root object
   * @param viewHandler view handler
   * @param modelManager model manager
   * @param root root
   */
  Booking currentBooking;
  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.root = root;
    reset();
  }

  /**
   * Initializes the binary file to the model manager
   */
  public void initialize()
  {
    modelManager = new BookingModelManager("hotel.bin");
  }

  /**
   *
   * @return
   */
  public Region getRoot()
  {
    return root;
  }

  /**
   * Resets the CheckOut page using the method updateCheckoutPage()
   */
  public void reset()
  {
    updateCheckOutPage();
  }

  /**
   * Text area in order for guest information to be imported
   */

  public void updateCheckOutPage() {
    guestName.setText(" ");
    phoneNumber.setText(" ");
    arrivalDate.setText(" ");
    departureDate.setText(" ");
    discount.clear();
    cleaningFee.clear();
    totalPrice.setText("");
  }

  /**
   * First it uses the reset method
   * Gets the currentBooking from the CheckOut table
   * Makes a new variable price which converts the total price to String data type
   * Imports guest information to the text area
   * Prints out the currentBooking
   * Sets the cleaningFee to enable if in the room is allowed to smoke
   */
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
    if(viewHandler.getMainPageController().checkoutListTable.getSelectionModel().getSelectedItem().getRoom().isSmoking())
    {
      cleaningFee.setDisable(false);
    }
  }

  /**
   * Applies the discount to the currentBooking and makes it a double
   * Calculates the totalPrice when discount is added
   * Prints out the currentBooking
   */
  public void discount() {

    if (discount.getText() != null)
    {
      currentBooking.applyDiscount(parseDouble(discount.getText()));
      totalPrice.setText(String.valueOf(Math.round(currentBooking.getTotalPrice())));
      System.out.println(currentBooking);
    }
  }

  /**
   * Applies cleaningFee to the currentBooking and makes it a double
   * Calculates the totalPrice after the cleaningFee is added
   */
  public void cleaningFee()
  {
    if (cleaningFee.getText() != null)
    {
      currentBooking.applyCleaningFee(parseDouble(cleaningFee.getText()));
      totalPrice.setText(String.valueOf(Math.round(currentBooking.getTotalPrice())));
    }
  }

  /**
   * Confirmation alert type which prints out for how many days the guest has to pay
   * and the final amount
   * If the payment is confirmed it sets the booking to checkedOut
   * Prints the currentBooking
   * Loads and saves the hotel and viewHandler opens the MainPage view
   */

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
