package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TurnConstraints;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.roadrunner.Drawing;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.TankDrive;
import org.firstinspires.ftc.teamcode.roadrunner.tuning.TuningOpModes;

@TeleOp
public class TeleOpRR extends LinearOpMode {

    DcMotorEx motor_v, motor_z, motor_v2, motor_z2;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        motor_z = hardwareMap.get(DcMotorEx.class, "motor_z");
        motor_z2 = hardwareMap.get(DcMotorEx.class, "motor_z");
        motor_v = hardwareMap.get(DcMotorEx.class, "motor_v");
        motor_v2 = hardwareMap.get(DcMotorEx.class, "motor_v2");

        motor_z.setDirection(DcMotorSimple.Direction.FORWARD);

        motor_v.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_v2.setDirection(DcMotorSimple.Direction.FORWARD);

        motor_v.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_v2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            Pose2d pose = drive.localizer.getPose();
            motor_z2.setPower(0.8);

            if (gamepad1.dpad_down) {
                motor_v.setPower(0);
                motor_v2.setPower(0);
            }
            if (gamepad1.dpad_right) {
                motor_v.setPower(0.6);
                motor_v2.setPower(0.6);
            }
            if (gamepad1.dpad_left) {
                motor_v.setPower(-1);
                motor_v2.setPower(-1);
            }
            if (gamepad1.dpad_up) {
                motor_v.setPower(-0.84);
                motor_v2.setPower(-0.84);
            }

            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));


            if (gamepad1.right_bumper) {
                motor_z.setPower(-0.85);
            } else {
                motor_z.setPower(-gamepad1.right_trigger + gamepad1.left_trigger*0.8);
            }

            drive.updatePoseEstimate();

            telemetry.addData("x", pose.position.x);
            telemetry.addData("y", pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.update();
        }
    }
}

