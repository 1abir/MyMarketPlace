package newClient;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.control.*;
        import util.NetworkUtil;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class RiceController {
    private Main main;
    private NetworkUtil nc;
    private List<String> productIdList=new ArrayList<>();
    private List<String> riceList;
    private ObservableList<String> observableStringList;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setNc(NetworkUtil nc) {
        this.nc = nc;
    }

    @FXML
    private TextArea RiceList;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;


    @FXML
    private TextField CropsType;

    @FXML
    private TextField ProductId;

    @FXML
    private Button buyButton;

    @FXML
    private ListView<String> RiceListView;



    @FXML
    void addButtonAction(ActionEvent event) {
        try {
            main.showAddItem("rice");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backButtonAction(ActionEvent event) {
        try {
            main.showCropsHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buyButtonAction(ActionEvent event) {
        String productId=ProductId.getText();
        System.out.println(productIdList);
        if (productIdList.contains(productId))
        {
            nc.write("buybutton" + Client.token + "rice" + Client.token + productId+Client.token+main.getName());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thanks for buying");
            alert.setHeaderText("Enjoy fresh crpos directly from fermer");
            alert.setContentText("Enjoy shopping");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect ProductId");
            alert.setHeaderText("Product ID is Invalid");
            alert.setContentText("Give a valid product Id");
            alert.showAndWait();
        }
    }

    void loadRiceList()
    {
        riceList=new ArrayList<>();
        nc.write("sendlist"+Client.token+"rice");
        riceList =(ArrayList<String>)nc.read();
        observableStringList= FXCollections.observableList(riceList);
        changeListView(RiceListView,observableStringList);
        System.out.println(riceList);
    }
    void changeListView(ListView<String> meizeListView, ObservableList<String> stringList) {

        meizeListView.setItems(stringList);
        meizeListView.setCellFactory((list) ->new ListCell<String>()
        {
            Node root;
            MeizeListItemController cellController;

            {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("meizeListItem.fxml"));

                    root = loader.load();
                    cellController = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                //
                super.updateItem(item, empty);
                if (item == null || empty) {

                    setGraphic(null);
                } else {
                    //
                    String[] strings=item.split(Client.token);
                    if (strings.length==6)
                    {
                        cellController.textArea.setEditable(false);
                        cellController.textArea.setText("Farmer's Name : "+strings[0]+"\n"+"Product Id : "+strings[1]+"\n"+
                                "Mobile Number : "+ strings[2]+"\n"+"RiceType : "+strings[4]+"\n"+"Price : "+strings[3]+"\n"+
                                "Stock weight : "+strings[5]+" KG\n");
                        cellController.setProductId(strings[1]);
                        cellController.setNc(nc);
                        cellController.setMain(main);
                        cellController.setFromWhich("rice");
                        productIdList.add(strings[1]);
                    }

                    setGraphic(root);
                }
            }
        });
    }

}