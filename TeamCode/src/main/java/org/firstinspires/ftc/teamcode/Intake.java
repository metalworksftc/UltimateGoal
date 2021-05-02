package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    DcMotorEx beaterBar, conveyorBelt, flywheelMotor;
    Servo pushServo;
    Telemetry telemetry;
    double flywheelVelocity = -1175;
    public boolean flywheelAtSpeed = false;

    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        init(hardwareMap, telemetry);
    }

    private void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        beaterBar = hardwareMap.get(DcMotorEx.class, "bb");
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "lm");
        pushServo = hardwareMap.servo.get("ps");
    }

    public void intake(double vel) {
        beaterBar.setVelocity(vel * 500);
    }

    public void flywheel(boolean on) {
        final double velocity = flywheelMotor.getVelocity();
        if (velocity < flywheelVelocity + 20 && velocity > flywheelVelocity - 20) {
            flywheelAtSpeed = true;
            telemetry.addLine("Flywheel is at speed");
        } else {
            flywheelAtSpeed = false;
            telemetry.addLine("Flywheel is not at speed");
        }
        telemetry.update();


        if (on) {
            flywheelMotor.setVelocity(flywheelVelocity);
            telemetry.addLine(String.valueOf(flywheelMotor.getVelocity()));
        } else {
            flywheelMotor.setVelocity(0);
        }
    }

    public void push(boolean on) {
        if (on){
            pushServo.setPosition(1);
        }
        else {
            pushServo.setPosition(0);

        }
    }

    public void autoPush() {
        pushServo.setPosition(1);
        sleep(500);
        pushServo.setPosition(0);
        sleep(500);
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}