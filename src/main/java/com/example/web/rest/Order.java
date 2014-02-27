package com.example.web.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 27/02/14
 * Time: 13:04
 *
 */
public class Order implements Serializable {

    private Date dateTimeOfSubmission;

    private List orderLines;

    private UUID identifier;


}
