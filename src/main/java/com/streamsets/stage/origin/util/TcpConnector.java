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
    private Socket socket;

    TcpConnector(String ip, int port, int timeOut) {
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
    }

    byte[] makeTCPConnect(byte[] byteCommand) throws StageException {
        byte[] resultMsg = new byte[1024];
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(timeOut);
            OutputStream output = socket.getOutputStream();
            output.write(byteCommand);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            int len = is.readInt();
            //is.readFully(resultMsg);
            if (len > 0) is.readFully(resultMsg);
            socket.close();
        }
        catch (UnknownHostException | SocketException e) {throw new StageException(Errors.ERROR_454);}
        catch (IOException e) {
            try { socket.close(); }
            catch (IOException e1) { e1.printStackTrace(); }
            throw new StageException(Errors.ERROR_451);
        }
        return resultMsg;
    }
}
