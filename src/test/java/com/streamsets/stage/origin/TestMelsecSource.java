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
import com.streamsets.stage.origin.menuconfig.MelsecSystemType;
import com.streamsets.stage.origin.menuconfig.TagHexAddressInput;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMelsecSource {
    private static final int MAX_BATCH_SIZE = 5;

    @Test
    public void testOrigin() throws Exception {
        TagHexAddressInput testHexdata = new TagHexAddressInput();
        testHexdata.beginAddress="000000";
        testHexdata.endAddress="000001";
        //testHexdata.networkId="FF";
        //testHexdata.stationId="00";
        testHexdata.isReadOnly=true;
        List<TagHexAddressInput> testHexList = new ArrayList<>();
        testHexList.add(testHexdata);

        //{beginAddress: 000000, endAddress: 000000, stationID:00, networkID: FF,readonly:true};
        SourceRunner runner = new SourceRunner.Builder(MelsecDSource.class)
                .addConfiguration("xAddress", true)
                .addConfiguration("timeOut", 3000)
                .addConfiguration("yAddress", false)
                .addConfiguration("xAddressRange", testHexList)
                .addConfiguration("port", 5000)
                .addConfiguration("commType", MelsecCommtype.UDP)
                .addConfiguration("systemType", MelsecSystemType.Q_SERIES)
                .addConfiguration("ipAddress", "127.0.0.1")
                .addOutputLane("lane")
                .build();


            runner.runInit();
        try {
            final String lastSourceOffset = null;
            StageRunner.Output output = runner.runProduce(lastSourceOffset, MAX_BATCH_SIZE);
            Assert.assertEquals("5", output.getNewOffset());
            List<Record> records = output.getRecords().get("lane");
            Assert.assertEquals(5, records.size());
            Assert.assertTrue(records.get(0).has("/fieldName"));
            Assert.assertEquals("Some Value", records.get(0).get("/fieldName").getValueAsString());

        } finally {
            runner.runDestroy();
        }
    }

}
