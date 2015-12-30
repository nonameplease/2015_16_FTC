package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by owner on 12/28/2015.
 */

//just a test to see if push function on github works

public class sensorauto_v1 extends LinearOpMode {

    DeviceInterfaceModule dim;
    OpticalDistanceSensor ods_l;
    OpticalDistanceSensor ods_r;
    ColorSensor color;

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;

    @Override
    public void runOpMode() throws InterruptedException {

        dim = hardwareMap.deviceInterfaceModule.get("device");
        ods_l = hardwareMap.opticalDistanceSensor.get("odsl");
        ods_r = hardwareMap.opticalDistanceSensor.get("odsr");
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

        double distance_l = ods_l.getLightDetected();
        double distance_r = ods_r.getLightDetected();

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

            if (distance_l < 0.01)
            {
                leftMotor.setPower(0.5);
                //rightMotor.setPower(0.5);
                leftMotorRear.setPower(0.5);
                // rightMotorRear.setPower(0.5);
            }
            else
            {
                leftMotor.setPowerFloat();
                // rightMotor.setPowerFloat();
                leftMotorRear.setPowerFloat();
                // rightMotorRear.setPowerFloat();
            }

            if (distance_r < 0.01)
            {
                rightMotor.setPower(0.5);
                rightMotorRear.setPower(0.5);
            }
            else
            {
                rightMotorRear.setPowerFloat();
                rightMotor.setPowerFloat();
            }
        }

        telemetry.addData("Distance Detected Left", distance_l);
        telemetry.addData("Distance Detected Right", distance_r);
        telemetry.addData("Red", Red);
        telemetry.addData("Blue", Blue);
        telemetry.addData("Green", Green);

    }
}
