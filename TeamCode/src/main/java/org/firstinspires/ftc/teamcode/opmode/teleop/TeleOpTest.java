package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

//import com.bylazar.telemetry.PanelsTelemetry;
//import com.bylazar.panels.telemetry.TelemetryManager;

//import com.bylazar.telemetry;
//import com.bylazar.telemetry.PanelsTelemetry;

import com.bylazar.telemetry.PanelsTelemetry;

@TeleOp(name = "TeleOpTest")
public class TeleOpTest extends OpMode {



    private Follower follower;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);

//        TelemetryManager panelsTelemetry =
//            PanelsTelemetry.INSTANCE.getTelemetry();
    }

    @Override
    public void loop() {
        follower.manual(
            -gamepad1.left_stick_y,
            -gamepad1.left_stick_x,
            -gamepad1.right_stick_x
        );

        follower.update();

        // Send real-time data back to Panels dashboard
        telemetry.addData("Path Busy", follower.isBusy());
        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.update();
    }
}
