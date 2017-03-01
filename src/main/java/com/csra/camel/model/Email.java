package com.csra.camel.model;

import lombok.Data;

/**
 * Created by steffen on 2/28/17.
 */
@Data
public class Email {
    private String from;
    private String to;
    private String date;
    private String body;
}
