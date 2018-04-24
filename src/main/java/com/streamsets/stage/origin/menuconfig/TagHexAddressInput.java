package com.streamsets.stage.origin.menuconfig;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.stage.lib.MelsecOriginConstants;
public class TagHexAddressInput {
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.STRING,
            label = "Start Address(HEX)",
            defaultValue = "000000",
            description = "",
            displayPosition = 210

    )
    public String beginAddress = "";

    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = "End Address(HEX)",
            defaultValue = "",
            description = "",
            displayPosition = 220
    )
    public String endAddress = "";

    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = "Station ID(HEX)",
            defaultValue = "",
            description = "",
            displayPosition = 230
    )
    public String stationId = "";

    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = "Network ID(HEX)",
            defaultValue = "",
            description = "",
            displayPosition = 240
    )
    public String networkId = "";
    @ConfigDef(
            required = false,
            type = ConfigDef.Type.MODEL,
            label = "CPU Select",//03ff ...
            defaultValue = "CPULOCAL",
            description = MelsecOriginConstants.MELSEC_CPU_LOCATION_DESC,
            displayPosition = 250
    )
    @ValueChooserModel(MelsecCPULocationChooserValues.class)
    public MelsecCPULocation cpuLocation = MelsecCPULocation.CPULOCAL;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "true",
            label = "Read only?",
            description = "Read only",
            displayPosition = 260
    )
    public boolean isReadOnly;

    private String filloutValue(String address, String fillValue, int length){
        if(address.length()<length){ while (address.length()<length) address = fillValue + address; }
        return address;
    }
    public String getBeginAddress(){ return filloutValue(beginAddress, "0", 6); }
    public String getEndAddress() { return filloutValue(endAddress, "0", 6); }
    public String getStationId() { return filloutValue(stationId, "0", 2); }
    public String getNetworkId() { return filloutValue(networkId, "F", 2); }
    public String getCPULocation() { return cpuLocation.name().equals("") ? "CPULOCAL":cpuLocation.name(); }
    public boolean isReadonly() { return isReadOnly; }
}
