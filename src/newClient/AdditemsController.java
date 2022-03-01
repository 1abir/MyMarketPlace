package newClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import util.NetworkUtil;

public class AdditemsController {

    private Main main;
    private NetworkUtil nc;
    String fromWhichPage;

    @FXML
    private TextField Cropname;

    @FXML
    private TextField Type;

    @FXML
    private TextField Price;

    @FXML
    private TextField Stock;

    @FXML
    private Button AddButton;
    @FXML
    private Button BackButton;

    @FXML
    void AddButtonAction(ActionEvent event) {

        String CropName=Cropname.getText();
        String type=Type.getText();
        String price=Price.getText();
        String stock=Stock.getText();
        if (CropName!=null&&type!=null&&price!=null&&stock!=null) {
            String concated = "addbutton" + Client.token + main.getName() + Client.token + CropName + Client.token + type + Client.token + price + Client.token + stock;
            nc.write(concated);
            System.out.println("written concated");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Item Added");
            alert.setHeaderText("Thanks for Adding your crop");
            alert.setContentText("Your product has been successfully added");
            alert.showAndWait();
            Cropname.setText("");
            Price.setText("");
            Stock.setText("");
            Type.setText("");
        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Item wasn't Added");
            alert.setHeaderText("fill up all the boxs");
            alert.setContentText("You can't leave box Empty");
            alert.showAndWait();
        }


    }
    @FXML
    void BackButtonAction(ActionEvent event) {
        if (fromWhichPage.equals("maize"))
        {
            try {
                main.showMaize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (fromWhichPage.equals("rice"))
        {
            try {
                main.showRice();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(fromWhichPage.equals("wheat"))
        {
            try {
                main.showWheat();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (fromWhichPage.equals("others"))
        {
            try {
                main.showOthersCrop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                main.showCropsHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setNc(NetworkUtil nc) {
        this.nc = nc;
    }
}
