package newClient;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.NetworkUtil;

public class LogInController {

    private Main main;
    private NetworkUtil nc;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Email;

    @FXML
    private Button SignIn;

    @FXML
    private Button SignUp;


    @FXML
    void SignInAction(ActionEvent event) {

        String email=Email.getText();
        String password=Password.getText();
        String loginString=email+ Client.token+password;
            nc.write("logInButton" + Client.token + loginString);

            String str = (String) nc.read();
        String[] tockens = null;
        try {
            tockens = str.split(Client.token);

        if (tockens[0].toLowerCase().equals("loginbutton") && tockens[1].toLowerCase().equals("true"))//Infomation.varifyLogin(email,password))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Successful Login");
                alert.setHeaderText("You are our part");
                alert.setContentText("Welcome to Our World");
                alert.showAndWait();
                main.setName(email);
                try {
                    main.showCropsHomePage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect User name or or email");
                alert.setHeaderText("Incorrect user name or email");
                alert.setContentText("Change User Name & Email");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect User name or or email");
            alert.setHeaderText("Incorrect user name or email");
            alert.setContentText("Change User Name & Email");
            alert.showAndWait();
        }

    }

    @FXML
    void SignUpAction(ActionEvent event) {
        try {
            main.showRegistrationPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main)
    {
        this.main=main;
    }

    public void setNc(NetworkUtil nc) {
        this.nc = nc;
    }
}
