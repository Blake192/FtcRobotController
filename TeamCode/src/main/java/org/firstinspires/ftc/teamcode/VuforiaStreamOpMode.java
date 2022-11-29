package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/*
 * This sample demonstrates how to stream frames from Vuforia to the dashboard. Make sure to fill in
 * your Vuforia key below and select the 'Camera' preset on top right of the dashboard. This sample
 * also works for UVCs with slight adjustments.
 */
@Autonomous
public class VuforiaStreamOpMode extends LinearOpMode {


    public static final String VUFORIA_LICENSE_KEY = "Ae9b5eL/////AAABmYn/x0uzXEOVpQo7EQKrnU9fSjZdjxvrbceVLRBpdRCifJu5fhMSLs3MVrp94CWqYmBPzOU8fT37uSy6tWPB5qaS/+WKfgUu8a7a+VypBDGfxrjmzGM9dKKt8gB/n0rc7ekIj4piqHUCtRHJgyVRS6K+P2qniSXQvlJyZNIXliCJuTPq6UZJCOJvejNLcxG+3K/ZiumYzWz2SHsDgXnhNfmHnRLs5P31RKG1M3EQVp/K+/VJgd1CJQSiihB6eHeBaCz6Tp6Kqni9KWIAnFD4XFK0jwkhd2K3ar8ksvvwaA9Z1nE24pFXEW6UANWq59HhSj2E2lXCZ9w5krvz8L6Q434JOEaL0M1JYMoLYmYXICbK";

    @Override
    public void runOpMode() throws InterruptedException {
        // gives Vuforia more time to exit before the watchdog notices
        msStuckDetectStop = 2500;

        VuforiaLocalizer.Parameters vuforiaParams = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        vuforiaParams.vuforiaLicenseKey = VUFORIA_LICENSE_KEY;
        vuforiaParams.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(vuforiaParams);

        FtcDashboard.getInstance().startCameraStream(vuforia, 0);

        waitForStart();

        while (opModeIsActive());
    }
}