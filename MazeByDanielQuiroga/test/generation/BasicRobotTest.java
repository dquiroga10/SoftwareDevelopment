package generation;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import gui.BasicRobot;
import gui.Controller;
import gui.Robot;
import gui.Robot.Direction;
import gui.Robot.Turn;

public class BasicRobotTest {

	
	@BeforeEach
	public void setUp() throws Exception {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
	}

	@Test
	public void testRotate() {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		assertTrue(robot.getCurrentDirection() == CardinalDirection.East);
		robot.rotate(Turn.RIGHT);
		assertTrue(robot.getBatteryLevel() == 2997);
		assertTrue(robot.getCurrentDirection() == CardinalDirection.North);
		robot.rotate(Turn.AROUND);
		assertTrue(robot.getBatteryLevel() == 2991);
		assertTrue(robot.getCurrentDirection() == CardinalDirection.South);
		robot.rotate(Turn.LEFT);
		assertTrue(robot.getBatteryLevel() == 2988);
		assertTrue(robot.getCurrentDirection() == CardinalDirection.West);
	}

	@Test
	public void testMove() {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		int x = 5;
		try {
			x = robot.getCurrentPosition()[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int y = 5;
		try {
			y = robot.getCurrentPosition()[1];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(robot.distanceToObstacle(Direction.FORWARD) > 0) {
			robot.move(1, false);
		}
		else if(robot.distanceToObstacle(Direction.RIGHT) > 0) {
			robot.rotate(Turn.RIGHT);
			robot.move(1, false);
		}
		else if(robot.distanceToObstacle(Direction.LEFT) > 0) {
			robot.rotate(Turn.LEFT);
			robot.move(1, false);
		}
		else if(robot.distanceToObstacle(Direction.BACKWARD) > 0) {
			robot.rotate(Turn.AROUND);
			robot.move(1, false);
		}
		try {
			assertTrue(x != robot.getCurrentPosition()[0] || y != robot.getCurrentPosition()[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetCurrentPosition() {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		int x = 5;
		try {
			x = robot.getCurrentPosition()[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int y = 5;
		try {
			y = robot.getCurrentPosition()[1];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(x >= 0 && x <= 4);
		assertTrue(y >= 0 && y <= 4);
	}

	@Test
	public void testCanSeeExit() {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		assertFalse(robot.canSeeExit(Direction.FORWARD));
		assertFalse(robot.canSeeExit(Direction.BACKWARD));
		assertFalse(robot.canSeeExit(Direction.LEFT));
		assertFalse(robot.canSeeExit(Direction.RIGHT));
	}

	@Test
	public void testIsInsideRoom() {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		assertFalse(robot.isInsideRoom());
	}

	@Test
	public void testGetCurrentDirection() {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		assertTrue(robot.getCurrentDirection() == CardinalDirection.East);
		robot.rotate(Turn.RIGHT);
		assertTrue(robot.getCurrentDirection() == CardinalDirection.North);
		robot.rotate(Turn.AROUND);
		assertTrue(robot.getCurrentDirection() == CardinalDirection.South);
		robot.rotate(Turn.LEFT);
		assertTrue(robot.getCurrentDirection() == CardinalDirection.West);
	}

	@Test
	public void testGetBatteryLevelandsetBatteryLevel() {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		assertTrue(robot.getBatteryLevel() == 3000);
		robot.setBatteryLevel(1500);
		assertTrue(robot.getBatteryLevel() == 1500);
		
	}


	@Test
	public void testGetandResetOdometer() {
		Controller control = new Controller();
		Robot robot = new BasicRobot();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, null);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		//robot.resetOdometer();
		assertTrue(robot.getOdometerReading() == 0);
		if(robot.distanceToObstacle(Direction.FORWARD) > 0) {
			robot.move(1, false);
		}
		else if(robot.distanceToObstacle(Direction.RIGHT) > 0) {
			robot.rotate(Turn.RIGHT);
			robot.move(1, false);
		}
		else if(robot.distanceToObstacle(Direction.LEFT) > 0) {
			robot.rotate(Turn.LEFT);
			robot.move(1, false);
		}
		else if(robot.distanceToObstacle(Direction.BACKWARD) > 0) {
			robot.rotate(Turn.AROUND);
			robot.move(1, false);
		}
		assertTrue(robot.getOdometerReading() == 1);
		robot.resetOdometer();
		assertTrue(robot.getOdometerReading() == 0);
	}

	

}
