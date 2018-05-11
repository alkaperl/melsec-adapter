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

import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.sdk.SourceRunner;
import com.streamsets.pipeline.sdk.StageRunner;
import com.streamsets.stage.origin.menuconfig.MelsecCommtype;
import com.streamsets.stage.origin.menuconfig.MelsecDataType;
import com.streamsets.stage.origin.menuconfig.MelsecSystemType;
import com.streamsets.stage.origin.menuconfig.TagAddressInput;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMelsecSource {
    private static final int MAX_BATCH_SIZE = 1;

    @Test
    public void testOrigin() throws Exception {
        TagAddressInput testDdata = new TagAddressInput();
        List<TagAddressInput> testDList = new ArrayList<>();
        testDdata.beginAddress = "000100";
        testDdata.endAddress = "00010f";
        testDdata.dataType = MelsecDataType.WORD;
        testDList.add(testDdata);

        TagAddressInput testMdata = new TagAddressInput();
        List<TagAddressInput> testMList = new ArrayList<>();
        testMdata.beginAddress = "000100";
        testMdata.endAddress = "00010F";
        testMdata.dataType = MelsecDataType.BOOLEAN;
        testMList.add(testMdata);

        TagAddressInput testYdata = new TagAddressInput();
        List<TagAddressInput> testYList = new ArrayList<>();
        testYdata.beginAddress = "000000";
        testYdata.endAddress = "000000F";
        testYdata.dataType = MelsecDataType.BOOLEAN;
        testYList.add(testYdata);


        SourceRunner runner = new SourceRunner.Builder(MelsecDSource.class)
                .addConfiguration("maxBlockSize", 256)
                .addConfiguration("transferMode", false)
                .addConfiguration("xAddress", false)
                .addConfiguration("yAddress", false)
                .addConfiguration("yAddressRange", testYList)
                .addConfiguration("mAddress", true)
                .addConfiguration("mAddressRange", testMList)
                .addConfiguration("dAddress", true)
                .addConfiguration("dAddressRange", testDList)
                .addConfiguration("wAddress", false)
                .addConfiguration("cnAddress", false)
                .addConfiguration("csAddress", false)
                .addConfiguration("ssAddress", false)
                .addConfiguration("vAddress", false)
                .addConfiguration("zAddress", false)
                .addConfiguration("tnAddress", false)
                .addConfiguration("swAddress", false)
                .addConfiguration("tsAddress", false)
                .addConfiguration("snAddress", false)
                .addConfiguration("dxAddress", false)
                .addConfiguration("tcAddress", false)
                .addConfiguration("ccAddress", false)
                .addConfiguration("sbAddress", false)
                .addConfiguration("fAddress", false)
                .addConfiguration("scAddress", false)
                .addConfiguration("snAddress", false)
                .addConfiguration("bAddress", false)
                .addConfiguration("dyAddress", false)
                .addConfiguration("lAddress", false)
                .addConfiguration("zrAddress", false)
                .addConfiguration("rAddress", false)
                .addConfiguration("sAddress", false)
                .addConfiguration("timeOut", 3000)
                .addConfiguration("timeInterval", 1000)
                .addConfiguration("port", 5000)
                .addConfiguration("commType", MelsecCommtype.TCPIP)
                .addConfiguration("systemType", MelsecSystemType.Q_SERIES)
                .addConfiguration("ipAddress", "127.0.0.1")
                .addOutputLane("lane")
                .build();


        runner.runInit();
        try {
            final String lastSourceOffset = null;
            StageRunner.Output output = runner.runProduce(lastSourceOffset, MAX_BATCH_SIZE);
            //Assert.assert("1", output.getNewOffset());
            List<Record> records = output.getRecords().get("lane");
            //Assert.assertEquals(1, records.size());
            //Assert.assertTrue(records.get(0).toString()=="");
            //Assert.assertEquals("Some Value", records.get(0).get("/fieldName").getValueAsString());

        } finally {
            runner.runDestroy();
        }
    }

}
