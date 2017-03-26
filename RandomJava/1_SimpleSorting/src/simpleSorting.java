import java.util.ArrayList;
import java.util.Scanner;

/**
 * Uses Arraylist. sorts from the smallest to biggest number.
 * Created by Yohan on 3/26/2017.
 */
public class simpleSorting {

    public static ArrayList<Integer> theList = new ArrayList();
    public static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) { //Starts here
        Menu();
    }

    public static void addTempNumbers() {
        theList.add(72);
        theList.add(-12);
        theList.add(10);
        theList.add(-27);
    }

    public static void resetScreen() { //Making sure to "reset" screen. Making things better
        for(int i = 0; i < 99; i++) {
            System.out.println();
        }
    }

    public static void Menu() { //The menu class.
        resetScreen();
        System.out.println("WELCOME\n\n1. Add Value\n2. Reset List\n3. Sort the list by smallest number\n4. Quit");
        int chosen = userInput.nextInt();

        switch(chosen) {
            case 1:
                addToList();
                break;
            case 2:
                clearList();
                break;
            case 3:
                sortListBySmall();
                break;
            default:
                break;
        }
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

    public static void sortListBySmall() { //SORTS LIST BY THE SMALLEST NUMBER
        ArrayList<Integer> tempList = new ArrayList();
        for(int i = 0; i < theList.size(); i++) {
            tempList.add(theList.get(i));
            System.out.println("ADDING: " + theList.get(i));
        }
        for(int i = 0; i < tempList.size(); i++) {
            System.out.println(tempList.get(i));
        }
        theList.clear();

        System.out.println();

        for(int n = 0; n < 4; n++) {
            int smallInt = 0;

            for(int i = 0; i < tempList.size(); i++) {
                if(smallInt < tempList.get(i)) {
                    smallInt = tempList.get(i) + 1;
                }
            }

            for (int i = 0; i < tempList.size(); i++) {

                if (smallInt > tempList.get(i)) {
                    smallInt = tempList.get(i);
                }
            }

            for(int i = 0; i < tempList.size(); i++) {
                if(smallInt == tempList.get(i)) {
                    tempList.remove(i);
                }
            }
            theList.add(smallInt);
        }

        System.out.println("RESULT: ");

        for(int i = 0; i < theList.size(); i++) {
            System.out.println(theList.get(i));
        }
    }
}
