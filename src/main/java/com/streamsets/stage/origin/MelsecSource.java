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
import com.streamsets.stage.origin.util.CommandRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.lang.Thread.sleep;

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

    private Map<String, Field> executeCommand(List<TagHexAddressInput> getPlcAddressRange, String plcAddrHexCode) throws Exception {
        Map<String, Field> resultMap = new HashMap<>();
        CommandRunner commandRunner = new CommandRunner(getIpAddress(), getPort(), getSystemType().name(), getCommType().name(), getTimeOut());
        long beginTime = System.currentTimeMillis();
        for (TagHexAddressInput item : getPlcAddressRange) {
            Map<String, Field> tempMap;
            tempMap = commandRunner.readBitInByteCommandResult(
                    item.getBeginAddress(), //begin Address
                    item.getEndAddress(),  //endAddress
                    item.getNetworkId(),  // NETWORK ID
                    item.getPlcId(),  // PLC ID
                    item.getCPULocation(),  // CPU Module(CPU LOCATION)
                    item.getStationId(),  //Station ID
                    item.getdataType(),
                    plcAddrHexCode);
            resultMap.putAll(tempMap);
        }
        /////////////////////////////////////////
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
        return resultMap;
    }

    @Override
    public String produce(String lastSourceOffset, int maxBatchSize, BatchMaker batchMaker) throws StageException {
        long nextSourceOffset = 0;
        if (lastSourceOffset != null) {
            nextSourceOffset = Long.parseLong(lastSourceOffset);
        }
        try {
            long beginTime = System.currentTimeMillis();
            if (xAddressEnabled()) {
                Record record = getContext().createRecord(String.valueOf(UUID.randomUUID()));
                Map<String, Field> result;
                result = executeCommand(getXAddressRange(), MelsecOriginConstants.PLC_XADDR_HEXCODE);
                record.set(Field.create(result));
                for (String key : result.keySet()) {
                    System.out.println("key=" + key + "::::::::::::::::::value=" + result.get(key));
                }
                batchMaker.addRecord(record);
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
            long endTime = System.currentTimeMillis();
            long timeGap = getTimeInterval() - (endTime - beginTime);
            if (timeGap < 0) {
                timeGap = 0;
            }
            sleep(timeGap);
        } catch (Exception e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    e.printStackTrace();
                    return "Error01";
                }

                @Override
                public String getMessage() {
                    return "Error Code Occurred";
                }
            };
            throw new StageException(errorCode);

        }
        ++nextSourceOffset;
        return String.valueOf(nextSourceOffset);
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

    public abstract int getTimeInterval();

    public abstract List<TagHexAddressInput> getXAddressRange();
}
