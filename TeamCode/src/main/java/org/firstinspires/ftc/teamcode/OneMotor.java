package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "One Motor ")

public class OneMotor extends OpMode {
    DcMotor motor;
    Servo servo;
    @Override
    public void init() {

        motor = hardwareMap.dcMotor.get("m");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        motor.setPower(gamepad1.left_stick_y);
    }
}
