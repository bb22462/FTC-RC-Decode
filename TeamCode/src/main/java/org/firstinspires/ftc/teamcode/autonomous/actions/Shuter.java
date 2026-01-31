package org.firstinspires.ftc.teamcode.autonomous.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shuter {
    public DcMotorEx shuter, shuter2;

    public Shuter(HardwareMap hardwareMap) {
        shuter = hardwareMap.get(DcMotorEx.class, "motor_v");
        shuter2 = hardwareMap.get(DcMotorEx.class, "motor_v2");

        shuter.setDirection(DcMotorSimple.Direction.FORWARD);
        shuter2.setDirection(DcMotorSimple.Direction.REVERSE);
        shuter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shuter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public class SetPower implements Action {
        private final double power;

        public SetPower(double power) {
            this.power = power;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            shuter.setPower(this.power);
            shuter2.setPower(this.power);
            return false;
        }
    }
    public Action setPower(double power) {
        return new SetPower(power);
    }
}