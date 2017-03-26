import java.util.ArrayList;
import java.util.Scanner;

/**
 * Uses Arraylist. sorts from the smallest to biggest number.
 * Created by Yohan on 3/26/2017.
 */
public class simpleSorting {

    public static ArrayList<Integer> theList = new ArrayList();
    public static Scanner userInput = new Scanner(System.in);

    public static void main() { //Starts here
        Menu();
    }

    public static void resetScreen() { //Making sure to "reset" screen. Making things better
        for(int i = 0; i < 99; i++) {
            System.out.println();
        }
    }

    public static void Menu() { //The menu class.
        resetScreen();
        System.out.println("WELCOME\n\n1. Add Value\n2. Reset List\n3. Sort the list\n4. Quit");
        int chosen = userInput.nextInt();

    }

    public static void addToList() { //Add Value to list
        resetScreen();
        System.out.print("Integer Value to add: "); int theValue = userInput.nextInt();
        theList.add(theValue);
        Menu();
    }

    public static void clearList() { //Clear value
        theList.clear();
        Menu();
    }
}
