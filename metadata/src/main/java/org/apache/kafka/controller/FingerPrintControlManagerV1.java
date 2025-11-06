package org.apache.kafka.controller;


import java.util.Map;

public interface FingerPrintControlManagerV1 {
    String installId();

    void checkLicense(Map<String, String> configs, String installId);

//    void startScheduleCheck();
//
//    void replay(FingerPrintRecord record);
//
//    boolean exists();
}
