package com;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RunWith(ConcordionRunner.class)
public class CheckPageTextTest {

    public boolean urlContainsText(String url, String text) throws Exception {
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        reader.close();
        return sb.toString().contains(text);
    }

    public String getTestUrl() {
	    String ip = System.getProperty("externalIp");
        return (ip != null && !ip.isBlank())
	                ? "http://" + ip + "/"
	                : "no url provided";
    }
}
