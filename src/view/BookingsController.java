package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Booking;
import model.BookingModelManager;
import model.Guest;
import model.Hotel;

public class BookingsController
{
    private Region root;
    private BookingModelManager modelManager;

    private MyActionListener listener;

    private Booking currentBooking;

    @FXML private TextField searchField;
    @FXML private TableView<Guest> guestListTable;
    @FXML private TableColumn<Guest,String> firstNameColumn;
    @FXML private TableColumn<Guest,String> lastNameColumn;
    @FXML private TableColumn<Guest,String> phoneNumberColumn;
    @FXML private Button newGuestButton;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private Button bookButton;

    public void initialize()
    {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("phoneNumber"));
    }
    private class MyActionListener implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent e)
        {
            if (e.getSource() == newGuestButton)
            {

            }
        }
    }
}
