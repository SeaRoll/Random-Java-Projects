import java.util.ArrayList;
import java.util.Scanner;

/**
 * Uses Arraylist. sorts from the smallest to biggest number.
 * Created by Yohan on 3/26/2017.
 */
public class simpleSorting {

    private static ArrayList<Integer> theList = new ArrayList();
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) { //Starts here
        Menu();
    }

    private static void resetScreen() { //Making sure to "reset" screen. Making things better
        for(int i = 0; i < 99; i++) {
            System.out.println();
        }
    }

    private static void Menu() { //The menu class.
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

    private static void addToList() { //Add Value to list
        resetScreen();
        System.out.print("Integer Value to add: "); int theValue = userInput.nextInt();
        theList.add(theValue);
        Menu();
    }

    private static void clearList() { //Clear value
        theList.clear();
        Menu();
    }

    private static void sortListBySmall() { //SORTS LIST BY THE SMALLEST NUMBER
        ArrayList<Integer> tempList = new ArrayList();
        for (Integer aTheList1 : theList) {
            tempList.add(aTheList1);
            System.out.println("ADDING: " + aTheList1);
        }
        for (Integer aTempList : tempList) {
            System.out.println(aTempList);
        }
        theList.clear();

        System.out.println();

        int listSize = tempList.size();
        for(int n = 0; n < listSize; n++) {

            int smallInt = 0;
            for (int i = 0; i < tempList.size(); i++) {

                if(i > 0) {
                    if (smallInt > tempList.get(i)) {
                        smallInt = tempList.get(i);
                    }
                }else {
                    smallInt = tempList.get(i);
                }
            }

            for (Integer aTempList : tempList) {
                if(smallInt == tempList.get(aTempList)) {
                    tempList.remove(aTempList);
                }
            }
            theList.add(smallInt);
        }

        System.out.println("RESULT: ");

        for (Integer aTheList : theList) {
            System.out.println(aTheList);
        }
    }
}
