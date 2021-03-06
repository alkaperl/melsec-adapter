package com.streamsets.stage.origin.menuconfig;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.stage.lib.MelsecOriginConstants;

public class TagAddressInput {
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.STRING,
            label = MelsecOriginConstants.START_ADDRESS_LABEL,
            defaultValue = "000000",
            description = MelsecOriginConstants.START_ADDRESS_DESC
            //displayPosition = 210
    )
    public String beginAddress;
    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = MelsecOriginConstants.END_ADDRESS_LABEL,
            defaultValue = "",
            description = MelsecOriginConstants.END_ADDRESS_DESC
            //displayPosition = 220
    )
    public String endAddress = "";
    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = MelsecOriginConstants.STATION_ID_LABEL,
            defaultValue = "",
            description = MelsecOriginConstants.STATION_ID_DESC
            //displayPosition = 230
    )
    public String stationId = "";
    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = MelsecOriginConstants.PLC_ID_LABEL,
            defaultValue = "",
            description = MelsecOriginConstants.PLC_ID_DESC
            //displayPosition = 240
    )
    public String plcId = "";
    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = MelsecOriginConstants.NETWORK_ID_LABEL,
            defaultValue = "",
            description = MelsecOriginConstants.NETWORK_ID_DESC
            //displayPosition = 240
    )
    public String networkId = "";
    @ConfigDef(
            required = false,
            type = ConfigDef.Type.MODEL,
            label = MelsecOriginConstants.MELSEC_CPULOCATION_LABEL,//03ff ...
            defaultValue = "CPULOCAL",
            description = MelsecOriginConstants.MELSEC_CPU_LOCATION_DESC
            //displayPosition = 250
    )
    @ValueChooserModel(MelsecCPULocationChooserValues.class)
    public MelsecCPULocation cpuLocation = MelsecCPULocation.CPULOCAL;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            label = MelsecOriginConstants.DATA_TYPE_LABEL,
            defaultValue = "BOOLEAN",
            description = MelsecOriginConstants.MELSEC_DATA_TYPE_DESC
            //displayPosition = 250
    )
    @ValueChooserModel(MelsecDataTypeChooserValues.class)
    public MelsecDataType dataType = MelsecDataType.BOOLEAN;

    private String filloutValue(String address, String fillValue, int length) {
        if (address.length() < length) { while (address.length() < length) address = fillValue + address; }
        else if (address.length()> length){ while (address.length() > length) address=address.substring(1, address.length()); }
        return address;
    }

    public String getBeginAddress() { return filloutValue(beginAddress, "0", 6); }

    public String getEndAddress() {
        if(endAddress.length()!=0){ return filloutValue(endAddress, "0", 6);}
        else { return getBeginAddress(); }
    }
    public String getStationId() { return filloutValue(stationId, "0", 2); }
    public String getNetworkId() { return filloutValue(networkId, "0", 2); }
    public String getPlcId() { return filloutValue(plcId, "F", 2); }
    public String getDataType() { return dataType.name(); }
    public String getCPULocation() { return cpuLocation.name().equals("") ? "CPULOCAL" : cpuLocation.name(); }
}
