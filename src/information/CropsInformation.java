package information;

import server.Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CropsInformation {




    public static int productId;

    public static final String PRODUCT_ID_FILE = "src/information/prductId.txt";






    public static boolean loadProductId() {

        BufferedReader br = null;
        try {
            String line;
            //String[] strings;
            br = new BufferedReader(new FileReader(PRODUCT_ID_FILE));

            line = br.readLine();
            Integer integer = Integer.parseInt(line);
            if (integer != null) {
                productId = integer;
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean saveProductId() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(PRODUCT_ID_FILE));
            String pid = String.valueOf(productId);
            System.out.println("saving productId"+pid);
            bw.write(pid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static void updateProductId()
    {
        productId++;
        saveProductId();
    }
    public static void loadAll()
    {
        MaizeInformation.loadMaize();
        RiceInformation.loadRice();
        WheatInformation.loadWheat();
        loadProductId();
        OthersInformation.loadOthers();
    }

}
