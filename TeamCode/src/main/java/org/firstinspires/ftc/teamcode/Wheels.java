package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Wheels {
    DcMotor leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;

    public void init(HardwareMap hardwareMap) {
        leftFrontMotor = hardwareMap.dcMotor.get("lfm");
        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontMotor = hardwareMap.dcMotor.get("rfm");
        leftRearMotor = hardwareMap.dcMotor.get("lrm");
        leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRearMotor = hardwareMap.dcMotor.get("rrm");
    }
}
