package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "New Axis Drive")

public class NewAxisDrive extends OpMode {
    Wheels wheels;

    DcMotor leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;

    @Override
    public void init() {
        wheels = new Wheels(hardwareMap, telemetry);
    }

    @Override
    public void loop() {

        if (gamepad1.left_bumper) {
            wheels.reversePower(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        }
        else {
            if (gamepad1.right_bumper) {
                wheels.driveCartesian(gamepad1.left_stick_x * 1, gamepad1.left_stick_y * 1, gamepad1.right_stick_x * 1);
            }
            else {
                wheels.driveCartesian(gamepad1.left_stick_x*0.5, gamepad1.left_stick_y*0.5, gamepad1.right_stick_x*0.5);
            }
        }
    }

}
