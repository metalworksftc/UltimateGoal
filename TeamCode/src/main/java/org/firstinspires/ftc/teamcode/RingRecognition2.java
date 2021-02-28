package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RingRecognition2")

public class RingRecognition2 extends LinearOpMode {
    Wheels wheels;
    Camera camera;
    Arm arm;
    Intake intake;

    @Override
    public void runOpMode() {
        wheels = new Wheels(hardwareMap, telemetry);
        camera = new Camera(hardwareMap, telemetry, this);
        arm = new Arm(hardwareMap, telemetry);
        intake = new Intake(hardwareMap, telemetry);
        arm.close();
        intake.pushServo.setPosition(0);
        telemetry.addLine("inited");
        telemetry.update();

        waitForStart();

        wheels.forward(8, 0.25);

        String rings = camera.seeRings();

        if (rings.equals("Quad")) {
            standardDrive(35,95,30,70);

        } else if (rings.equals("Single")) {
            driveAround(35,75,20,30);

        } else if (rings.equals("no stack")) {
          standardDrive(35,45,0,70);
         }

        if (rings.equals("no stack")){
            wheels.forward(15,0.5);
        }
        intake.flywheel(true);
        wheels.absoluteTurnPower(0, 0.5);
        sleep(3000);
        intake.autoPush();
        sleep(500);
        intake.autoPush();
        sleep(500);
        intake.autoPush();
        wheels.forward(10,0.5);
}

    public void standardDrive(int left, int forward, int back, int right) {
        wheels.left(left,wheels.driveSpeed);
        wheels.absoluteTurnPower(0, wheels.driveSpeed);
        wheels.forward(forward, wheels.driveSpeed);
        wheels.right(10,wheels.driveSpeed);
        arm.drop(3000);
        wheels.backwards(back, wheels.driveSpeed);
        wheels.right(right,wheels.driveSpeed);
        wheels.absoluteTurnPower(0,wheels.driveSpeed);
      //  arm.drop(3000);
    }

    public void driveAround(int left, int forward, int back, int right) {
        wheels.left(left, wheels.driveSpeed);
        wheels.absoluteTurnPower(0, wheels.driveSpeed);
        wheels.forward(forward, wheels.driveSpeed);
        wheels.right(45,wheels.driveSpeed);
        arm.drop(3000);
        wheels.backwards(back, wheels.driveSpeed);
        wheels.right(right,wheels.driveSpeed);
        wheels.absoluteTurnPower(0,wheels.driveSpeed);
        //  arm.drop(3000);
    }
}
