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

        double rightStick = gamepad1.right_stick_y;

        double leftStick = gamepad1.left_stick_y;

        m_tankDrive.tankDrive(leftStick, rightStick);

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
