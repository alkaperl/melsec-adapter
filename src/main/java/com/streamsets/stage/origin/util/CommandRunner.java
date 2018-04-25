package com.streamsets.stage.origin.util;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.MelsecOriginConstants;

import java.util.HashMap;
import java.util.Map;

public class CommandRunner {
    private String systemType;
    private String ip;
    private int port, timeOut;
    private String commType;
    public CommandRunner(String ip, int port, String systemType, String commType,  int timeOut){
        this.systemType = systemType;
        this.ip = ip;
        this.port = port;
        this.timeOut = timeOut;
        this.commType = commType;
    }

    private byte [] getCpuLocationByteCommand(String cpuLocation) throws StageException {
        byte [] byteCommand = null;
        try{
            switch (cpuLocation){
                case "CPULOCAL":
                    byteCommand = new byte[] {0x03, (byte) 0xFF};
                    break;
                case "CPUCONTROL":
                    break;
                case"CPUNO1":
                    byteCommand = new byte[] {0x03, (byte) 0xE0};
                    break;
                case"CPUNO2":
                    byteCommand = new byte[] {0x03, (byte) 0xE1};
                    break;
                case"CPUNO3":
                    byteCommand = new byte[] {0x03, (byte) 0xE2};
                    break;
                case"CPUNO4":
                    byteCommand = new byte[] {0x03, (byte) 0xE3};
                    break;
                case"CPUSTANDBY":
                    break;
                case"CPUSYSTEMA":
                    break;
                case"CPUSYSTEMB":
                    break;
                default:
                    byteCommand = new byte[] {0x03, (byte) 0xFF};
            }
        } catch (Exception e) {
        ErrorCode errorCode = new ErrorCode() {
            @Override
            public String getCode() {
                return "504";
            }
            @Override
            public String getMessage() {
                return "System Type cannot verified for building CPU Location, check System Type. Some model is not implemented!";
            }
        };
        throw new StageException(errorCode);
        }
        return byteCommand;
    }

    private byte [] getSubCommand(String dataType) throws StageException {
        byte [] byteCommand = null;
        try{
            switch (dataType){
                case "BOOLEAN":
                    byteCommand = new byte[] {0x01, 0x00,0x01, 0x00};  //bit read 1 bit
                    break;
                case "WORD":
                    byteCommand = new byte[] {0x00, 0x00,0x01, 0x00};  //word read 1 bit
                    break;
                case "SHORT":
                    byteCommand = new byte[] {0x00, 0x00,0x01, 0x00};  //word read 1 bit
                    break;
                case "FLOAT":
                    byteCommand = new byte[] {0x00, 0x00,0x02, 0x00};  //word read 2 bit
                    break;
                case "DWORD":
                    byteCommand = new byte[] {0x00, 0x00,0x02, 0x00};  //word read 2 bit
                    break;

            }
        } catch (Exception e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "503";
                }

                @Override
                public String getMessage() {
                    return "System Type cannot verified for dataType, check System Type. Some model is not implemented!";
                }
            };
            throw new StageException(errorCode);
        }
        return byteCommand;
    }

    private String getTagPrefix(String tagType) throws StageException {
        String result="";
        try{
            switch (tagType){
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
        } catch (Exception e) {
        ErrorCode errorCode = new ErrorCode() {
            @Override
            public String getCode() {
                return "505";
            }
            @Override
            public String getMessage() {
                return "Register Tag cannot verified during building the result address";
            }
        };
        throw new StageException(errorCode);
    }
        return result;
    }

    private byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) { return null; }
        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) { ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16); }
        return ba;
    }

    private byte[] getByteCommand(String networkId, String plcId, String cpuLocation, String stationId, String dataType, String tagType) throws StageException {
        byte[] byteCommand = null;
        byte [] cpuLocationByte = getCpuLocationByteCommand(cpuLocation);
        byte [] subCommand = getSubCommand(dataType);
        try{
            switch (systemType) {
                case "Q_SERIES":
                    byteCommand = new byte[]{0x50, 0x00,  //header for Q series
                            hexToByteArray(networkId)[0], hexToByteArray(plcId)[0],//station number
                            cpuLocationByte[1], cpuLocationByte[0],//CPULocation (big endian -> reversed low bit)
                            hexToByteArray(stationId)[0],//des. station number
                            0x0C, 0x00,//request length 요 뒤에 총 12개 바이트가 이ㅆ으(default value = 12(C))
                            MelsecOriginConstants.LOW_BYTE_CPU_TIMER, MelsecOriginConstants.HI_BYTE_CPU_TIMER,//cpu timer
                            0x01, 0x04,//Main command
                            subCommand[3], subCommand[2],//sub command(bits read or word read? word 01 00)
                            0x00,0x00,0x00,//default value 0
                            hexToByteArray(tagType)[0],//D*
                            subCommand[1], subCommand[0]};//1 point read(default 1)
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
                    return "501";
                }

                @Override
                public String getMessage() {
                    return "System Type cannot verified for building command binary, check System Type. Some model is not implemented!";
                }
            };
            throw new StageException(errorCode);
        }
        return byteCommand;
    }

    private int singleBitInByteCommand(byte[] byteCommand, String address, String commType) throws StageException {
        UdpConnector udpConnector = new UdpConnector(ip, port, timeOut);
        byte[] resultMsg=null;
        try {
            switch (systemType) {
                case "Q_SERIES":
                    byteCommand[15] = hexToByteArray(address)[2];
                    byteCommand[16] = hexToByteArray(address)[1];
                    byteCommand[17] = hexToByteArray(address)[0];
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
        } catch (Exception e){
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "500";
                }
                @Override
                public String getMessage() {
                    return "System Type cannot verified, for building address binary check System Type. Some model is not implemented!";
                }
            };
            throw new StageException(errorCode);
        }
        if(commType.equals("UDP")) {
            resultMsg = udpConnector.makeUDPConnect(byteCommand);

        }//TODO/////////////////////////////////////////////////////////////////
        //else { resultMsg = tcpConnector.makeTCPConnect(byteCommand); }

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

    private String getFullMelsecAddress(String currentAddress, String networkId, String plcId, String cpuLocation, String stationId, String tagType) throws StageException {
        StringBuilder fullAddress = new StringBuilder();
        fullAddress.append(getTagPrefix(tagType)).append(currentAddress);
        fullAddress.append(MelsecOriginConstants.TAG_DELIMETER).append(networkId).append(MelsecOriginConstants.TAG_DELIMETER).append(plcId).append(MelsecOriginConstants.TAG_DELIMETER).append(cpuLocation).append(MelsecOriginConstants.TAG_DELIMETER).append(stationId);
        return String.valueOf(fullAddress);
    }

    public Map<String, Field> readBitInByteCommandResult(String beginAddressString, String endAddressString, String networkId, String plcId, String cpuLocation, String stationId, String dataType, String tagType) throws Exception {

        byte [] byteCommand = getByteCommand(networkId, plcId, cpuLocation, stationId, dataType ,tagType);

        Map <String, Field> resultMap = new HashMap<>();
        long beginAddress, endAddress = 0;
        if(tagType.equals(MelsecOriginConstants.PLC_XADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_YADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_MADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_LADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_FADDR_HEXCODE)||
           tagType.equals(MelsecOriginConstants.PLC_VADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_BADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_TSADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_TCADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_SSADDR_HEXCODE)||
           tagType.equals(MelsecOriginConstants.PLC_SCADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_SNADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_CSADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_CCADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_SBADDR_HEXCODE)||
           tagType.equals(MelsecOriginConstants.PLC_SADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_DXADDR_HEXCODE)||tagType.equals(MelsecOriginConstants.PLC_DYADDR_HEXCODE)){
            beginAddress = Long.parseLong(beginAddressString, 16);
            if(!endAddressString.equals("")){ endAddress = Long.parseLong(endAddressString, 16); }
            long count;
            if(!endAddressString.equals("000000")){ count = endAddress - beginAddress; }
            else { count = 1; }
            String currentAddress = beginAddressString;
            for (int i=0;i<=count;i++){
                resultMap.put(getFullMelsecAddress(currentAddress, networkId, plcId, cpuLocation, stationId, tagType), Field.create(singleBitInByteCommand(byteCommand, currentAddress, commType)));
                currentAddress = increaseHex(currentAddress);
                if(dataType.equals(MelsecOriginConstants.DWORD)||dataType.equals(MelsecOriginConstants.FLOAT)){ currentAddress = increaseHex(currentAddress); }
            }
        }
        else {//Decimal command count
            beginAddress = Long.parseLong(beginAddressString);
            if(!endAddressString.equals("000000")){ endAddress = Long.parseLong(endAddressString);}
            long count;
            if(!endAddressString.equals("")){ count = endAddress - beginAddress; }
            else { count = 1; }
            String currentAddress = beginAddressString;
            for (int i=0;i<=count;i++){
                resultMap.put(getFullMelsecAddress(currentAddress, networkId, plcId, cpuLocation, stationId, tagType), Field.create(singleBitInByteCommand(byteCommand, currentAddress, commType)));
                currentAddress = increaseDecimal(currentAddress);
            }
        }
        return resultMap;
    }
}
