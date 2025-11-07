/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kafka.image;

import org.apache.kafka.common.metadata.FingerPrintRecord;

/**
 * Represents changes to the fingerprint information in the metadata image.
 */
public final class FingerPrintDelta {
    private final FingerPrintImage image;

    private Integer maxNodeCount = null;
    private Long createdTimestamp = null;

    public FingerPrintDelta(FingerPrintImage image) {
        this.image = image;
    }

    public void replay(FingerPrintRecord record) {
        maxNodeCount = record.maxNodeCount();
        createdTimestamp = record.createdTimestamp();
    }

    public FingerPrintImage apply() {
        int newMaxNodeCount = maxNodeCount != null ? maxNodeCount : image.maxNodeCount();
        long newCreatedTimestamp = createdTimestamp != null ? createdTimestamp : image.createdTimestamp();
        return new FingerPrintImage(newMaxNodeCount, newCreatedTimestamp);
    }
}

