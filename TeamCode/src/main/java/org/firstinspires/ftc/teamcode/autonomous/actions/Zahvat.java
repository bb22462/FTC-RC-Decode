package org.firstinspires.ftc.teamcode.autonomous.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Zahvat {
    public DcMotorEx zahvat;

    public Zahvat(HardwareMap hardwareMap) {
        zahvat = hardwareMap.get(DcMotorEx.class, "motor_z");
        zahvat.setDirection(DcMotorSimple.Direction.FORWARD);
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
}