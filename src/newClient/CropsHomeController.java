package newClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CropsHomeController {
    private Main main;
    @FXML
    private Button maize;

    @FXML
    private Button rice;

    @FXML
    private Button wheat;
    @FXML
    private Button othersButton;
    @FXML
    private Button LogoutButton;

    @FXML
    void OthersButtonAction(ActionEvent event) {
        try {
            main.showOthersCrop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void maizeAction(ActionEvent event) {
        try {
            main.showMaize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void riceAction(ActionEvent event) {
        try {
            main.showRice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void wheatAction(ActionEvent event) {
        try {
            main.showWheat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logOutButtonAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}

