package com.streamsets.stage.origin.util;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.Errors;

import java.io.IOException;
import java.net.*;
////////////////ERROR CODE BEGINS 400 (cannot connect)
class UdpConnector {
    private String ip;
    private int port, timeOut;
    private DatagramPacket rp = null;

    UdpConnector(String ip, int port, int timeOut) {
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
    }
    int getLength(){
        return rp.getLength();
    }
    byte[] makeUDPConnect(byte[] byteCommand) throws StageException  {
        byte[] receiveMsg = new byte[10240];
        DatagramSocket socket;
        try {
            InetAddress ipAddr = InetAddress.getByName(ip);
            socket = new DatagramSocket();
            socket.setSoTimeout(timeOut);
            DatagramPacket sendCommand = new DatagramPacket(byteCommand, byteCommand.length, ipAddr, port);
            socket.send(sendCommand);
            rp = new DatagramPacket(receiveMsg, receiveMsg.length);
            socket.receive(rp);
            socket.close();

        } catch (UnknownHostException | SocketException e) { throw new StageException(Errors.ERROR_404);}
        catch (IOException e) { throw new StageException(Errors.ERROR_401); }
        return rp.getData();
    }

}
