    package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;



@TeleOp
public class MainTeleOp extends LinearOpMode {

    DcMotorEx motor_left_front, motor_left_back, motor_right_back, motor_right_front, motor_v, motor_z, motor_v2;
    Servo Zahvat, RotateZahvat;
    TouchSensor touch;


    //OpenCvCamera camera;
    @Override
    public void runOpMode() throws InterruptedException {

        motor_left_front = hardwareMap.get(DcMotorEx.class, "motor_l_f");
        motor_right_back = hardwareMap.get(DcMotorEx.class, "motor_r_r");
        motor_left_back = hardwareMap.get(DcMotorEx.class, "motor_l_r");
        motor_right_front = hardwareMap.get(DcMotorEx.class, "motor_r_f");
        motor_z = hardwareMap.get(DcMotorEx.class, "motor_z");
        motor_v = hardwareMap.get(DcMotorEx.class, "motor_v");
        motor_v2 = hardwareMap.get(DcMotorEx.class, "motor_v2");


        motor_left_front.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_right_back.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_left_back.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_right_front.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_z.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_v.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_v2.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_v.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_v2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
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
            if (gamepad1.left_bumper){
                motor_left_front.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * 0.25);
                motor_right_back.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * 0.25);
                motor_left_back.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * 0.25);
                motor_right_front.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) * 0.25);
            } else {
                motor_left_front.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * 1);
                motor_right_back.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * 1);
                motor_left_back.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * 1);
                motor_right_front.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) * 1);
            }
            if (gamepad1.right_bumper) {
                motor_z.setPower(-0.85);
            } else {
                motor_z.setPower(-gamepad1.right_trigger + gamepad1.left_trigger*0.8);
            }

        }

        telemetry.update();
    }
}