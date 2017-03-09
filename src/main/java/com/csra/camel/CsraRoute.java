package com.csra.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * Created by steffen on 2/18/17.
 */

@Component
public class CsraRoute extends RouteBuilder {
    static Logger log = LoggerFactory.getLogger(CsraRoute.class.getName());

    @Override
    public void configure() throws Exception {
        from("file:/tmp/outbound")
                .to("activemq:queue:Observation");

        from("activemq:queue:Observation")
                .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.POST))
                .to("http4:10.255.242.20:8182/observation/toOru")
                .to("activemq:queue:ToGenesis");

        from("activemq:queue:TEST")
                .to("file:/tmp/inbound?fileName=oru_r01-${date:now:yyyyMMdd}.er7");

        from("imap://alerts@10.255.242.20?port=143&password=password&username=alerts@soa1.soadv.localdomain&mail.imap.auth=true&mail.imap.starttls.enable=true&unseen=true")
                .process("emailProcessor")
                .to("activemq:queue:FromEssentris");

        from("activemq:queue:FromEssentris")
                .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.POST))
                .to("http4:10.255.242.22/xalert/toFhir")
                .to("activemq:queue:ToGenesis");

        from("activemq:queue:FromACCS")
                .to("activemq:queue:ToGenesis");

        from("activemq:queue:ToGenesis")
                .to("activemq:queue:Genesis");
    }

}
