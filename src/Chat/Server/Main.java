package Chat.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public Main() throws IOException {
    }

    public static void main(String[] args) throws InterruptedException {

        try (ServerSocket server = new ServerSocket(1237)) {

            Socket client = server.accept();

            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            DataInputStream in = new DataInputStream(client.getInputStream());

            while (true) {

                String entry = in.readUTF();

                System.out.println("Клиент сказал - " + entry);


                if (entry.equalsIgnoreCase("quit")) {
                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("От сервера пришёл ответ - " + entry + " - OK");
                    out.flush();
                    break;
                }

                out.writeUTF("Клиент сказал - " + entry + " - OK");
                out.flush();

                out.flush();
            }

            System.out.println("Клиент отключился");

            in.close();
            out.close();

            client.close();


            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    Socket socket2 = new Socket("localhost", 1237);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    DataOutputStream oos = new DataOutputStream(socket2.getOutputStream());
    DataInputStream ois = new DataInputStream(socket2.getInputStream());

    {

        while (true) {

            try {
                if (br.ready()) {

                    Thread.sleep(1000);
                    String clientCommand = br.readLine();

                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("Клиент отправил это сообщение на сервер: " + clientCommand);
                    Thread.sleep(1000);

                    if (clientCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Client kill connections");
                        break;
                    }


                    Thread.sleep(2000);

                    System.out.println("reading...");
                    String in = ois.readUTF();
                    System.out.println(in);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }


    }
}
