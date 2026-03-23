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

    DcMotorEx leftFront, leftBack, rightBack, rightFront, motor_v, motor_z, motor_v2, motor_z2;
    Servo Zahvat, RotateZahvat;
    TouchSensor touch;


    //OpenCvCamera camera;
    @Override
    public void runOpMode() throws InterruptedException {

        leftFront = hardwareMap.get(DcMotorEx.class, "motor_l_f");
        leftBack = hardwareMap.get(DcMotorEx.class, "motor_l_r");
        rightBack = hardwareMap.get(DcMotorEx.class, "motor_r_r");
        rightFront = hardwareMap.get(DcMotorEx.class, "motor_r_f");

        motor_z = hardwareMap.get(DcMotorEx.class, "motor_z");
        motor_z2 = hardwareMap.get(DcMotorEx.class, "motor_z2");
        motor_v = hardwareMap.get(DcMotorEx.class, "motor_v");
        motor_v2 = hardwareMap.get(DcMotorEx.class, "motor_v2");


        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor_z.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_z2.setDirection(DcMotorSimple.Direction.REVERSE);

        motor_v.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_v2.setDirection(DcMotorSimple.Direction.FORWARD);

        motor_v.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_v2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();
        motor_z2.setPower(0.8);
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

            if (gamepad1.left_stick_button){
                leftFront.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) * 0.25);
                rightBack.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * 0.25);
                leftBack.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * 0.25);
                rightFront.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * 0.25);
            } else {
                leftFront.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) * 1);
                rightBack.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * 1);
                leftBack.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * 1);
                rightFront.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * 1);
            }

            if(gamepad1.left_bumper) {
                rightFront.setPower(1);
                leftBack.setPower(1);
            }
            else if(gamepad1.right_bumper) {
                leftFront.setPower(1);
                rightBack.setPower(1);
            }

            motor_z.setPower(-gamepad1.right_trigger + gamepad1.left_trigger*0.8);

        }

        telemetry.update();
    }
}