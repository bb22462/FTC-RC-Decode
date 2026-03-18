package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.autonomous.actions.Shuter;
import org.firstinspires.ftc.teamcode.autonomous.actions.Zahvat;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Autonomous
public class RedAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startPose = new Pose2d(-47, 46, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);
        Zahvat zahvat = new Zahvat(hardwareMap);
        Shuter shuter = new Shuter(hardwareMap);
        zahvat.setPowerZ2(0.8);
        TrajectoryActionBuilder throwPreloaded = drive.actionBuilder(startPose)
                .afterTime(0, () -> {
                    zahvat.setPower(0);
                })
                .afterTime(0.3, () -> {
                    shuter.setPower(0.85);
                })
                .afterTime(2, () -> {
                    zahvat.setPower(0.6);
                })
                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
                .waitSeconds(2);

        TrajectoryActionBuilder takeAndThrowFirst = throwPreloaded.endTrajectory()
                .afterTime(0, () -> {
                    zahvat.setPower(0);
                    shuter.setPower(0);
                })
                .splineTo(new Vector2d(-11, 29), Math.toRadians(90))
                .lineToY(45)
                .afterTime(0.3, () -> {
                    shuter.setPower(0.85);
                })
                .afterTime(2, () -> {
                    zahvat.setPower(0.6);
                })
                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
                .waitSeconds(2);

        TrajectoryActionBuilder takeAndThrowSecond = takeAndThrowFirst.endTrajectory()
                .afterTime(0, () -> {
                    zahvat.setPower(0);
                    shuter.setPower(0);
                })
                .splineTo(new Vector2d(12, 29), Math.toRadians(90))
                .lineToY(45)
                .afterTime(0.3, () -> {
                    shuter.setPower(0.85);
                })
                .afterTime(2, () -> {
                    zahvat.setPower(0.6);
                })
                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
                .waitSeconds(2);

        TrajectoryActionBuilder takeAndThrowThird = takeAndThrowSecond.endTrajectory()
                .afterTime(0, () -> {
                    zahvat.setPower(0);
                    shuter.setPower(0);
                })
                .splineTo(new Vector2d(36, 29), Math.toRadians(90))
                .lineToY(45)
                .afterTime(0.3, () -> {
                    shuter.setPower(0.85);
                })
                .afterTime(2, () -> {
                    zahvat.setPower(0.6);
                })
                .splineTo(new Vector2d(-30, 31), Math.toRadians(135))
                .waitSeconds(2);

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        throwPreloaded.build(),
                        takeAndThrowFirst.build(),
                        takeAndThrowSecond.build(),
                        takeAndThrowThird.build()
                )
        );

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
