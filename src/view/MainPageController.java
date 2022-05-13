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

public class MainPageController
{
  private Region root;
  private BookingModelManager modelManager;
  private RoomList roomList;
  private GuestList guestList;
  private BookingList bookingList;
  private ViewHandler viewHandler;

  /**
   * A GUI tab containing components for displaying a list of rooms.
   * @author Diana Stratan
   * @version 1.0
   */

  @FXML private TextField searchField;
  @FXML private TableView<Room> roomsListTable;
  @FXML private TableColumn<Room,Integer> roomNoColumn;
  @FXML private TableColumn<Room, Integer> priceColumn;
  @FXML private TableColumn<Room, Integer> capacityColumn;
  @FXML private Button bookButton;
  @FXML private DatePicker arrivalDate;
  @FXML private DatePicker departureDate;
  @FXML private ComboBox<String> roomType;
  @FXML private Button searchButton;
  @FXML private TextField resetButton;

  public void initialize() {
    modelManager = new BookingModelManager("hotel.bin");
    roomNoColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("roomNumber"));
    roomNoColumn.setPrefWidth(90);
    priceColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("price"));
    priceColumn.setPrefWidth(90);
    capacityColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
    capacityColumn.setPrefWidth(72);

    updateRoomsArea();
    updateRoomTypes();
  }
  public void reset() {
    updateRoomTypes();
  }
  public void bookButton(ActionEvent event) {
    viewHandler.openView("Bookings");
  }
  public void checkInButton(ActionEvent event)
  {
    viewHandler.openView("RegisterGuestDetails");
  }
  private void updateRoomTypes()
  { roomType.getItems().clear();
    RoomList roomList1 = modelManager.load().getAllRooms();
    //ArrayList<String> types = new ArrayList<>();
    for (int i = 0; i < roomList1.size(); i++)
    {
      if (!roomType.getItems().contains(roomList1.getRoom(i).getType())) {
        roomType.getItems().add(roomList1.getRoom(i).getType());
      }
    }
  }

  public void updateRoomsArea()
  {
    roomsListTable.getItems().clear();
    RoomList rooms = modelManager.load().getAllRooms();

    for(int i = 0; i<rooms.size(); i++)
    {
      roomsListTable.getItems().add(rooms.getRoom(i));
    }
  }

  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  { this.viewHandler=viewHandler;
    this.modelManager=modelManager;
    this.root=root;
  }

  public Region getRoot()
  { return root;
  }
}
