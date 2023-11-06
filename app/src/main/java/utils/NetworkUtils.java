package utils;

import android.net.Uri;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String SERVER_API = "http://192.168.0.202:1337/api/";
    private static final String TOOLS = "tools";
    private static final String EMPLOYEES = "employees";
    private static final String NOTES = "notes";

    public static URL generateURLTools() {
        Uri builtUri = Uri.parse(SERVER_API).buildUpon().appendPath(TOOLS).build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public static URL generateURLEmployees() {
        Uri builtUri = Uri.parse(SERVER_API).buildUpon().appendPath(EMPLOYEES).build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public static URL generateURLNotes() {
        Uri builtUri = Uri.parse(SERVER_API).buildUpon().appendPath(NOTES).build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
