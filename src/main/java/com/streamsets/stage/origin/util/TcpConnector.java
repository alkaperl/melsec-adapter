package com.streamsets.stage.origin.util;

public class TcpConnector {
    private String ip;
    private int port;
    TcpConnector(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
}
