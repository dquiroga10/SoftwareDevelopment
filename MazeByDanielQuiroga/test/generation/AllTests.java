package generation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllCellsTests.class, BasicRobotTest.class, CellsTest.class, CellsTestIterator.class, ExplorerTest.class,
		MazeBuilderEllerTest.class, MazeFactoryTest.class, PledgeTest.class, WallFollowerTest.class, WizardTest.class })
public class AllTests {

}
