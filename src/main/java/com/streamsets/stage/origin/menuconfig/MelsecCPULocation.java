package com.streamsets.stage.origin.menuconfig;

import com.streamsets.pipeline.api.Label;
import com.streamsets.stage.lib.MelsecOriginConstants;

public enum MelsecCPULocation implements Label {
    CPULOCAL(MelsecOriginConstants.CPU_LOCAL),
    CPUCONTROL(MelsecOriginConstants.CPU_CONTROL),
    CPUNO1(MelsecOriginConstants.CPU_NO1),
    CPUNO2(MelsecOriginConstants.CPU_NO2),
    CPUNO3(MelsecOriginConstants.CPU_NO3),
    CPUNO4(MelsecOriginConstants.CPU_NO4),
    CPUSTANDBY(MelsecOriginConstants.CPU_STANDBY),
    CPUSYSTEMA(MelsecOriginConstants.CPU_SYSTEM_A),
    CPUSYSTEMB(MelsecOriginConstants.CPU_SYSTEM_B);
    private final String label;

    MelsecCPULocation(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
