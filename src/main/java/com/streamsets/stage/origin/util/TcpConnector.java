package com.streamsets.stage.origin.util;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.Errors;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

////////////////ERROR CODE BEGINS 450 (cannot connect)
class TcpConnector {
    private String ip;
    private int port;
    private int timeOut;
    private int length;
    private Socket socket;

    TcpConnector(String ip, int port, int timeOut) {
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;

    }
    int  getLength(){
        return length;
    }
    byte[] makeTCPConnect(byte[] byteCommand) throws StageException {
        byte[] resultMsg = null;
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(timeOut);
            OutputStream output = socket.getOutputStream();
            output.write(byteCommand);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            length = 1024;
            resultMsg = new byte[length];
            is.read(resultMsg);
            socket.close();
        }
        catch (UnknownHostException | SocketException e) {throw new StageException(Errors.ERROR_454);}
        catch (IOException e) {
            try { socket.close(); }
            catch (IOException e1) { e1.printStackTrace(); }
            throw new StageException(Errors.ERROR_451);
        }
        //catch (InterruptedException e) { e.printStackTrace(); }
        return resultMsg;
    }
}
