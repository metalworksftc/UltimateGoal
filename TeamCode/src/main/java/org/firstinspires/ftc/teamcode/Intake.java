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

    public void intake(boolean on) {
        if (on){
            beaterBar.setPower(-75);
        }
        else {
            beaterBar.setPower(0);
        }
    }

    public void flywheel(boolean on) {

        if (on){
            launchMotor.setPower(-0.85);
        }
        else {
            launchMotor.setPower(0);
        }
        final long time = System.currentTimeMillis() - formerTime;
        if (time > 500) {
            final int counts = launchMotor.getCurrentPosition() - formerCounts;
            formerCounts = launchMotor.getCurrentPosition();
            formerTime = System.currentTimeMillis();
            telemetry.addLine("Counts" + String.valueOf(counts));
            telemetry.addLine("Time " + String.valueOf(time));
            telemetry.addLine("Speed Seconds " + String.valueOf(1000 * counts / time));
            telemetry.update();
        }

    }
    public void push(float on) {
    pushServo.setPosition(on);
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