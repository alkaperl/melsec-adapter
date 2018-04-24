package com.streamsets.stage.origin.util;

import com.streamsets.stage.lib.MelsecOriginConstants;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class CommandGenerator {
    private String systemType, connectionType;

    public CommandGenerator(String systemType){
        this.systemType = systemType;
    }
    private byte [] getCpulocationByteCommand(String cpuLocation){
        byte [] byteCommand = null;
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
        return byteCommand;
    }

    public byte[] getByteCommand(String stationId, String networkId, String module, String destnationModule, String subCommand, String tagType){
        byte[] byteCommand = null;
        byte [] cpuLocation = getCpulocationByteCommand(module);
        switch (systemType) {
            case "Q_SERIES":
                byteCommand = new byte[]{0x50, 0x00,  //header for Q series
                        HexBin.decode(stationId)[0], HexBin.decode(networkId)[0], //station number
                        cpuLocation[1],cpuLocation[0],//module (big endian -> reversed low bit)
                        HexBin.decode(destnationModule)[0],//des. station number
                        0x0C, 0x00,//request length 요 뒤에 총 12개 바이트가 이ㅆ으(default value = 12(C))
                        MelsecOriginConstants.LOW_BYTE_CPU_TIMER, MelsecOriginConstants.HI_BYTE_CPU_TIMER,//cpu timer
                        0x01, 0x04,//Main command
                        HexBin.decode(subCommand)[1], HexBin.decode(subCommand)[0],//sub command(bits read or word read? word 01 00)
                        0x00,0x00,0x00,//default value 0
                        HexBin.decode(tagType)[0],//D*
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
        return byteCommand;
    }
}
