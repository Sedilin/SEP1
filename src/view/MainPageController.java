package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;

public class MainPageController
{
  private Region root;
  private BookingModelManager modelManager;
  private RoomList roomList;
  private GuestList guestList;
  private BookingList bookingList;

  //* Room list page

  //

  @FXML private TextField searchField;
  @FXML private TableView<Guest> guestListTable;
  @FXML private TableColumn<Guest,String> RoomNoColumn;
  @FXML private TableColumn<Guest,String> PriceColumn;
  @FXML private TableColumn<Guest,String> CapacityColumn;
  @FXML private Button bookButton;
  @FXML private DatePicker arrivalDate;
  @FXML private DatePicker departureDate;
  @FXML private ToggleButton roomType;
  @FXML private Button searchButton;
  @FXML private TextField resetButton;

}
