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
import com.streamsets.stage.origin.menuconfig.TagAddressInput;
import com.streamsets.stage.origin.util.CommandRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOG = LoggerFactory.getLogger(MelsecSource.class);
    Map<String, Integer> lastResultRecord;
    /**
     * Gives access to the UI configuration of the stage provided by the {@link MelsecDSource} class.
     */

    @Override
    protected List<ConfigIssue> init() {

        // Validate configuration values and open any required resources.
        List<ConfigIssue> issues = super.init();
        lastResultRecord = new HashMap<>();
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

    private Map<String, Integer> executeCommand(List<TagAddressInput> getPlcAddressRange, String plcAddrHexCode) throws StageException {
        Map<String, Integer> resultMap = new HashMap<>();
        CommandRunner commandRunner = new CommandRunner(getIpAddress(), getPort(), getSystemType().name(), getCommType().name(), getTimeOut());
        //long beginTime = System.currentTimeMillis();
        for (TagAddressInput item : getPlcAddressRange) {
            Map<String, Integer> tempMap;
            tempMap = commandRunner.readByteCommandResult(
                    item.getBeginAddress(), //begin Address
                    item.getEndAddress(),  //endAddress
                    item.getNetworkId(),  // NETWORK ID
                    item.getPlcId(),  // PLC ID
                    item.getCPULocation(),  // CPU Module(CPU LOCATION)
                    item.getStationId(),  //Station ID
                    item.getdataType(),
                    plcAddrHexCode, getMaxBlockSize());
            resultMap.putAll(tempMap);
        }
        /////////////////////////////////////////
        //long endTime = System.currentTimeMillis();
        //System.out.println(endTime - beginTime);
        //LOG.error(String.valueOf(endTime - beginTime));
        return resultMap;
    }
    @Override
    public String produce(String lastSourceOffset, int maxBatchSize, BatchMaker batchMaker) throws StageException {
        long nextSourceOffset = 0;
        Map<String, Integer> currentResultRecord = new HashMap<>();
        if (lastSourceOffset != null) {
            nextSourceOffset = Long.parseLong(lastSourceOffset);
        }
        try {
            long beginTime = System.currentTimeMillis();
            if (xAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getXAddressRange(), MelsecOriginConstants.PLC_XADDR_HEXCODE));
            }
            if (yAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getYAddressRange(), MelsecOriginConstants.PLC_YADDR_HEXCODE));
            }
            if (mAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getMAddressRange(), MelsecOriginConstants.PLC_MADDR_HEXCODE));
            }
            if (dAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getDAddressRange(), MelsecOriginConstants.PLC_DADDR_HEXCODE));
            }
            if (lAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getLAddressRange(), MelsecOriginConstants.PLC_LADDR_HEXCODE));
            }
            if (fAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getFAddressRange(), MelsecOriginConstants.PLC_FADDR_HEXCODE));
            }
            if (vAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getVAddressRange(), MelsecOriginConstants.PLC_VADDR_HEXCODE));
            }
            if (bAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getBAddressRange(), MelsecOriginConstants.PLC_BADDR_HEXCODE));
            }
            if (wAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getWAddressRange(), MelsecOriginConstants.PLC_WADDR_HEXCODE));
            }
            if (tsAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getTSAddressRange(), MelsecOriginConstants.PLC_TSADDR_HEXCODE));
            }
            if (tcAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getTCAddressRange(), MelsecOriginConstants.PLC_TCADDR_HEXCODE));
            }
            if (tnAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getTNAddressRange(), MelsecOriginConstants.PLC_TNADDR_HEXCODE));
            }
            if (ssAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getSSAddressRange(), MelsecOriginConstants.PLC_SSADDR_HEXCODE));
            }
            if (scAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getSCAddressRange(), MelsecOriginConstants.PLC_SCADDR_HEXCODE));
            }
            if (snAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getSNAddressRange(), MelsecOriginConstants.PLC_SNADDR_HEXCODE));
            }
            if (csAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getCSAddressRange(), MelsecOriginConstants.PLC_CSADDR_HEXCODE));
            }
            if (ccAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getCCAddressRange(), MelsecOriginConstants.PLC_CCADDR_HEXCODE));
            }
            if (cnAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getCNAddressRange(), MelsecOriginConstants.PLC_CNADDR_HEXCODE));
            }
            if (sbAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getSBAddressRange(), MelsecOriginConstants.PLC_SBADDR_HEXCODE));
            }
            if (swAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getSWAddressRange(), MelsecOriginConstants.PLC_SWADDR_HEXCODE));
            }
            if (sAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getSAddressRange(), MelsecOriginConstants.PLC_SADDR_HEXCODE));
            }
            if (dxAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getDXAddressRange(), MelsecOriginConstants.PLC_DXADDR_HEXCODE));
            }
            if (dyAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getDYAddressRange(), MelsecOriginConstants.PLC_DYADDR_HEXCODE));
            }
            if (zAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getZAddressRange(), MelsecOriginConstants.PLC_ZADDR_HEXCODE));
            }
            if (rAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getRAddressRange(), MelsecOriginConstants.PLC_RADDR_HEXCODE));
            }
            if (zrAddressEnabled()) {
                currentResultRecord.putAll(executeCommand(getZRAddressRange(), MelsecOriginConstants.PLC_ZRADDR_HEXCODE));
            }

            if (getTransferMode()) {
                if (lastResultRecord.size()==0){
                    for (Object key : currentResultRecord.keySet()) {
                        Record record = getContext().createRecord(String.valueOf(UUID.randomUUID()));
                        Map <String, Field> resultMap = new HashMap<>();
                        resultMap.put(key.toString(), Field.create(currentResultRecord.get(key)));
                        record.set(Field.create(resultMap));
                        batchMaker.addRecord(record);
                    }
                }
                else {
                    for (Object key : currentResultRecord.keySet()) {
                        if (!lastResultRecord.get(key).equals(currentResultRecord.get(key))) {
                            Record record = getContext().createRecord(String.valueOf(UUID.randomUUID()));
                            Map <String, Field> resultMap = new HashMap<>();
                            resultMap.put(key.toString(), Field.create(currentResultRecord.get(key)));
                            record.set(Field.create(resultMap));
                            batchMaker.addRecord(record);
                        }
                    }
                }
                lastResultRecord = (Map<String, Integer>) ((HashMap<String, Integer>) currentResultRecord).clone();
                currentResultRecord.clear();
            }
            else{
                for (Object key : currentResultRecord.keySet()) {
                    Record record = getContext().createRecord(String.valueOf(UUID.randomUUID()));
                    Map <String, Field> resultMap = new HashMap<>();
                    resultMap.put(key.toString(), Field.create(currentResultRecord.get(key)));
                    record.set(Field.create(resultMap));
                    batchMaker.addRecord(record);
                }
                currentResultRecord.clear();
            }

            long endTime = System.currentTimeMillis();
            long timeGap = getTimeInterval() - (endTime - beginTime);
            if (timeGap < 0) {
                timeGap = 0;
            }
            sleep(timeGap);

        } catch (StageException e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() { return e.getErrorCode().getCode(); }
                @Override
                public String getMessage() { return e.getMessage().substring(6); }
            };
            throw new StageException(errorCode);

        } catch (InterruptedException e) {
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "999";
                }
                @Override
                public String getMessage() {
                    return "General Error";
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
    public abstract int getTimeOut();
    public abstract int getTimeInterval();
    public abstract int getMaxBlockSize();
    public abstract boolean getTransferMode();
    public abstract boolean xAddressEnabled();
    public abstract boolean yAddressEnabled();
    public abstract boolean mAddressEnabled();
    public abstract boolean dAddressEnabled();
    public abstract boolean lAddressEnabled();
    public abstract boolean fAddressEnabled();
    public abstract boolean vAddressEnabled();

    public abstract boolean bAddressEnabled();
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

    public abstract boolean sbAddressEnabled();

    public abstract boolean swAddressEnabled();

    public abstract boolean sAddressEnabled();
    public abstract boolean dxAddressEnabled();
    public abstract boolean dyAddressEnabled();
    public abstract boolean zAddressEnabled();
    public abstract boolean rAddressEnabled();
    public abstract boolean zrAddressEnabled();
    public abstract List<TagAddressInput> getXAddressRange();
    public abstract List<TagAddressInput> getYAddressRange();
    public abstract List<TagAddressInput> getMAddressRange();
    public abstract List<TagAddressInput> getDAddressRange();
    public abstract List<TagAddressInput> getLAddressRange();
    public abstract List<TagAddressInput> getFAddressRange();
    public abstract List<TagAddressInput> getVAddressRange();

    public abstract List<TagAddressInput> getBAddressRange();
    public abstract List<TagAddressInput> getWAddressRange();
    public abstract List<TagAddressInput> getTSAddressRange();
    public abstract List<TagAddressInput> getTCAddressRange();
    public abstract List<TagAddressInput> getTNAddressRange();
    public abstract List<TagAddressInput> getSSAddressRange();
    public abstract List<TagAddressInput> getSCAddressRange();
    public abstract List<TagAddressInput> getSNAddressRange();
    public abstract List<TagAddressInput> getCSAddressRange();
    public abstract List<TagAddressInput> getCCAddressRange();
    public abstract List<TagAddressInput> getCNAddressRange();

    public abstract List<TagAddressInput> getSAddressRange();

    public abstract List<TagAddressInput> getSWAddressRange();

    public abstract List<TagAddressInput> getSBAddressRange();
    public abstract List<TagAddressInput> getDXAddressRange();
    public abstract List<TagAddressInput> getDYAddressRange();
    public abstract List<TagAddressInput> getZAddressRange();
    public abstract List<TagAddressInput> getRAddressRange();
    public abstract List<TagAddressInput> getZRAddressRange();
}
