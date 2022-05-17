package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import main.HotelManagerGUI;
import model.*;

import java.util.ArrayList;

public class MainPageController {
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

    @FXML
    private TextField searchField;
    @FXML
    private TableView<Room> roomsListTable;
    @FXML
    private TableColumn<Room, Integer> roomNoColumn;
    @FXML
    private TableColumn<Room, Integer> priceColumn;
    @FXML
    private TableColumn<Room, Integer> capacityColumn;
    @FXML
    private Button bookButton;
    @FXML
    private DatePicker arrivalDate;
    @FXML
    private DatePicker departureDate;
    @FXML
    private ComboBox<String> roomType;
    @FXML
    private Button searchButton;
    @FXML
    private TextField resetButton;
    @FXML
    MenuItem exitMenuItem;
    @FXML
    MenuItem helpMenuItem;
    @FXML TableView<Booking> bookingListTable;
    @FXML TableColumn<Booking, String> guestColumn;
    @FXML TableColumn<Booking, String> phoneNoColumn;
    @FXML TableColumn<Booking, Date>   arrivalDateColumn;
    @FXML TableColumn<Booking, Date>   departureDateColumn;
    @FXML TableColumn<Booking, Integer>   roomNoColumn2;

    public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root) {
        this.viewHandler = viewHandler;
        this.modelManager = modelManager;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    public void initialize() {
        modelManager = new BookingModelManager("hotel.bin");
        roomNoColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("roomNumber"));
        roomNoColumn.setPrefWidth(90);
        priceColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("price"));
        priceColumn.setPrefWidth(90);
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
        capacityColumn.setPrefWidth(72);
        //Booking list table columns
        guestColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("guestName"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("phoneNumber"));
        arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<Booking, Date>("arrivalDate"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<Booking, Date>("departureDate"));
        roomNoColumn2.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("roomNumber"));
        //Check out table columns

        updateRoomsArea();
        updateRoomTypes();
        updateBookingArea();


    }

    public void reset() {
        updateRoomTypes();
        roomType.getSelectionModel().clearSelection();
        updateBookingArea();
        updateRoomsArea();
    }

    public void bookButton(ActionEvent event) {
        if (this.arrivalDate.getValue() != null && this.departureDate.getValue() != null && roomsListTable.getSelectionModel().getSelectedItem() != null) {
            Date arrivalDate = new Date(this.arrivalDate.getValue().getDayOfMonth(), this.arrivalDate.getValue().getMonthValue(), this.arrivalDate.getValue().getYear());
            Date departureDate = new Date(this.departureDate.getValue().getDayOfMonth(), this.departureDate.getValue().getMonthValue(), this.departureDate.getValue().getYear());
            Booking booking = new Booking(roomsListTable.getSelectionModel().getSelectedItem(), arrivalDate, departureDate);
            Hotel hotel = modelManager.load();
            hotel.addBooking(booking);
            modelManager.save(hotel);
            viewHandler.openView("Bookings");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fill out all fields!");
            alert.setHeaderText(null);
            alert.showAndWait();

        }
    }

    public void updateBookingArea() {
        bookingListTable.getItems().clear();
        BookingList bookings = modelManager.load().getAllBookings();
        for (int i=0; i< bookings.size(); i++) {
            bookingListTable.getItems().add(bookings.getBookingByIndex(i));
        }
    }
    public void checkInButton(ActionEvent event) {
        viewHandler.openView("RegisterGuestDetails");
    }

    private void updateRoomTypes() {
        roomType.getItems().clear();
        RoomList roomList1 = modelManager.load().getAllRooms();

        //ArrayList<String> types = new ArrayList<>();

        for (int i = 0; i < roomList1.size(); i++) {
            if (!roomType.getItems().contains(roomList1.getRoom(i).getType())) {
                roomType.getItems().add(roomList1.getRoom(i).getType());
            }
        }
        roomType.setPromptText("Room type");
    }

    public void searchButton() {
        roomsListTable.getItems().clear();
        roomList = modelManager.load().getAllRooms();

        RoomList searchRoom = modelManager.load().getAvailableRooms(new Date(arrivalDate.getValue().getDayOfMonth(), arrivalDate.getValue().getMonthValue(), arrivalDate.getValue().getYear()),
                new Date(departureDate.getValue().getDayOfMonth(), departureDate.getValue().getMonthValue(), departureDate.getValue().getYear()), roomType.getSelectionModel().getSelectedItem());
//    for (int i=0; i<roomList.size(); i++) {
//      if (roomType.getSelectionModel().getSelectedItem().equals(roomList.getRoom(i).getType())) {
//        searchRoom.addRoom(roomList.getRoom(i));
//      }
//    }
        for (int i = 0; i < searchRoom.size(); i++) {
            roomsListTable.getItems().add(searchRoom.getRoom(i));
        }
    }

    public void updateRoomsArea() {
        roomsListTable.getItems().clear();
        RoomList rooms = modelManager.load().getAllRooms();

        for (int i = 0; i < rooms.size(); i++) {
            roomsListTable.getItems().add(rooms.getRoom(i));
        }
    }

    public void resetSearch() {
        arrivalDate.getEditor().clear();
        departureDate.getEditor().clear();
        roomsListTable.getItems().clear();
        updateRoomsArea();
        updateRoomTypes();
    }

    public void handleActions(ActionEvent event) {
        if (event.getSource() == exitMenuItem) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Do you really want to exit the program?",
                    ButtonType.YES, ButtonType.NO);
            alert.setTitle("Exit");
            alert.setHeaderText(null);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                System.exit(0);
            }
        } else if (event.getSource() == helpMenuItem) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Help");
            alert.setContentText("Contact Support");
            alert.showAndWait();
        }

    }
}
