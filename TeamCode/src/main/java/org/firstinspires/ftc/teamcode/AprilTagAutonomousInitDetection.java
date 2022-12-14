/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;



@Autonomous(name="AprilTag Image Detection Auto", group="Competition")




public class AprilTagAutonomousInitDetection extends LinearOpMode
{
    /*
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
    DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
    DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
    DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
    */


    public DcMotorEx lift;
    public ServoImplEx servoLeft;
    public ServoImplEx servoRight;







    OpenCvCamera camera;
    OpenCvCamera webcamLeft;
    OpenCvCamera webcamRight;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    AprilTagDetectionPipeline aprilTagDetectionPipelineLeft;
    AprilTagDetectionPipeline aprilTagDetectionPipelineRight;



    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    int ID_TAG_OF_INTEREST1 = 17;
    int ID_TAG_OF_INTEREST2 = 18;
    int ID_TAG_OF_INTEREST3 = 19; // Tag ID 18 from the 36h11 family

    AprilTagDetection tagOfInterestLeft = null;
    AprilTagDetection tagOfInterestRight = null;

    @Override
    public void runOpMode()
    {
        lift = hardwareMap.get(DcMotorEx.class, "lift");

        // Needed for internal run to position PIDs
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Lift will suspend in midair rather than coasting when at 0 power
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoLeft = hardwareMap.get(ServoImplEx.class, "servoLeft");
        servoRight = hardwareMap.get(ServoImplEx.class, "servoRight");

        servoLeft.setPwmEnable();
        servoRight.setPwmEnable();


        // put in run opmode
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//
//        Trajectory location1Part1 = drive.trajectoryBuilder(new Pose2d())
//                .forward(63d)
//                .build();
        double forwardDistance = 68;
        double strafeDistane = 54 ;

        // Use to go to tile 2
        forwardDistance = forwardDistance/1.5;
        forwardDistance = forwardDistance + 2;

        TrajectorySequence location1 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(strafeDistane)
//                .turn(10)
                .forward(forwardDistance)
//                .waitSeconds(2)

//                .waitSeconds(1)
                .build();

//        Trajectory location1Part2 = drive.trajectoryBuilder(new Pose2d()).strafeLeft(40d).build();


        TrajectorySequence location2 = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(forwardDistance+4)
//                .waitSeconds(1)
                .build();

        TrajectorySequence location3 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(strafeDistane-8)
                .forward(forwardDistance-8)
//                .waitSeconds(2)

//                .waitSeconds(1)
                .build();

//        Trajectory location3Part1 = drive.trajectoryBuilder(new Pose2d())
//                .forward(63d)
//                .build();
//        Trajectory location3Part2 = drive.trajectoryBuilder(new Pose2d()).strafeRight(40d).build();



        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcamLeft = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam Left"), cameraMonitorViewId);
        webcamRight =  OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam Right"), cameraMonitorViewId);

        aprilTagDetectionPipelineLeft = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
        aprilTagDetectionPipelineRight = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
        webcamLeft.setPipeline(aprilTagDetectionPipelineLeft);
        webcamRight.setPipeline(aprilTagDetectionPipelineRight);

        webcamLeft.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcamLeft.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        webcamRight.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcamRight.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested())
        {
            // Left webcam
            ArrayList<AprilTagDetection> currentDetections1 = aprilTagDetectionPipelineLeft.getLatestDetections();

            if(currentDetections1.size() != 0)
            {
                boolean tag1Found = false;
                boolean tag2Found = false;
                boolean tag3Found = false;


                for(AprilTagDetection tag : currentDetections1)
                {
                    if(tag.id == ID_TAG_OF_INTEREST1)
                    {
                        tagOfInterestLeft = tag;
                        tag1Found = true;
                        break;
                    }
                    else if(tag.id == ID_TAG_OF_INTEREST2)
                    {
                        tagOfInterestLeft = tag;
                        tag2Found = true;
                        break;
                    }
                    else if(tag.id == ID_TAG_OF_INTEREST3)
                    {
                        tagOfInterestLeft = tag;
                        tag3Found = true;
                        break;
                    }

                }

                if(tag1Found)
                {
                    telemetry.addLine("Left webcam: Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterestLeft);
                }
                else if(tag2Found)
                {
                    telemetry.addLine("Left webcam: Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterestLeft);
                }
                else if(tag3Found)
                {
                    telemetry.addLine("Left webcam: Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterestLeft);
                }


                else
                {
                    telemetry.addLine("Left webcam: Don't see tag of interest :(");

                    if(tagOfInterestLeft == null)
                    {
                        telemetry.addLine("Left webcam: (The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nLeft webcam: But we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterestLeft);
                    }
                }

            }
            else
            {
                telemetry.addLine("Left webcam: Don't see tag of interest :(");

                if(tagOfInterestLeft == null)
                {
                    telemetry.addLine("Left webcam: (The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nLeft webcam: But we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterestLeft);
                }

            }

            telemetry.update();
            sleep(20);

            // Right webcam
            ArrayList<AprilTagDetection> currentDetectionsRight = aprilTagDetectionPipelineRight.getLatestDetections();

            if(currentDetectionsRight.size() != 0)
            {
                boolean tag1Found = false;
                boolean tag2Found = false;
                boolean tag3Found = false;


                for(AprilTagDetection tag : currentDetectionsRight)
                {
                    if(tag.id == ID_TAG_OF_INTEREST1)
                    {
                        tagOfInterestRight = tag;
                        tag1Found = true;
                        break;
                    }
                    else if(tag.id == ID_TAG_OF_INTEREST2)
                    {
                        tagOfInterestRight = tag;
                        tag2Found = true;
                        break;
                    }
                    else if(tag.id == ID_TAG_OF_INTEREST3)
                    {
                        tagOfInterestRight = tag;
                        tag3Found = true;
                        break;
                    }

                }

                if(tag1Found)
                {
                    telemetry.addLine("Right webcam: Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterestRight);
                }
                else if(tag2Found)
                {
                    telemetry.addLine("Right webcam: Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterestRight);
                }
                else if(tag3Found)
                {
                    telemetry.addLine("Right webcam: Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterestRight);
                }


                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterestRight == null)
                    {
                        telemetry.addLine("Right webcam: (The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nRight webcam: But we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterestRight);
                    }
                }

            }
            else
            {
                telemetry.addLine("Right webcam: Don't see tag of interest :(");

                if(tagOfInterestRight == null)
                {
                    telemetry.addLine("Right webcam: (The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nRight webcam: But we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterestRight);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterestLeft != null)
        {
            telemetry.addLine("Left webcam: Tag snapshot:\n");
            tagToTelemetry(tagOfInterestLeft);
            telemetry.update();
        }

        else
        {
            telemetry.addLine("Left webcam: No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        if(tagOfInterestRight != null)
        {
            telemetry.addLine("Right webcam: Tag snapshot:\n");
            tagToTelemetry(tagOfInterestRight);
            telemetry.update();
        }

        else
        {
            telemetry.addLine("Right webcam:  No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }
        boolean hasRun = false;
        while (opModeIsActive()) {
            /* Actually do something useful */
            if (tagOfInterestLeft == null && tagOfInterestRight == null) {
                /*
                 * Insert your autonomous code here, presumably running some default configuration
                 * since the tag was never sighted during INIT
                 */
                // Just in case go to location 1 if it fails
//                drive.followTrajectory(location2);
//                telemetry.addData("It failed, going to location 2 (Straight)", "");
//                telemetry.update();

                  if(hasRun == true) {
                      break;
                  }
                  hasRun = true;
            } else {
                /*
                 * Insert your autonomous code here, probably using the tag pose to decide your configuration.
                 */

                // e.g.
                if (tagOfInterestLeft.pose.x <= 20 || tagOfInterestRight.pose.x <= 20) {

                    if (tagOfInterestLeft.id == 17 || tagOfInterestRight.id == 17 && hasRun == false) {
                        //Run auto for Image 1
                        telemetry.addData(">", "Running Auto for Image 1");
                        telemetry.update();

                        //Do auto Code ~ 20 seconds


                        //Move to Area 1 ~ 10 seconds
                        drive.followTrajectorySequence(location1);
                        hasRun = true;
                    } else if (tagOfInterestLeft.id == 18 || tagOfInterestRight.id == 18 && hasRun == false) {
                        //Run auto for Image 2
                        telemetry.addData(">", "Running Auto for Image 2");
                        telemetry.update();

                        //Do auto Code ~ 20 seconds


                        //Move to Area 2 ~ 10 seconds
                        drive.followTrajectorySequence(location2);
                        hasRun = true;
                    } else if (tagOfInterestLeft.id == 19 || tagOfInterestRight.id == 19 && hasRun == false) {
                        //Run auto for Image 3
                        telemetry.addData(">", "Running Auto for Image 3");
                        telemetry.update();

                        //Do auto Code ~ 20 seconds


                        //Move to Area 3 ~ 10 seconds
                        drive.followTrajectorySequence(location3);


                        hasRun = true;
                    }

                } else {
                    telemetry.addData(">", "Detected Other Teams April Tag");
                }


            }
        }



    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }

}