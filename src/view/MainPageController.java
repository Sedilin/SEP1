package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import main.HotelManagerGUI;
import model.*;


import java.time.chrono.*;
import java.time.*;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

/**
 * A controller for the mainPage view
 * @author Lukasz, Diana, Gabriela
 */
public class MainPageController
{
  private Region root;
  private BookingModelManager modelManager;
  private ViewHandler viewHandler;

  private RoomList roomList;
  private GuestList guestList;
  private BookingList bookingList;

  /**
   * A GUI tab containing components for displaying a list of rooms.
   *
   * @author Diana Stratan
   * @version 1.0
   */

  @FXML private TextField searchFieldCheckOut;
  @FXML private TextField searchFieldBookings;
  @FXML private TableView<Room> roomsListTable;
  @FXML private TableColumn<Room, Integer> roomNoColumn;
  @FXML private TableColumn<Room, Integer> priceColumn;
  @FXML private TableColumn<Room, Integer> capacityColumn;
  @FXML private Button bookButton;
  @FXML private Button deleteButton;
  @FXML private DatePicker arrivalDate;
  @FXML private DatePicker departureDate;
  @FXML private ComboBox<String> roomType;
  @FXML private Button searchButton;
  @FXML private TextField resetButton;
  @FXML MenuItem exitMenuItem;
  @FXML MenuItem helpMenuItem;
  //booking list table
  @FXML TableView<Booking> bookingListTable;
  @FXML TableColumn<Booking, String> guestColumn;
  @FXML TableColumn<Booking, String> phoneNoColumn;
  @FXML TableColumn<Booking, Date> arrivalDateColumn;
  @FXML TableColumn<Booking, Date> departureDateColumn;
  @FXML TableColumn<Booking, Integer> roomNoColumn2;
  //check out table
  @FXML TableView<Booking> checkoutListTable;
  @FXML TableColumn<Booking, String> guestColumn2;
  @FXML TableColumn<Booking, String> phoneNoColumn2;
  @FXML TableColumn<Booking, Date> arrivalDateColumn2;
  @FXML TableColumn<Booking, Date> departureDateColumn2;
  @FXML TableColumn<Booking, Integer> roomNoColumn3;
  @FXML Button payAndCheckOut;


  /**
   * Initializes viewHandler object, modelManager object and root object
   * @param viewHandler view handler
   * @param modelManager model manager
   * @param root root
   */
  public void init(ViewHandler viewHandler, BookingModelManager modelManager,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.root = root;
  }

  /**
   * Returns the root layout of the controller's view
   * @return root of the controller's view
   */
  public Region getRoot()
  {
    return root;
  }

  /**
   * Assigns values to table columns for room list, booking list and check out
   * Updates the tables
   */
  public void initialize()
  {
    modelManager = new BookingModelManager("hotel.bin");
    //Room list table columns
    roomNoColumn.setCellValueFactory(
        new PropertyValueFactory<Room, Integer>("roomNumber"));
    roomNoColumn.setPrefWidth(90);
    priceColumn.setCellValueFactory(
        new PropertyValueFactory<Room, Integer>("price"));
    priceColumn.setPrefWidth(90);
    capacityColumn.setCellValueFactory(
        new PropertyValueFactory<Room, Integer>("capacity"));
    capacityColumn.setPrefWidth(72);
    //Booking list table columns
    guestColumn.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("guestName"));
    phoneNoColumn.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("phoneNumber"));
    arrivalDateColumn.setCellValueFactory(
        new PropertyValueFactory<Booking, Date>("arrivalDate"));
    departureDateColumn.setCellValueFactory(
        new PropertyValueFactory<Booking, Date>("departureDate"));
    roomNoColumn2.setCellValueFactory(
        new PropertyValueFactory<Booking, Integer>("roomNumber"));
    //Check out table columns
    guestColumn2.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("guestName"));
    phoneNoColumn2.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("phoneNumber"));
    arrivalDateColumn2.setCellValueFactory(
        new PropertyValueFactory<Booking, Date>("arrivalDate"));
    departureDateColumn2.setCellValueFactory(
        new PropertyValueFactory<Booking, Date>("departureDate"));
    roomNoColumn3.setCellValueFactory(
        new PropertyValueFactory<Booking, Integer>("roomNumber"));

    updateRoomsArea();
    updateRoomTypes();

    updateBookingArea();

    updateCheckOutArea();

  }

  /**
   * Updates the room types, booking list table, room list table and check out table
   */

  public void reset()
  {
    updateRoomTypes();
    roomType.getSelectionModel().clearSelection();
    updateBookingArea();
    updateRoomsArea();
    updateCheckOutArea();
  }

  /**
   * Action event for book button
   * Alert Error dialog displayed in case of unfulfilled requirements
   */
  public void bookButton(ActionEvent event)
  {
    ChronoLocalDate currentDate = LocalDate.from(ZonedDateTime.now());
    if (this.arrivalDate.getValue() != null && !(this.arrivalDate.getValue()
        .isBefore(currentDate)) && this.departureDate.getValue() != null
        && roomsListTable.getSelectionModel().getSelectedItem() != null)
    {
      Date arrivalDate = new Date(this.arrivalDate.getValue().getDayOfMonth(),
          this.arrivalDate.getValue().getMonthValue(),
          this.arrivalDate.getValue().getYear());
      Date departureDate = new Date(
          this.departureDate.getValue().getDayOfMonth(),
          this.departureDate.getValue().getMonthValue(),
          this.departureDate.getValue().getYear());
      Booking booking = new Booking(
          roomsListTable.getSelectionModel().getSelectedItem(), arrivalDate,
          departureDate);
      Hotel hotel = modelManager.load();
      hotel.addBooking(booking);
      modelManager.save(hotel);
      viewHandler.openView("Bookings");
    }

    else
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Wrong date compatibility or fields are not filled. ");
      alert.setHeaderText(null);
      alert.showAndWait();

    }
  }

  public void deleteButton(ActionEvent event)
  {
    Hotel hotel = modelManager.load();
    hotel.getAllBookings().removeBooking(bookingListTable.getSelectionModel().getSelectedItem());
    modelManager.save(hotel);
    updateBookingArea();
  }

  /**
   * Action event for searching booking on the checkout page
   */
  public void searchCheckedOutBookings(ActionEvent event) {
    if (modelManager.load().findBookingsByPhoneNumber(searchFieldCheckOut.getText()) != null)
    {
      BookingList searchBooking = modelManager.load().findBookingsByPhoneNumber(searchFieldCheckOut.getText());
      checkoutListTable.getItems().clear();
      for (int i=0; i<searchBooking.size(); i++) {
        checkoutListTable.getItems().add(searchBooking.getCheckedInBookings().getBookingByIndex(i));
      }
    }
  }

  /**
   * Action event for searching booking on booking list page
   */

    public void searchBookings(ActionEvent event) {
        if (modelManager.load().findBookingsByPhoneNumber(searchFieldBookings.getText()) != null)
        {
            BookingList searchBooking = modelManager.load().findBookingsByPhoneNumber(searchFieldBookings.getText());
            bookingListTable.getItems().clear();
            for (int i=0; i<searchBooking.size(); i++) {
                bookingListTable.getItems().add(searchBooking.getNotCheckedInBookings().getBookingByIndex(i));
            }
        }
    }

  /**
   * Updates the booking list table
   */

  public void updateBookingArea()
  {
    bookingListTable.getItems().clear();
    BookingList bookings = modelManager.load().getAllBookings().getNotCheckedInBookings();
    for (int i = 0; i < bookings.size(); i++)
    {
      bookingListTable.getItems().add(bookings.getBookingByIndex(i));
    }
  }

  /**
   * Updates the checkout table
   */

  public void updateCheckOutArea()
  {
    checkoutListTable.getItems().clear();
    BookingList checkIn = modelManager.load().getAllBookings().getCheckedInBookings();
    for (int i = 0; i < checkIn.size(); i++)
    {
      checkoutListTable.getItems().add(checkIn.getBookingByIndex(i));
    }
  }

  /**
   * Action event which opens Register Guest Details page
   * @param event checkInButton
   */
  public void checkInButton(ActionEvent event)
  {
    viewHandler.openView("RegisterGuestDetails");
  }

  /**
   * Updates the room type combo box
   */
  private void updateRoomTypes()
  {
    roomType.getItems().clear();
    RoomList roomList1 = modelManager.load().getAllRooms();

    //ArrayList<String> types = new ArrayList<>();

    for (int i = 0; i < roomList1.size(); i++)
    {
      if (!roomType.getItems().contains(roomList1.getRoom(i).getType()))
      {
        roomType.getItems().add(roomList1.getRoom(i).getType());
      }
    }
    roomType.setPromptText("Room type");
  }

  /**
   * Searches the room availability and displays it to the room list table
   */

  public void searchButton()
  {

    roomsListTable.getItems().clear();
    Hotel hotel = modelManager.load();
    roomList = hotel.getAllRooms();

    RoomList searchRoom = hotel.getAvailableRooms(
        new Date(arrivalDate.getValue().getDayOfMonth(),
            arrivalDate.getValue().getMonthValue(),
            arrivalDate.getValue().getYear()),
        new Date(departureDate.getValue().getDayOfMonth(),
            departureDate.getValue().getMonthValue(),
            departureDate.getValue().getYear()),
        roomType.getSelectionModel().getSelectedItem());
    //    for (int i=0; i<roomList.size(); i++) {
    //      if (roomType.getSelectionModel().getSelectedItem().equals(roomList.getRoom(i).getType())) {
    //        searchRoom.addRoom(roomList.getRoom(i));
    //      }
    //    }
    for (int i = 0; i < searchRoom.size(); i++)
    {
      roomsListTable.getItems().add(searchRoom.getRoom(i));
    }
  }

  /**
   * Updates the room list table
   */

  public void updateRoomsArea()
  {
    roomsListTable.getItems().clear();
    RoomList rooms = modelManager.load().getAllRooms();

    for (int i = 0; i < rooms.size(); i++)
    {
      roomsListTable.getItems().add(rooms.getRoom(i));
    }
  }

  /**
   * Clears all the fields and updates the room type and room list table
   */

  public void resetSearch()
  {
    arrivalDate.getEditor().clear();
    departureDate.getEditor().clear();
    roomsListTable.getItems().clear();
    updateRoomsArea();
    updateRoomTypes();
  }

  /**
   * viewHandler opens a new page CheckOut
   */

  public void payAndCheckOutButton()
  {
    viewHandler.openView("CheckOut");
  }

  /**
   * Action event which displays alert dialogs for the menu items in the menu bar
   */

  public void handleActions(ActionEvent event)
  {
    if (event.getSource() == exitMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to exit the program?", ButtonType.YES,
          ButtonType.NO);
      alert.setTitle("Exit");

      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        System.exit(0);
      }
    }
    else if (event.getSource() == helpMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText(null);

      alert.setTitle("Help");
      alert.setContentText("Please get in touch and our support team " + "\n" + "will help you solve the issue!" + "\n" +"\n" +
          "Phone number: 25792799 ");
      alert.showAndWait();
    }
  }
}
