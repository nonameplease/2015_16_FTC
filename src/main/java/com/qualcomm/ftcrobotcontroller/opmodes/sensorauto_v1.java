package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by owner on 12/28/2015.
 */
public class sensorauto_v1 extends LinearOpMode {

    DeviceInterfaceModule dim;
    OpticalDistanceSensor ods;
    ColorSensor color;

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;

    @Override
    public void runOpMode() throws InterruptedException {

        dim = hardwareMap.deviceInterfaceModule.get("device");
        ods = hardwareMap.opticalDistanceSensor.get("ods");
        color = hardwareMap.colorSensor.get("color");

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotorRear = hardwareMap.dcMotor.get("left_drive_rear");
        rightMotorRear = hardwareMap.dcMotor.get("right_drive_rear");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRear.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        sleep(2000);

        int Red = color.red();
        int Blue = color.blue();
        int Green = color.green();

        double distance = ods.getLightDetected();

        if(Red < 375 && Blue < 350 && Green < 350)
        {
            leftMotor.setPower(0.5);
            rightMotor.setPower(0.5);
            leftMotorRear.setPower(0.5);
            rightMotorRear.setPower(0.5);
        }
        else
        {
            leftMotor.setPowerFloat();
            rightMotor.setPowerFloat();
            leftMotorRear.setPowerFloat();
            rightMotorRear.setPowerFloat();
            sleep(500);

            leftMotor.setPower(0);
            leftMotorRear.setPower(0);
            rightMotor.setPower(1);
            rightMotorRear.setPower(1);
            sleep(500);
            leftMotorRear.setPowerFloat();
            leftMotor.setPowerFloat();
            rightMotorRear.setPowerFloat();
            rightMotor.setPowerFloat();

            if (distance < 0.01)
            {
                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);
                leftMotorRear.setPower(0.5);
                rightMotorRear.setPower(0.5);
            }
            else
            {
                leftMotor.setPowerFloat();
                rightMotor.setPowerFloat();
                leftMotorRear.setPowerFloat();
                rightMotorRear.setPowerFloat();
            }
        }

        telemetry.addData("Distance Detected", distance);
        telemetry.addData("Red", Red);
        telemetry.addData("Blue", Blue);
        telemetry.addData("Green", Green);

    }
}
