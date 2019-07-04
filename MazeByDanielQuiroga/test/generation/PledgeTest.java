/**
 * 
 */
package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import gui.BasicRobot;
import gui.Controller;
import gui.Pledge;
import gui.Robot.Direction;
import gui.Robot.Turn;

/**
 * @author Daniel Quiroga
 *
 */
public class PledgeTest {

	/**
	 * The constructor of the Pledge class needs to not be the 
	 * standard once the driver has connected to the controller and the robot
	 * The correct behavior is that there should be values for 
	 * each of the variables and not the generic null or 0
	 */
	@Test
	public void testPledge() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		assertNotNull(pledge.getDist());
		assertTrue(pledge.getWidth() == 4);
		assertTrue(pledge.getHeight() == 4);
		assertTrue(pledge.getPathLength() == 0);
		assertTrue(pledge.getEnergyConsumption() == 0);
	}
	
	/**
	 * making sure the robot is not null
	 */
	@Test
	public void testSetRobot() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		assertNotNull(pledge.getRobot());
	}
	
	/**
	 * make sure that the setDimension in the driver is 
	 * working properly and indeed setting the dimension
	 */
	@Test
	public void testSetDimensions() {
		Pledge pledge = new Pledge();
		pledge.setDimensions(3, 3);
		assertTrue(pledge.getHeight() == 3);
		assertTrue(pledge.getWidth() == 3);
	}

	/**
	 * The Drive2Exit of the Pledge tests to make sure the method 
	 * returns true once it gets to the exit of the maze
	 * The correct behavior at skill 0 is that the robot should get to 
	 * the exit with no issues and without running out of power fairly quickly
	 * This behavior is correct because of the size of the maze and how the 
	 * driver cheats by getting the information for the exit from the controller
	 */
	@Test
	public void testDrive2ExitSkill0() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.setSkillLevel(0);
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		try {
			assertTrue(pledge.drive2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The Drive2Exit of the Pledge tests to make sure the method returns
	 *  true once it gets to the exit of the maze
	 * The correct behavior at skill 1 is that the robot should get to the 
	 * exit with no issues and without running out of power fairly quickly
	 * This behavior is correct because of the size of the maze and how the 
	 * driver cheats by getting the information for the exit from the controller
	 */
	@Test
	public void testDrive2ExitSkill1() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.setSkillLevel(1);
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		try {
			assertTrue(pledge.drive2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  The Drive2Exit of the Pledge tests to make sure the method returns 
	 *  true once it gets to the exit of the maze
	 * The correct behavior at skill 2 is that the robot should get to the 
	 * exit with no issues and without running out of power fairly quickly
	 * This behavior is correct because of the size of the maze and how the 
	 * driver cheats by getting the information for the exit from the controller
	 */
	@Test
	public void testDrive2ExitSkill2() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.setSkillLevel(2);
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		try {
			assertTrue(pledge.drive2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The Drive2Exit of the Pledge tests to make sure the method returns 
	 * true once it gets to the exit of the maze
	 * The correct behavior at skill 10 is that the robot should not get to 
	 * the exit and it runs out of power, taking AWHILE
	 * This behavior is correct because of the size of the maze and how the 
	 * driver just simply does not have enough power to make it to the end 
	 * 
	 */
	@Test
	public void testDrive2ExitSkill10() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.setSkillLevel(10);
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		try {
			assertFalse(pledge.drive2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The GetEnergyConsumption of the Pledge checks to see how much energy has 
	 * been consumed by the pledge after completing a series of actions
	 * The correct behavior after completing a specific task is 
	 */
	@Test
	public void testGetEnergyConsumption() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.setSkillLevel(0);
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		assertTrue(pledge.getEnergyConsumption() == 0);
		if(robot.distanceToObstacle(Direction.FORWARD) > 0) {
			robot.move(1, false);
			assertTrue(pledge.getEnergyConsumption() == 6);
		}
		else if(robot.distanceToObstacle(Direction.RIGHT) > 0) {
			robot.rotate(Turn.RIGHT);
			robot.move(1, false);
			assertTrue(pledge.getEnergyConsumption() == 10);
		}
		else if(robot.distanceToObstacle(Direction.LEFT) > 0) {
			robot.rotate(Turn.LEFT);
			robot.move(1, false);
			assertTrue(pledge.getEnergyConsumption() == 11);
		}
		else if(robot.distanceToObstacle(Direction.BACKWARD) > 0) {
			robot.rotate(Turn.AROUND);
			robot.move(1, false);
			assertTrue(pledge.getEnergyConsumption() == 15);
		}
		assertTrue(pledge.getEnergyConsumption() > 0);
	}

	/**
	 * The getPathLength of the driver is being tested here 
	 * What is expected is after every movement that the odomter only increases by one
	 * it is correct because there was only one movement occurring
	 * I tested this three times in this single test rather than having to write 
	 * the same test case three times
	 */
	@Test
	public void testGetPathLength() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.setSkillLevel(0);
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		assertTrue(pledge.getPathLength() == 0);
		// moving the robot one unit and checking the odometer
				if(robot.distanceToObstacle(Direction.FORWARD) > 0) {
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 1);
				}
				else if(robot.distanceToObstacle(Direction.RIGHT) > 0) {
					robot.rotate(Turn.RIGHT);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 1);
				}
				else if(robot.distanceToObstacle(Direction.LEFT) > 0) {
					robot.rotate(Turn.LEFT);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 1);
				}
				else if(robot.distanceToObstacle(Direction.BACKWARD) > 0) {
					robot.rotate(Turn.AROUND);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 1);
				}
				assertTrue(pledge.getPathLength() > 0);
				
				// moving the robot again to get a reading of 2
				if(robot.distanceToObstacle(Direction.FORWARD) > 0) {
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 2);
				}
				else if(robot.distanceToObstacle(Direction.RIGHT) > 0) {
					robot.rotate(Turn.RIGHT);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 2);
				}
				else if(robot.distanceToObstacle(Direction.LEFT) > 0) {
					robot.rotate(Turn.LEFT);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 2);
				}
				else if(robot.distanceToObstacle(Direction.BACKWARD) > 0) {
					robot.rotate(Turn.AROUND);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 2);
				}
				
				
				// moving the robot a third time to make sure that the odometer is updating correctly
				if(robot.distanceToObstacle(Direction.FORWARD) > 0) {
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 3);
				}
				else if(robot.distanceToObstacle(Direction.RIGHT) > 0) {
					robot.rotate(Turn.RIGHT);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 3);
				}
				else if(robot.distanceToObstacle(Direction.LEFT) > 0) {
					robot.rotate(Turn.LEFT);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 3);
				}
				else if(robot.distanceToObstacle(Direction.BACKWARD) > 0) {
					robot.rotate(Turn.AROUND);
					robot.move(1, false);
					assertTrue(pledge.getPathLength() == 3);
				}
	}
	
	/**
	 * testing to see that getHeight method passes through the 
	 * information of the maze if the heigh is 0
	 */
	@Test
	public void testgetHeight() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.setSkillLevel(0);
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		pledge.getHeight();
		assertTrue(pledge.getHeight() == 4);
	}
	/**
	 * setBattery to 0 to test that the robot gets to the error message
	 */
	@Test
	public void testsetBattery2zero() {
		Controller control = new Controller();
		BasicRobot robot = new BasicRobot();
		Pledge pledge = new Pledge();
		control.setBuilder(Order.Builder.DFS);
		control.setRobotAndDriver(robot, pledge);
		robot.setMaze(control);
		control.turnOffGraphics();
		control.setSkillLevel(0);
		control.switchFromTitleToGenerating(0);
		control.waitForPlayingState();
		pledge.setRobot(robot);
		robot.setBatteryLevel(0);
		try {
			assertFalse(pledge.drive2Exit());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
