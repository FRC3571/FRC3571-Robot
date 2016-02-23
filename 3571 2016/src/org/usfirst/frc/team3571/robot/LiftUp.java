package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;

public class LiftUp extends Command {
	Talon lift = new Talon(6);
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		

		
		lift.set(0.5);

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		
		lift.set(0);
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
