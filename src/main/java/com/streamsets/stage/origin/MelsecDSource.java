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
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_LADDR_LABEL,
            description = MelsecOriginConstants.PLC_LADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean lAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_FADDR_LABEL,
            description = MelsecOriginConstants.PLC_FADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean fAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_VADDR_LABEL,
            description = MelsecOriginConstants.PLC_VADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean vAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_BADDR_LABEL,
            description = MelsecOriginConstants.PLC_BADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean bAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_WADDR_LABEL,
            description = MelsecOriginConstants.PLC_WADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean wAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_TSADDR_LABEL,
            description = MelsecOriginConstants.PLC_TSADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean tsAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_TCADDR_LABEL,
            description = MelsecOriginConstants.PLC_TCADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean tcAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_TNADDR_LABEL,
            description = MelsecOriginConstants.PLC_TNADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean tnAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_SSADDR_LABEL,
            description = MelsecOriginConstants.PLC_SSADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean ssAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_SCADDR_LABEL,
            description = MelsecOriginConstants.PLC_SCADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean scAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_SNADDR_LABEL,
            description = MelsecOriginConstants.PLC_SNADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean snAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_CSADDR_LABEL,
            description = MelsecOriginConstants.PLC_CSADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean csAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_CCADDR_LABEL,
            description = MelsecOriginConstants.PLC_CCADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean ccAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_CNADDR_LABEL,
            description = MelsecOriginConstants.PLC_CNADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean cnAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_SBADDR_LABEL,
            description = MelsecOriginConstants.PLC_SBADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean sbAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_SWADDR_LABEL,
            description = MelsecOriginConstants.PLC_SWADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean swAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_SADDR_LABEL,
            description = MelsecOriginConstants.PLC_SADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean sAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_DXADDR_LABEL,
            description = MelsecOriginConstants.PLC_DXADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean dxAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_DYADDR_LABEL,
            description = MelsecOriginConstants.PLC_DYADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean dyAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_ZADDR_LABEL,
            description = MelsecOriginConstants.PLC_ZADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean zAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_RADDR_LABEL,
            description = MelsecOriginConstants.PLC_RADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean rAddress;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "false",
            label = MelsecOriginConstants.PLC_ZRADDR_LABEL,
            description = MelsecOriginConstants.PLC_ZRADDR_DESC,
            displayPosition = MelsecOriginConstants.UI_DEFAULT_LOCATION,
            group = MelsecOriginConstants.BASIC_GROUP
    )
    public boolean zrAddress;
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
    public List<TagAddressInput> xAddressRange;
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
    public List<TagAddressInput> yAddressRange;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_MADDR_LABEL,
            description = MelsecOriginConstants.PLC_MADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.MTAG_GROUP,
            dependsOn = "mAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> mAddressRange;

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
    public List<TagAddressInput> dAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_LADDR_LABEL,
            description = MelsecOriginConstants.PLC_LADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.FTAG_GROUP,
            dependsOn = "fAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> lAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_FADDR_LABEL,
            description = MelsecOriginConstants.PLC_FADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.LTAG_GROUP,
            dependsOn = "lAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> fAddressRange;

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_VADDR_LABEL,
            description = MelsecOriginConstants.PLC_VADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.VTAG_GROUP,
            dependsOn = "vAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> vAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_BADDR_LABEL,
            description = MelsecOriginConstants.PLC_BADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.BTAG_GROUP,
            dependsOn = "bAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> bAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_WADDR_LABEL,
            description = MelsecOriginConstants.PLC_WADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.WTAG_GROUP,
            dependsOn = "wAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> wAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_TSADDR_LABEL,
            description = MelsecOriginConstants.PLC_TSADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.TSTAG_GROUP,
            dependsOn = "tsAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> tsAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_TCADDR_LABEL,
            description = MelsecOriginConstants.PLC_TCADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.TCTAG_GROUP,
            dependsOn = "tcAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> tcAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_TNADDR_LABEL,
            description = MelsecOriginConstants.PLC_TNADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.TNTAG_GROUP,
            dependsOn = "tnAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> tnAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_SSADDR_LABEL,
            description = MelsecOriginConstants.PLC_SSADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.SSTAG_GROUP,
            dependsOn = "ssAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> ssAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_SCADDR_LABEL,
            description = MelsecOriginConstants.PLC_SCADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.SCTAG_GROUP,
            dependsOn = "scAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> scAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_SNADDR_LABEL,
            description = MelsecOriginConstants.PLC_SNADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.SNTAG_GROUP,
            dependsOn = "snAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> snAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_CSADDR_LABEL,
            description = MelsecOriginConstants.PLC_CSADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.CSTAG_GROUP,
            dependsOn = "csAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> csAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_CCADDR_LABEL,
            description = MelsecOriginConstants.PLC_CCADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.CCTAG_GROUP,
            dependsOn = "ccAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> ccAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_CNADDR_LABEL,
            description = MelsecOriginConstants.PLC_CNADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.CNTAG_GROUP,
            dependsOn = "cnAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> cnAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_SADDR_LABEL,
            description = MelsecOriginConstants.PLC_SADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.STAG_GROUP,
            dependsOn = "sAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> sAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_SBADDR_LABEL,
            description = MelsecOriginConstants.PLC_SBADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.SBTAG_GROUP,
            dependsOn = "sbAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> sbAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_SWADDR_LABEL,
            description = MelsecOriginConstants.PLC_SWADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.SWTAG_GROUP,
            dependsOn = "swAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> swAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_DXADDR_LABEL,
            description = MelsecOriginConstants.PLC_DXADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.DXTAG_GROUP,
            dependsOn = "dxAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> dxAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_DYADDR_LABEL,
            description = MelsecOriginConstants.PLC_DYADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.DYTAG_GROUP,
            dependsOn = "dyAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> dyAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_ZADDR_LABEL,
            description = MelsecOriginConstants.PLC_ZADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.ZTAG_GROUP,
            dependsOn = "zAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> zAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_RADDR_LABEL,
            description = MelsecOriginConstants.PLC_RADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.RTAG_GROUP,
            dependsOn = "rAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> rAddressRange;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "",
            label = MelsecOriginConstants.PLC_ZRADDR_LABEL,
            description = MelsecOriginConstants.PLC_ZRADDR_DESC,
            displayPosition = 210,
            group = MelsecOriginConstants.ZRTAG_GROUP,
            dependsOn = "zrAddress",
            triggeredByValue = "true"
    )
    @ListBeanModel
    public List<TagAddressInput> zrAddressRange;
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
    public boolean dAddressEnabled() { return dAddress; }
    @Override
    public boolean lAddressEnabled() { return lAddress; }
    @Override
    public boolean fAddressEnabled() { return fAddress; }
    @Override
    public boolean vAddressEnabled() { return vAddress; }
    @Override
    public boolean bAddressEnabled() { return bAddress; }
    @Override
    public boolean wAddressEnabled() { return wAddress; }
    @Override
    public boolean tsAddressEnabled() { return tsAddress; }
    @Override
    public boolean tcAddressEnabled() { return tcAddress; }
    @Override
    public boolean tnAddressEnabled() { return tnAddress; }
    @Override
    public boolean ssAddressEnabled() { return ssAddress; }
    @Override
    public boolean scAddressEnabled() { return scAddress; }
    @Override
    public boolean snAddressEnabled() { return snAddress; }
    @Override
    public boolean csAddressEnabled() { return csAddress; }
    @Override
    public boolean ccAddressEnabled() { return ccAddress; }
    @Override
    public boolean cnAddressEnabled() { return cnAddress; }
    @Override
    public boolean sbAddressEnabled() { return sbAddress; }
    @Override
    public boolean swAddressEnabled() { return swAddress; }
    @Override
    public boolean sAddressEnabled() { return sAddress; }
    @Override
    public boolean dxAddressEnabled() { return dxAddress; }
    @Override
    public boolean dyAddressEnabled() { return dyAddress; }
    @Override
    public boolean zAddressEnabled() { return zAddress; }
    @Override
    public boolean rAddressEnabled() { return rAddress; }
    @Override
    public boolean zrAddressEnabled() { return zrAddress;}
    @Override
    public List<TagAddressInput> getXAddressRange() {
        return xAddressRange;
    }
    @Override
    public List<TagAddressInput> getYAddressRange() {
        return yAddressRange;
    }
    @Override
    public List<TagAddressInput> getMAddressRange() { return mAddressRange; }
    @Override
    public List<TagAddressInput> getDAddressRange() { return dAddressRange; }
    @Override
    public List<TagAddressInput> getLAddressRange() {
        return lAddressRange;
    }
    @Override
    public List<TagAddressInput> getFAddressRange() {
        return fAddressRange;
    }
    @Override
    public List<TagAddressInput> getVAddressRange() { return vAddressRange; }
    @Override
    public List<TagAddressInput> getBAddressRange() { return bAddressRange; }
    @Override
    public List<TagAddressInput> getWAddressRange() { return wAddressRange; }
    @Override
    public List<TagAddressInput> getTSAddressRange() {
        return tsAddressRange;
    }
    @Override
    public List<TagAddressInput> getTCAddressRange() {
        return tcAddressRange;
    }
    @Override
    public List<TagAddressInput> getTNAddressRange() { return tnAddressRange; }
    @Override
    public List<TagAddressInput> getSSAddressRange() { return ssAddressRange; }
    @Override
    public List<TagAddressInput> getSCAddressRange() {
        return scAddressRange;
    }
    @Override
    public List<TagAddressInput> getSNAddressRange() {
        return snAddressRange;
    }
    @Override
    public List<TagAddressInput> getCSAddressRange() { return csAddressRange; }
    @Override
    public List<TagAddressInput> getCCAddressRange() { return ccAddressRange; }
    @Override
    public List<TagAddressInput> getCNAddressRange() {
        return cnAddressRange;
    }
    @Override
    public List<TagAddressInput> getSAddressRange() {
        return sAddressRange;
    }
    @Override
    public List<TagAddressInput> getSWAddressRange() {
        return swAddressRange;
    }
    @Override
    public List<TagAddressInput> getSBAddressRange() {
        return sbAddressRange;
    }
    @Override
    public List<TagAddressInput> getDXAddressRange() {
        return dxAddressRange;
    }
    @Override
    public List<TagAddressInput> getDYAddressRange() { return dyAddressRange; }
    @Override
    public List<TagAddressInput> getZAddressRange() { return zAddressRange; }
    @Override
    public List<TagAddressInput> getRAddressRange() {
        return rAddressRange;
    }
    @Override
    public List<TagAddressInput> getZRAddressRange() {
        return zrAddressRange;
    }
}
