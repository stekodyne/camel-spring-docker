package com.csra.camel;

import com.csra.camel.model.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by steffen on 2/28/17.
 */
public class EmailProcessor implements Processor {

    @Autowired
    ObjectMapper objectMapper;

    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        Message out;
        Email email = new Email();

        email.setFrom(in.getHeader("From").toString());
        email.setTo(in.getHeader("To").toString());
        email.setDate(in.getHeader("Date").toString());
        email.setBody(in.getBody().toString());

        in.setBody(objectMapper.writeValueAsString(email));
    }
}
