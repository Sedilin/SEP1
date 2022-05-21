package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.BookingModelManager;

import java.io.IOException;

/**
 * A class that handles switches between scenes
 */
public class ViewHandler {
    private Scene scene;
    private Stage window;
    private MainPageController mainPageController;
    private BookingsController bookingsController;
    private RegisterGuestDetailsController registerGuestDetailsController;
    private CheckOutController checkOut;

    private BookingModelManager modelManager;

    /**
     * Initializes modelManager and a new scene
     * @param modelManager
     * @throws IOException indicates a failure in reading from the file
     */
    public ViewHandler (BookingModelManager modelManager) throws IOException
    {
        this.modelManager = modelManager;
        scene = new Scene(new Region());
    }

    /**
     * Passes the created stage object to the viewHandler and opens the mainView
     * @param window stage object
     */
    public void start(Stage window)
    {
        this.window = window;
        openView("MainPage");
    }

    /**
     * Switches between scenes basing on their ID
     * @param id ID of the scenes
     */
    public void openView(String id)
    {
        Region root = null;
        switch (id)
        {
            case "MainPage":
                root = loadMainPage();
                break;
            case "RegisterGuestDetails":
                root = loadRegisterGuestDetails();
                break;
            case "Bookings":
                root = loadBookings();
                break;
            case "CheckOut":
                root = loadCheckOut();
                break;
        }
        scene.setRoot(root);
        String title = "";
        if (root.getUserData() != null)
        {
            title += root.getUserData();
        }

        window.setTitle(title);
        window.setScene(scene);
        window.setWidth(root.getPrefWidth());
        window.setHeight(root.getPrefHeight());
        window.show();
    }

    private Region loadBookings() {
        if (bookingsController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Bookings.fxml"));
                Region root = loader.load();
                bookingsController = loader.getController();
                bookingsController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            bookingsController.reset();
        }
        return bookingsController.getRoot();
    }
    private Region loadMainPage()
    {
        if (mainPageController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MainPage.fxml"));
                Region root = loader.load();
                mainPageController = loader.getController();
                mainPageController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            mainPageController.reset();
        }
        return mainPageController.getRoot();
    }
    private Region loadRegisterGuestDetails()
    {
        if (registerGuestDetailsController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("RegisterGuestDetails.fxml"));
                Region root = loader.load();
                registerGuestDetailsController = loader.getController();
                registerGuestDetailsController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            registerGuestDetailsController.reset();
        }
        return registerGuestDetailsController.getRoot();
    }
    private Region loadCheckOut()
    {
        if (checkOut == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CheckOut.fxml"));
                Region root = loader.load();
                checkOut = loader.getController();
                checkOut.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            checkOut.reset();
        }
        return checkOut.getRoot();
    }

    /**
     * Returns mainPageController
     * @return mainPageController
     */
    public MainPageController getMainPageController()
    {
        return mainPageController;
    }

    /**
     * Returns registerGuestDetailsController
     * @return registerGuestDetailsController
     */
    public RegisterGuestDetailsController getRegisterGuestDetailsController()
    {
        return registerGuestDetailsController;
    }
}
