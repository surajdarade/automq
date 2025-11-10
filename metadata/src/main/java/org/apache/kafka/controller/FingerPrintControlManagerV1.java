package org.apache.kafka.controller;

import org.apache.kafka.clients.admin.AlterConfigOp;
import org.apache.kafka.common.Reconfigurable;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.metadata.ConfigRecord;
import org.apache.kafka.common.metadata.FingerPrintRecord;

import java.util.Map;


public interface FingerPrintControlManagerV1  extends Reconfigurable {
    String TIME_KEY = "createdTimestamp";
    String NODE_COUNT_KEY = "maxNodeCount";

    String installId();

    boolean checkLicense();
//    void checkLicense(Map<String, String> configs, String installId);

    boolean startScheduleCheck();
//
    boolean replay(FingerPrintRecord record);

    boolean replayLicenseConfig(ConfigRecord record);
//
    boolean recordExists();

    boolean updateDynamicConfig(Map<ConfigResource, Map<String, Map.Entry<AlterConfigOp.OpType, String>>> configChanges);
}
