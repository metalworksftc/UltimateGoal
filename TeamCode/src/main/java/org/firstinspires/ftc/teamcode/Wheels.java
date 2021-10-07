package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Wheels {
    DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    Telemetry telemetry;
    protected Orientation angles;
    protected BNO055IMU imu;

    public Wheels(HardwareMap hardwareMap, Telemetry telemetry) {init(hardwareMap,telemetry);
    }

    private void init(HardwareMap hardwareMap, Telemetry telemetry) {
        rightFrontMotor = hardwareMap.dcMotor.get("rfm");
        leftFrontMotor = hardwareMap.dcMotor.get("lfm");
        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor = hardwareMap.dcMotor.get("lbm");
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackMotor = hardwareMap.dcMotor.get("rbm");
        this.telemetry = telemetry;
        BNO055IMU.Parameters parameters2 = new BNO055IMU.Parameters();
        parameters2.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters2.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters2.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters2.loggingEnabled      = true;
        parameters2.loggingTag          = "IMU";
        parameters2.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters2);
    }

    private void normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++) {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude) {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0) {
            for (int i = 0; i < wheelSpeeds.length; i++) {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }

    public void driveCartesian(double x, double y, double rotation) {
        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0] = -x - y + rotation;
        wheelSpeeds[1] = x + y + rotation;
        wheelSpeeds[2] = -x + y + rotation;
        wheelSpeeds[3] = -x - y + rotation;

        normalize(wheelSpeeds);

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftBackMotor.setPower(wheelSpeeds[2]);
        rightBackMotor.setPower(wheelSpeeds[3]);

    }   //mecanumDrive_Cartesian

    protected static final double DRIVE_CALIBRATION = 49.5;
    protected static final double CALIBRATION_COUNTS = 2000;
    double COUNTS_PER_INCH = CALIBRATION_COUNTS / DRIVE_CALIBRATION;

    public void forward(double distance, double power) {

        forwardCounts(distance*COUNTS_PER_INCH, power);
    }

    public void forwardCounts (double distance, double power) {

        int target = leftBackMotor.getCurrentPosition() - (int)  distance;
        driveCartesian(0, power, 0);

        while (leftBackMotor.getCurrentPosition() > target) {
            telemetry.addLine(String.valueOf(System.currentTimeMillis()));
            telemetry.addLine("Driving: " + leftBackMotor.getCurrentPosition() + " of " + target);
            telemetry.update();
        }
        driveCartesian(0, 0, 0);
    }

    public void backwards(double distance, double power) {
        backwardsCount(distance*COUNTS_PER_INCH, power);

    }

    public void backwardsCount(double distance, double power) {

        int target = leftBackMotor.getCurrentPosition() + (int) (distance);
        driveCartesian(0, -power, 0);

        telemetry.addLine("Driving: " + leftBackMotor.getCurrentPosition() + " of " + target);
        telemetry.update();

        while (leftBackMotor.getCurrentPosition() < target) {
            telemetry.addLine("Driving: " + leftBackMotor.getCurrentPosition() + " of " + target);
            telemetry.update();
        }
        driveCartesian(0, 0, 0);
    }

    public void right(double distance, double power) {

        float heading = getHeading();
//        sleep(2000);

        int target = leftBackMotor.getCurrentPosition() - (int) (COUNTS_PER_INCH * distance);
        driveCartesian(-power, 0, 0);

        telemetry.addLine("Driving: " + leftBackMotor.getCurrentPosition() + " of " + target);
        telemetry.addLine("Position " + getHeading());
        telemetry.update();

        while (leftBackMotor.getCurrentPosition() > target) {
//            if (getHeading() < heading){
//                //tweak right
//                driveCartesian(-power,0 ,0.1);
//            }
//            else if (getHeading() > heading) {
//                //tweak left
//                driveCartesian(-power,0,-0.1);
//            }
//            else {
//                //continue
//                driveCartesian(-power,0,0);
//            }

            telemetry.addLine("Driving: " + leftBackMotor.getCurrentPosition() + " of " + target);
            telemetry.update();
        }

        driveCartesian(0, 0, 0);
    }

    public void left(double distance, double power) {

        float heading = getHeading();
//        sleep(2000);

        int target = leftBackMotor.getCurrentPosition() + (int) (COUNTS_PER_INCH * distance);
        driveCartesian(power, 0, 0);

        telemetry.addLine("Driving: " + leftBackMotor.getCurrentPosition() + " of " + target);
        telemetry.addLine("Position " + getHeading());
        telemetry.update();

        while (leftBackMotor.getCurrentPosition() < target) {
//            if (getHeading() > heading){
//                //tweak left
//                driveCartesian(power,0 ,-0.1);
//            }
//            else if (getHeading() < heading) {
//                //tweak right
//                driveCartesian(power,0,0.1);
//            }
//            else {
//                //continue
//                driveCartesian(power,0,0);
//            }
//
//            telemetry.addLine("Driving: " + leftRearMotor.getCurrentPosition() + " of " + target);
//            telemetry.addLine("Position" + getHeading());
//            telemetry.update();
        }

        driveCartesian(0, 0, 0);
    }

    public void diagonal (double x, double y, double power){
        int targetX= rightFrontMotor.getCurrentPosition() - (int)  (COUNTS_PER_INCH * x);
        int targetY = rightFrontMotor.getCurrentPosition() - (int)  y;
        driveCartesian(power, power,0);
        while (rightFrontMotor.getCurrentPosition() > targetY) {
            telemetry.addLine("Driving: " + rightFrontMotor.getCurrentPosition() + " of " + targetX);
            telemetry.update();
        }
        while (rightFrontMotor.getCurrentPosition() > targetY) {
            telemetry.addLine("Driving: " + rightFrontMotor.getCurrentPosition() + " of " + targetX);
            telemetry.update();
        }
        driveCartesian(0,0,0);

    }

    protected void absoluteTurnPower(float target, double power) {
        //turn left
        float distLeft = target - getHeading();
        if (distLeft < 0) {
            distLeft += 360;
        }
        float distRight = 360 - distLeft;

        telemetry.addLine(String.valueOf("distLeft " + distLeft + "distRight " + distRight));
        telemetry.update();
        if (distLeft < distRight) {
            driveCartesian(0,0,-power);
            //turn left
            while (distLeft > 8) {
                telemetry.addLine("Turning Left: " + getHeading() + " of " + target);
                telemetry.update();
                distLeft = target - getHeading();
                if (distLeft < 0) {
                    distLeft += 360;
                }
            }
        } else {
            driveCartesian(0,0,power);
            //turn right
            while (distRight > 2) {
                telemetry.addLine("Turning Right: " + getHeading() + " of " + target);
                telemetry.update();
                distLeft = target - getHeading();
                if (distLeft < 0) {
                    distLeft += 360;
                }
                distRight = 360 - distLeft;
            }
        }
       driveCartesian(0,0,0);
    }

    protected float getHeading() {
        angles = imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX);
        return AngleUnit.DEGREES.normalize(angles.firstAngle);
    }

    public void sleep(double milliseconds) {
        try {
            Thread.sleep((long) milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public double driveSpeed = 0.5;



    public void reversePower( float xPower, float yPower, float rotation){
        driveCartesian(-xPower*0.5,-yPower*0.5,rotation*0.5);

    }

}