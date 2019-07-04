package generation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import generation.Order.Builder;
import generation.StubOrder;

public class MazeFactoryTest {
	
	protected MazeConfiguration configuration;
	protected StubOrder stubBasic;
	protected MazeFactory facBasic;
	Cells cells;
	Distance distance;

	@Before
	public void setUp() throws Exception {
		boolean deterministic = false;
		stubBasic = new StubOrder(0, Builder.DFS, deterministic);
		facBasic = new MazeFactory(deterministic);
		facBasic.order(stubBasic);
		facBasic.waitTillDelivered();
		configuration = stubBasic.getMazeConfig();
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testsetUpWorksFine_And_MazeFactory() {
		assertNotNull(stubBasic);
		assertNotNull(configuration);
		assertNotNull(facBasic);

		MazeFactory fac = new MazeFactory();
		assertNotNull(fac);
	}
	
	@Test
	public void testhasOnlyOneExit() {
		int count = 0;
		cells = ((MazeContainer)configuration).getMazecells();
		for (int col = 0; col < configuration.getWidth(); col++) {
			for (int row = 0; row < configuration.getHeight(); row++) {
				if (cells.isExitPosition(col, row) == true) {
					count++;
				}
			}
		}
		assertEquals(1, count);
	}

}
