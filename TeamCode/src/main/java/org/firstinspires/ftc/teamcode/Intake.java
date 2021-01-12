package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    DcMotor beaterBar, conveyorBelt,launchMotor;
    Servo pushServo;


    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        init(hardwareMap,telemetry);
    }

    private void init(HardwareMap hardwareMap, Telemetry telemetry) {
        beaterBar = hardwareMap.dcMotor.get("bb");
//        conveyorBelt = hardwareMap.dcMotor.get("cb");
        launchMotor = hardwareMap.dcMotor.get("lm");
//        pushServo = hardwareMap.servo.get("ps");
    }

    public void intake(boolean on) {
        if (on) {
            beaterBar.setPower(1);
//            conveyorBelt.setPower(1);
        }
        else {
            beaterBar.setPower(0);
//            conveyorBelt.setPower(0);
        }
    }

    public void flywheel(boolean on) {
        if (on){
            launchMotor.setPower(-1);
        }
        else {
            launchMotor.setPower(0);
        }
    }
//    public void push(boolean on) {
//       if (on){
//            pushServo.setPosition(1);
//       }
//       else {
//           pushServo.setPosition(0);
//       }
//    }

}