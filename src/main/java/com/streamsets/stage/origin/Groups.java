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

import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;
import com.streamsets.stage.lib.MelsecOriginConstants;

@GenerateResourceBundle
public enum Groups implements Label {
    BASIC(MelsecOriginConstants.BASIC_GROUP),
    XTAG("INPUT"),
    YTAG("OUTPUT"),
    MTAG("LOGIC"),
    LTAG("LATCH"),
    FTAG("ARNANCY"),
    VTAG("EDGERELAY"),
    BTAG("LINK_RELAY"),
    DTAG("DATA"),
    WTAG("LINK_REGISTER"),
    TSTAG("TIMER CONTACT"),
    TCTAG("TIMER COIL"),
    TNTAG("TIMER VALUE"),
    SSTAG("ACCUMULATOR CONTACT"),
    SCTAG("ACCUMULATOR COIL"),
    SNTAG("ACCUMULATOR VALUE"),
    CSTAG("COUNTER CONTACT"),
    CCTAG("COUNTER COIL"),
    CNTAG("COUNTER VALUE"),
    SBTAG("LINK SPECIAL RELAY"),
    SWTAG("LINK SPECIAL REGISTER"),
    STAG("STEP RELAY"),
    DXTAG("DIRECT INPUT RELAY"),
    DYTAG("DIRECT OUTPUT RELAY"),
    ZTAG("INDEX REGISTER"),
    RTAG("FILE REGISTER"),
    ZRTAG("FILE REGISTER2");
    private final String label;

    Groups(String label) {
        this.label = label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabel() {
        return this.label;
    }
}