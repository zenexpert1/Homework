package Chat.Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        try(Socket socket = new Socket("localhost", 1237);
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            while(true){

                if(br.ready()){

                    Thread.sleep(1000);
                    String clientCommand = br.readLine();

                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("Клиент отправил это сообщение на сервер: " + clientCommand);
                    Thread.sleep(1000);

                    if(clientCommand.equalsIgnoreCase("quit")){
                        System.out.println("Client kill connections");
                        break;
                    }


                    Thread.sleep(2000);

                    System.out.println("reading...");
                    String in = ois.readUTF();
                    System.out.println(in);
                }
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
