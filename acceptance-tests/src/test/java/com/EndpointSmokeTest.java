/*package com;

import org.concordion.integration.junit5.ConcordionExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.hc.client5.http.fluent.Request;

@ExtendWith(ConcordionExtension.class)
public class EndpointSmokeTest {

    public String callEndpoint() throws Exception {
        String ip = System.getProperty("externalIp");
        return Request.get("http://" + ip + "/")
                .execute()
                .returnContent()
                .asString();
    }
}*/
package com;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.apache.hc.client5.http.fluent.Request;

@RunWith(ConcordionRunner.class)
public class EndpointSmokeTest {

    public String callEndpoint() throws Exception {
        String ip = System.getProperty("externalIp");
        return Request.get("http://" + ip + "/")
                .execute()
                .returnContent()
                .asString();
    }
}
