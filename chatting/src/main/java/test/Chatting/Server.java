package test.Chatting;

//import test.DBInsert;

import java.net.*;
import java.io.*;
import java.util.*;


public class Server extends Thread{

    private ArrayList<Socket> list;

    private Map<String, Socket> clientMap;

    private Socket sock;
    private String name;

    public Server(ArrayList<Socket> list, Socket sock, Map<String, Socket> clientMap) {
        this.list = list;
        this.sock = sock;
        this.clientMap = clientMap;
    }

    public void run() {
        try {
            InputStream is = sock.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            name = br.readLine();

            clientMap.put(name, sock);

            sendMsg("[" + name + "] 님이 입장함");

            while(true) {

                String msg = br.readLine();

                if (msg==null) {
                    sendMsg("["+name + "]님이 퇴장함");

                    list.remove(sock);

                    br.close();
                    sock.close();
                    break;
                }
                if (msg.startsWith("/")) {

                    sendWhisper(msg);

                } else {
                    sendMsg("" + name + " : " + msg);
//                    insertData(msg);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void insertData(String msg) {
//        DBInsert.insert(name, msg, null);
//    }

    private void sendWhisper(String msg) {
        String [] split =msg.split(" ");
        String nickName = split[0].substring(1);


        Socket soc=clientMap.get(nickName);
        if(soc !=null) {
            try{

                PrintWriter pw = new PrintWriter(soc.getOutputStream());

                pw.println(name + " 님의 귓속말 > " + msg.substring(nickName.length()+2));
                pw.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try{
                PrintWriter pw = new PrintWriter(sock.getOutputStream());

                pw.println("사용자 없음");
                pw.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private void sendMsg(String msg) {
        for( int i = 0; i<list.size(); i++) {
            Socket soc = list.get(i);

            if(soc !=sock) {
                try{
                    PrintWriter pw = new PrintWriter(soc.getOutputStream());

                    pw.println(msg);

                    pw.flush();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String [] args) {
        ServerSocket server = null;

        ArrayList<Socket> list = new ArrayList<Socket>();
        HashMap<String,Socket> clientMap = new HashMap<>();

        try{
            server = new ServerSocket(8080);

            while(true) {
                 System.out.println("클라이언트 접속 대기");

                Socket sock = server.accept();

                InetAddress ia = sock.getInetAddress();

                System.out.println("ip주소 ["+ ia.getHostAddress() +"]가 접속함");

                list.add(sock);

                Thread server1=new Server(list, sock, clientMap);
                server1.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}