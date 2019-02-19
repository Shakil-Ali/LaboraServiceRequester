package com.labora.laboraservicerequester;

public class Requests
{
    // Create variables
    private String requestId;
    private String requesterName;
    private String requesterPostCode;
    private String requesterPhone;
    private String requesterEmail;
    private String requesterJobDesc;
    private String requesterKeyWords;

    // Constructor, takes no arguments
    public Requests()
    {

    }

    // Parameterised constructor
    public Requests(String requestId, String requesterName, String requesterPostCode, String requesterPhone, String requesterEmail, String requesterJobDesc, String requesterKeyWords)
    {
        this.requestId = requestId;
        this.requesterName = requesterName;
        this.requesterPostCode = requesterPostCode;
        this.requesterPhone = requesterPhone;
        this.requesterEmail = requesterEmail;
        this.requesterJobDesc = requesterJobDesc;
        this.requesterKeyWords = requesterKeyWords;
    }

    // Getters for the variables of the constructor
    public String getRequestId() {
        return requestId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getRequesterPostCode() {
        return requesterPostCode;
    }

    public String getRequesterPhone() {
        return requesterPhone;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public String getRequesterJobDesc() {
        return requesterJobDesc;
    }

    public String getRequesterKeyWords() {
        return requesterKeyWords;
    }

}
