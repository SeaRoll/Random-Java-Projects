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
        addTempNumbers();
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

        for(int i = 0; i < tempList.size(); i++) {
            int integer = 0;
            for(int n = 0; n < tempList.size(); n++) {
                integer = 0;

                if(integer > tempList.get(n)) {
                    if(theList.size() > 0) {
                        for (int k = 0; k < theList.size(); k++) {
                            System.out.println("theList get: " + theList.get(k) + ", Integer: " + tempList.get(n));
                            if (tempList.get(n) != theList.get(k) && tempList.get(n) > theList.get(k)) {
                                integer = tempList.get(n);
                            }
                        }
                    }else{
                        integer = tempList.get(n);
                        System.out.println("First Value : " + tempList.get(n));
                    }
                }
            }

            theList.add(integer);
            System.out.println();
        }

        for(int i = 0; i < tempList.size(); i++) {
            System.out.println(theList.get(i));
        }

    }
}
