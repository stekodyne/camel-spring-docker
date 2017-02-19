package com.csra.camel;

import org.apache.camel.management.event.ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.EventObject;

/**
 * Created by steffen on 12/16/16.
 */
@Component
public class LoggingEventNotifier extends EventNotifierSupport {
    static Logger log = LoggerFactory.getLogger(LoggingEventNotifier.class.getName());

    public void notify(EventObject event) throws Exception {

        if (event instanceof ExchangeSentEvent) {
            ExchangeSentEvent sent = (ExchangeSentEvent) event;
            log.info("Took " + sent.getTimeTaken() + " millis to send to: " + sent.getEndpoint());
        }

    }

    public boolean isEnabled(EventObject event) {
        // Only time the sent events.
        return event instanceof ExchangeSentEvent;
    }

    protected void doStart() throws Exception {
    }

    protected void doStop() throws Exception {
    }

}
