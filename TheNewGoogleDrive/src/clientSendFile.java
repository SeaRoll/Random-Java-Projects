import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * The client! It sends file to the server
 * Created by Yohan on 4/13/2017.
 */
public class clientSendFile {

    private String serverName = "127.0.0.1";
    int port = 0;
    private Socket client;
    boolean running = true, ended = false;

    OutputStream outToServer;
    DataOutputStream out;
    InputStream inFromServer;
    DataInputStream in;


    public clientSendFile(int Port) {
        port = Port;

        try {
            //---CONNECT TO SERVER---//
            System.out.println("Connecting to " + serverName + " on port " + port);
            client = new Socket(serverName, port);

            //---PRINT WHERE IT CONNECTED TO---//
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            outToServer = client.getOutputStream();
            out = new DataOutputStream(outToServer);
            //out.writeUTF("Hello from " + client.getLocalSocketAddress());

            //---INPUT FROM SERVER---//
            inFromServer = client.getInputStream();
            in = new DataInputStream(inFromServer);

            while(running) { //Client Menu
                clientMenu();
            }

        }catch(IOException e) {
            e.printStackTrace();
            running = false;
        }
    }

    private void clientMenu() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Menu: \n 1. Send File to server \n 2. Quit\n");
        int choosen = userInput.nextInt();
        switch(choosen) {
            case 1:
                try {
                    out.writeUTF("butterfly.jpg");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendFile();
               break;
            case 2:
                running = false;
                break;
        }
    }

    private void sendFile() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Input file:");
        String file = userInput.next();

        System.out.println("//UPLOADING...");
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] fileBuffer = Files.readAllBytes(new File(file).toPath());
            byte[] buffer = new byte[1024];

            //dos.write(fileBuffer);

            System.out.println("//GOT PAST BUFFER");

            int count;
            while((count=fis.read(buffer)) > 0 && !ended){
                out.write(buffer, 0, count);
                System.out.println("Count: " + count);
                count = 0;
                out.writeUTF("butterfly.jpg");
                out.flush();
            }

            /**while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }**/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
