package com.streamsets.stage.origin.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.util.HashMap;
import java.util.Map;

public class CommandRunner {
    private String systemType;
    private String ip;
    private int port, timeOut;
    public CommandRunner(String ip, int port, String systemType, int timeOut){
        this.systemType = systemType;
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
    }
    private int singleBitInByteCommand(byte[] byteCommand, String address, String commType) throws Exception {
        UdpConnector udpConnector = new UdpConnector(ip, port, timeOut);
        byte[] resultMsg=null;
        switch (systemType) {
            case "Q_SERIES":
                byteCommand[15] = HexBin.decode(address)[2];
                byteCommand[16] = HexBin.decode(address)[1];
                byteCommand[17] = HexBin.decode(address)[0];
                //TODO UDP connector making
                break;
            case "A_SERIES":
                break;
            case "FX3U":
                break;
            case "QNA_SERIES":
                break;
            case "L_SERIES":
                break;
            case "IQF_SERIES":
                break;
            case "IQR_SERIES":
                break;
        }
        if(commType.equals("UDP")) {
            try {
                resultMsg = udpConnector.makeUDPConnect(byteCommand);
            } catch (Exception e) {
                throw new Exception();
            }
        }//TODO/////////////////////////////////////////////////////////////////
        //else { resultMsg = tcpConnector.makeTCPConnect(byteCommand); }

        assert resultMsg != null;
        return resultParser(resultMsg);
    }
    private int resultParser(byte[] byteResult){
        int intResult = Integer.parseInt(String.valueOf(byteResult[11]));
        if(intResult < 0){ intResult += 256; }
        return intResult;
    }

    private String increaseHex (String currentAddress){
        long addressNext = Long.parseLong(currentAddress, 16);
        addressNext= addressNext+1;
        String result = Long.toHexString(addressNext);
        while (result.length()<6){ result = "0"+result; }
        return result;
    }
    private String increaseDecimal (String currentAddress){ return String.valueOf(Long.parseLong(currentAddress)+1); }

    public Map<String, Integer> getBitInByteCommandResult(byte[] byteCommand, String beginAddressString, String endAddressString, String plcCode, String commType) throws Exception {
        Map <String, Integer> resultMap = new HashMap<>();
        long beginAddress, endAddress = 0;
        //TODO add some more PLC CODE HERE!!!
        if(plcCode.equals("9C")||plcCode.equals("9D")){
            beginAddress = Long.parseLong(beginAddressString, 16);
            if(!endAddressString.equals("")){ endAddress = Long.parseLong(endAddressString, 16); }
            long count;
            if(!endAddressString.equals("000000")){ count = endAddress - beginAddress; }
            else { count = 1; }
            String currentAddress = beginAddressString;
            for (int i=0;i<count;i++){
                resultMap.put(currentAddress, singleBitInByteCommand(byteCommand, currentAddress, commType));
                currentAddress = increaseHex(currentAddress);
            }
        }
        else {//Decimal command count
            beginAddress = Long.parseLong(beginAddressString);
            if(!endAddressString.equals("000000")){ endAddress = Long.parseLong(endAddressString);}
            long count;
            if(!endAddressString.equals("")){ count = endAddress - beginAddress; }
            else { count = 1; }
            String currentAddress = beginAddressString;
            for (int i=0;i<count;i++){
                resultMap.put(currentAddress, singleBitInByteCommand(byteCommand, currentAddress, commType));
                currentAddress = increaseDecimal(currentAddress);
            }
        }
        return resultMap;
    }
}
