package newClient;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import util.NetworkUtil;

public class MessagesController {
    private Main main;
    private NetworkUtil nc;

    @FXML
    TextArea mesageTextArea;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setNc(NetworkUtil nc) {
        this.nc = nc;
    }



}
