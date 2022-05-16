package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;

public class BookingsController {
    private Region root;
    private BookingModelManager modelManager;
    private ViewHandler viewHandler;

    private Booking currentBooking;

    @FXML
    private TextField searchField;
    @FXML
    private TableView<Guest> guestListTable;
    @FXML
    private TableColumn<Guest, String> firstNameColumn;
    @FXML
    private TableColumn<Guest, String> lastNameColumn;
    @FXML
    private TableColumn<Guest, String> phoneNumberColumn;
    @FXML
    private Button newGuestButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Button bookButton;

    public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root) {
        this.viewHandler = viewHandler;
        this.modelManager = modelManager;
        this.root = root;
        reset();
    }

    public void reset() {
        updateGuestTable();
    }

    public Region getRoot() {
        return root;
    }

    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("phoneNumber"));

    }

    public void handleForBookings(ActionEvent event) {
        if (event.getSource() == bookButton) {
            Hotel hotel = modelManager.load();
            if ((!(firstNameField.getText().equals("Guest")) && !(lastNameField.getText().equals("Not")) && !(phoneNumberField.getText().equals("Found"))) && (!(firstNameField.getText().equals("")) && !(lastNameField.getText().equals("")) && !(phoneNumberField.getText().equals("")))) {
                Guest guest = new Guest(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText());
                    hotel.getAllBookings().getLastBooking().addGuest(guest);
                    hotel.addGuest(guest);
                    System.out.println(modelManager.load().getAllBookings());
                    modelManager.save(hotel);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "The guest has been created.");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                    viewHandler.openView("MainPage");
                    viewHandler.getMainPageController().reset();

            }
        }
        else if (event.getSource() == newGuestButton)
        {
            firstNameField.setDisable(false);
            lastNameField.setDisable(false);
            phoneNumberField.setDisable(false);
        }
        else if(event.getSource() == searchField)
        {
            Guest searchGuest = new Guest("Guest", "Not", "Found");
            if (modelManager.load().findGuestByPhoneNumber(searchField.getText()) != null)
            searchGuest = modelManager.load().findGuestByPhoneNumber(searchField.getText());
            guestListTable.getItems().clear();
            guestListTable.getItems().add(searchGuest);
        }
    }

    public void addGuestDetailsToTextField()
    {
        Guest guest = guestListTable.getSelectionModel().getSelectedItem();
        firstNameField.setText(guest.getFirstName());
        lastNameField.setText(guest.getLastName());
        phoneNumberField.setText(guest.getPhoneNumber());
    }
    private void updateGuestTable()
    {
        guestListTable.getItems().clear();
        firstNameField.setDisable(true);
        lastNameField.setDisable(true);
        phoneNumberField.setDisable(true);
        firstNameField.clear();
        lastNameField.clear();
        phoneNumberField.clear();

        GuestList guests = modelManager.load().getAllGuests();

        for (int i = 0; i < guests.size(); i++) {
            guestListTable.getItems().add(guests.getGuest(i));
        }
    }
}
