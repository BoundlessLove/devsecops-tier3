package com;

import org.junit.jupiter.api.Test;
import org.apache.hc.client5.http.fluent.Request;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndpointTest {

	@Test
	void callEndpoint() throws Exception {
	    String ip = System.getProperty("externalIp");

	    assertTrue(ip != null && !ip.isBlank(), "externalIp system property is missing");

	    String response = Request.get("http://" + ip + "/")
	            .execute()
	            .returnContent()
	            .asString();

	    assertTrue(!response.trim().isEmpty(), "Endpoint returned an empty response");
	}

    
    public boolean isNonEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}