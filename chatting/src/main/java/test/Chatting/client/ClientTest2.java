package test.Chatting.client;

import java.net.Socket;

public class ClientTest2 {
    public static void main(String[] args) {
        try {
            Client client = new Client();
            Socket socket = client.getConnection();

            client.setStart(socket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
