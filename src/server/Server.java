package server;

import information.CropsInformation;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static String token="/ /";
    private ServerSocket serverSocket;
    private int clientCouner;
    private ExecutorService executorService;
    Hashtable <String,NetworkUtil> stringNetworkUtilHashtable;
    Server()
    {
       stringNetworkUtilHashtable  = new Hashtable<>();
       //WriteThreadServer writeThreadServer=new WriteThreadServer(stringNetworkUtilHashtable, "server");
        information.Infomation.loadInformation();
        CropsInformation.loadAll();
        executorService= Executors.newFixedThreadPool(11111);
        try {
            serverSocket=new ServerSocket(51111);
            while (true)
            {
                Socket clientSocket=serverSocket.accept();
                System.out.println(clientSocket);
                serve(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean serve(Socket clientSocket)
    {
        try {
            NetworkUtil nc= new NetworkUtil(clientSocket);
            clientCouner++;
           // String name=(String)nc.read();
            //System.out.println("client "+clientCouner+" = "+name);
            //stringNetworkUtilHashtable.put(name,nc);
            //ReadThreadServer readThreadServer= new ReadThreadServer(this,nc);
            executorService.execute(new ReadThreadServer(this,nc));
         } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Server server=new Server();
    }
}