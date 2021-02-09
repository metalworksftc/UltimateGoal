package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RingRecognition2")

public class RingRecognition2 extends LinearOpMode {
    Wheels wheels;
    Camera camera;
    Arm arm;

    @Override
    public void runOpMode() {
        wheels = new Wheels(hardwareMap, telemetry);
        camera = new Camera(hardwareMap, telemetry, this);
        arm = new Arm(hardwareMap, telemetry);
        arm.close();
        telemetry.addLine("inited");
        telemetry.update();
        waitForStart();

        wheels.forward(8, 0.25);

        String rings = camera.seeRings();
//        telemetry.addLine(rings);
//        telemetry.update();
//        sleep(1000);


        if (rings.equals("Quad")) {
            standardDrive(30,95,30,80);

        } else if (rings.equals("Single")) {
            driveAround(30,75,15,50);

    } else if (rings.equals("no stack")) {
        standardDrive(30,45,0,80);
    }


}

    public void standardDrive(int left, int forward, int back, int right) {
        wheels.left(left,wheels.driveSpeed);
        wheels.absoluteTurnPower(0, wheels.driveSpeed);
        wheels.forward(forward, wheels.driveSpeed);
        sleep(2000);
        wheels.right(10,wheels.driveSpeed);
        arm.drop(3000);
        wheels.backwards(back, wheels.driveSpeed);
        sleep(500);
        wheels.right(right,wheels.driveSpeed);
        arm.drop(3000);
    }

    public void driveAround(int left, int forward, int back, int right) {
        wheels.left(left, wheels.driveSpeed);
        wheels.absoluteTurnPower(0, wheels.driveSpeed);
        wheels.forward(forward, wheels.driveSpeed);
        wheels.right(right-5,wheels.driveSpeed);
        sleep(500);
        arm.drop(3000);
        wheels.backwards(back, wheels.driveSpeed);
        sleep(500);
        wheels.right(back,wheels.driveSpeed);
        arm.drop(3000);
    }
}
