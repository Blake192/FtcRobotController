package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);


//        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(30, 30, Math.toRadians(180), Math.toRadians(180), 14)
//                .setDimensions(14, 14)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-33, -24*3+20/2, Math.toRadians(90)))
//                                .strafeLeft(27d)
////                .turn(10) // 24d is one square!
//                                .forward(30d)
////                .waitSeconds(2)
//
////                .waitSeconds(1)
//                                .strafeRight(2d)
//                                .forward(20d)
//                                .strafeRight(12d)
//                                .turn(Math.toRadians(90))
//                                .forward(12d)
//                                .build()
//                );

        // good code yes!
//        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14)
//                .setDimensions(14, 14)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-33, -62, Math.toRadians(90)))
//
//                                .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(190))
////                                .splineToSplineHeading(new Pose2d(-60, -36, Math.toRadians(90)), Math.toRadians(40))
////                                .strafeLeft(1.6)
////                                .forward(.5)
//
//                                .waitSeconds(.5) // Grab cone or something :yawn:
////                                .splineToSplineHeading(new Pose2d(-56, -23.6, Math.toRadians(0)), Math.toRadians(0))
//                                // back 1 down 1
//
//                                .back(1)
//
//
//                                .splineToSplineHeading(new Pose2d(-23.5, -12, Math.toRadians(90)), Math.toRadians(0))
//                                .forward(2)
//                                .waitSeconds(.5)
//                                .back(2)
//                                .waitSeconds(.5)
//                                .back(1)
//                                .strafeLeft(1)
//                                .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(190))
//
//
//
////                .waitSeconds(2)
//
////                .waitSeconds(1)
//
//                                .build()
//                );

        // Good code works well to go to cone stack first then high junction
//        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14)
//                .setDimensions(14, 14)
//                .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-33, -62, Math.toRadians(90)))
//
//                                        .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(190))
//
//
//
//
//                                        .waitSeconds(.5) // Grab cone or something :yawn:
//                                        .back(1)
//                                        .splineToSplineHeading(new Pose2d(-23.5, -14.5, Math.toRadians(90)), Math.toRadians(0))
//                                        .forward(5)
//                                        .waitSeconds(.5)
//                                        .back(5)
//                                        .strafeLeft(1)
//                                        .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(180))
//
//                                        .waitSeconds(.5) // Grab cone or something :yawn:
//                                        .back(1)
//                                        .splineToSplineHeading(new Pose2d(-23.5, -14.5, Math.toRadians(90)), Math.toRadians(0))
//                                        .forward(5)
//                                        .waitSeconds(.5)
//                                        .back(5)
//                                        .strafeLeft(1)
//                                        .splineToSplineHeading(new Pose2d(-58, -12, Math.toRadians(180)), Math.toRadians(180))
//
//                                        .build()
//                );


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180)*2, Math.toRadians(180)*2, 14)
                .setDimensions(14, 14)
                .followTrajectorySequence(drive ->

                                .strafeRight(21)
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




                                .build()                        drive.trajectorySequenceBuilder(new Pose2d(-33, -62, Math.toRadians(90)))


                );





        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)

                .start();
    }


}