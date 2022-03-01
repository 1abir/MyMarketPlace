package information;

import server.Server;
import sms.hu.ozekisms.TestTcpSms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MaizeInformation {
    public static final String MAIZE_FILE = "src/information/maizeInformation.txt";
    public static ArrayList<String> maizeInfomationList = new ArrayList<>();

    private String userName;
    private int productId;
    private String type;
    private String mobileNumber;
    private String price;
    private String stock;

    public static boolean loadMaize() {
    boolean flag = true;
    BufferedReader br = null;
    try {
        String line;
        //String[] strings;
        br = new BufferedReader(new FileReader(MAIZE_FILE));
        while (true) {
            line = br.readLine();
            String[] strings;
            if (line == null) break;
            strings = line.split(Server.token);
            if (strings.length == 6)
                maizeInfomationList.add(line);
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

    public static boolean saveMaize() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(MAIZE_FILE));
            for (String maizeInf : maizeInfomationList) {
                bw.write(maizeInf);
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

    public MaizeInformation(String userName, int productId, String type, String mobileNumber, String price, String stock) {
        this.userName = userName;
        this.productId = productId;
        this.type = type;
        this.mobileNumber = mobileNumber;
        this.price = price;
        this.stock = stock;
    }
    public static void addToArrayList(MaizeInformation maizeInformation)
    {
        maizeInfomationList.add(maizeInformation.toString());
        saveMaize();
    }
    public static void addToArrayList(String s)
    {
        try {
            String [] strings=s.split(Server.token);
            MaizeInformation maizeInformation=new MaizeInformation(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]);
            addToArrayList(maizeInformation);
            saveMaize();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return userName+Server.token+String.valueOf(productId)+Server.token+mobileNumber+Server.token+type+
                Server.token+price+Server.token+stock;
    }

    public static void removeProduct(String s, Infomation infomation) {
        for (String maize:maizeInfomationList)
        {
            String[] strings=maize.split(Server.token);
            if (s.equals(strings[1]))
            {
//                maizeInfomationList.remove(maize);
//                MaizeInformation.saveMaize();

                String byerMobi=infomation.getMobileNumber();
                String byerAddress=infomation.getAddress();
                String byerName=infomation.getUsername();
                String Message="Dear "+strings[0]+",\n Your Maize of type :"+strings[3]+"Amount : "+strings[5]+" is bought by : "+byerName+"\n Mobile Number: "+
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
