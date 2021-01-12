package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "DriveCartesion2")

public class DriveCartesian2 extends OpMode {
    Wheels wheels;
//    private Arm arm;
    Intake intake;

    @Override
    public void init() {
        wheels = new Wheels(hardwareMap, telemetry);
//        arm = new Arm(hardwareMap,telemetry);
        intake = new Intake(hardwareMap,telemetry);
    }

    @Override
    public void loop() {

        intake.intake(gamepad2.right_bumper);
        intake.flywheel(gamepad2.left_bumper );

        if (gamepad1.left_bumper) {
            wheels.driveCartesian(gamepad1.left_stick_x * 1, gamepad1.left_stick_y * 1, gamepad1.right_stick_x * 1);
        }
        else {
            wheels.driveCartesian(gamepad1.left_stick_x*0.5, gamepad1.left_stick_y*0.5, gamepad1.right_stick_x*0.5);
        }

    }
}