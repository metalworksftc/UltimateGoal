package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Two Motor ")

public class TwoMotors extends OpMode {
    DcMotor motor1, motor2;
    private double power1 = 0;
    private double power2 = 0;

    @Override
    public void init() {
        motor1 = hardwareMap.dcMotor.get("m1");
        motor2 = hardwareMap.dcMotor.get("m2");
    }

    @Override
    public void loop() {
        if (gamepad1.left_bumper) {
            power1 = -1;
        }
        if (gamepad1.right_bumper) {
            power2 = -1;
        }
        if (gamepad1.b){
            power1 = 1;
            power2 = 1;
        }
        if (gamepad1.a || gamepad1.y || gamepad1.x || gamepad1.back || gamepad1.dpad_down || gamepad1.dpad_left || gamepad1.dpad_right || gamepad1.dpad_up || gamepad1.left_stick_button || gamepad1.right_stick_button || gamepad1.start) {
            power1 = 0;
            power2 = 0;
        }
        motor1.setPower(power1);
        motor2.setPower(power2);
    }
}
