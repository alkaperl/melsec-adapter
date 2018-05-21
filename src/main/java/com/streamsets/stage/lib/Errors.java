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
package com.streamsets.stage.lib;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {

    ERROR_001("Unexpected Halt during processing Command, Please try restart the program"),
    ERROR_101("Abnormal command result Check Address Range or Address structure(HEX/DEC){}"),
    ERROR_200("Check Address should fit in DEC : {}"),
    ERROR_201("Verify Data read type : {}"),
    ERROR_401("UDP Connection Has failed. Check Melsec UDP port is opened or ping command reachable{}"),
    ERROR_404("UDP message has sent, but cannot receive reply message, Check the connectivity or port opened.{}"),
    ERROR_451("TCP/IP Connection has established, However the TCP cannot get send Message.{}"),
    ERROR_454("TCP/IP Connection Has failed. Check Melsec TCP port is opened or ping command reachable{}"),
    ERROR_500("System Type cannot verified, for building address binary check System Type. Some model is not implemented!{}"),
    ERROR_501("System Type cannot verified for building command binary, check System Type. Some model is not implemented!{}"),
    ERROR_502("{}"),
    ERROR_503("System Type cannot verified for dataType, check System Type. Some model is not implemented!{}"),
    ERROR_504("System Type cannot verified for building CPU Location, check System Type. Some model is not implemented!{}"),
    ERROR_505("Register Tag cannot verified during building the result address{}");
    private final String msg;
    Errors(String msg) { this.msg = msg; }

    /** {@inheritDoc} */
    @Override
    public String getCode() {
        return name();
    }
    /** {@inheritDoc} */
    @Override
    public String getMessage() {
        return msg;
    }
}
