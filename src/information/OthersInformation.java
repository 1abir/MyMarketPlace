package information;

import server.Server;
import sms.hu.ozekisms.TestTcpSms;

import java.io.*;
import java.util.ArrayList;

public class OthersInformation {

    public static final String OTHERS_FILE = "src/information/othersInformation.txt";
    public static ArrayList<String> othersInfomationList = new ArrayList<>();

    private String cropsName;
    private String userName;
    private int productId;
    private String type;
    private String mobileNumber;
    private String price;
    private String stock;

    public OthersInformation(String cropsName, String userName, int productId, String type, String mobileNumber, String price, String stock) {

        this.cropsName = cropsName;
        this.userName = userName;
        this.productId = productId;
        this.type = type;
        this.mobileNumber = mobileNumber;
        this.price = price;
        this.stock = stock;
    }


    public static boolean loadOthers() {

        boolean flag = true;
        BufferedReader br = null;
        try {
            String line;
            //String[] strings;
            br = new BufferedReader(new FileReader(OTHERS_FILE));
            while (true) {
                line = br.readLine();
                String[] strings;
                if (line == null) break;
                strings = line.split(Server.token);
                if (strings.length == 7)
                    othersInfomationList.add(line);
            }
            return true;
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
    public static boolean saveOthers (){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(OTHERS_FILE));
            for (String othersInf : othersInfomationList) {
                bw.write(othersInf);
                bw.newLine();
            }
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
    @Override
    public String toString() {
        return cropsName+Server.token+userName+Server.token+String.valueOf(productId)+Server.token+mobileNumber+Server.token+type+
                Server.token+price+Server.token+stock;
    }
    public static void addToArrayList(OthersInformation othersInformation)
    {
        othersInfomationList.add(othersInformation.toString());
        saveOthers();
    }
    public static void removeProduct(String pId, Infomation infomation)
    {
        for (String s:othersInfomationList)
        {
           String[] strings=  s.split(Server.token);
           if (strings[2].equals(pId))
           {
//               System.out.println("matched id of others");
//               othersInfomationList.remove(s);
//               saveOthers();

               String byerMobi=infomation.getMobileNumber();
               String byerAddress=infomation.getAddress();
               String byerName=infomation.getUsername();
               String Message="Dear "+strings[1]+",\n Your "+strings[0]+" of type :"+strings[4]+"\nAmount : "+strings[6]+" is bought by : "+byerName+"\n Mobile Number: "+
                       byerMobi+"\n Address : "+byerAddress;

               Infomation in=Infomation.getInfomationFromName(strings[1]);
                String mob=in.getMobileNumber();
                if(Integer.parseInt(mob)!=0){

                try {
                    TestTcpSms testTcpSms=new TestTcpSms(Message,mob);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                }

               break;
           }
        }
    }
}
