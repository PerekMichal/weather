package pl.perekmichal.models.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class HttpUtils {
    public static String makeHttpRequest (String url){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL urlClass = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) urlClass.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            int read = 0;
            while ((read = reader.read()) != -1) {
                stringBuilder.append((char) read);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
