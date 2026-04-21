package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@TeleOp(name = "Управляемый RR Beta")
public class TeleOpRRTesting extends LinearOpMode {

    DcMotorEx motor_v, motor_z, motor_v2, motor_z2;

    boolean shetkiEnabled = true;

    double voltage;

    boolean autoRunning = false;

    public static double voltageSt = 12.8;

    double powerCorrect(double p) {
        return (voltageSt / voltage) * p;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        motor_z = hardwareMap.get(DcMotorEx.class, "motor_z");
        motor_z2 = hardwareMap.get(DcMotorEx.class, "motor_z2");
        motor_v = hardwareMap.get(DcMotorEx.class, "motor_v");
        motor_v2 = hardwareMap.get(DcMotorEx.class, "motor_v2");

        motor_z.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_z2.setDirection(DcMotorSimple.Direction.REVERSE);

        motor_v.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_v2.setDirection(DcMotorSimple.Direction.FORWARD);

        motor_v.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_v2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            Pose2d pose = drive.localizer.getPose();
            voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
            if(gamepad1.back) {
                shetkiEnabled = !shetkiEnabled;
            }
            if(shetkiEnabled) {
                motor_z2.setPower(0.8);
            }
            else {
                motor_z2.setPower(0);
            }

            if (gamepad1.dpad_down) {
                motor_v.setPower(0);
                motor_v2.setPower(0);
            }
            if (gamepad1.dpad_right) {
                motor_v.setPower(powerCorrect(0.6));
                motor_v2.setPower(powerCorrect(0.6));
            }
            if (gamepad1.dpad_left) {
                motor_v.setPower(-1);
                motor_v2.setPower(-1);
            }
            if (gamepad1.dpad_up) {
                motor_v.setPower(powerCorrect(-0.75));
                motor_v2.setPower(powerCorrect(-0.75));
            }


//            if (gamepad1.left_bumper) {
//                drive.setDrivePowers(new PoseVelocity2d(
//                        new Vector2d(1, 1),  // y = forward, x = strafe right
//                        0
//                ));
//            }
//            else if (gamepad1.right_bumper) {
//                drive.setDrivePowers(new PoseVelocity2d(
//                        new Vector2d(-1, 1),  // y = forward, x = strafe left
//                        0
//                ));
//            }
            if (gamepad1.a) {
                autoRunning = true;
                Action toTarget = drive.actionBuilder(drive.localizer.getPose())
                        .strafeTo(new Vector2d(0, 0))
                        .build();
                Actions.runBlocking(toTarget);
                autoRunning = false;
            }

            if(!autoRunning) {
                drive.setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -gamepad1.left_stick_y,
                                -gamepad1.left_stick_x
                        ),
                        -gamepad1.right_stick_x
                ));
            }

            drive.updatePoseEstimate();

            telemetry.addData("x", pose.position.x);
            telemetry.addData("y", pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.update();
        }
    }
}

