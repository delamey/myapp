package com.example.delamey.myapplication5.bean;

public class bitmapbean {


    /**
     * Code : InvalidArgument
     * Message : thumbnail param error,  thumbnail=140y98/ shoud be: digital
     * Resource : pic-bucket/photo/0001/2018-08-25/DQ2SSNG900AN0001NOS.jpg
     * RequestId : dd228e4f0aa0000000005b8761a4849f
     */

    private String Code;
    private String Message;
    private String Resource;
    private String RequestId;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getResource() {
        return Resource;
    }

    public void setResource(String Resource) {
        this.Resource = Resource;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }
}
