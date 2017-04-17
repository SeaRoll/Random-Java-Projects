import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Yohan on 4/13/2017.
 */
public class serverMain extends Thread {

    //ServerSocket and ArrayList to make it dynamic
    private ServerSocket serverSocket;
    public ArrayList<serverSocket> sockets = new ArrayList();

    public serverMain(int port) {
        System.out.println("SETTING UP SERVER...");
        Socket socket = null;

        //Listen
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Check for a connection
        System.out.println("//---//\nDONE. Awaiting connection...");
        while (true) {
            try {
                socket = serverSocket.accept();


            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            //Add the client socket to ArrayList
            sockets.add( new serverSocket(socket));
            sockets.get(sockets.size() - 1).start();
        }
    }
}
