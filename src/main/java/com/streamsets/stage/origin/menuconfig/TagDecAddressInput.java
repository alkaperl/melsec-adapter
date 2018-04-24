package com.streamsets.stage.origin.menuconfig;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.stage.lib.MelsecOriginConstants;

public class TagDecAddressInput {
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.STRING,
            label = "Start Address(DEC)",
            defaultValue = "000000",
            description = ""
    )
    public String beginAddress = "";

    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = "End Address(DEC)",
            defaultValue = "",
            description = ""
    )
    public String endAddress = "";

    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = "Station ID(HEX)",//00ff
            defaultValue = "",
            description = ""
    )
    public String stationId = "";

    @ConfigDef(
            required = false,
            type = ConfigDef.Type.STRING,
            label = "Network ID(HEX)",//00ff
            defaultValue = "",
            description = ""
    )
    public String networkId = "";
    @ConfigDef(
            required = false,
            type = ConfigDef.Type.MODEL,
            label = "CPU",//03ff ...
            defaultValue = "",
            description = MelsecOriginConstants.MELSEC_CPU_LOCATION_DESC
    )
    @ValueChooserModel(MelsecCommtypeChooserValues.class)
    public MelsecCPULocation cpuLocation = MelsecCPULocation.CPULOCAL;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "true",
            label = "Read only?",
            description = "Read only"
    )
    public boolean isReadOnly;


    public String toString() {
        return "beginAddress:" + beginAddress +
                "endAddress:"+endAddress+
                "stationID"+stationId+
                "networkID:"+networkId+
                "CPULocation:"+cpuLocation+
                "readonly:"+ isReadOnly;
    }
}

