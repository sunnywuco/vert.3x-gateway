package com.stone.rules.facts.routing;

/**
 * Created by young on 16/7/1.
 */
public class RoutingInfo {

    private String host;
    private int port;
    private String path;


    public RoutingInfo(String host,int port,String path){
        this.host=host;
        this.path=path;
        this.port=port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
