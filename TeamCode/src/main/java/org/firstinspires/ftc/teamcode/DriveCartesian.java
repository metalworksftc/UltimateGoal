package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "DriveCartesion")

public class DriveCartesian extends OpMode {
    Wheels wheels;
    private Arm arm;

    @Override
    public void init() {
        wheels = new Wheels(hardwareMap, telemetry);
        arm = new Arm(hardwareMap,telemetry);
    }

    @Override
    public void loop() {
        wheels.driveCartesian(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        arm.close(gamepad2.right_trigger);
        arm.swing(gamepad2.right_stick_y*0.75);
        telemetry.addLine(String.valueOf(arm.armMotor.getCurrentPosition()));
        telemetry.update();

    }
}