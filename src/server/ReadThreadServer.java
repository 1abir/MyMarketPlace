package server;

import information.*;
import newClient.Client;
import util.NetworkUtil;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    //private Hashtable<String, NetworkUtil> table;
    private NetworkUtil nc;
    private Server server;

    public ReadThreadServer(Server server,NetworkUtil nc) {
        //this.table = table;
        this.server=server;
        this.nc = nc;
        //this.thr = new Thread(this);
        //thr.start();
    }

    public void run() {
        try {

            while (true) {
                String s=(String)nc.read();
               //System.out.println("Server has got from read : "+s+"    "+nc);
                //if(s!=null) process(s);
                if(s!=null) //process(s);
                {
                    nc.resetOut();
                       // System.out.println("Server has got from read : "+s+"    ");

                        String [] newUser=s.split(Server.token);
                        System.out.println(newUser[0]);
                        if(newUser[0].equals("RegistrationButton"))
                        {
                            Infomation infomation=new Infomation(newUser[1],newUser[2],newUser[3],newUser[4],newUser[5]);
                            if (infomation.isValidId())
                            {
                                server.stringNetworkUtilHashtable.put(newUser[2],nc);
                                Infomation.infomationList.add(infomation);
                                Infomation.saveInformation();
                                nc.write("RegistrationButton"+Server.token+"true");

                            }
                            else {
                                nc.write("RegistrationButton"+Server.token+"false");
                            }
                        }
                        else if (newUser[0].equals("logInButton"))
                        {
                            if (Infomation.varifyLogin(newUser[1],newUser[2]))
                            {
                                nc.write("loginbutton"+Server.token+"true");
                            }
                            else
                            {
                                nc.write("loginbutton"+Server.token+"false");
                            }
                        }
                        else if (newUser[0].toLowerCase().equals("addbutton"))
                        {
                            if (newUser.length==6) {
                                Infomation infomation = Infomation.getInfomationFromEmail(newUser[1]);
                                System.out.println("in lingth");
                                if (newUser[2].toLowerCase().equals("maize")) {
                                    System.out.println("in maize");
                                    MaizeInformation maizeInformation = new MaizeInformation(infomation.getUsername(), CropsInformation.productId, newUser[4], infomation.getMobileNumber(), newUser[3], newUser[5]);
                                    MaizeInformation.addToArrayList(maizeInformation);
                                    System.out.println(MaizeInformation.maizeInfomationList);
                                    CropsInformation.updateProductId();
                                }
                                else if (newUser[2].toLowerCase().equals("rice")) {
                                    RiceInformation riceInformation = new RiceInformation(infomation.getUsername(), CropsInformation.productId, newUser[4], infomation.getMobileNumber(), newUser[3], newUser[5]);
                                    RiceInformation.addToArrayList(riceInformation.toString());
                                    System.out.println(RiceInformation.riceInfomationList);
                                    CropsInformation.updateProductId();
                                }
                                else if (newUser[2].toLowerCase().equals("wheat")) {
                                    System.out.println("wheat");
                                    WheatInformation wheatInformation = new WheatInformation(infomation.getUsername(), CropsInformation.productId, newUser[4], infomation.getMobileNumber(), newUser[3], newUser[5]);
                                    WheatInformation.addToArrayList(wheatInformation);
                                    System.out.println(WheatInformation.wheatInfomationList);
                                    CropsInformation.updateProductId();
                                }
                                else
                                {
                                    OthersInformation othersInformation=new OthersInformation(newUser[2],infomation.getUsername(), CropsInformation.productId, newUser[4], infomation.getMobileNumber(), newUser[3], newUser[5]);
                                    OthersInformation.addToArrayList(othersInformation);
                                    CropsInformation.updateProductId();
                                    System.out.println(OthersInformation.othersInfomationList);
                                }
                            }

                            else
                            {
                                System.out.println("length : "+newUser.length+" \n");
                                int i=0;
                                for (String s2:newUser)
                                {
                                    System.out.println(i+++" : "+s2 );
                                }
                            }
                        }

                        else if (newUser[0].toLowerCase().equals("sendlist"))
                        {
                            if (newUser[1].toLowerCase().equals("rice"))
                            {
                                nc.write(RiceInformation.riceInfomationList);
                            }
                            else if (newUser[1].toLowerCase().equals("maize"))
                            {
                                nc.write(MaizeInformation.maizeInfomationList);
                            }
                            else if (newUser[1].toLowerCase().equals("wheat"))
                            {
                                nc.write(WheatInformation.wheatInfomationList);
                            }
                            else if (newUser[1].equalsIgnoreCase("others"))
                            {
                                nc.write(OthersInformation.othersInfomationList);
                            }
                        }
                        else if (newUser[0].toLowerCase().equals("buybutton"))
                        {
                            System.out.println("got request to buy product id : "+newUser[2]+" from "+newUser[3]);

                            Infomation infomation=Infomation.getInfomationFromEmail(newUser[3]);

                            if (newUser[1].toLowerCase().equals("rice"))
                            {
                                RiceInformation.removeProduct(newUser[2],infomation);
                            }
                            else if (newUser[1].toLowerCase().equals("maize"))
                            {
                                MaizeInformation.removeProduct(newUser[2],infomation);
                            }
                            else if (newUser[1].toLowerCase().equals("wheat"))
                            {
                                WheatInformation.removeProduct(newUser[2],infomation);
                            }
                            else
                            {
                                OthersInformation.removeProduct(newUser[2],infomation);
                            }


                        }
                        else if (newUser[0].equals("requestOfClosing"))
                        {
                            System.out.println("got request of close" );
                            break;
                        }


                }
                /*StringTokenizer st = new StringTokenizer(s);
                String cName = st.nextToken();
                String destinationName=st.nextToken();
                NetworkUtil ny = server.stringNetworkUtilHashtable.get(destinationName);
                if (ny != null) {
                    System.out.println("Message going to : "+destinationName+"   "+s);
                    ny.write(destinationName + ":" + s);
                }*/
            }
            System.out.println("proceessed breaked  "+nc);
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            nc.closeConnection();
            System.out.println("connection closed");
            nc=null;
        }
        System.out.println("finished thread   " + nc);
    }
//    private void process(String s)
//    {
//        System.out.println("Server has got from read : "+s+"    ");
//
//        String [] newUser=s.split(Server.token);
//        System.out.println(newUser[0]);
//        if(newUser[0].equals("RegistrationButton"))
//        {
//            Infomation infomation=new Infomation(newUser[1],newUser[2],newUser[3],newUser[4],newUser[5]);
//            if (infomation.isValidId())
//            {
//                server.stringNetworkUtilHashtable.put(newUser[2],nc);
//                Infomation.infomationList.add(infomation);
//                Infomation.saveInformation();
//                nc.write("RegistrationButton"+Server.token+"true");
//
//            }
//            else {
//                nc.write("RegistrationButton"+Server.token+"false");
//            }
//        }
//        else if (newUser[0].equals("logInButton"))
//        {
//            if (Infomation.varifyLogin(newUser[1],newUser[2]))
//            {
//                nc.write("loginbutton"+Server.token+"true");
//            }
//            else
//            {
//                nc.write("loginbutton"+Server.token+"false");
//            }
//        }
//        else if (newUser[0].toLowerCase().equals("addbutton"))
//        {
//            if (newUser.length==6) {
//                Infomation infomation = Infomation.getInfomationFromEmail(newUser[1]);
//                System.out.println("in lingth");
//                if (newUser[2].toLowerCase().equals("maize")) {
//                    System.out.println("in maize");
//                    MaizeInformation maizeInformation = new MaizeInformation(infomation.getUsername(), CropsInformation.productId, newUser[4], infomation.getMobileNumber(), newUser[3], newUser[5]);
//                    MaizeInformation.addToArrayList(maizeInformation);
//                    System.out.println(MaizeInformation.maizeInfomationList);
//                    CropsInformation.updateProductId();
//                }
//                else if (newUser[2].toLowerCase().equals("rice")) {
//                    RiceInformation riceInformation = new RiceInformation(infomation.getUsername(), CropsInformation.productId, newUser[4], infomation.getMobileNumber(), newUser[3], newUser[5]);
//                    RiceInformation.addToArrayList(riceInformation.toString());
//                    System.out.println(RiceInformation.riceInfomationList);
//                    CropsInformation.updateProductId();
//                }
//                else if (newUser[2].toLowerCase().equals("wheat")) {
//                    System.out.println("wheat");
//                    WheatInformation wheatInformation = new WheatInformation(infomation.getUsername(), CropsInformation.productId, newUser[4], infomation.getMobileNumber(), newUser[3], newUser[5]);
//                    WheatInformation.addToArrayList(wheatInformation.toString());
//                    System.out.println(WheatInformation.wheatInfomationList);
//                    CropsInformation.updateProductId();
//                }
//            }
//            else
//            {
//                System.out.println("length : "+newUser.length+" \n");
//                int i=0;
//                for (String s2:newUser)
//                {
//                    System.out.println(i+++" : "+s2 );
//                }
//            }
//        }
//
//        else if (newUser[0].toLowerCase().equals("sendlist"))
//        {
//            if (newUser[1].toLowerCase().equals("rice"))
//            {
//                nc.write(RiceInformation.riceInfomationList);
//            }
//            else if (newUser[1].toLowerCase().equals("maize"))
//            {
//                nc.write(MaizeInformation.maizeInfomationList);
//            }
//            else if (newUser[1].toLowerCase().equals("wheat"))
//            {
//                nc.write(WheatInformation.wheatInfomationList);
//            }
//        }
//        else if (newUser[0].toLowerCase().equals("buybutton"))
//        {
//            if (newUser[1].toLowerCase().equals("rice"))
//            {
//                RiceInformation.removeProduct(newUser[2]);
//            }
//            else if (newUser[1].toLowerCase().equals("maize"))
//            {
//                MaizeInformation.removeProduct(newUser[2]);
//            }
//            else if (newUser[1].toLowerCase().equals("wheat"))
//            {
//                WheatInformation.removeProduct(newUser[2]);
//            }
//            else if (newUser[1].toLowerCase().equals("wheat"))
//            {
//                OthersInformation.removeProduct(newUser[2]);
//            }
//        }
//        else if (newUser[0].equals("requestOfClosing"))
//        {
//            System.out.println("got request of close" );
////            aliveThread=false;
////            System.out.println(aliveThread)
//        }
//
//    }
}