package br.com.oralura.openshift.agenda;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by rmartine on 6/5/16.
 */
public class WebClient {
    public String post(String json) {
        try {
            URL servidor = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection conn = (HttpURLConnection) servidor.openConnection();
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            conn.setDoOutput(true);

            PrintStream output = new PrintStream(conn.getOutputStream());
            output.println(json);

            conn.connect();

            Scanner scanner = new Scanner(conn.getInputStream());
            String resposta = scanner.next();
            return resposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
