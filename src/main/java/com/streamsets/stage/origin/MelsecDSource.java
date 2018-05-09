/**
 * Copyright 2015 StreamSets Inc.
 * <p>
 * Licensed under the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.stage.origin;

import com.streamsets.pipeline.api.*;
import com.streamsets.stage.lib.MelsecOriginConstants;
import com.streamsets.stage.origin.menuconfig.*;

import java.util.List;

@StageDef(
        version = 1,
        label = "Mitsubishi Melsec Data Origin",
        description = MelsecOriginConstants.MELSEC_ORIGIN_DESC,
        icon = "mitsubishi.png",
        execution = ExecutionMode.STANDALONE,
        recordsByRef = true,
        onlineHelpRefUrl = ""
)
@ConfigGroups(value = Groups.class)

@GenerateResourceBundle

public class MelsecDSource extends MelsecSource {

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "UDP",
            label = MelsecOriginConstants.COMMTYPE_LABEL,
            description = MelsecOriginConstants.COMMTYPE_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    @ValueChooserModel(MelsecCommtypeChooserValues.class)
    public MelsecCommtype commType = MelsecCommtype.UDP;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "Q_SERIES",
            label = MelsecOriginConstants.MELSEC_TYPE_LABEL,
            description = MelsecOriginConstants.MELSEC_TYPE_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    @ValueChooserModel(MelsecSystemTypeChooserValues.class)
    public MelsecSystemType systemType = MelsecSystemType.Q_SERIES;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.STRING,
            defaultValue = "localhost",
            label = MelsecOriginConstants.IP_ADDR_LABEL,
            description = MelsecOriginConstants.IP_ADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public String ipAddress;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.NUMBER,
            defaultValue = "5000",
            label = MelsecOriginConstants.PORT_LABEL,
            description = MelsecOriginConstants.PORT_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public int port;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.NUMBER,
            defaultValue = "3000",
            label = MelsecOriginConstants.TIMEOUT_LABEL,
            description = MelsecOriginConstants.TIMEOUT_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public int timeOut;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.NUMBER,
            defaultValue = "1000",
            label = MelsecOriginConstants.TIMEINTERVAL_LABEL,
            description = MelsecOriginConstants.TIMEINTERVAL_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public int timeInterval;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.NUMBER,
            defaultValue = "256",
            label = MelsecOriginConstants.MAXBLOCK_LABEL,
            description = MelsecOriginConstants.MAXBLOCK_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP,
            min = MelsecOriginConstants.MIN_BLOCK_SIZE,
            max = MelsecOriginConstants.MAX_BLOCK_SIZE
    )
    public int maxBlockSize;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "true",
            label = MelsecOriginConstants.TRANSFER_MODE_LABEL,
            description = MelsecOriginConstants.TRANSFER_MODE_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean transferMode;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_XADDR_LABEL,
            description = MelsecOriginConstants.PLC_XADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean xAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_YADDR_LABEL,
            description = MelsecOriginConstants.PLC_YADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean yAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_MADDR_LABEL,
            description = MelsecOriginConstants.PLC_MADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean mAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_DADDR_LABEL,
            description = MelsecOriginConstants.PLC_DADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean dAddress;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = "",
            description = MelsecOriginConstants.PLC_XADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.XTAG_GROUP,
            dependsOn = "xAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagHexAddressInput> xAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = "",
            description = MelsecOriginConstants.PLC_YADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.YTAG_GROUP,
            dependsOn = "yAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagHexAddressInput> yAddressRange;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = "",
            description = MelsecOriginConstants.PLC_MADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.MTAG_GROUP,
            dependsOn = "mAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagHexAddressInput> mAddressRange;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = "",
            description = MelsecOriginConstants.PLC_DADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.DTAG_GROUP,
            dependsOn = "dAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagHexAddressInput> dAddressRange;

    /**
     * {@inheritDoc}
     */
    @Override
    public MelsecCommtype getCommType() {
        return commType;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public MelsecSystemType getSystemType() {
        return systemType;
    }

    @Override
    public boolean xAddressEnabled() {
        return xAddress;
    }

    @Override
    public boolean yAddressEnabled() {
        return yAddress;
    }

    @Override
    public boolean mAddressEnabled() {
        return mAddress;
    }
    @Override
    public boolean dAddressEnabled() {
        return dAddress;
    }
    @Override
    public int getTimeOut() {
        return timeOut;
    }

    @Override
    public int getTimeInterval() {
        return timeInterval;
    }
    @Override
    public int getMaxBlockSize() {
        return maxBlockSize;
    }
    @Override
    public boolean getTransferMode() {
        return transferMode;
    }
    @Override
    public List<TagHexAddressInput> getXAddressRange() {
        return xAddressRange;
    }
    @Override
    public List<TagHexAddressInput> getYAddressRange() {
        return yAddressRange;
    }
    @Override
    public List<TagHexAddressInput> getMAddressRange() { return mAddressRange; }
    @Override
    public List<TagHexAddressInput> getDAddressRange() { return dAddressRange; }
}
