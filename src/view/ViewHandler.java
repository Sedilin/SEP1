package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.BookingModelManager;

import java.io.IOException;

public class ViewHandler {
    private Scene scene;
    private Stage window;
    private MainPageController mainPageController;
    private BookingsController bookingsController;
    private RegisterGuestDetailsController registerGuestDetailsController;

    private BookingModelManager modelManager;

    public ViewHandler (BookingModelManager modelManager) throws IOException
    {
        this.modelManager = modelManager;
        scene = new Scene(new Region());
    }
    public void start(Stage window)
    {
        this.window = window;
        openView("MainPage");
    }
    public void openView(String id)
    {
        Region root = null;
        switch (id)
        {
            case "MainPage":
                root = loadMainPage();
                break;
//            case "RegisterGuestDetails":
//                root = loadRegisterGuestDetails();
//                break;
            case "Bookings":
                root = loadBookings();
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
//    private Region loadRegisterGuestDetails()
//    {
//        if (registerGuestDetailsController == null) {
//            try {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("RegisterGuestDetails.fxml"));
//                Region root = loader.load();
//                registerGuestDetailsController = loader.getController();
//                registerGuestDetailsController.init(this, modelManager, root);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            registerGuestDetailsController.reset();
//        }
//        return registerGuestDetailsController.getRoot();
//    }
}
