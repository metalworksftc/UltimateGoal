package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoTest")

public class AutoTest extends LinearOpMode {
    Wheels wheels;
    Arm arm;
    Camera camera;
    Intake  intake;

    @Override
    public void runOpMode() {
        wheels = new Wheels(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
        camera = new Camera(hardwareMap, telemetry, this);
        intake = new Intake(hardwareMap, telemetry);
        arm.close();
        waitForStart();
//        wheels.forward(8, 0.25);
//        String rings = camera.seeRings();
//        telemetry.addLine(rings);
//        telemetry.update();
//        sleep(1000);
//        wheels.forwardCounts(2000,0.5);

        boolean on = true;
        intake.flywheel(on);
        sleep(500000000);
    }
}