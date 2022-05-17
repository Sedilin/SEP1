package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.Booking;
import model.BookingModelManager;

import static java.lang.Double.parseDouble;

public class CheckOut

{
  @FXML private Text guestName;
  @FXML private Text phoneNumber;
  @FXML private Text arrivalDate;
  @FXML private Text departureDate;
  @FXML private TextField discount;
  @FXML private Text totalPrice;
  private ViewHandler viewHandler;
  private BookingModelManager modelManager;
  private Region root;


  Booking currentBooking = new Booking(null, null, null);
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
  public void updateCheckOutPage() {
    guestName.setText("Guest name");
    phoneNumber.setText("Phone number");
    arrivalDate.setText("Arrival date");
    departureDate.setText("Departure date");
    discount.clear();
    totalPrice.setText("  ");
  }
  public void importButton() {

    String price = String.valueOf(currentBooking.getTotalPrice());

    currentBooking = viewHandler.getMainPageController().checkoutListTable.getSelectionModel().getSelectedItem();
    guestName.setText(currentBooking.getGuestName());
    phoneNumber.setText(currentBooking.getPhoneNumber());
    arrivalDate.setText(currentBooking.getArrivalDate().toString());
    departureDate.setText(currentBooking.getDepartureDate().toString());
    totalPrice.setText(price);
  }
  public void discount() {

    if (discount.getText() != null) {
      currentBooking.applyDiscount(parseDouble(discount.getText()));
     totalPrice.setText(String.valueOf(currentBooking.getTotalPrice()));
    }
  }

}
