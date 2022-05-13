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
import model.*;

public class BookingsController
{
    private Region root;
    private BookingModelManager modelManager;
    private ViewHandler viewHandler;

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

    public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
    {
        this.viewHandler = viewHandler;
        this.modelManager = modelManager;
        this.root = root;
        reset();
    }
    public void reset()
    {
        updateGuestTable();
    }
    public Region getRoot()
    {
        return root;
    }
    public void initialize()
    {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("phoneNumber"));

    }
    public void handleForBookings(ActionEvent event)
    {
        if (event.getSource() == bookButton)
        {
            Hotel hotel = modelManager.load();
            if (!(firstNameField.getText().equals("")) && !(lastNameField.getText().equals("")) && !(phoneNumberField.getText().equals(""))) {
                Guest guest = new Guest(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText());

                if (guestListTable.getItems().size() == 0)
                {
                    guestListTable.getItems().add(guest);
                    hotel.addGuest(guest);
                    modelManager.save(hotel);
                }
                else
                {
                    for (int i = 0; i < guestListTable.getItems().size(); i++) {
                        if (!(guestListTable.getItems().get(i).getFirstName().equals(firstNameField.getText()) && guestListTable.getItems().get(i).getLastName().equals(lastNameField.getText()))) {
                            updateGuestTable();
                            guestListTable.getItems().add(guest);
                            hotel.addGuest(guest);
                            modelManager.save(hotel);
                        }
                    }
                }
            }
            viewHandler.openView("MainPage");
        }
        else if (event.getSource() == newGuestButton)
        {
            firstNameField.setDisable(false);
            lastNameField.setDisable(false);
            phoneNumberField.setDisable(false);
        }
    }

    public void addGuestDetailsToTextField()
    {
        Guest guest = guestListTable.getSelectionModel().getSelectedItem();
        firstNameField.setText(guest.getFirstName());
        lastNameField.setText(guest.getLastName());
        phoneNumberField.setText(guest.getPhoneNumber());
    }
    public void updateGuestTable()
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
