package com.streamsets.stage.origin.menuconfig;
import com.streamsets.pipeline.api.Label;
import com.streamsets.stage.lib.MelsecOriginConstants;


public enum MelsecCommtype implements Label {
    TCPIP(MelsecOriginConstants.TCPIP),
    UDP(MelsecOriginConstants.UDP);
    private final String label;

    MelsecCommtype(String label) { this.label = label; }

    @Override
    public String getLabel() {
        return label;
    }
}
