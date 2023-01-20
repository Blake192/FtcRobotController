package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/*
 * This is a simple routine to test translational drive capabilities.
 */
@Config
@Autonomous(name = "Trajectory Test", group = "drive")
public class TrajectoryTest extends LinearOpMode {
    public static double DISTANCE = 60; // in

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence epicRoute = drive.trajectorySequenceBuilder(new Pose2d(-48, -24))
                .waitSeconds(.3d)
                .splineToSplineHeading(new Pose2d(-23.5, -10, Math.toRadians(90)), Math.toRadians(180-25))
                .waitSeconds(.5) // Score first cone
                .back(4.5d)
                .strafeLeft(1)
                .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(180))
                .waitSeconds(.5) // Grab cone or something :yawn:
                .back(1)
                .splineToSplineHeading(new Pose2d(-23.5, -14.5, Math.toRadians(90)), Math.toRadians(0))
                .forward(5)
                .waitSeconds(.5)
//                                .back(4.5d)
//                                .strafeLeft(1)
//                                .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(180))
//                                .waitSeconds(.5) // Grab cone or something :yawn:
//                                .back(1)
//                                .splineToSplineHeading(new Pose2d(-23.5, -14.5, Math.toRadians(90)), Math.toRadians(0))
//                                .forward(5)
//                                .waitSeconds(.5)
//                                .back(4.5d)
//                                .strafeLeft(1)
//                                .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(180))
//                                .waitSeconds(.5) // Grab cone or something :yawn:
//                                .back(1)
//                                .splineToSplineHeading(new Pose2d(-23.5, -14.5, Math.toRadians(90)), Math.toRadians(0))
//                                .forward(5)
//                                .waitSeconds(.5)
//                                .back(4.5d)
//                                .strafeLeft(1)
//                                .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(180))
//                                .waitSeconds(.5) // Grab cone or something :yawn:
//                                .back(1)
//                                .splineToSplineHeading(new Pose2d(-23.5, -14.5, Math.toRadians(90)), Math.toRadians(0))
//                                .forward(5)
//                                .waitSeconds(.5)

                // Go to location 3
                .back(4.5d)
                .strafeLeft(1)
                .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(180))
                .waitSeconds(.5) // Grab cone or something :yawn:

                // Go to location 2
//                                .back(2.5d)
//                                .strafeLeft(12)
//                                .turn(Math.toRadians(90))

                // go to location 1
//                                .back(2.5d)
//                                .strafeRight(12)
//                                .turn(Math.toRadians(90))




                .build();


        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(epicRoute);

        Pose2d poseEstimate = drive.getPoseEstimate();
        telemetry.addData("finalX", poseEstimate.getX());
        telemetry.addData("finalY", poseEstimate.getY());
        telemetry.addData("finalHeading", poseEstimate.getHeading());
        telemetry.update();

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
