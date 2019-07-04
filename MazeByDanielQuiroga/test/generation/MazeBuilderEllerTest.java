package generation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import generation.StubOrder;
import generation.Order.Builder;
import generation.MazeBuilderEller;

public class MazeBuilderEllerTest {

		protected MazeFactory fac;
		protected StubOrder stub;
		protected MazeConfiguration mConfig;
		MazeBuilderEller Eller;
		Cells cells;
		Distance dist;
		
		
		@Before
		public void setUp() throws Exception{
			fac = new MazeFactory();
			stub = new StubOrder(1, Builder.Eller, false);
			Eller = new MazeBuilderEller();
			
			fac.order(stub);
			fac.waitTillDelivered();
			mConfig = stub.getMazeConfig();
		}
		
		@Test
		public void testEllerBuild() {
			assertNotNull(stub);
			assertNotNull(Eller);
		}
		
		
		@Test
		public void testNoCellSeparated() {
			Eller.buildOrder(stub);
			Eller.run();
			dist = mConfig.getMazedists();
			int counter = 0;
			cells = ((MazeContainer)mConfig).getMazecells();
			for (int col = 0; col < mConfig.getWidth(); col++) {
				for (int row = 0; row < mConfig.getHeight(); row++) {
					if (dist.getDistanceValue(col, row) == 0) {
						counter++;
					}
				}
			}
			assertEquals(0, counter);
		}
		
}