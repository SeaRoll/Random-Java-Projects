import documentSearcher.GoogleSearch;

/**
 *
 * @author Yohan
 */
public class googleAPI {

    public static String documentText;
    public static GoogleSearch google;
    public static frame theFrame;

    public static void main(String[] args) {



        google = new GoogleSearch();
        theFrame = new frame();
        theFrame.setVisible(true);
    }

    public static void doubleSearch(String file) {
        google.webs.clear();
        google.webcon.clear();
        searchFunction(file);
        for(int i = 0; i < google.webcon.size(); i++) {
            System.out.println(google.webcon.get(i));
        }
    }

    public static void searchFunction(String file) {

        String fileText = google.readFile(file);
        String splitString[] = fileText.split("\\.|\\?|\\!|\\[|\\]\\;");
        int amount = 0;
        int done = 0;

        for(int i = 0; i < splitString.length; i++) {
            amount += google.searchFunc(splitString[i], "web2.uconn.edu.jks");
        }

        double percentage = (Math.round(((double)amount/(double)splitString.length) * 100.0) / 100.0)*100;

        theFrame.textArea.setText("Document Contains:\n" +
                amount + "/" + splitString.length + "\n" +
                percentage + "%"
        );
    }
}
