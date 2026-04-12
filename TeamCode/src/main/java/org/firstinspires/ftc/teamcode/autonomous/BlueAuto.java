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

@Autonomous(name = "Синий автоном на 12")
public class BlueAuto extends LinearOpMode {

    double voltage;

    double powerCorrect(double p) {
        return (12.8 / voltage) * p;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
        Pose2d startPose = new Pose2d(-47, -46, Math.toRadians(270));
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);
        Zahvat zahvat = new Zahvat(hardwareMap);
        Shuter shuter = new Shuter(hardwareMap);
        TrajectoryActionBuilder throwPreloaded = drive.actionBuilder(startPose)
                // Первый заброс
                .stopAndAdd(zahvat.setPowerZ2(powerCorrect(0.8)))
                .afterTime(0, zahvat.setPower(0))
                .afterTime(0.3, shuter.setPower(powerCorrect(0.87)))
                .afterTime(2, zahvat.setPower(powerCorrect(1)))
                .splineToLinearHeading(new Pose2d(-30, -31, Math.toRadians(225)), Math.toRadians(225))
                .waitSeconds(1.2)

                // Второй заброс
                .afterTime(0, zahvat.setPower(0))
                .afterTime(0, shuter.setPower(0))
                .strafeToLinearHeading(new Vector2d(-8, -21), Math.toRadians(270))
                .afterTime(0, zahvat.setPower(powerCorrect(0.8)))
                .afterTime(0.0, shuter.setPower(powerCorrect(-0.86)))
                .afterTime(1.4, zahvat.setPower(powerCorrect(-1)))
                .afterTime(1.5, zahvat.setPower(0))
                .strafeTo(new Vector2d(-8, -53))
                .afterTime(1.3, shuter.setPower(powerCorrect(0.87)))
                .afterTime(2, zahvat.setPower(powerCorrect(1)))
                .splineToLinearHeading(new Pose2d(-28, -28, Math.toRadians(225)), Math.toRadians(225))
                .waitSeconds(1)

                // Третий заброс
                .afterTime(0, zahvat.setPower(0))
                .afterTime(0, shuter.setPower(0))
                .strafeToLinearHeading(new Vector2d(17, -18), Math.toRadians(270))
                .afterTime(0, zahvat.setPower(powerCorrect(0.8)))
                .afterTime(0.0, shuter.setPower(powerCorrect(-0.86)))
                .afterTime(1.4, zahvat.setPower(powerCorrect(-1)))
                .afterTime(1.5, zahvat.setPower(0))
                .strafeTo(new Vector2d(17, -58))
                .afterTime(2, shuter.setPower(powerCorrect(0.87)))
                .afterTime(2.95, zahvat.setPower(powerCorrect(1)))
                .strafeTo(new Vector2d(17, -40))
                .splineToLinearHeading(new Pose2d(-30, -31, Math.toRadians(225)), Math.toRadians(25))
                .waitSeconds(0.8)

                // Четвертый заброс
                .afterTime(0, zahvat.setPower(0))
                .afterTime(0, shuter.setPower(0))
                .strafeToLinearHeading(new Vector2d(39, -18), Math.toRadians(270))
                .afterTime(0, zahvat.setPower(powerCorrect(0.8)))
                .afterTime(0.0, shuter.setPower(powerCorrect(-0.87)))
                .afterTime(1.6, zahvat.setPower(powerCorrect(-1)))
                .afterTime(1.7, zahvat.setPower(0))
                .strafeTo(new Vector2d(39, -58))
                .afterTime(3.5, shuter.setPower(powerCorrect(0.87)))
                .afterTime(4.2, zahvat.setPower(powerCorrect(1)))
                .strafeTo(new Vector2d(39, -40))
                .splineToLinearHeading(new Pose2d(-30, -31, Math.toRadians(225)), Math.toRadians(135))
                .waitSeconds(1);




//        TrajectoryActionBuilder takeAndThrowThird = takeAndThrowSecond.endTrajectory()
//                .afterTime(0, () -> {
//                    zahvat.setPower(0);
//                    shuter.setPower(0);
//                })
//                .splineTo(new Vector2d(36, 29), Math.toRadians(90))
//                .lineToY(45)
//                .afterTime(0.3, () -> {
//                    shuter.setPower(-0.3);
//                })
//                .afterTime(2, () -> {
//                    zahvat.setPower(0.6);
//                })
//                .splineTo(new Vector2d(-30, 31), Math.toRadians(135))
//                .waitSeconds(2);

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        throwPreloaded.build()
                        //takeAndThrowFirst.build()
//                        takeAndThrowSecond.build(),
//                        takeAndThrowThird.build()
                )
        );

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
