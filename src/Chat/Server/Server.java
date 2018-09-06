package Chat.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Server implements Runnable {
    private static Socket clientDialog;

    public Server(Socket client) {
        Server.clientDialog = client;
    }


    public void run() {

        try {
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());

            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created");

            while (!clientDialog.isClosed()) {

                String messeg = in.readUTF();

                System.out.println("READ from clientDialog message - " + messeg);

                if (messeg.equalsIgnoreCase("quit")) {

                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("Server reply - " + messeg + " - OK");
                    Thread.sleep(3000);
                    break;
                }


                System.out.println("Server try writing to channel");
                out.writeUTF("Server reply - " + messeg + " - OK");
                System.out.println("Server Wrote message to clientDialog.");

                out.flush();

            }

            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            in.close();
            out.close();

            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
