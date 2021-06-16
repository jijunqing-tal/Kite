package org.tal.webrtc.tests;

import org.tal.webrtc.checks.local.*;
import org.tal.webrtc.checks.remote.RemotePeerConnectionCheck;
import org.tal.webrtc.checks.remote.RemoteSubscribeVideoDisplayCheck;
import org.tal.webrtc.steps.ScreenRecordStep;
import org.tal.webrtc.steps.local.LocalJoinRoomStep;
import org.tal.webrtc.steps.remote.RemoteControlAudioStep;
import org.tal.webrtc.steps.remote.RemoteControlVideoStep;
import org.tal.webrtc.steps.remote.RemoteJoinRoomStep;
import org.webrtc.kite.tests.TestRunner;

public class WebRtcQuitRecoveryVideoTest extends TalTest{
    @Override
    protected void populateTestSteps(TestRunner runner) {
        LocalJoinRoomStep localJoinRoomStep = new LocalJoinRoomStep(runner);
        RemoteJoinRoomStep remoteJoinRoomStep = new RemoteJoinRoomStep(runner);

        //local先进，remote后进，否则会导致streamMap混乱
        localJoinRoomStep.setRoomId(roomId);
        localJoinRoomStep.setUserId(localUserId);
        localJoinRoomStep.setServer(localServer);
        runner.addStep(localJoinRoomStep);

        remoteJoinRoomStep.setRoomId(roomId);
        remoteJoinRoomStep.setUserId(remoteUserId);
        remoteJoinRoomStep.setServer(remoteServer);
        runner.addStep(remoteJoinRoomStep);

        runner.addStep(new LocalPeerConnectionCheck(runner));
        runner.addStep(new LocalSubscribeVideoDisplayCheck(runner,remoteUserId));
        runner.addStep(new RemotePeerConnectionCheck(runner));
        runner.addStep(new RemoteSubscribeVideoDisplayCheck(runner));

        runner.addStep(new RemoteControlVideoStep(runner));
        runner.addStep(new ScreenRecordStep(runner,"remote操作了mute video，demo截图。"));
        runner.addStep(new LocalSubscribeVideoMutedCheck(runner,1));
        runner.addStep(new ScreenRecordStep(runner,"看不到订阅视频测试通过，demo截图。"));

        runner.addStep(localJoinRoomStep);//local退出重进房间
        runner.addStep(new LocalSubscribeVideoMutedCheck(runner,0));//local stream刷新后remote stream的index为0
        runner.addStep(new ScreenRecordStep(runner,"退出重进后看不到订阅视频测试通过，demo截图。"));

        runner.addStep(new RemoteControlVideoStep(runner));
        runner.addStep(new ScreenRecordStep(runner,"remote操作了unmute video，demo截图。"));
        runner.addStep(new LocalSubscribeVideoUnmutedCheck(runner,0));//local stream刷新后remote stream的index为0
        runner.addStep(new ScreenRecordStep(runner,"能看到订阅视频测试通过，demo截图。"));
    }
}
