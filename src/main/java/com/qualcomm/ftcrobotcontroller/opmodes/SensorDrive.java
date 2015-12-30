package com.qualcomm.ftcrobotcontroller.opmodes;

import android.os.CountDownTimer;

import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import java.util.Timer;

/**
 * Created by owner on 11/9/2015.
 */
public class SensorDrive {

    private DeviceInterfaceModule interfaceModule;
    private OpticalDistanceSensor leftDistance;
    private OpticalDistanceSensor rightDistance;
    double reflectanceLeft = leftDistance.getLightDetected();
    double reflectanceRight = rightDistance.getLightDetected();

public SensorDrive(DeviceInterfaceModule device, OpticalDistanceSensor leftd, OpticalDistanceSensor rightd)
{
    interfaceModule = device;
    leftDistance = leftd;
    rightDistance = rightd;


}

    public double DriveForwardLeftPower()
    {

        double powerLeft = 0;
        double powerRight = 0;
        while(reflectanceLeft > 10 && reflectanceRight > 10)
        {
            if(reflectanceLeft > reflectanceRight)
            {
                 powerLeft = 0.2;
                 powerRight = 0.5;
            }
            else if(reflectanceLeft < reflectanceRight)
            {
                 powerLeft = 0.5;
                 powerRight = 0.2;
            }
            else if(reflectanceLeft == reflectanceRight)
            {
                 powerLeft = 0.2;
                 powerRight = 0.2;
            }
        }
        return powerLeft;
    }

    public double DriveForwardRightPower()
    {
        double powerLeft = 0;
        double powerRight = 0;
        while(reflectanceLeft > 10 && reflectanceRight > 10)
        {
            if(reflectanceLeft > reflectanceRight)
            {
                powerLeft = 0.2;
                powerRight = 0.5;
            }
            else if(reflectanceLeft < reflectanceRight)
            {
                powerLeft = 0.5;
                powerRight = 0.2;
            }
            else if(reflectanceLeft == reflectanceRight)
            {
                powerLeft = 0.2;
                powerRight = 0.2;
            }
        }
        return powerRight;
    }
}
