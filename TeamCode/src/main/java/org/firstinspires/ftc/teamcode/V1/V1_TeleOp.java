package org.firstinspires.ftc.teamcode.V1;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.billbot.DriveBaseHW;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.FlywheelHW;
import org.firstinspires.ftc.teamcode.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.subsystems.IntakeHW;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

@TeleOp(name = "V1_TeleOp")
public class V1_TeleOp extends OpMode {



    private DriveBaseHW m_driveBase;

    private Follower m_follower;

    //FlywheelHW m_flywheel;
    Flywheel m_flywheel;

    IntakeHW m_intake;


    TelemetryManager panels;



    @Override
    public void init() {

        m_follower = Constants.create(hardwareMap);

        m_flywheel = new Flywheel(this.hardwareMap, "flywheel");
        m_intake = new IntakeHW(this, "intake");

        panels = PanelsTelemetry.INSTANCE.getTelemetry();
    }

    @Override
    public void init_loop() {


        telemetry.update();
    }

    @Override
    public void loop()
    {


        // Send real-time data back to Panels dashboard
//        telemetry.addData("Path Busy", follower.isBusy());
//        telemetry.addData("X", follower.pose().x());
//        telemetry.addData("Y", follower.pose().y());
//        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.update();

//        if (gamepad2.rightBumperWasPressed())
//        {
//            m_flywheel.increase_power();
//        }
//        else if (gamepad2.leftBumperWasPressed())
//        {
//            m_flywheel.decrease_power();
//        }

//        telemetry.addData("flywheelPower", m_flywheel.getPower());

        if (gamepad1.right_trigger > 0.1)
        {
            m_intake.intake();
        }
        else if (gamepad1.right_trigger <= 0.1)
        {
            m_intake.stop();
        }


        if (gamepad1.left_trigger > 0.1)
        {
            m_intake.outtake();
        }
        else if (gamepad1.left_trigger <= 0.1)
        {
            m_intake.stop();
        }

        telemetry.addData("intakePower", m_intake.getPower());


        m_follower.manual(
            -gamepad1.left_stick_y,
            -gamepad1.left_stick_x,
            -gamepad1.right_stick_x
        );

        m_follower.update();

        // Send real-time data back to Panels dashboard
        telemetry.addData("Path Busy", m_follower.isBusy());
        telemetry.addData("X", m_follower.pose().x());
        telemetry.addData("Y", m_follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(m_follower.pose().heading()));
        telemetry.update();


        if (gamepad1.a)
        {
            m_flywheel.applyPIDF();
            m_flywheel.setVelocity(Flywheel.FlyWheelConfigurables.setVel);
            //flywheel.setRPM(4500);
        }
        else if (gamepad1.b)
        {
            m_flywheel.stop();
        }

        telemetry.addData(
            "Target RPM", m_flywheel.getTargetRPM()
        );
        telemetry.addData(
            "Actual RPM", m_flywheel.getRPM()
        );
        telemetry.addData(
            "Velocity Error", m_flywheel.getError()
        );

        telemetry.addData(
            "Target Velocity", m_flywheel.getTargetVelocity()
        );
        //telemetry.update();


        panels.addData("curr vel", m_flywheel.getCurrentVelocity());
        panels.addData("set vel", m_flywheel.getTargetVelocity());
        panels.addData("0", 0);


        panels.update(telemetry);
    }
}
