package com.streamsets.stage.origin.util;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.StageException;

import java.io.IOException;
import java.net.*;

class UdpConnector {
    private String ip;
    private int port, timeOut;

    UdpConnector(String ip, int port, int timeOut) {
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
    }

    byte[] makeUDPConnect(byte[] byteCommand) throws StageException {
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
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "404";
                }

                @Override
                public String getMessage() {
                    return "UDP Connection Has failed. Check Melsec UDP port is opened or ping command reachable";
                }
            };
            throw new StageException(errorCode);

        } catch (IOException e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "401";
                }

                @Override
                public String getMessage() {
                    return "UDP message has sent, but cannot receive reply message, Check the connectivity or port opened.";
                }
            };
            socket.close();
            throw new StageException(errorCode);
        }
        return receiveMsg;
    }

}
