package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TestArm")

public class TestArm extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        telemetry.addLine(String.valueOf(gamepad1.left_stick_y));
    }
}
