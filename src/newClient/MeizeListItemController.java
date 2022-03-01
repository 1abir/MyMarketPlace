package newClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import util.NetworkUtil;

public class MeizeListItemController {

    private String productId;
    private NetworkUtil nc;
    private Main main;
    private String fromWhich;

    @FXML
    TextArea textArea;

    @FXML
    Label message;



    @FXML
    void buyMeize(ActionEvent event) {
        nc.write("buybutton" + Client.token + fromWhich + Client.token + productId+Client.token+main.getName());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thanks for buying");
        alert.setHeaderText("Enjoy fresh crpos directly from fermer");
        alert.setContentText("Enjoy shopping");
        alert.showAndWait();
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setNc(NetworkUtil nc) {
        this.nc = nc;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setFromWhich(String fromWhich) {
        this.fromWhich = fromWhich;
    }
}
