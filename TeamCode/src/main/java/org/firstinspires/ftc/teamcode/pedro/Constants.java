package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        return new Follower(
            new PinpointLocalizer(h, localizerConfig),
            new Mecanum(h, drivetrainConfig),
            new Foresight(foresightConfig)
        );
    }

   public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
      c.frontLeftName.set("lf");
      c.frontRightName.set("rf");
      c.backLeftName.set("lb");
      c.backRightName.set("rb");
      c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
      c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
      c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
      c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
   });


//    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
//        c.name.set("pinpoint");
//        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
////        c.xPodOffset.set(5.16843600535956);    //strafe
////        c.yPodOffset.set(6.7910826675535185);  // straight
//        c.xPodOffset.set(5.4);    //straight
//        c.yPodOffset.set(-6.75);  // strafe
//        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
//        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
//        c.globalDistanceUnit.set(DistanceUnit.INCH);
//        c.offsetUnits.set(DistanceUnit.INCH);
//    });

//public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
//    c.name.set("pinpoint");
//    c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
////    c.xPodOffset.set(-0.953099558672567);
////    c.yPodOffset.set(7.2361647237942925);
//    c.xPodOffset.set(-7.2361647237942925);
//    c.yPodOffset.set(0.953099558672567);
//    c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
//    c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
//    c.globalDistanceUnit.set(DistanceUnit.INCH);
//    c.offsetUnits.set(DistanceUnit.INCH);
//});

public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
    c.name.set("pinpoint");
    c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
    c.xPodOffset.set(-7.573604073111467);
    //c.xPodOffset.set(11.573604073111467);
    c.yPodOffset.set(-0.8246276134581078);
    c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
    c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
    c.globalDistanceUnit.set(DistanceUnit.INCH);
    c.offsetUnits.set(DistanceUnit.INCH);
});


    public static ForesightConfig foresightConfig = new ForesightConfig(
        c -> {
            Controller primaryTranslationalForward = Controller.proportional(0.3481945480816956);
            Controller secondaryTranslationalForward = Controller.proportional(0.12864858158650883);
            Controller primaryTranslationalLateral = Controller.proportional(0.5294116890774574);
            Controller secondaryTranslationalLateral = Controller.proportional(0.19560347297325514);

            c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
            c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

            c.coast.set(Controller.proportionalFeedforward(0.01485063957272908));
            c.brake.set(Controller.proportionalFeedforward(0.012623043636819717));

            c.headingFeedback.set(Controller.proportional(5.565059181126977));
            c.headingBrakeCoefficients.set(Vector2D.cartesian(0.06190734695985878, 0.006174960035164882));

            c.linearBrakeCoefficients.set(Matrix.diag(0.14828746533304807, 0.08782029789707356));
            c.quadraticBrakeCoefficients.set(Matrix.diag(9.622222936180159E-4, 0.0016830647625253378));

            c.maxAchievableForwardVelocity.set(69.96094825585399);
            c.maxAchievableStrafeVelocity.set(54.94452891366568);
            c.naturalForwardDeceleration.set(38.132512193961645);
            c.naturalStrafeDeceleration.set(74.36694302959165);
        }
    );


//    public static ForesightConfig foresightConfig = new ForesightConfig(
//        c -> {
//            Controller primaryTranslationalForward = Controller.proportional(0.32953708264517884);
//            Controller secondaryTranslationalForward = Controller.proportional(0.12175514664437405);
//            Controller primaryTranslationalLateral = Controller.proportional(0.505712072080328);
//            Controller secondaryTranslationalLateral = Controller.proportional(0.186847097758245);
//
//            c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
//            c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));
//
//            c.coast.set(Controller.proportionalFeedforward(0.014264205098821413));
//            c.brake.set(Controller.proportionalFeedforward(0.012124574333998201));
//
//            c.headingFeedback.set(Controller.proportional(5.554844445752633));
//            c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04835352658207922, 0.009888220312453563));
//
//            c.linearBrakeCoefficients.set(Matrix.diag(0.11593150970706961, 0.022575440854596316));
//            c.quadraticBrakeCoefficients.set(Matrix.diag(8.126315431400021E-4, 0.0025152454845972275));
//
//            c.maxAchievableForwardVelocity.set(71.28356925434439);
//            c.maxAchievableStrafeVelocity.set(47.692312110808835);
//            c.naturalForwardDeceleration.set(35.04145856205512);
//            c.naturalStrafeDeceleration.set(83.64563548625772);
//        }
//    );
}