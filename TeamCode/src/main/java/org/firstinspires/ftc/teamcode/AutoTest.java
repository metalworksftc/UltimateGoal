package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoTest")

public class AutoTest extends LinearOpMode {
    Wheels wheels;
    Arm arm;
    Camera camera;

    @Override
    public void runOpMode() {
        wheels = new Wheels(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
        camera = new Camera(hardwareMap, telemetry, this);
        arm.close();
            waitForStart();
        wheels.forward(8, 0.25);
        String rings = camera.seeRings();
        telemetry.addLine(rings);
        telemetry.update();
        sleep(1000);
    }
}