package information;

import server.Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MessageInformation {

    public static final String messageInformationFile="src/information/messageInformation.txt";
    public static List <String> messageList=new ArrayList<>();
    public static Hashtable<String ,String> emailMessageHashtable=new Hashtable<>();

    public static boolean loadmessage()
    {
        boolean flag = true;
        BufferedReader br = null;
        try {
            String line;
            //String[] strings;
            br = new BufferedReader(new FileReader(messageInformationFile));
            while (true) {
                line = br.readLine();
                String[] strings;
                strings =line.split(Server.token);

                if (line == null) break;
                {
                    messageList.add(line);

                }
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


}
