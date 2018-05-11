package com.streamsets.stage.lib;

public final class MelsecOriginConstants {
    // CPU TIMER(0x00, 1 unit = 250 msec)
    public static final byte LOW_BYTE_CPU_TIMER = 0x02; //CPU WAIT TIMER 0x02 00means 250*02 = 500Msecs
    public static final byte HI_BYTE_CPU_TIMER = 0x00;
    //Tag Delimeter
    public static final String TAG_DELIMETER = "_";
    // positions
    public static final int UI_DEFAULT_LOCATION = 10;

    public static final int DEFAULT_BYTE_SIZE_READ_IN_BINARY = 8;  // Byte Block size per every command, Return binary array is 8 bit block, should leave 8;
    //Block size
    public static final int MIN_BLOCK_SIZE = 1;
    public static final int MAX_BLOCK_SIZE = 256;
    //Groups
    public static final String BASIC_GROUP = "BASIC";
    public static final String XTAG_GROUP = "XTAG";
    public static final String YTAG_GROUP = "YTAG";
    public static final String MTAG_GROUP = "MTAG";
    public static final String LTAG_GROUP = "LTAG";
    public static final String FTAG_GROUP = "FTAG";
    public static final String VTAG_GROUP = "VTAG";
    public static final String BTAG_GROUP = "BTAG";
    public static final String DTAG_GROUP = "DTAG";
    public static final String WTAG_GROUP = "WTAG";
    public static final String TSTAG_GROUP = "TSTAG";
    public static final String TNTAG_GROUP = "TNTAG";
    public static final String TCTAG_GROUP = "TCTAG";
    public static final String SSTAG_GROUP = "SSTAG";
    public static final String SCTAG_GROUP = "SCTAG";
    public static final String SNTAG_GROUP = "SNTAG";
    public static final String CSTAG_GROUP = "CSTAG";
    public static final String CCTAG_GROUP = "CCTAG";
    public static final String CNTAG_GROUP = "CNTAG";
    public static final String SBTAG_GROUP = "SBTAG";
    public static final String SWTAG_GROUP = "SWTAG";
    public static final String STAG_GROUP = "STAG";
    public static final String DXTAG_GROUP = "DXTAG";
    public static final String DYTAG_GROUP = "DYTAG";
    public static final String ZTAG_GROUP = "ZTAG";
    public static final String RTAG_GROUP = "RTAG";
    public static final String ZRTAG_GROUP = "ZRTAG";

    ///descriptions
    public static final String START_ADDRESS_DESC = "Specify input Start address, Make sure that format should fit HEX/DEC, Ex)D TAG in DEC, Y TAG in HEX";
    public static final String END_ADDRESS_DESC = "Specify input end address, Make sure that format should fit HEX/DEC, Ex)D TAG in DEC, Y TAG in HEX, if blank, only one tag will be read";
    public static final String STATION_ID_DESC = "Specified Station ID(HEX), will set FF or blank if read local Melsec CPU directly. \n For further information, follow Manual or PLC install report";
    public static final String PLC_ID_DESC = "Specified PLC ID(HEX), will set 00 or blank if read local station directly. \n For further information, follow Manual or PLC install report";
    public static final String NETWORK_ID_DESC = "Specified NETWORK ID(HEX), will set 00 or blank if read local device directly. \n For further information, follow Manual or PLC install report";
    public static final String TRANSFER_MODE_DESC = "If true, Transfer data only tag value changes. can save lots of dataflow";
    public static final String MELSEC_ORIGIN_DESC = "Mitsubishi PLC melsec data connector";
    public static final String IP_ADDR_DESC = "Set Mitsubishi PLC IP addrress (Default is 'localhost')";
    public static final String PORT_DESC = "Specify input port for Melsec can use of. Default value is 5000(UDP), 5100(TCP)";
    public static final String TIMEOUT_DESC = "Specify input TIMEOUT for Melsec can wait, default is 3 seconds(3000 msec)";
    public static final String MELSEC_TYPE_DESC = "Select a MODEL type for Mitsubishi melsec";
    public static final String MELSEC_CPU_LOCATION_DESC = "Select a CPU Location";
    public static final String MELSEC_DATA_TYPE_DESC = "Select proper data type";
    public static final String COMMTYPE_DESC = "Select a Communication Type";
    public static final String MAXBLOCK_DESC = "Specify input Block (word) units per one read command, do not exceed 256";
    public static final String TIMEINTERVAL_DESC = "Specify input TIME Interval for Melsec communication running time, default is 1 seconds(1000 msec)";

    public static final String PLC_XADDR_DESC = "Specify input X-Range Address for server can collect";
    public static final String PLC_YADDR_DESC = "Specify input Y-Range Address for server can collect";
    public static final String PLC_MADDR_DESC = "Specify input M-Range Address for server can collect";
    public static final String PLC_LADDR_DESC = "Specify input L-Range Address for server can collect";
    public static final String PLC_FADDR_DESC = "Specify input F-Range Address for server can collect";
    public static final String PLC_VADDR_DESC = "Specify input V-Range Address for server can collect";
    public static final String PLC_BADDR_DESC = "Specify input B-Range Address for server can collect";
    public static final String PLC_DADDR_DESC = "Specify input D-Range Address for server can collect";
    public static final String PLC_WADDR_DESC = "Specify input W-Range Address for server can collect";
    public static final String PLC_TSADDR_DESC = "Specify input TS-Range Address for server can collect";
    public static final String PLC_TCADDR_DESC = "Specify input TC-Range Address for server can collect";
    public static final String PLC_TNADDR_DESC = "Specify input TN-Range Address for server can collect";
    public static final String PLC_SSADDR_DESC = "Specify input SS-Range Address for server can collect";
    public static final String PLC_SCADDR_DESC = "Specify input SC-Range Address for server can collect";
    public static final String PLC_SNADDR_DESC = "Specify input SN-Range Address for server can collect";
    public static final String PLC_CSADDR_DESC = "Specify input CS-Range Address for server can collect";
    public static final String PLC_CCADDR_DESC = "Specify input CC-Range Address for server can collect";
    public static final String PLC_CNADDR_DESC = "Specify input CN-Range Address for server can collect";
    public static final String PLC_SBADDR_DESC = "Specify input SB-Range Address for server can collect";
    public static final String PLC_SWADDR_DESC = "Specify input SW-Range Address for server can collect";
    public static final String PLC_SADDR_DESC = "Specify input S-Range Address for server can collect";
    public static final String PLC_DXADDR_DESC = "Specify input DX-Range Address for server can collect";
    public static final String PLC_DYADDR_DESC = "Specify input DY-Range Address for server can collect";
    public static final String PLC_ZADDR_DESC = "Specify input Z-Range Address for server can collect";
    public static final String PLC_RADDR_DESC = "Specify input R-Range Address for server can collect";
    public static final String PLC_ZRADDR_DESC = "Specify input ZR-Range Address for server can collect";

    /////////////////LABELs
    public static final String START_ADDRESS_LABEL = "Start Address(HEX/DEC)";
    public static final String END_ADDRESS_LABEL = "End Address(HEX/DEC)(Optional)";
    public static final String STATION_ID_LABEL = "Station ID(HEX)";
    public static final String PLC_ID_LABEL = "PLC ID(HEX)";
    public static final String NETWORK_ID_LABEL = "Network ID(HEX)";
    public static final String TRANSFER_MODE_LABEL = "Transfer Mode";
    public static final String DATA_TYPE_LABEL = "Data Type";
    public static final String COMMTYPE_LABEL = "Communication Type";
    public static final String IP_ADDR_LABEL = "IP Address";
    public static final String PORT_LABEL = "Port";
    public static final String TIMEOUT_LABEL = "Timeout (Milliseconds)";
    public static final String TIMEINTERVAL_LABEL = "Time Interval (Milliseconds)";
    public static final String MELSEC_TYPE_LABEL = "Model";
    public static final String MELSEC_CPULOCATION_LABEL = "CPU Location";
    public static final String MAXBLOCK_LABEL = "Maximum BlockSize (word unit)";

    public static final String PLC_XADDR_LABEL = "Enable X Address";
    public static final String PLC_YADDR_LABEL = "Enable Y Address";
    public static final String PLC_MADDR_LABEL = "Enable M Address";
    public static final String PLC_LADDR_LABEL = "Enable L Address";
    public static final String PLC_FADDR_LABEL = "Enable F Address";
    public static final String PLC_VADDR_LABEL = "Enable V Address";
    public static final String PLC_BADDR_LABEL = "Enable B Address";
    public static final String PLC_DADDR_LABEL = "Enable D Address";
    public static final String PLC_WADDR_LABEL = "Enable W Address";
    public static final String PLC_TSADDR_LABEL = "Enable TS Address";
    public static final String PLC_TCADDR_LABEL = "Enable TC Address";
    public static final String PLC_TNADDR_LABEL = "Enable TN Address";
    public static final String PLC_SSADDR_LABEL = "Enable SS Address";
    public static final String PLC_SCADDR_LABEL = "Enable SC Address";
    public static final String PLC_SNADDR_LABEL = "Enable SN Address";
    public static final String PLC_CSADDR_LABEL = "Enable CS Address";
    public static final String PLC_CCADDR_LABEL = "Enable CC Address";
    public static final String PLC_CNADDR_LABEL = "Enable CN Address";
    public static final String PLC_SBADDR_LABEL = "Enable SB Address";
    public static final String PLC_SWADDR_LABEL = "Enable SW Address";
    public static final String PLC_SADDR_LABEL = "Enable S Address";
    public static final String PLC_DXADDR_LABEL = "Enable DX Address";
    public static final String PLC_DYADDR_LABEL = "Enable DY Address";
    public static final String PLC_ZADDR_LABEL = "Enable Z Address";
    public static final String PLC_RADDR_LABEL = "Enable R Address";
    public static final String PLC_ZRADDR_LABEL = "Enable ZR Address";

    //option list
    public static final String TCPIP = "TCP/IP";
    public static final String UDP = "UDP";
    //Data TYPE option
    public static final String BOOLEAN = "boolean(1Bit)";
    public static final String FLOAT = "float(16Bit)";
    public static final String WORD = "word(16Bit)";
    public static final String DWORD = "Double Word(32Bit)";
    public static final String SHORT = "Short Number(16 Bit)";

    //CPU TYPE OPTION
    public static final String Q_SERIES = "Q Series";
    public static final String A_SERIES = "A Series";
    public static final String FX3U = "FX3U";
    public static final String QNA_SERIES = "QnA Series";
    public static final String L_SERIES = "L Series";
    public static final String IQF_SERIES = "iQ-F Series";
    public static final String IQR_SERIES = "iQ-R Series";
    //CPU LOCATION OPTION
    public static final String CPU_LOCAL = "Local CPU";
    public static final String CPU_CONTROL = "Control CPU";
    public static final String CPU_NO1 = "CPU No.1";
    public static final String CPU_NO2 = "CPU No.2";
    public static final String CPU_NO3 = "CPU No.3";
    public static final String CPU_NO4 = "CPU No.4";
    public static final String CPU_STANDBY = "Standby CPU";
    public static final String CPU_SYSTEM_A = "System A CPU";
    public static final String CPU_SYSTEM_B = "System B CPU";
    //TAG LIST OPTION
    public static final String PLC_XADDR_HEXCODE = "9C";
    public static final String PLC_YADDR_HEXCODE = "9D";
    public static final String PLC_MADDR_HEXCODE = "90";
    public static final String PLC_LADDR_HEXCODE = "92";
    public static final String PLC_FADDR_HEXCODE = "93";
    public static final String PLC_VADDR_HEXCODE = "94";
    public static final String PLC_BADDR_HEXCODE = "A0";
    public static final String PLC_DADDR_HEXCODE = "A8";
    public static final String PLC_WADDR_HEXCODE = "B4";
    public static final String PLC_TSADDR_HEXCODE = "C1";
    public static final String PLC_TCADDR_HEXCODE = "C0";
    public static final String PLC_TNADDR_HEXCODE = "C2";
    public static final String PLC_SSADDR_HEXCODE = "C7";
    public static final String PLC_SCADDR_HEXCODE = "C6";
    public static final String PLC_SNADDR_HEXCODE = "C8";
    public static final String PLC_CSADDR_HEXCODE = "C4";
    public static final String PLC_CCADDR_HEXCODE = "C3";
    public static final String PLC_CNADDR_HEXCODE = "C5";
    public static final String PLC_SBADDR_HEXCODE = "A1";
    public static final String PLC_SWADDR_HEXCODE = "B5";
    public static final String PLC_SADDR_HEXCODE = "98";
    public static final String PLC_DXADDR_HEXCODE = "A2";
    public static final String PLC_DYADDR_HEXCODE = "A3";
    public static final String PLC_ZADDR_HEXCODE = "CC";
    public static final String PLC_RADDR_HEXCODE = "AF";
    public static final String PLC_ZRADDR_HEXCODE = "B0";

    public static final String NUMBER_FORMAT_ERROR_MSG = "Address format Fault!";
}
