/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: First Linear OpMode", group="Linear Opmode")
//@Disabled
public class FirstOpMode_Linear extends LinearOpMode {

    MSJHardware robot   = new MSJHardware();

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.dropperServo.setPosition(.1);

            // Setup a variable for each drive wheel to save power level for telemetry
            double shootPower;
            double frontRightPower;
            double frontLeftPower;
            double backRightPower;
            double backLeftPower;
            double liftPower;
            double armPower;


            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double shoot = gamepad2.left_stick_y;
            double y = -gamepad1.left_stick_y;
            double x  =  gamepad1.left_stick_x*1.5;
            double rx = gamepad1.right_stick_x;
            double lift = gamepad2.right_stick_y;
            double armup = gamepad2.left_trigger;
            double armdown = gamepad2.right_trigger;


            if(gamepad1.left_bumper){
                y*=0.25;
                x*=0.25;
                rx*=0.25;
            }

            if(gamepad2.dpad_up){
                robot.clawServo.setPosition(0);
            }

            if(gamepad2.dpad_down){
                robot.clawServo.setPosition(0.25);
            }

            //apparently you cannot set servos to a negative position. Only 0 through 1


            shootPower    = Range.clip(shoot, -1.0, 1.0) ;
            frontRightPower   = Range.clip(y - x-rx, -.8, .8);
            frontLeftPower    = Range.clip(y+x+rx, -.8, .8);
            backRightPower    = Range.clip(y+x-rx,-.8,.8);
            backLeftPower     = Range.clip(y-x+rx,-.8,.8);
            liftPower    = Range.clip(lift,-1.0,1.0);
            armPower = Range.clip(armup-armdown,-.5,.5);



            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;
      /*      if(gamepad2.dpad_left) {
                robot.liftMotor.setDirection(DcMotor.Direction.FORWARD);
                robot.liftMotor.setTargetPosition(-3908);
                robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.liftMotor.setPower(0.5);
                while(opModeIsActive() && robot.liftMotor.getCurrentPosition() < robot.liftMotor.getTargetPosition())
                {
                    telemetry.addData("encoder-liftmotor", robot.liftMotor.getCurrentPosition());
                    telemetry.update();
                    idle();
                }
                robot.liftMotor.setPower(0.0);
                robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
          if(gamepad2.dpad_up) {
             robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
              robot. liftMotor.setTargetPosition(-5650);
              robot.liftMotor.setPower(0.5);
              while(opModeIsActive() && robot.liftMotor.getCurrentPosition() < robot.liftMotor.getTargetPosition())
              {
                  telemetry.addData("encoder-liftmotor", robot.liftMotor.getCurrentPosition());
                  telemetry.update();
                  idle();
             }
              robot.liftMotor.setPower(0.0);
              robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
              robot.liftMotor.setDirection(DcMotor.Direction.REVERSE);
          }

            if(gamepad2.dpad_down) {
                robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot. liftMotor.setTargetPosition(6750);
                robot.liftMotor.setPower(0.5);
                while(opModeIsActive() && robot.liftMotor.getCurrentPosition() < robot.liftMotor.getTargetPosition())
                {
                    telemetry.addData("encoder-liftmotor", robot.liftMotor.getCurrentPosition());
                    telemetry.update();
                    idle();
                }
                robot.liftMotor.setPower(0.0);
                robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.liftMotor.setDirection(DcMotor.Direction.REVERSE);
            }

            */


         if(gamepad2.right_bumper){
              robot.intakeMotor.setPower(0.5);
          }

         if(gamepad2.left_bumper){
              robot.intakeMotor.setPower(0.0);
          }


         if(gamepad2.y){
              robot.loaderServo.setPower(-0.5);
          }

          if(gamepad2.x){
              robot.loaderServo.setPower(0.0);
          }

          if(gamepad2.a){
              robot.loaderServo.setPower(0.5);
          }



            // Send calculated power to wheels
            robot.shooterMotor.setPower(shootPower);

            robot.frontRightMotor.setPower(frontRightPower);
            robot.frontLeftMotor.setPower(frontLeftPower);
            robot.backRightMotor.setPower(backRightPower);
            robot.backLeftMotor.setPower(backLeftPower);

            robot.liftMotor.setPower(liftPower);

            robot.armMotor.setPower(armPower);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Shooter Motor", "shoot (%.2f)", shootPower);
            telemetry.addData("Lift Motor", "lift (%.2f)", liftPower);
            telemetry.addData("encoder-liftmotor",robot.liftMotor.getCurrentPosition());
            telemetry.addData("Front Right Motor", "frontRightMotor", y);
            telemetry.addData("Front Left Motor", "frontLeftMotor", x);
            telemetry.addData("Back Right Motor", "frontLeftMotor", rx);
            telemetry.addData("Front Left Motor", "backLeftMotor", backLeftPower);
            telemetry.addData("Claw:",robot.clawServo.getPosition());
            telemetry.update();
        }
    }
}
