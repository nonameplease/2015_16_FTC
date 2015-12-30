package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by owner on 11/11/2015.
 */
public class ScottAuto2  extends OpMode {


    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;
    DcMotor leftMotorRotate;
    DcMotor rightMotorRotate;

     DeviceInterfaceModule dim;
     OpticalDistanceSensor leftDistance;
     OpticalDistanceSensor rightDistance;
    double reflectanceLeft = leftDistance.getLightDetected();
    double reflectanceRight = rightDistance.getLightDetected();
     ColorSensor colorRGB;


    final double UP_POSITION = 0.0;
    final double DOWN_POSITION = 0.3;

    Servo climber;

    public void LeftPower(double power)
    {
        leftMotor.setPower(power);
        leftMotorRear.setPower(power);
    }

    public void RightPower(double power)
    {
        rightMotorRear.setPower(power);
        rightMotor.setPower(power);
    }


    @Override
    public void init() {
            /* get reference to the motors from hardware map */
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotorRear = hardwareMap.dcMotor.get("left_drive_rear");
        rightMotorRear = hardwareMap.dcMotor.get("right_drive_rear");
        leftMotorRotate = hardwareMap.dcMotor.get("left_drive_rotate");
        rightMotorRotate = hardwareMap.dcMotor.get("right_drive_rotate");

        dim = hardwareMap.deviceInterfaceModule.get("device");
               leftDistance = hardwareMap.opticalDistanceSensor.get("odslf");
               rightDistance = hardwareMap.opticalDistanceSensor.get("odsrf");
               colorRGB = hardwareMap.colorSensor.get("color");

        /* reverse the right motor */
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRear.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRotate.setDirection(DcMotor.Direction.REVERSE);



        climber = hardwareMap.servo.get("climber");


        climber.setPosition(UP_POSITION);

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotorRear.setPower(0);
        rightMotorRear.setPower(0);


    }

    @Override
    public void loop() {

        int Red = colorRGB.red();
        int Blue = colorRGB.blue();
        int Green = colorRGB.green();

        double Power1 = 0.2;
        double Power2 = 0.5;


        while(Red <= 200 && Blue <= 200 && Green <= 200)
        {

            LeftPower(Power1);
            RightPower(Power1);
            //resetStartTime();
            //if(time > 3)
                //break;
        }

        LeftPower(0);
        RightPower(0);


        if(reflectanceLeft > 10 && reflectanceRight > 10)
        {
            if(reflectanceLeft > reflectanceRight)
            {
                LeftPower(Power1);
                RightPower(Power2);
            }
            else if(reflectanceLeft < reflectanceRight)
            {
                LeftPower(Power2);
                RightPower(Power1);
            }
            else if(reflectanceLeft == reflectanceRight)
            {
                LeftPower(Power1);
                RightPower(Power1);
            }
        }
        else if(reflectanceLeft < 10 && reflectanceRight < 10)
        {
            LeftPower(0);
            RightPower(0);

            climber.setPosition(DOWN_POSITION);
        }
        else
        {
            leftMotor.setPowerFloat();
            rightMotor.setPowerFloat();
            leftMotorRear.setPowerFloat();
            rightMotorRear.setPowerFloat();
        }

        for (int i = 0; i < 2; i++)
        {
            resetStartTime();
            if (time < 500)
            {
                leftMotor.setPower(-0.2);
                rightMotor.setPower(-0.2);
            }
            else if(time > 500 && time < 1000)
            {
                leftMotor.setPower(-0.3);
                rightMotor.setPower(0.1);
            }
        }

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB Sensor.

        final View relativeLayout = ((Activity)
                hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        telemetry.addData("ODS Left", reflectanceLeft);
        telemetry.addData("ODS Right", reflectanceRight);
    }
}
