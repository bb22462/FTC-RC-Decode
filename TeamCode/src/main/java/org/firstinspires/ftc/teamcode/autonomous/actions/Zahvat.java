package org.firstinspires.ftc.teamcode.autonomous.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Zahvat {
    public DcMotorEx zahvat, zahvat2;

    public Zahvat(HardwareMap hardwareMap) {
        zahvat = hardwareMap.get(DcMotorEx.class, "motor_z");
        zahvat2 = hardwareMap.get(DcMotorEx.class, "motor_z2");
        zahvat.setDirection(DcMotorSimple.Direction.REVERSE);
        zahvat2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public class SetPower implements Action {
        private final double power;

        public SetPower(double power) {
            this.power = power;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            zahvat.setPower(this.power);
            return false;
        }
    }
    public Action setPower(double power) {
        return new SetPower(power);
    }


    public class SetPowerZ2 implements Action {
        private final double power;

        public SetPowerZ2(double power) {
            this.power = power;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            zahvat2.setPower(this.power);
            return false;
        }
    }
    public Action setPowerZ2(double power) {
        return new SetPowerZ2(power);
    }
}