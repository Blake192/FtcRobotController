package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */


@TeleOp(name = "Localization Test 9108", group = "Linear Opmode")



public class LocalizationTest9108 extends LinearOpMode {
    // Custom for lift
    //private DcMotorEx lift;
    // Custom for claw
    private Servo servoLeft, servoRight;

    public DcMotorEx lift;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lift = hardwareMap.get(DcMotorEx.class, "lift");
        servoLeft = hardwareMap.get(Servo.class, "servoLeft");
        servoRight = hardwareMap.get(Servo.class, "servoRight");

        waitForStart();

        boolean invert = false;
        int invertInt;
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
            telemetry.update();


            if (gamepad1.left_trigger > 0.05 && gamepad1.right_trigger < 0.05) { // Raise up on right trigger
                lift.setPower(-1 * gamepad1.left_trigger);
                telemetry.addData("Left Trigger: ", gamepad1.left_trigger);
                telemetry.update();
            }

            if (gamepad1.right_trigger > 0.05 && gamepad1.left_trigger < 0.05) {
                lift.setPower(gamepad1.right_trigger);
            }
            else {
                lift.setPower(0);
            }



            if (gamepad1.x) {
                invert = !invert;
            }

            if (invert = false) {
                invertInt = 1;
            } else {
                invertInt = -1;
            }

            if (gamepad2.right_trigger > 0.05) {
                servoRight.setPosition(servoRight.getPosition()-.5*invertInt);
            } else {
                servoRight.setPosition(servoRight.getPosition());
            }

            if (gamepad2.left_trigger > 0.05) {
                servoLeft.setPosition(servoLeft.getPosition()+.5*invertInt);
            } else {
                servoLeft.setPosition(servoLeft.getPosition());
            }






        }
    }
}
