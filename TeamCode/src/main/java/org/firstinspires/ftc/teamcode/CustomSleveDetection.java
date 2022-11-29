package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Consumer;
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
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
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
import org.firstinspires.ftc.teamcode.ml.CustomSleve;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.TensorFlowLite;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.Delegate;
import org.tensorflow.lite.nnapi.NnApiDelegate;
import org.tensorflow.lite.R;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions;
import org.tensorflow.lite.task.vision.classifier.Classifications;
import org.tensorflow.lite.task.core.BaseTaskApi;
import org.tensorflow.lite.task.core.TaskJniUtils;
import org.tensorflow.lite.task.vision.detector.Detection;
import org.tensorflow.lite.task.vision.core.BaseVisionTaskApi;
import org.tensorflow.lite.task.vision.classifier.ImageClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@TeleOp(name = "CustomSleveDetection", group = "Linear Opmode")
public class CustomSleveDetection extends LinearOpMode {




    private static final String VUFORIA_KEY =
            "Ae9b5eL/////AAABmYn/x0uzXEOVpQo7EQKrnU9fSjZdjxvrbceVLRBpdRCifJu5fhMSLs3MVrp94CWqYmBPzOU8fT37uSy6tWPB5qaS/+WKfgUu8a7a+VypBDGfxrjmzGM9dKKt8gB/n0rc7ekIj4piqHUCtRHJgyVRS6K+P2qniSXQvlJyZNIXliCJuTPq6UZJCOJvejNLcxG+3K/ZiumYzWz2SHsDgXnhNfmHnRLs5P31RKG1M3EQVp/K+/VJgd1CJQSiihB6eHeBaCz6Tp6Kqni9KWIAnFD4XFK0jwkhd2K3ar8ksvvwaA9Z1nE24pFXEW6UANWq59HhSj2E2lXCZ9w5krvz8L6Q434JOEaL0M1JYMoLYmYXICbK";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */

    private VuforiaLocalizer vuforia;




    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        telemetry.addData(">", "Vuforia has INIT");
        telemetry.update();

        // Default Images for Teachable machine are 224 x 224 pixels





        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        //FtcDashboard.getInstance().startCameraStream(vuforia, 0);
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                //TODO: Create the Implementation of the TFlite model
                //TODO: Take image from Vuforia

                /*
                try {
                    CustomSleve model = CustomSleve.newInstance(context);

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);
                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    CustomSleve.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    // Releases model resources if no longer used.
                    model.close();


                } catch (IOException e) {
                    // TODO Handle the exception
                    // Hopefully it just crashes and gives error
                    telemetry.addData(">","Something Broke");
                }
                */

            }
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
}
