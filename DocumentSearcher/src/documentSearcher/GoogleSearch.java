/*
This is a API Library made by Yohan Joo. It uses JSoup and some other Native
Java Libraries to search a sentence. and see if there is similar
sentence.
*/
package documentSearcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 *
 * @author Yohan
 */
public class GoogleSearch {

    private static String[] splitted_text;
    public static ArrayList<String> webs = new ArrayList();
    public static ArrayList<String> webcon = new ArrayList();

    //Search Function
    public int searchFunc(String searchText, String certificatePath) {
        //disableCertificateValidation();
        System.setProperty("javax.net.ssl.trustStore", certificatePath);
        System.out.println("Text: " + searchText);

        //Replace all Special Swedish Characters with A, O, A
        String search = searchText;
        search = search.replaceAll("Ä", "A");
        search = search.replaceAll("Ö", "O");
        search = search.replaceAll("Å", "A");
        search = search.replaceAll("ä", "a");
        search = search.replaceAll("ö", "o");
        search = search.replaceAll("å", "a");
        Charset.forName("UTF-8").encode(search);
        System.out.println("Text: " + search);
        searchGoogle(search);


        int amountFound = 0;
        for (String web : webs) {
            if (amountFound == 0) {
                amountFound += searchThePage(web, search);
            }
        }

        int amountFound1 = 0;
        for (String aWebcon : webcon) {
            if (amountFound1 == 0) {
                amountFound1 += searchThePage(aWebcon, search);
            }
        }

        System.out.println("Amount Results found: " + amountFound + amountFound1);

        if(amountFound > 0 || amountFound1 > 0) {
            return 1;
        }
        return 0;
    }

    //Search stuff on Google
    public void searchGoogle(String search) {

        try {
            //Get Strings
            String google = "http://www.google.se/search?q=";
            String charset = "UTF-8";
            String userAgent = "Mozilla";

            Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");

            for (Element link : links) {
                String url = link.absUrl("href");
                url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

                //Only Text
                if (url.startsWith("https") || url.startsWith("http")) {
                    //System.out.println(title);
                    webs.add(new String(url.getBytes("UTF-8"), "UTF-8"));
                }
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Encoding ERROR");
        } catch (IOException ex) {
            System.out.println("IOEXCEPTION");
        }
    }

    //Search the Page
    public int searchThePage(String page, String search) {

        //HTTPS
        enableSSLSocket();

        Collator usCollator = Collator.getInstance(new Locale("sv", "SE"));
        usCollator.setStrength(Collator.PRIMARY);

        Document doc;
        try {
            doc = Jsoup.connect(page).get();
            String textContents = doc.text();
            splitted_text = textContents.split("\\.|\\?|\\!|\\[|\\]\\;");

            for (String splitted_text1 : splitted_text) {
                String lowerSplit = splitted_text1.toLowerCase();
                String lowerSearch = search.toLowerCase();

                lowerSplit = lowerSplit.replaceAll("ä", "a");
                lowerSplit = lowerSplit.replaceAll("ö", "o");
                lowerSplit = lowerSplit.replaceAll("å", "a");

                if(usCollator.compare(lowerSplit, lowerSearch) == 0) {
                    System.out.println("FOUND RESULT! Link: " + page);
                    for(int i = 0; i < webcon.size(); i++) {
                        if(webcon.get(i).equals(page)) {
                            webcon.add(page);
                        }
                    }
                    return 1;
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(GoogleSearch.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    //Read File
    public String readFile(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();

            return everything;
        } catch (IOException ex) {
            Logger.getLogger(GoogleSearch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(GoogleSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "null";
    }

    public static void enableSSLSocket() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (KeyManagementException ex) {
            Logger.getLogger(GoogleSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GoogleSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
