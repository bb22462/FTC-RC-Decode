package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.actions.Shuter;
import org.firstinspires.ftc.teamcode.autonomous.actions.Zahvat;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Autonomous
public class TestAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);
        Zahvat zahvat = new Zahvat(hardwareMap);
        Shuter shuter = new Shuter(hardwareMap);
        TrajectoryActionBuilder trajectory1 = drive.actionBuilder(startPose)
                .splineTo(new Vector2d(24, 24), Math.toRadians(90))
                .stopAndAdd(zahvat.setPower(1))
                .waitSeconds(3)
                .stopAndAdd(zahvat.setPower(0));


        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        trajectory1.build()
                )
        );

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
