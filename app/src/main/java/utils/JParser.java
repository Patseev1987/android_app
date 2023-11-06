package utils;

import android.os.AsyncTask;
import android.webkit.CookieManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class JParser extends AsyncTask<Void, Integer, JSONObject> {

    private boolean cookies;
    public static final String TAG = "JParser", METHOD_POST = "POST", METHOD_GET = "GET";
    private String url, method, cookiesUrl, charset = "UTF-8";
    private String[] params;

    public JParser(String url, String method, String[] params, Response response) {
        this.url = url;
        this.method = method;
        this.params = params;
        this.response = response;
    }

    private Response response;
    public interface Response {
        void onPreExecute();
        void onProgressUpdate(Integer... value);
        void onPostExecute(JSONObject result);
        void onCancelled();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        response.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        try {
            return make(url, method, params);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        response.onProgressUpdate(progress);
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        response.onPostExecute(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        response.onCancelled();
    }

    public void setCookies(String cookiesUrl) {
        this.cookiesUrl = cookiesUrl;
        this.cookies = true;
    }

    private JSONObject make(String url, String method, String[] params) throws JSONException, IOException {

        StringBuilder p = new StringBuilder();
        int i = 0;
        for (String param : params) {
            if (i != 0) p.append("&");
            p.append(param.split("=")[0]).append("=").append(URLEncoder.encode(param.split("=")[1], charset));
            i++;
        }

        URL urlObj;
        HttpURLConnection connection = null;

        if (method.equals(METHOD_POST)) {

            urlObj = new URL(url);
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(METHOD_POST);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);

            if (cookies) connection.setRequestProperty("Cookie", CookieManager.getInstance().getCookie(cookiesUrl/*"http://site.ru"*/));//здесь передаем сохраненные куки

            connection.connect();

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(p.toString());
            wr.flush();
            wr.close();

        } else
        if (method.equals(METHOD_GET)) {

            if (p.length() != 0) url += "?" + p.toString();
            urlObj = new URL(url);
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setDoOutput(false);
            connection.setRequestMethod(METHOD_GET);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setConnectTimeout(15000);
            connection.connect();

        }

        StringBuilder result = new StringBuilder();
        if (connection != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(connection.getInputStream())));
            String line;
            while ((line = reader.readLine()) != null) result.append(line);
            reader.close();
            connection.disconnect();
        }

        return new JSONObject(result.toString());
    }


// Using--------------------------
//
//    JParser jp = new JParser("http://site.ru", JParser.METHOD_POST, new String[] {"key=value", "key1=value1"}, new JParser.Response() {
//        @Override
//        public void onPreExecute() {
//            //аналогично AsynsTask
//        }
//        @Override
//        public void onProgressUpdate(Integer... value) {
//            //аналогично AsynsTask
//        }
//        @Override
//        public void onPostExecute(JSONObject result) {
//            //здесь что-то делаем с ответом, который должен быть в формате Json
//        }
//        @Override
//        public void onCancelled() {
//            //аналогично AsynsTask
//        }
//    });
////Суем куки если нужно
////jp.setCookies("http://site.ru");
//jp.execute();

}