package com.streamsets.stage.origin.util;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.StageException;

import java.io.*;
import java.net.*;

public class TcpConnector {
    private String ip;
    private int port;
    private int timeOut;
    private Socket socket;

    TcpConnector(String ip, int port, int timeOut) {
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
    }
    byte[] makeTCPConnect(byte[] byteCommand) throws StageException, IOException {
        byte[] resultMsg = new byte[1024];
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(timeOut);
            OutputStream output = socket.getOutputStream();
            output.write(byteCommand);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            is.readFully(resultMsg);
            socket.close();
        } catch (UnknownHostException | SocketException e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "454";
                }

                @Override
                public String getMessage() {
                    return "TCP Connection Has failed. Check Melsec TDP port is opened or ping command reachable";
                }
            };
            throw new StageException(errorCode);

        } catch (IOException e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "451";
                }

                @Override
                public String getMessage() {
                    return "TCP Connection has established, However the TDP cannot get send Message.";
                }
            };
            socket.close();
            throw new StageException(errorCode);
        }
        return resultMsg;
    }
}
