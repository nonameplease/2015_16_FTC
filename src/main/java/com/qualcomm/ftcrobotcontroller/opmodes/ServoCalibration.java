package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by owner on 12/4/2015.
 */
public class ServoCalibration extends OpMode {

    Servo climber;
    //Servo resQ;
    //Servo zipLine;

    @Override
    public void init() {

        climber = hardwareMap.servo.get("climber");
        //resQ = hardwareMap.servo.get("resQ");
        //zipLine = hardwareMap.servo.get("zipLine");

    }

    @Override
    public void loop() {
        if(gamepad1.a)
        {
            climber.setPosition(0);
            //resQ.setPosition(0);
            //zipLine.setPosition(0);
        }
        else if(gamepad1.b)
        {
            climber.setPosition(0.1);
           // resQ.setPosition(0.1);
            //zipLine.setPosition(0.1);
        }
        else if(gamepad1.x)
        {
            climber.setPosition(0.2);
            //resQ.setPosition(0.2);
            //zipLine.setPosition(0.2);
        }
        else if(gamepad1.y)
        {
            climber.setPosition(0.3);
            //resQ.setPosition(0.3);
            //zipLine.setPosition(0.3);
        }
        else if(gamepad1.dpad_up)
        {
            climber.setPosition(0.4);
            //resQ.setPosition(0.4);
           // zipLine.setPosition(0.4);
        }
        else if(gamepad1.dpad_right)
        {
            climber.setPosition(0.5);
           //resQ.setPosition(0.5);
            //zipLine.setPosition(0.5);
        }
        else if(gamepad1.dpad_down)
        {
            climber.setPosition(0.6);
            //resQ.setPosition(0.6);
            //zipLine.setPosition(0.6);
        }
        else if(gamepad1.dpad_left)
        {
            climber.setPosition(0.7);
            //resQ.setPosition(0.7);
            //zipLine.setPosition(0.7);
        }
        else if(gamepad1.left_bumper)
        {
            climber.setPosition(0.8);
            //resQ.setPosition(0.8);
            //zipLine.setPosition(0.8);
        }
        else if(gamepad1.right_bumper)
        {
            climber.setPosition(0.9);
            //resQ.setPosition(0.9);
            //zipLine.setPosition(0.9);
        }
        else if(gamepad1.left_stick_button)
        {
            climber.setPosition(1.0);
            //resQ.setPosition(1.0);
            //zipLine.setPosition(1.0);
        }

        telemetry.addData("climber", climber.getPosition());
       // telemetry.addData("resQ", resQ.getPosition());
       // telemetry.addData("zipLine", zipLine.getPosition());

    }
}
