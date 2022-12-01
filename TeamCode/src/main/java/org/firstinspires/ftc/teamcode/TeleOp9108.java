package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.ServoImplEx;


import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */


@TeleOp(name = "TeleOp 9108", group = "Linear Opmode")



public class TeleOp9108 extends LinearOpMode {
    // Custom for lift
    //private DcMotorEx lift;
    // Custom for claw
    public ServoImplEx servoLeft, servoRight;


    public DcMotorEx lift;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lift = hardwareMap.get(DcMotorEx.class, "lift");

        // Needed for internal run to position PIDs
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Lift will suspend in midair rather than coasting when at 0 power
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoLeft = hardwareMap.get(ServoImplEx.class, "servoLeft");
        servoRight = hardwareMap.get(ServoImplEx.class, "servoRight");

        servoLeft.setPwmEnable();
        servoRight.setPwmEnable();


        waitForStart();





        boolean clawInvertRight = true, clawInvertLeft = true;


        while (!isStopRequested()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.addData(" Vaule of: servoLeft.getPwmRange(): ", servoLeft.getPwmRange());
            telemetry.addData("Value of: servoLeft.isPwmEnabled(): ", servoLeft.isPwmEnabled());
            telemetry.addData("Value of servoLeft.getPosition();", servoLeft.getPosition());

            telemetry.addData(" Vaule of: servoRight.getPwmRange(): ", servoRight.getPwmRange());
            telemetry.addData("Value of: servoRight.isPwmEnabled(): ", servoRight.isPwmEnabled());
            telemetry.addData("Value of servoRight.getPosition();", servoRight.getPosition());

            telemetry.addData("lift.getCurrentPosition(): ", lift.getCurrentPosition());
            telemetry.update();
//
//            telemetry.addData(" Vaule of: servoLeft.getPwmRange(): ", servoLeft.getPwmRange());
//            telemetry.addData("Value of: servoLeft.isPwmEnabled(): ", servoLeft.isPwmEnabled());
//            telemetry.addData("Value of servoLeft.getPosition();", servoLeft.getPosition());
//
//            telemetry.addData(" Vaule of: servoRight.getPwmRange(): ", servoRight.getPwmRange());
//            telemetry.addData("Value of: servoRight.isPwmEnabled(): ", servoRight.isPwmEnabled());
//            telemetry.addData("Value of servoRight.getPosition();", servoRight.getPosition());
//
//            telemetry.addData("lift.getCurrentPosition(): ", lift.getCurrentPosition());
//
//
//            telemetry.update();

            if (gamepad2.left_trigger > 0.05 && gamepad2.right_trigger < 0.05) { // Raise up on right trigger
//                lift.setTargetPosition(lift.getCurrentPosition()+100);
                lift.setPower(1);
                telemetry.addData("Left Trigger: ", gamepad1.left_trigger);
                telemetry.update();
            }

            if (gamepad2.right_trigger > 0.05 && gamepad2.left_trigger < 0.05) {
//                lift.setTargetPosition(lift.getCurrentPosition()-100);
                lift.setPower(-1);
            }

            else {
//                lift.setTargetPosition(lift.getCurrentPosition());
                lift.setPower(0);
            }



            float closeDistance = 1.0f;
            float openDistance = 0.6f;

            // Right trigger controller 2
            // Starts closed with cone and clawInvertRight = true
            if (gamepad1.right_trigger > 0.05 && !clawInvertRight) {
                // Servo only go 0 to 1
                servoRight.setPosition(closeDistance); // Close
                clawInvertRight = !clawInvertRight;
            }

            if (gamepad1.right_trigger > 0.05 && clawInvertRight) {
                servoRight.setPosition(openDistance); // Open
                clawInvertRight = !clawInvertRight;
            }
            else {
                servoRight.setPosition(servoRight.getPosition());
            }

            // Left trigger controller 2
            // Starts closed with cone and clawInvertLeft = true at start
            if (gamepad1.left_trigger > 0.05 && !clawInvertLeft) {
                servoLeft.setPosition(closeDistance); // Close
                clawInvertLeft = !clawInvertLeft;
            }
            if (gamepad1.left_trigger > 0.05 && clawInvertLeft) {
                servoLeft.setPosition(openDistance); // Open
                clawInvertLeft = !clawInvertLeft;
            }
            else {
                servoLeft.setPosition(servoLeft.getPosition());
            }






        }
    }
}
