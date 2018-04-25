package com.streamsets.stage.origin.menuconfig;

import com.streamsets.pipeline.api.Label;
import com.streamsets.stage.lib.MelsecOriginConstants;

public enum MelsecSystemType implements Label {
    Q_SERIES(MelsecOriginConstants.Q_SERIES),
    A_SERIES(MelsecOriginConstants.A_SERIES),
    FX3U(MelsecOriginConstants.FX3U),
    QNA_SERIES(MelsecOriginConstants.QNA_SERIES),
    L_SERIES(MelsecOriginConstants.L_SERIES),
    IQF_SERIES(MelsecOriginConstants.IQF_SERIES),
    IQR_SERIES(MelsecOriginConstants.IQR_SERIES);
    private final String label;

    MelsecSystemType(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
