package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.util.ThreadPool;
import com.vuforia.Frame;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.tfrec.Detector;

@Autonomous(name="Open House Auto Program F5 and A2", group="Open House")




public class AutoForOpenHouse extends LinearOpMode {
    //Detector myDector = new Detector(null, "/sdcard/FIRST/ExternalLibraries/SleveImageDetection.tflite", null, null);

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
    DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
    DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
    DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
    DcMotor lift = hardwareMap.dcMotor.get("lift");
    private Servo servoLeft;
    private Servo servoRight;

    Servo servo;
    Double servoPosition = 0.0;

    public void move(String direction, double distance){
        if( direction == "forward"){
            motorBackLeft.setPower(0.5);
            motorFrontRight.setPower(0.5);
            motorFrontLeft.setPower(0.5);
            motorBackRight.setPower(0.5);
            sleep((long) (500 * distance));
            motorBackLeft.setPower(0.0);
            motorFrontRight.setPower(0.0);
            motorFrontLeft.setPower(0.0);
            motorBackRight.setPower(0.0);
        }
        if( direction == "right"){
            motorFrontRight.setPower(-0.5);
            motorFrontLeft.setPower(0.5);
            motorBackRight.setPower(0.5);
            motorBackLeft.setPower(-0.5);
            sleep((long) (500 * distance));
            motorBackLeft.setPower(0.0);
            motorFrontRight.setPower(0.0);
            motorFrontLeft.setPower(0.0);
            motorBackRight.setPower(0.0);
        }
        if( direction == "left"){
            motorFrontRight.setPower(0.5);
            motorFrontLeft.setPower(-0.5);
            motorBackRight.setPower(-0.5);
            motorBackLeft.setPower(0.5);
            sleep((long) (500 * distance));
            motorBackLeft.setPower(0.0);
            motorFrontRight.setPower(0.0);
            motorFrontLeft.setPower(0.0);
            motorBackRight.setPower(0.0);
        }
        if( direction == "reverse"){
            motorBackLeft.setPower(-0.5);
            motorFrontRight.setPower(-0.5);
            motorFrontLeft.setPower(-0.5);
            motorBackRight.setPower(-0.5);
            sleep((long) (500 * distance));
            motorBackLeft.setPower(0.0);
            motorFrontRight.setPower(0.0);
            motorFrontLeft.setPower(0.0);
            motorBackRight.setPower(0.0);
        }
    }

    public void arm(String liftPosition){
        if (liftPosition == "ground"){
            lift.setPower(-1);
            sleep(3000);
            lift.setPower(0);
        }
        if (liftPosition == "medium"){
            lift.setPower(1);
            sleep(3000);
            lift.setPower(0);
         }
    }

    public void grabber(String position){
        if (position == "open"){
            servoPosition = 1.0;
            servoRight.setPosition(1.0);
            servoLeft.setPosition(1.0);
        }
        if (position == "close"){
            servoPosition = 0.0;
            servoRight.setPosition(0.0);
            servoLeft.setPosition(0.0);
        }

    }

    @Override
    public void runOpMode() {
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");


        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // ########################################################################################
        // !!!            IMPORTANT Drive Information. Test your motor directions.            !!!!!
        // ########################################################################################
        // Most robots need the motors on one side to be reversed to drive forward.
        // The motor reversals shown here are for a "direct drive" robot (the wheels turn the same direction as the motor shaft)
        // If your robot has additional gear reductions or uses a right-angled drive, it's important to ensure
        // that your motors are turning in the correct direction.  So, start out with the reversals here, BUT
        // when you first test your robot, push the left joystick forward and observe the direction the wheels turn.
        // Reverse the direction (flip FORWARD <-> REVERSE ) of any wheel that runs backward
        // Keep testing until ALL the wheels move the robot forward when you push the left joystick forward.


        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //move half tile left
            move("left", 0.5);
            //raise arm to medium
            //arm("medium");
            //move to medium junction
            move("forward", 1.0);
            //drop cone
            //grabber("open");
            //lower arm
            //arm("ground");
            // reverse
            move("reverse", 0.5);
            // move to location 1
            move("left", 0.5);
            move("forward", 1);

            }
        }
}

