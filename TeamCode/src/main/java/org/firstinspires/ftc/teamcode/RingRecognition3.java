package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RingRecognition3")

public class RingRecognition3 extends LinearOpMode {
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
        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        wheels.forward(8, 0.25);

        String rings = camera.seeRings();

        if (rings.equals("Quad")) {
            standardDrive(35,95,40,50);
            wheels.forward(5,wheels.driveSpeed);

        } else if (rings.equals("Single")) {
            driveAround(35,75,20,20);
            wheels.forward(5,wheels.driveSpeed);

        } else if (rings.equals("no stack")) {
          standardDrive(35,45,5,50);
            wheels.forward(15,0.5);
         }
        intake.flywheel(true);
        wheels.right(16, 0.5);
        wheels.absoluteTurnPower(15, 0.3);
        wheels.absoluteTurnPower(0, 0.3);


        for (int i = 0; i < 3; i ++) {
            double velocity = intake.flywheelMotor.getVelocity();

            while (velocity > intake.flywheelVelocity + 50 || velocity < intake.flywheelVelocity - 20) {
                sleep(250);
                velocity =  intake.flywheelMotor.getVelocity();
            }
            intake.autoPush();
        }
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
    }
}
