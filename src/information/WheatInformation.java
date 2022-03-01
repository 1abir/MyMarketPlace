package information;

import server.Server;
import sms.hu.ozekisms.TestTcpSms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WheatInformation {
    public static final String WHEAT_FILE = "src/information/wheatInformation.txt";
    public static ArrayList<String> wheatInfomationList = new ArrayList<>();

    private String userName;
    private int productId;
    private String type;
    private String mobileNumber;
    private String price;
    private String stock;


    public static boolean loadWheat() {

        boolean flag = true;
        BufferedReader br = null;
        try {
            String line;
            //String[] strings;
            br = new BufferedReader(new FileReader(WHEAT_FILE));
            while (true) {
                line = br.readLine();
                String[] strings;
                if (line == null) break;
                strings = line.split(Server.token);
                if (strings.length == 6)
                    wheatInfomationList.add(line);
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

    public static boolean saveWheat() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(WHEAT_FILE));
            for (String wheatInf : wheatInfomationList) {
                bw.write(wheatInf);
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

    public WheatInformation(String userName, int productId, String type, String mobileNumber, String price, String stock) {
        this.userName = userName;
        this.productId = productId;
        this.type = type;
        this.mobileNumber = mobileNumber;
        this.price = price;
        this.stock = stock;
    }
    @Override
    public String toString() {
        return userName+Server.token+String.valueOf(productId)+Server.token+mobileNumber+Server.token+type+
                Server.token+price+Server.token+stock;
    }
    public static void addToArrayList(WheatInformation wheatInformation)
    {
        wheatInfomationList.add(wheatInformation.toString());
        saveWheat();
    }
    public static void addToArrayList(String s)
    {
        try {
            String [] strings=s.split(Server.token);
            WheatInformation wheatInformation=new WheatInformation(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]);
            addToArrayList(wheatInformation);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void removeProduct(String s, Infomation infomation) {

        for (String wheat:wheatInfomationList)
        {
            String [] strings = wheat.split(Server.token);
            if (strings[1].equals(s))
            {
//                wheatInfomationList.remove(wheat);
//                WheatInformation.saveWheat();

                String byerMobi=infomation.getMobileNumber();
                String byerAddress=infomation.getAddress();
                String byerName=infomation.getUsername();
                String Message="Dear "+strings[0]+",\n Your Wheat of type :"+strings[3]+"Amount : "+strings[5]+" is bought by : "+byerName+"\n Mobile Number: "+
                        byerMobi+"\n Address : "+byerAddress;
                Infomation in=Infomation.getInfomationFromName(strings[0]);
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
