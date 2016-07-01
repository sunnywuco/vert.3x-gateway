package com.stone.rules.facts.request;



/**
 * Created by young on 16/7/1.
 */
public class Request2Route  {


    private String method;
    private String path;


    public Request2Route(String method,String path){


        this.method=method;
        this.path=path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
