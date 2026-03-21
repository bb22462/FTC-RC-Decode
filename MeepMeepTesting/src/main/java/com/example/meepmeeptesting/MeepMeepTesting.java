package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

//        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-47, 46, Math.toRadians(90)))
//                // Первый заброс
//                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
//                .waitSeconds(1.5)
//
//                // Первый забор
//                .splineTo(new Vector2d(-11, 29), Math.toRadians(90))
//                .lineToY(45)
//                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
//                .waitSeconds(1.5)
//
//                // Второй Забор
//                .splineTo(new Vector2d(12, 29), Math.toRadians(90))
//                .lineToY(45)
//                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
//                .waitSeconds(5)
////
////                // Третий Забор
//                .splineTo(new Vector2d(36, 29), Math.toRadians(90))
//                .lineToY(45)
//                .splineTo(new Vector2d(-30, 31), Math.toRadians(135))
//                .waitSeconds(5)
////
////                // На линию
//                // .splineTo(new Vector2d(0, 47), Math.toRadians(270))
//                .build());

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .lineToX(24)
                .turn(Math.toRadians(90))
                .lineToY(24)
                .turn(Math.toRadians(90))
                .lineToX(0)
                .turn(Math.toRadians(90))
                .lineToY(0)
                .turn(Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}