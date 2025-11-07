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
import org.apache.kafka.image.writer.ImageWriter;
import org.apache.kafka.image.writer.ImageWriterOptions;

import java.util.Objects;

/**
 * Represents the fingerprint information in the metadata image.
 *
 * This class is thread-safe.
 */
public final class FingerPrintImage {
    public static final FingerPrintImage EMPTY = new FingerPrintImage(0, 0L);

    private final int maxNodeCount;
    private final long createdTimestamp;

    public FingerPrintImage(int maxNodeCount, long createdTimestamp) {
        this.maxNodeCount = maxNodeCount;
        this.createdTimestamp = createdTimestamp;
    }

    public boolean isEmpty() {
        return maxNodeCount == 0 && createdTimestamp == 0L;
    }

    public int maxNodeCount() {
        return maxNodeCount;
    }

    public long createdTimestamp() {
        return createdTimestamp;
    }

    public void write(ImageWriter writer, ImageWriterOptions options) {
        if (!isEmpty()) {
            writer.write(0, new FingerPrintRecord()
                .setMaxNodeCount(maxNodeCount)
                .setCreatedTimestamp(createdTimestamp));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxNodeCount, createdTimestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FingerPrintImage)) return false;
        FingerPrintImage other = (FingerPrintImage) o;
        return maxNodeCount == other.maxNodeCount &&
            createdTimestamp == other.createdTimestamp;
    }

    @Override
    public String toString() {
        return "FingerPrintImage(maxNodeCount=" + maxNodeCount +
            ", createdTimestamp=" + createdTimestamp + ")";
    }
}

