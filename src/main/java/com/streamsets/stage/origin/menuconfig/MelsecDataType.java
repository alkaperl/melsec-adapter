package com.streamsets.stage.origin.menuconfig;

import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;
import com.streamsets.stage.lib.MelsecOriginConstants;

@GenerateResourceBundle
public enum MelsecDataType implements Label {
    BOOLEAN(MelsecOriginConstants.BOOLEAN),
    WORD(MelsecOriginConstants.WORD),
    //SHORT(MelsecOriginConstants.SHORT),
    DWORD(MelsecOriginConstants.DWORD);
    //FLOAT(MelsecOriginConstants.FLOAT);
    private final String label;

    MelsecDataType(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
