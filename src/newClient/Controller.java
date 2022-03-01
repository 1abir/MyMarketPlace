package newClient;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.NetworkUtil;

public class Controller {
    private Main main;
    private NetworkUtil nc;
    @FXML
    private TextField Username;

    @FXML
    private TextField Email;

    @FXML
    private TextField MobileNumber;

    @FXML
    private PasswordField Password;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField Address;


    @FXML
    void RegisterButtonAction(ActionEvent event) {
        String username=Username.getText();
        String email=Email.getText();
        String password=Password.getText();
        String mobileNumber=MobileNumber.getText();
        String address=Address.getText();
       // Infomation newUser=new Infomation(username,email,password,mobileNumber,address);
        String RegistrationButton=username+ Client.token+email+ Client.token+password+ Client.token+mobileNumber+ Client.token+address;
        nc.write(("RegistrationButton"+ Client.token+RegistrationButton));
        try {
            String fromServer=(String) nc.read();
            String [] s=fromServer.split(Client.token);
            if (s[0].equals("RegistrationButton"))
                if(s[1].equals("true"))
                {
                    //nc .write(("validUser"+Client.token+RegistrationButton));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Successfull Registration");
                    alert.setHeaderText("You have registered " + "successfully");
                    alert.setContentText("Now you can log in to out site");
                    alert.showAndWait();
                    try {
                        main.showLoginPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect username  or or email");
                alert.setHeaderText("Incorrect username or or email");
                alert.setContentText("Change userName & Email");
                alert.showAndWait();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect username  or or email");
            alert.setHeaderText("Incorrect username or or email");
            alert.setContentText("The username and password you provided already exists.");
            alert.showAndWait();
            e.printStackTrace();
        }

        /*System.out.println(Infomation.infomationList.get(Infomation.infomationList.size()-1));
        System.out.println("The size of the information list is : "+ Infomation.infomationList.size());
        System.out.println(Infomation.saveInformation());*/

    }
    void setMain(Main main) {
        this.main = main;
    }

    public void setNc(NetworkUtil nc) {
        this.nc = nc;
    }
    @FXML
    void backButtonAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
