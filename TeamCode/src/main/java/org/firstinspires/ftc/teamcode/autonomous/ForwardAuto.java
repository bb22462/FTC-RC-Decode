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

@Autonomous(name = "Проезд вперед")
public class ForwardAuto extends LinearOpMode {

    double voltage;

    double powerCorrect(double p) {
        return (12.8 / voltage) * p;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);
        Zahvat zahvat = new Zahvat(hardwareMap);
        Shuter shuter = new Shuter(hardwareMap);
        TrajectoryActionBuilder throwPreloaded = drive.actionBuilder(startPose)
                .strafeToLinearHeading(new Vector2d(24, 0), Math.toRadians(0));





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
