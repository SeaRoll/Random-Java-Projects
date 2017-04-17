import java.io.*;
import java.net.Socket;

/**
 * Created by Yohan on 4/13/2017.
 */
public class serverSocket extends Thread {
    private Socket socket;
    private boolean connected = true;
    private DataInputStream in;
    private DataOutputStream out;

    serverSocket(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        //Check if client is connected to server
        while(connected) try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //Update client by sending a message
            //Read Messages
            while (connected) {
                saveFile();
                System.out.println("Wtf i'm looping");
            }
        } catch (IOException e) {
            connected = false;
            System.out.println("Lost connection. IOException");
        }
        System.out.println("Connection closed");
    }

    private void saveFile() throws IOException {
        File theFile = null;
        if(theFile == null) {
            System.out.println("Stuck here");
            theFile = new File(in.readUTF());
        }
        byte[] buffer = new byte[1024];

        FileOutputStream fos = new FileOutputStream(theFile, true);

        int filesize = 15123; // Send file size in separate msg
        int read;
        int totalRead = 0;
        int remaining = filesize;

        while((read = in.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
            totalRead += read;
            remaining -= read;
            System.out.println("read " + totalRead + " bytes.");
            fos.write(buffer, 0, read);
            fos.flush();
            out.writeUTF("");
        }


        //runForever();
        //fos.close();
    }
}
