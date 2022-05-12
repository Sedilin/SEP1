package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import main.HotelManagerGUI;
import model.*;

public class MainPageController
{
  private Region root;
  private BookingModelManager modelManager;
  private RoomList roomList;
  private GuestList guestList;
  private BookingList bookingList;

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
  @FXML private ToggleButton roomType;
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

}
