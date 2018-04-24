package com.streamsets.stage.origin.util;

import com.streamsets.pipeline.api.StageException;

import java.io.IOException;
import java.net.*;

class UdpConnector {
    private String ip;
    private int port, timeOut;
    UdpConnector(String ip, int port, int timeOut){
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
    }
    byte[] makeUDPConnect(byte[] byteCommand) throws Exception {
        byte[] receiveMsg = new byte[1024];
        DatagramSocket socket = null;
        try {
            InetAddress ipAddr = InetAddress.getByName(ip);
            socket = new DatagramSocket();
            socket.setSoTimeout(timeOut);
            DatagramPacket sendCommand = new DatagramPacket(byteCommand, byteCommand.length, ipAddr, port);
            socket.send(sendCommand);

            DatagramPacket rp = new DatagramPacket(receiveMsg, receiveMsg.length);
            socket.receive(rp);
            //receiveMsg = rp.getData();
            socket.close();

        } catch (UnknownHostException | SocketException e) {
            throw new SocketException();
        } catch (IOException e) {
            socket.close();
            throw new IOException();

        }
        return receiveMsg;
    }

}
