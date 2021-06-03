package org.tal.webrtc.tests;

import org.webrtc.kite.tests.KiteBaseTest;

public abstract class TalTest extends KiteBaseTest {
    public static String apprtcURL = "https://112-126-100-194.eaydu.com/index";
    public static String localServer="8-140-113-148.eaydu.com";
    public static String remoteServer="8-140-113-148.eaydu.com";
    public static String roomId="23982308";
    public static String localUserId="239823081";
    public static String remoteUserId="239823082";
    public static String rtnUserId="1001";

    protected boolean getStats = true;
    private boolean finished=false;

    @Override
    protected void payloadHandling() {
        super.payloadHandling();
        if (this.payload != null) {
            apprtcURL = payload.getString("url", apprtcURL);
        }
        if (this.payload != null) {
            localServer = payload.getString("localServer",localServer);
        }
        if (this.payload != null) {
            remoteServer = payload.getString("remoteServer",remoteServer);
        }
    }
}
