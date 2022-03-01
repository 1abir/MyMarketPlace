package newClient;//package client;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.NetworkUtil;

public class Main extends Application {

    String serverAddress = "127.0.0.1";
    int serverPort = 51111;
   /* @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Market Place");
        primaryStage.setScene(new Scene(root, 901, 701));
        primaryStage.show();
    }*/
   Stage stage;
   private NetworkUtil nc;
   private String name;

    public Main() {
        nc = new NetworkUtil(serverAddress, serverPort);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        stage.setMaxHeight(771);
        stage.setMaxWidth(971);
        //stage.setFullScreen(false);
        showLoginPage();
        stage.setOnCloseRequest(event -> {
            System.out.println("sending rewuest to close");
            nc.write("requestOfClosing"+Client.token+"please close");
            nc.closeConnection();
        });
    }


    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LogInController.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LogInController controller = loader.getController();
        controller.setMain((this));
        controller.setNc(nc);


        // Set the primary stage
        stage.setTitle("Market Place");
        stage.setScene(new Scene(root, 901, 701));
        stage.show();
    }

    public void showRegistrationPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        // Loading the controller
        Controller controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);
        controller.setNc(nc);

        // Set the primary stage
        stage.setTitle("Market Place");
        stage.setScene(new Scene(root, 901, 701));
        stage.show();
    }
    public void showCropsHomePage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Crops Homepage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        CropsHomeController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Market Place");
        stage.setScene(new Scene(root, 901, 701));
        stage.show();
    }
    public void showMaize ()throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Maizee.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MaizeController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);
        controller.setNc(nc);
        controller.loadMaizeList();

        // Set the primary stage
        stage.setTitle("Market Place");
        stage.setScene(new Scene(root, 901, 701));
        stage.show();
    }
    public void showAddItem(String page)throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("additems.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AdditemsController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);
        controller.setNc(nc);
        controller.fromWhichPage=page;

        // Set the primary stage
        stage.setTitle("Market Place");
        stage.setScene(new Scene(root, 901, 701));
        stage.show();
    }

    public void showOthersCrop()throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("othres.fxml"));
        Parent root = loader.load();

        // Loading the controller
        OthersController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);
        controller.setNc(nc);
        controller.loadOthersList();

        // Set the primary stage
        stage.setTitle("Market Place");
        stage.setScene(new Scene(root, 901, 701));
        stage.show();
    }
    public void showRice()throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Rice1.fxml"));
        Parent root = loader.load();

        // Loading the controller
        RiceController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);
        controller.setNc(nc);
        controller.loadRiceList();

        // Set the primary stage
        stage.setTitle("Market Place");
        stage.setScene(new Scene(root, 901, 701));
        stage.show();
    }
    public void showWheat() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("wheat.fxml"));
        Parent root = loader.load();

        // Loading the controller
        WheatController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);
        controller.setNc(nc);
        controller.loadWheatList();

        // Set the primary stage
        stage.setTitle("Market Place");
        stage.setScene(new Scene(root, 901, 701));
        stage.show();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Main m = new Main();
        launch(args);
    }
}