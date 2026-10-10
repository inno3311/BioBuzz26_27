package org.firstinspires.ftc.teamcode.opmode.auto;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;



@Autonomous(name = "SmartPark", group = "Autonomous")
public class SmartPark extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 9, 90);
    private final Pose inlinetopark = poseFactory.of(13.7581, 73.0397, 90);
    private final Pose inlinetoparkControl1 = poseFactory.of(14.3654, 2.0706, 0);
    private final Pose parkonright = poseFactory.of(8.8438, 122.603, -179.0427);
    private final Pose parkonrightControl1 = poseFactory.of(52.7294, 91.5199, 0);
    private final Pose parkonrightControl2 = poseFactory.of(38.0868, 123.4147, 0);
    private final Pose parkonleft = poseFactory.of(13.7581, 85.0397, 90);


    Command shootLaser = conditional(
        () -> armIsRaised,
        lowerArmCommand,
        raiseArmCommand
    );

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
            follow(follower, inlinetopark()),
            follow(follower, parkonright())
        );
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();





        waitForStart();
        schedule(autoRoutine());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
        }
    }

    public Path inlinetopark() {
        return Paths.curve(start, inlinetoparkControl1, inlinetopark).constant(inlinetopark);
    }

    public Path parkonright() {
        return Paths.curve(inlinetopark, parkonrightControl1, parkonrightControl2, parkonright).tangent();
    }
    public Path parkonleft() {
        return Paths.curve(inlinetopark, parkonleft).tangent();
    }

}
