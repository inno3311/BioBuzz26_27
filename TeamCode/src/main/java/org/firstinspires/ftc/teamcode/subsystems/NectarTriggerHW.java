package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class NectarTriggerHW
{

   // Name used for the hardware map and general ID.
   private String m_name;

   // Save a copy of the OpMode to gain access to telemetry, hardwareMap and controllers
   private OpMode m_OpMode = null;

   // The servo that drives the Pollen gate.
   private Servo m_servo;

   private double m_curPos;
   /**
    * Constructor
    * @param opMode Robot OpMode object
    * @param name Name of the Mapped hardware device
    */
   public NectarTriggerHW(OpMode opMode, String name)
   {
      //super(opMode);
      this.m_name = name;
      this.m_OpMode = opMode;
      init();
   }

   /**
    * Init the hardware device
    */
   public void init()
   {
      m_OpMode.telemetry.log().add("Adding: ", m_name);
      m_servo = m_OpMode.hardwareMap.get(Servo.class, m_name);
      m_servo.setPosition(0.5);
      m_curPos = m_servo.getPosition();

      m_servo.setDirection(Servo.Direction.REVERSE);
      //m_servo.scaleRange(0.5, 1.0); // only go from midpoint to far right point
   }

   /////////////////////////////////
   ///  Interface Controls
   /////////////////////////////////



   public void open()
   {
      m_servo.setPosition(0.2);
   }
   public void close()
   {
      m_servo.setPosition(0.5);
   }

   public void setPosition(float value) {
      m_servo.setPosition(value);
   }

   public void increase() {
      m_curPos = m_curPos + 0.1;
      m_servo.setPosition(m_curPos);
   }

   public void decrease() {
      m_curPos = m_curPos - 0.1;
      m_servo.setPosition(m_curPos);
   }

   public double getCurrentPos() {
      return m_servo.getPosition();
   }


   /////////////////////////////////
   ///  Commands Functions
   /////////////////////////////////


}
