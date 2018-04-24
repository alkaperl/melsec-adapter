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
import com.streamsets.pipeline.api.base.BaseSource;
import com.streamsets.stage.lib.MelsecOriginConstants;
import com.streamsets.stage.origin.menuconfig.MelsecCommtype;
import com.streamsets.stage.origin.menuconfig.MelsecSystemType;
import com.streamsets.stage.origin.menuconfig.TagHexAddressInput;
import com.streamsets.stage.origin.util.CommandGenerator;
import com.streamsets.stage.origin.util.CommandRunner;

import java.util.*;

/**
 * This source is an example and does not actually read from anywhere.
 * It does however, generate generate a simple record with one field.
 */
public abstract class MelsecSource extends BaseSource {

    /**
     * Gives access to the UI configuration of the stage provided by the {@link MelsecDSource} class.
     */

    @Override
    protected List<ConfigIssue> init() {
        // Validate configuration values and open any required resources.
        List<ConfigIssue> issues = super.init();

        /*if (getConfig().equals("invalidValue")) {
            issues.add(
                    getContext().createConfigIssue(
                            Groups.BASIC.name(), "config", Errors.SAMPLE_00, "Here's what's wrong..."
                    )
            );
        }*/

        // If issues is not empty, the UI will inform the user of each configuration issue in the list.
        return issues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        // Clean up any open resources.
        super.destroy();
    }

    /**
     * {@inheritDoc}
     */

    private Map<String, Integer> executeCommand(String plcAddrHexCode) throws Exception {
        Map <String, Integer> resultMap;
        CommandGenerator commandGenerator = new CommandGenerator(getSystemType().name());
        CommandRunner commandRunner = new CommandRunner(getIpAddress(), getPort(), getSystemType().name(), getTimeOut());
        long beginTime = System.currentTimeMillis();
        //TODO add for or foreach to have multiple command.
        byte[] command = commandGenerator.getByteCommand(getXAddressRange().get(0).getStationId(), getXAddressRange().get(0).getNetworkId(), getXAddressRange().get(0).getCPULocation(), "00", "0000", MelsecOriginConstants.PLC_XADDR_HEXCODE);
        resultMap = commandRunner.getBitInByteCommandResult(command, getXAddressRange().get(0).getBeginAddress(), getXAddressRange().get(0).getEndAddress(), plcAddrHexCode, getCommType().name());
        /////////////////////////////////////////

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
        return resultMap;
    }
    @Override
    public String produce(String lastSourceOffset, int maxBatchSize, BatchMaker batchMaker) throws StageException {

        Record record = getContext().createRecord(String.valueOf(UUID.randomUUID()));
        try {
            if (xAddressEnabled()) {

                executeCommand(MelsecOriginConstants.PLC_XADDR_HEXCODE);

            }
            if (yAddressEnabled()) {
            }
       /* if(mAddressEnabled()){}
        if(lAddressEnabled()){}
        if(fAddressEnabled()){}
        if(vAddressEnabled()){}
        if(dAddressEnabled()){}
        if(wAddressEnabled()){}
        if(tsAddressEnabled()){}
        if(tcAddressEnabled()){}
        if(tnAddressEnabled()){}
        if(ssAddressEnabled()){}
        if(scAddressEnabled()){}
        if(snAddressEnabled()){}
        if(csAddressEnabled()){}
        if(ccAddressEnabled()){}
        if(cnAddressEnabled()){}
        if(dxAddressEnabled()){}
        if(dyAddressEnabled()){}
        if(zaAddressEnabled()){}
        if(rAddressEnabled()){}
        if(zrAddressEnabled()){}*/

            batchMaker.addRecord(record);


        }
        catch (Exception e){
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "Error01";
                }

                @Override
                public String getMessage() {
                    return "Error Code Occurred";
                }
            };
            throw new StageException(errorCode);
        }

        return String.valueOf("");
    }

    public abstract MelsecCommtype getCommType();

    public abstract String getIpAddress();

    public abstract int getPort();

    public abstract MelsecSystemType getSystemType();

    public abstract boolean xAddressEnabled();
    public abstract boolean yAddressEnabled();
    /*public abstract boolean mAddressEnabled();
    public abstract boolean lAddressEnabled();
    public abstract boolean fAddressEnabled();
    public abstract boolean vAddressEnabled();
    public abstract boolean dAddressEnabled();
    public abstract boolean wAddressEnabled();
    public abstract boolean tsAddressEnabled();
    public abstract boolean tcAddressEnabled();
    public abstract boolean tnAddressEnabled();
    public abstract boolean ssAddressEnabled();
    public abstract boolean scAddressEnabled();
    public abstract boolean snAddressEnabled();
    public abstract boolean csAddressEnabled();
    public abstract boolean ccAddressEnabled();
    public abstract boolean cnAddressEnabled();
    public abstract boolean dxAddressEnabled();
    public abstract boolean dyAddressEnabled();
    public abstract boolean zaAddressEnabled();
    public abstract boolean rAddressEnabled();
    public abstract boolean zrAddressEnabled();*/

    public abstract int getTimeOut();

    public abstract List<TagHexAddressInput> getXAddressRange();
}
