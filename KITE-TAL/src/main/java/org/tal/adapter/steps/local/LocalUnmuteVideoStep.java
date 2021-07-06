package org.tal.adapter.steps.local;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.steps.TestStep;
import org.tal.adapter.pages.local.LocalO2oRTCPage;
import org.tal.adapter.pages.remote.RemoteO2oRTCPage;
import org.webrtc.kite.tests.TestRunner;

import java.net.MalformedURLException;

public class LocalUnmuteVideoStep extends TestStep {
    LocalO2oRTCPage localO2oRTCPage;

    public LocalUnmuteVideoStep(TestRunner runner){
        super(runner);
        this.localO2oRTCPage=new LocalO2oRTCPage(runner);
    }
    @Override
    protected void step() throws KiteTestException {
        localO2oRTCPage.unmuteLocalVideo();
    }

    @Override
    public String stepDescription() {
        return "local unmute视频。";
    }
}
