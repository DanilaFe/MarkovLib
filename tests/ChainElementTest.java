import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.danilafe.markovlib.ChainElement;

/**
 * Standard JUnit tests to ensure chain elements are working as expected.
 * @author vanilla
 *
 */
public class ChainElementTest {

	@Test
	/**
	 * Tests whether chain elements are created correctly.
	 */
	public void testCreation() {
		String value = "__start__";
		ChainElement<String> chainElement = new ChainElement<String>(value);
		assertEquals(chainElement.itemCount, 0);
		assertEquals(chainElement.occuranceCount.size(), 0);
		assertEquals(chainElement.relativeFrequency.size(), 0);
		assertEquals(chainElement.value, value);
	}

	@Test
	/**
	 * Tests whether chain elements get values added to them correctly.
	 */
	public void testSingleAddition(){
		String value = "__start__";
		String adding = "__word__";
		ChainElement<String> chainElement = new ChainElement<String>(value);
		chainElement.add(adding);
		chainElement.update();

		assertEquals(chainElement.itemCount, 1);
		assertEquals(chainElement.occuranceCount.size(), 1);
		assertEquals(chainElement.relativeFrequency.size(), 1);
		assertEquals(chainElement.getRandom(), adding);
	}

}
