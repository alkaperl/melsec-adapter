package com.streamsets.stage.origin.util;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.MelsecOriginConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRunner {
    private String systemType;
    private String ip;
    private int port, timeOut;
    private String commType;
    private String networkId, plcId, cpuLocation, stationId, dataType, tagType;
    private String currentAddress, endAddress;

    public CommandRunner(String ip, int port, String systemType, String commType, int timeOut) {
        this.systemType = systemType;
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
        this.commType = commType;
    }

    private byte[] getCpuLocationByteCommand(String cpuLocation) throws StageException {
        byte[] byteCommand = null;
        try {
            switch (cpuLocation) {
                case "CPULOCAL":
                    byteCommand = new byte[]{0x03, (byte) 0xFF};
                    break;
                case "CPUCONTROL":
                    break;
                case "CPUNO1":
                    byteCommand = new byte[]{0x03, (byte) 0xE0};
                    break;
                case "CPUNO2":
                    byteCommand = new byte[]{0x03, (byte) 0xE1};
                    break;
                case "CPUNO3":
                    byteCommand = new byte[]{0x03, (byte) 0xE2};
                    break;
                case "CPUNO4":
                    byteCommand = new byte[]{0x03, (byte) 0xE3};
                    break;
                case "CPUSTANDBY":
                    break;
                case "CPUSYSTEMA":
                    break;
                case "CPUSYSTEMB":
                    break;
                default:
                    byteCommand = new byte[]{0x03, (byte) 0xFF};
            }
        } catch (Exception e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return MelsecOriginConstants.ERROR_504;
                }

                @Override
                public String getMessage() {
                    return MelsecOriginConstants.ERROR_504_MESSAGE;
                }
            };
            throw new StageException(errorCode);
        }
        return byteCommand;
    }

    private byte[] getSubCommand(String dataType) throws StageException {
        byte[] byteCommand = null;
        try {
            switch (dataType) {
                case "BOOLEAN":
                    byteCommand = new byte[]{0x00, 0x00};  //bit read 1 bit
                    break;
                case "WORD":
                    byteCommand = new byte[]{0x00, 0x00};  //word read 1 bit
                    break;
                case "SHORT":
                    byteCommand = new byte[]{0x00, 0x00};  //word read 1 bit
                    break;
                case "FLOAT":
                    byteCommand = new byte[]{0x00, 0x00};  //word read 2 bit
                    break;
                case "DWORD":
                    byteCommand = new byte[]{0x00, 0x00};  //word read 2 bit
                    break;
            }
        } catch (Exception e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return MelsecOriginConstants.ERROR_503;
                }

                @Override
                public String getMessage() {
                    return MelsecOriginConstants.ERROR_503_MESSAGE;
                }
            };
            throw new StageException(errorCode);
        }
        return byteCommand;
    }

    private String getTagPrefix(String tagType) {
        String result = "";
            switch (tagType) {
                case MelsecOriginConstants.PLC_XADDR_HEXCODE:
                    result = "X";
                    break;
                case MelsecOriginConstants.PLC_YADDR_HEXCODE:
                    result = "Y";
                    break;
                case MelsecOriginConstants.PLC_MADDR_HEXCODE:
                    result = "M";
                    break;
                case MelsecOriginConstants.PLC_LADDR_HEXCODE:
                    result = "L";
                    break;
                case MelsecOriginConstants.PLC_FADDR_HEXCODE:
                    result = "F";
                    break;
                case MelsecOriginConstants.PLC_VADDR_HEXCODE:
                    result = "V";
                    break;
                case MelsecOriginConstants.PLC_BADDR_HEXCODE:
                    result = "B";
                    break;
                case MelsecOriginConstants.PLC_DADDR_HEXCODE:
                    result = "D";
                    break;
                case MelsecOriginConstants.PLC_WADDR_HEXCODE:
                    result = "W";
                    break;
                case MelsecOriginConstants.PLC_TSADDR_HEXCODE:
                    result = "TS";
                    break;
                case MelsecOriginConstants.PLC_TNADDR_HEXCODE:
                    result = "TN";
                    break;
                case MelsecOriginConstants.PLC_TCADDR_HEXCODE:
                    result = "TC";
                    break;
                case MelsecOriginConstants.PLC_SSADDR_HEXCODE:
                    result = "SS";
                    break;
                case MelsecOriginConstants.PLC_SCADDR_HEXCODE:
                    result = "SC";
                    break;
                case MelsecOriginConstants.PLC_SNADDR_HEXCODE:
                    result = "SN";
                    break;
                case MelsecOriginConstants.PLC_CSADDR_HEXCODE:
                    result = "CS";
                    break;
                case MelsecOriginConstants.PLC_CCADDR_HEXCODE:
                    result = "CC";
                    break;
                case MelsecOriginConstants.PLC_CNADDR_HEXCODE:
                    result = "CN";
                    break;
                case MelsecOriginConstants.PLC_SBADDR_HEXCODE:
                    result = "SB";
                    break;
                case MelsecOriginConstants.PLC_SWADDR_HEXCODE:
                    result = "SW";
                    break;
                case MelsecOriginConstants.PLC_SADDR_HEXCODE:
                    result = "S";
                    break;
                case MelsecOriginConstants.PLC_DXADDR_HEXCODE:
                    result = "DX";
                    break;
                case MelsecOriginConstants.PLC_DYADDR_HEXCODE:
                    result = "DY";
                    break;
                case MelsecOriginConstants.PLC_ZADDR_HEXCODE:
                    result = "Z";
                    break;
                case MelsecOriginConstants.PLC_RADDR_HEXCODE:
                    result = "R";
                    break;
                case MelsecOriginConstants.PLC_ZRADDR_HEXCODE:
                    result = "ZR";
                    break;
        }
        return result;
    }

    private byte[] numberToByteArray(String hex) {
        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) { ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16); }
        return ba;
    }

    private byte[] getByteCommand() throws StageException {
        byte[] byteCommand = null;
        byte[] cpuLocationByte = getCpuLocationByteCommand(cpuLocation);
        byte[] subCommand = getSubCommand(dataType);
        try {
            switch (systemType) {
                case "Q_SERIES":
                    byteCommand = new byte[]{0x50, 0x00,  //header for Q series
                            numberToByteArray(networkId)[0], numberToByteArray(plcId)[0],//station number
                            cpuLocationByte[1], cpuLocationByte[0],//CPULocation (big endian -> reversed low bit)
                            numberToByteArray(stationId)[0],//des. station number
                            0x0C, 0x00,//request length 요 뒤에 총 12개 바이트가 이ㅆ으(default value = 12(C))
                            MelsecOriginConstants.LOW_BYTE_CPU_TIMER, MelsecOriginConstants.HI_BYTE_CPU_TIMER,//cpu timer
                            0x01, 0x04,//Main command
                            subCommand[1], subCommand[0],//sub command(bits read or word read? word 01 00)
                            0x00, 0x00, 0x00,//default value 0
                            numberToByteArray(tagType)[0],//D*
                            0x01,0x00};//1 point read(default 1)
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
        } catch (Exception e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return MelsecOriginConstants.ERROR_501;
                }
                @Override
                public String getMessage() {
                    return MelsecOriginConstants.ERROR_501_MESSAGE;
                }
            };
            throw new StageException(errorCode);
        }
        return byteCommand;
    }

    private List<Integer> sendInByteCommand(String address, long blockSize) throws StageException{
        byte byteCommand [] = getByteCommand();
        byte[] resultMsg = null;
        byte[] addressByte;
        if (tagType.equals(MelsecOriginConstants.PLC_XADDR_HEXCODE) || tagType.equals(MelsecOriginConstants.PLC_YADDR_HEXCODE) ||tagType.equals(MelsecOriginConstants.PLC_BADDR_HEXCODE) ||
              tagType.equals(MelsecOriginConstants.PLC_WADDR_HEXCODE) ||tagType.equals(MelsecOriginConstants.PLC_SBADDR_HEXCODE) ||tagType.equals(MelsecOriginConstants.PLC_SWADDR_HEXCODE) ||
               tagType.equals(MelsecOriginConstants.PLC_DXADDR_HEXCODE) ||tagType.equals(MelsecOriginConstants.PLC_DYADDR_HEXCODE) ||tagType.equals(MelsecOriginConstants.PLC_ZRADDR_HEXCODE)) {
            addressByte = numberToByteArray(address);
        }
        else {
            StringBuilder addressString = new StringBuilder(Integer.toHexString(Integer.parseInt(address)));
            while(addressString.length()<6){ addressString.insert(0, "0"); }
            addressByte = numberToByteArray(addressString.toString());
        }
        try {
            switch (systemType) {
                case "Q_SERIES"://set proper address regarding HEX / DEC address structure
                    byteCommand[15] = addressByte[2];
                    byteCommand[16] = addressByte[1];
                    byteCommand[17] = addressByte[0];
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
            byteCommand[19]= (byte)(blockSize);
            byteCommand[20]= (byte)(blockSize >> 8);

        } catch (Exception e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return MelsecOriginConstants.ERROR_500;
                }
                @Override
                public String getMessage() {
                    return MelsecOriginConstants.ERROR_500_MESSAGE;
                }
            };
            throw new StageException(errorCode);
        }
        int resultLength=0;
        if (commType.equals("UDP")) {
            UdpConnector udpConnector = new UdpConnector(ip, port, timeOut);
            resultMsg = udpConnector.makeUDPConnect(byteCommand);
            resultLength = udpConnector.getLength();
        }
        else if (commType.equals("TCPIP")) {
            TcpConnector tcpConnector = new TcpConnector(ip,port, timeOut);
            resultMsg = tcpConnector.makeTCPConnect(byteCommand);
        }
        //resultMSG 9&10 should be 0x00 0x00
        assert resultMsg != null;
        if (!(resultMsg[9] == 0x00) || !(resultMsg[10] == 0x00)) {
            byte[] finalResultMsg = resultMsg;
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return MelsecOriginConstants.ERROR_101;
                }
                @Override
                public String getMessage() { return MelsecOriginConstants.ERROR_101_MESSAGE + finalResultMsg[10] + finalResultMsg[9]; }
            };
            throw new StageException(errorCode);
        }
        return resultBuilder(resultMsg, resultLength);
    }

    private List<Integer> resultBuilder(byte[] byteResult, int length) {
        List<Integer> result = new ArrayList();
        for (int i=11; i< length;i++) {
            int resultInt = Integer.parseInt(String.valueOf(byteResult[i]));
            if(resultInt< 0 ) resultInt = resultInt+256;
            result.add(resultInt);
        }
        return result;
    }

    private String increaseAddress(String currentAddress, int step) {
        long addressNext = Long.parseLong(currentAddress, 16);
        addressNext = addressNext + step;
        StringBuilder result = new StringBuilder(Long.toHexString(addressNext));
        while (result.length() < 6) { result.insert(0, "0"); }
        return result.toString();
    }

    private String getFullMelsecAddress(String currentAddress) {
        StringBuilder fullAddress = new StringBuilder();
        fullAddress.append(getTagPrefix(tagType)).append(currentAddress);
        fullAddress.append(MelsecOriginConstants.TAG_DELIMETER).append(networkId).append(MelsecOriginConstants.TAG_DELIMETER).append(plcId).append(MelsecOriginConstants.TAG_DELIMETER).append(cpuLocation).append(MelsecOriginConstants.TAG_DELIMETER).append(stationId);
        return String.valueOf(fullAddress);
    }

    public Map<String, Integer> readByteCommandResult(String beginAddressString, String endAddressString, String networkId, String plcId, String cpuLocation, String stationId, String dataType, String tagType, int blockSize) throws StageException {
        ////////////////////////////////////////////////
        //Initial the global variables.
        this.networkId = networkId;
        this.plcId = plcId;
        this.cpuLocation = cpuLocation;
        this.dataType = dataType;
        this.tagType = tagType;
        this.stationId = stationId;
        this.endAddress = endAddressString;
        /////////////////////////////////////////////////
        Map<String, Integer> resultMap = new HashMap<>();
        Map<String, Integer> tempMap;
        long beginAddress, endAddress;
        if (tagType.equals(MelsecOriginConstants.PLC_XADDR_HEXCODE) || tagType.equals(MelsecOriginConstants.PLC_YADDR_HEXCODE) || tagType.equals(MelsecOriginConstants.PLC_BADDR_HEXCODE) ||
                tagType.equals(MelsecOriginConstants.PLC_WADDR_HEXCODE) || tagType.equals(MelsecOriginConstants.PLC_SBADDR_HEXCODE) || tagType.equals(MelsecOriginConstants.PLC_SWADDR_HEXCODE) ||
                tagType.equals(MelsecOriginConstants.PLC_DXADDR_HEXCODE) || tagType.equals(MelsecOriginConstants.PLC_DYADDR_HEXCODE) || tagType.equals(MelsecOriginConstants.PLC_ZRADDR_HEXCODE)) {
            beginAddress = Long.parseLong(beginAddressString, 16);
            endAddress = Long.parseLong(this.endAddress, 16);
        } else {
            beginAddress = Long.parseLong(beginAddressString);
            endAddress = Long.parseLong(this.endAddress);
        }
        long count = endAddress - beginAddress +1;
        if(count < 0) { count = 0; }
        int wordCount = (int)count / MelsecOriginConstants.DEFAULT_BYTE_SIZE_READ_IN_BINARY+1;  // Total address count divide 16(0-F), remain is discard. ex) 16008/16=1001 ... will ++
        int blockRemain = wordCount%blockSize;  // Remain block that not fully filled one block. 1000/128=1000-896 = 104
        int blockCount = (wordCount /blockSize); //Bitcount divide max-blocksize per command remain add 1 ex) 1000/128 =8 7... should loop one more time;
        currentAddress = beginAddressString;
        for (int i = 0; i <= blockCount; i++) {//remain should loop one more time
            if (i < blockCount) { tempMap = singleBlockByteCommand(blockSize); }
            else { tempMap = singleBlockByteCommand(blockRemain); }
            resultMap.putAll(tempMap);
        }
        return resultMap;
    }

    private Map<String, Integer> singleBlockByteCommand(int blockSize) throws StageException {
        Map<String, Integer> resultMap=new HashMap<>();
        List<Integer> commandResult = sendInByteCommand(currentAddress, blockSize);
        int count = commandResult.size();
        if (this.dataType.equals("DWORD")) {
            count = count - 2;
        }
        for (int iter=0; iter<count; iter++) {
            switch (this.dataType) {
                case "BOOLEAN":
                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < MelsecOriginConstants.DEFAULT_BYTE_SIZE_READ_IN_BINARY; k++) {
                            String returnValue = returnBinaryValue(commandResult.get(iter));
                            resultMap.put(getFullMelsecAddress(currentAddress), Integer.valueOf(returnValue.substring(MelsecOriginConstants.DEFAULT_BYTE_SIZE_READ_IN_BINARY - 1 - k, MelsecOriginConstants.DEFAULT_BYTE_SIZE_READ_IN_BINARY - k)));
                            if (currentAddress.equalsIgnoreCase(this.endAddress)) { return resultMap; }//return value no other choice until 20180510
                            currentAddress = increaseAddress(currentAddress, 1);
                        }
                        iter++;
                    }
                    break;
                case "WORD": {
                    int resultValue = commandResult.get(iter);
                    resultValue += (commandResult.get(++iter) << 8);
                    resultMap.put(getFullMelsecAddress(currentAddress), resultValue);
                    break;
                }
                case "DWORD": {
                    int resultValue = commandResult.get(iter) + (commandResult.get(++iter) << 8) + (commandResult.get(++iter) << 16) + (commandResult.get(++iter) << 24);
                    if (resultValue < 0) { resultValue = resultValue + (int) Math.pow(2, 31); }
                    resultMap.put(getFullMelsecAddress(currentAddress), resultValue);
                    currentAddress = increaseAddress(currentAddress, 1);
                    break;
                }
            }
            currentAddress = increaseAddress(currentAddress, 1);
        }
        return resultMap;
    }
    private String returnBinaryValue (Integer itemValue){
        StringBuilder returnValue = new StringBuilder(Integer.toBinaryString(itemValue));
        while (returnValue.length() < MelsecOriginConstants.DEFAULT_BYTE_SIZE_READ_IN_BINARY) {
            returnValue.insert(0, "0");
        }
        return returnValue.toString();
    }
}