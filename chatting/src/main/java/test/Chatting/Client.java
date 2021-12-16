package test.Chatting;


public class Client {
    public static void main(String[] args) throws InterruptedException {
        EasyClient easyClient= new EasyClient("localhost",8080);
        easyClient.start();
    }
}