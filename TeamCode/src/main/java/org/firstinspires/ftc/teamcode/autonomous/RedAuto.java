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
        TrajectoryActionBuilder trajectory1 = drive.actionBuilder(startPose)
                .afterTime(0, () -> {
                    zahvat.setPower(0);
                })
                .afterTime(0.3, () -> {
                    shuter.setPower(0.85);
                })
                .afterTime(2, () -> {
                    zahvat.setPower(-0.6);
                })
                .afterTime(3.8, () -> {
                    zahvat.setPower(0);
                    shuter.setPower(0);
                })
                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
                .waitSeconds(1.5);

        TrajectoryActionBuilder trajectory2 = trajectory1.endTrajectory()
                .splineTo(new Vector2d(-11, 29), Math.toRadians(90))
                .lineToY(45)
                .afterTime(0, () -> {
                    zahvat.setPower(-1);
                })
                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
                .afterTime(0.3, () -> {
                    shuter.setPower(0.85);
                })
                .afterTime(2, () -> {
                    zahvat.setPower(-0.6);
                })
                .afterTime(3.8, () -> {
                    zahvat.setPower(0);
                    shuter.setPower(0);
                })
                .waitSeconds(1.5);
        TrajectoryActionBuilder trajectory3 = trajectory2.endTrajectory()
                .splineTo(new Vector2d(12, 29), Math.toRadians(90))
                .lineToY(45)
                .splineToLinearHeading(new Pose2d(-30, 31, Math.toRadians(135)), Math.toRadians(135))
                .afterTime(0.3, () -> {
                    shuter.setPower(0.85);
                })
                .afterTime(2, () -> {
                    zahvat.setPower(-0.6);
                })
                .afterTime(3.8, () -> {
                    zahvat.setPower(0);
                    shuter.setPower(0);
                })
                .waitSeconds(1.5);

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        trajectory1.build(),
                        trajectory2.build(),
                        trajectory3.build()
                )
        );

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
