package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "DriveCartesion2")

public class DriveCartesian2 extends OpMode {
    Wheels wheels;
    private Arm arm;
    Intake intake;

    @Override
    public void init() {
        wheels = new Wheels(hardwareMap, telemetry);
         arm = new Arm(hardwareMap,telemetry);
        intake = new Intake(hardwareMap,telemetry);
        intake.pushServo.setPosition(0);
    }

    @Override
    public void loop() {

        intake.intake(gamepad2.left_trigger);
        intake.flywheel(gamepad2.right_bumper);
//        intake.push(gamepad2.left_trigger);

        arm.setFingerPosition(gamepad2.right_trigger);
        arm.swing(-gamepad2.right_stick_y*0.75);

        if (intake.flywheelAtSpeed){
            intake.push(gamepad2.left_bumper);
        }
        else {
            intake.push(false);
        }

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
