package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    DcMotor beaterBar, conveyorBelt,launchMotor;
    Servo pushServo;
    Telemetry telemetry;
    int formerCounts;
    long formerTime;
    double flywheelPower = -0.6;
    public boolean flywheelAtSpeed = false;

    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        init(hardwareMap,telemetry);
    }

    private void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        beaterBar = hardwareMap.dcMotor.get("bb");
//        conveyorBelt = hardwareMap.dcMotor.get("cb");
        launchMotor = hardwareMap.dcMotor.get("lm");
        pushServo = hardwareMap.servo.get("ps");
        formerCounts = launchMotor.getCurrentPosition();
        formerTime = System.currentTimeMillis();
    }

    public void intake(float on) {
            beaterBar.setPower(-on);
    }

    public void flywheel(boolean on) {
        final long time = System.currentTimeMillis() - formerTime;
//            if (time > 250) {
//                final int counts = launchMotor.getCurrentPosition() - formerCounts;
//                formerCounts = launchMotor.getCurrentPosition();
//                formerTime = System.currentTimeMillis();
//                telemetry.addLine("Counts" + String.valueOf(counts));
//                telemetry.addLine("Time " + String.valueOf(time));
//                final long speed = 1000 * counts / time;
//                telemetry.addLine("Speed Seconds " + String.valueOf(speed));
//                telemetry.addLine("Power " + String.valueOf(flywheelPower));
//                telemetry.update();

//                if (speed > -1200 && flywheelPower > -0.75) {
//                    //tweak up
//                    flywheelPower = flywheelPower - 0.1;
//                } else if (speed < -1200 && flywheelPower < 0) {
//                    //tweak down
//                    flywheelPower = flywheelPower + 0.75;
//                }
//
//                if (speed < -1150 && speed > -1250) {
//                    flywheelAtSpeed = true;
////                    telemetry.addLine("Flywheel is at speed");
////                    telemetry.update();
//                } else {
//                    flywheelAtSpeed = false;
////                    telemetry.addLine("Flywheel is not at speed");
////                    telemetry.update();
//                }

//            }
//
//
//        if (on){
//            launchMotor.setPower(flywheelPower);
//        }
//        else {
//            launchMotor.setPower(0);
//        }

        flywheelAtSpeed = true;
        if (on){
            launchMotor.setPower(-0.75);
        }
        else {
            launchMotor.setPower(0);
        }
    }
    public void push(boolean on) {
    pushServo.setPosition(on? 1.0: 0);
    }

    public void autoPush() {
        pushServo.setPosition(1);
        sleep(700);
        pushServo.setPosition(0);
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}