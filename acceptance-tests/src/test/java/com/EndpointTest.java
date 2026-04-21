package com;

import org.junit.jupiter.api.Test;
import org.apache.hc.client5.http.fluent.Request;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndpointTest {

    @Test
    void callEndpoint() throws Exception {
        String ip = System.getProperty("externalIp");
        String response = Request.get("http://" + ip + "/")
                .execute()
                .returnContent()
                .asString();

        //assertTrue(response.contains("Hello"), "Endpoint did not return expected content");
        assertTrue(!response.trim().isEmpty(), "Endpoint returned an empty response");
    }
    
    public boolean isNonEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}