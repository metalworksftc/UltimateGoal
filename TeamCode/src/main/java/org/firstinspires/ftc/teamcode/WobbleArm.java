package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "WobbleArm")

public class WobbleArm extends OpMode {
    DcMotor motor;
    Servo servo;
    @Override
    public void init() {

        motor = hardwareMap.dcMotor.get("m");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        servo = hardwareMap.servo.get("s");
    }

    @Override
    public void loop() {

        motor.setPower(gamepad1.left_stick_x*0.5);
        servo.setPosition(1-gamepad1.right_trigger);
    }
}
