import java.util.Scanner;

/**
 * Created by Yohan on 4/13/2017.
 */
public class mainProgram {

    public static void main(String[] args) {
        choose();
    }

    private static void choose() {
        boolean done = false;
        Scanner userInput = new Scanner(System.in);
        System.out.print("Server or Client? (S/C): "); char inputed = userInput.next().charAt(0);
        do {
            if(inputed == 'S') { //Server
                serverMain server = new serverMain(25565);
                done = true;
            }else if(inputed == 'C') { //Client
                done = true;
                clientSendFile client = new clientSendFile(25565);
            }
        }while(!done);

    }
}
