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

public class OthersController {
    private Main main;
    private NetworkUtil nc;
    private List<String> productIdList=new ArrayList<>();
    private List<String> othersList;
    private ObservableList<String> observableStringList;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setNc(NetworkUtil nc) {
        this.nc = nc;
    }

    @FXML
    private TextArea CropList;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;


    @FXML
    private ListView<String> OthersListView;



    @FXML
    void addButtonAction(ActionEvent event) {
        try {
            main.showAddItem("others");
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

    void loadOthersList()
    {
        othersList = new ArrayList<>();

        nc.write("sendlist"+Client.token+"others");
        othersList =(ArrayList<String>)nc.read();
        observableStringList = FXCollections.observableList(othersList);
        changeListView(OthersListView,observableStringList);
        System.out.println(othersList);
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
                    if (strings.length==7)
                    {
                        cellController.textArea.setEditable(false);
                        cellController.textArea.setText("Crops Type : "+strings[0]+"\n"+"Farmer's Name : "+strings[1]+"\n"+"Product Id : "+strings[2]+"\n"+
                                "Mobile Number : "+ strings[3]+"\n"+"Crop Type : "+strings[5]+"\n"+"Price : "+strings[4]+"\n"+
                                "Stock weight : "+strings[6]+" KG"+"\n");
                        cellController.setProductId(strings[2]);
                        cellController.setNc(nc);
                        cellController.setMain(main);
                        cellController.setFromWhich("others");
                        productIdList.add(strings[2]);
                    }
                    else {
                        System.out.println("length : " + strings.length + strings);
                    }

                    setGraphic(root);
                }
            }
        });
    }

}