package information;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Infomation {
    public static List<Infomation> infomationList=new ArrayList();
    public static final String INPUT_FILE_NAME="src/information/userInformation.txt";
    public static final String OUTPUT_FILE_NAME="src/information/userInformation.txt";
    static String token="/ /";
    private String username;
    private String email;
    private String password;
    private String mobileNumber;
    private String address;
    @Override
    public String toString() {
        return username+token+email+token+password+token+mobileNumber+token+address+"\n";
    }

    public static boolean loadInformation()
    {
        boolean flag=true;
        try {
            String line;
            //String[] strings;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while (true) {
                line = br.readLine();
                String[] strings;
                if (line == null) break;
                strings=line.split(token);
                if (strings.length==5)
                    infomationList.add(new Infomation(strings[0],strings[1],strings[2],strings[3],strings[4]));
            }
            br.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            // br.close();
        }
        return false;
    }
    public static boolean saveInformation()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            for (Infomation infomation:infomationList)
            {
                bw.write(infomation.toString());
                //bw.write("\n");
            }
            // bw.write(text);
            // bw.write("\n");
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isValidId()
    {
        for (Infomation infomation: infomationList)
        {
            if(username.equals(infomation.username)||email.equals(infomation.email))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean varifyLogin(String email,String password)
    {
        for (Infomation infomation: infomationList)
        {
            if(password.equals(infomation.password)&&email.equals(infomation.email))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infomation that = (Infomation) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(mobileNumber, that.mobileNumber) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, email, password, mobileNumber, address);
    }

    public static List<Infomation> getInfomationList() {

        return infomationList;
    }

    public static void setInfomationList(List<Infomation> infomationList) {
        Infomation.infomationList = infomationList;
    }

    public static String getInputFileName() {
        return INPUT_FILE_NAME;
    }

    public static String getOutputFileName() {
        return OUTPUT_FILE_NAME;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Infomation.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Infomation(String username, String email, String password, String mobileNumber, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }


    public static Infomation getInfomationFromEmail(String email) {
        for (Infomation infomation: infomationList)
        {
            if(email.equals(infomation.email))
            {
                return infomation;
            }
        }
        return null;
    }
    public static Infomation getInfomationFromName(String nam) {
        for (Infomation infomation: infomationList)
        {
            if(nam.equals(infomation.username))
            {
                return infomation;
            }
        }
        return null;
    }
}



