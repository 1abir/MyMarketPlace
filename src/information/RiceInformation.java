package information;

import server.Server;
import sms.hu.ozekisms.TestTcpSms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RiceInformation {
    public static ArrayList<String> riceInfomationList = new ArrayList<>();
    public static final String RICE_FILE = "src/information/riceInformation.txt";

    private String userName;
    private int productId;
    private String type;
    private String mobileNumber;
    private String price;
    private String stock;

    public static boolean loadRice() {
        boolean flag = true;
        BufferedReader br = null;
        try {
            String line;
            //String[] strings;
            br = new BufferedReader(new FileReader(RICE_FILE));
            while (true) {
                line = br.readLine();
                String[] strings;
                if (line == null) break;
                strings = line.split(Server.token);
                if (strings.length == 6)
                    riceInfomationList.add(line);
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

    public static boolean saveRice() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(RICE_FILE));
            for (String riceInf : riceInfomationList) {
                bw.write(riceInf);
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public RiceInformation(String userName, int productId, String type, String mobileNumber, String price, String stock) {
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
    public static void addToArrayList(RiceInformation riceInformation)
    {
        riceInfomationList.add(riceInformation.toString());
        saveRice();
    }
    public static void addToArrayList(String s)
    {
        try {
            String [] strings=s.split(Server.token);
            RiceInformation riceInformation=new RiceInformation(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]);
            addToArrayList(riceInformation);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void removeProduct(String s,Infomation infomation) {
        for (String rice:riceInfomationList)
        {
            String[] strings=rice.split(Server.token);
            if (s.equals(strings[1]))
            {
//                riceInfomationList.remove(rice);
//                RiceInformation.saveRice();

                String byerMobi=infomation.getMobileNumber();
                String byerAddress=infomation.getAddress();
                String byerName=infomation.getUsername();
                String Message="Dear "+strings[0]+",\n Your Rice of type :"+strings[4]+" Amount : "+strings[5]+"Kg is bought by : "+byerName+"\n Mobile Number: "+
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
