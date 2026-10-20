package org.firstinspires.ftc.teamcode.mousedroid;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



//import com.bylazar.telemetry.PanelsTelemetry;

@TeleOp(name = "MouseDroidTeleOp")
public class MouseDroidTeleOp extends OpMode {


TankDriveHW m_tankDrive;

    @Override
    public void init() {

        m_tankDrive = new TankDriveHW((this));
    }

    @Override
    public void loop() {

        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = gamepad1.right_stick_y; //negative value when stick is pressed forward

        double leftPower = 0;
        double rightPower = 0;

        double cutSpeed = rightStickX;

        leftPower = 1.4*(rightStickY - cutSpeed);
        rightPower = -1.4*(rightStickY + cutSpeed);



        m_tankDrive.tankDrive(leftPower, rightPower);



        telemetry.addData("rightStickX", gamepad1.right_stick_x);
        telemetry.addData("rightStickY", gamepad1.right_stick_y);
        telemetry.addData("leftPower", leftPower);
        telemetry.addData("rightPower", rightPower);



        // Send real-time data back to Panels dashboard
//        telemetry.addData("Path Busy", follower.isBusy());
//        telemetry.addData("X", follower.pose().x());
//        telemetry.addData("Y", follower.pose().y());
//        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
//        telemetry.update();

    }

    @Override
    public void start() {
    }

}
