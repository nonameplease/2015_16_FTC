package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by owner on 10/4/2015.
 */
public class ExampleWithRobotDrive extends OpMode {

    ExampleRobotDrive myRobotDrive;

    @Override
    public void init() {
        myRobotDrive = new ExampleRobotDrive(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right)drive"));
    }

    @Override
    public void loop() {

        myRobotDrive.arcadeDrive(gamepad1);

    }
}
