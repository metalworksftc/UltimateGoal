package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TestWheels")

public class TestWheels extends OpMode {
    DcMotor motor;
    Servo servo;
    Wheels wheels;

    @Override
    public void init() {
        wheels = new Wheels(hardwareMap, telemetry);
        motor = hardwareMap.dcMotor.get("m");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        servo = hardwareMap.servo.get("s");
    }

    @Override
    public void loop() {
        wheels.driveCartesian(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        motor.setPower(gamepad1.right_stick_y * 0.5);
        servo.setPosition(gamepad1.right_trigger);

    }
}