package fi.sardion.varasto.data;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import fi.sardion.varasto.data.VUUID.ObjectType;

/** <p>
 * TODO: Write a brief description of the type here.
 * </p>
 * <p>
 * <ul>
 * <li>Created: 30 Oct 2016 14.50.38</li>
 * <li>Project: Payload</li>
 * <li>File: fi.sardion.varasto.data.TestVUUID</li>
 * </ul>
 * </p>
 * @author Christopher Harper, account: chris
 * @version %version%
 * @since %since% */
public class TestVUUID {

	/** <p>
	 * <ul>
	 * <li>Created: 4 Nov 2016 14.11.57</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>RANDOM = new Random();</code></li>
	 * </ul>
	 * </p>
	 * @since %since% */
	private static final Random RANDOM = new Random();

	/** Test method for {@link fi.sardion.varasto.data.VUUID#equals(java.lang.Object)}. */
	@Test
	public void testEqualsObject() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#getRepository()}. */
	@Test
	public void testGetRepository() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#getRepository(fi.sardion.varasto.data.VUUID)}. */
	@Test
	public void testGetRepositoryVUUID() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#getType()}. */
	@Test
	public void testGetType() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#getType(fi.sardion.varasto.data.VUUID)}. */
	@Test
	public void testGetTypeVUUID() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#getUUID()}. */
	@Test
	public void testGetUUID() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#getVUUID(java.lang.String)}. */
	@Test
	public void testGetVUUID() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#hashCode()}. */
	@Test
	public void testHashCode() {
		VUUID.newVUIID("junit", ObjectType.values()[TestVUUID.RANDOM.nextInt(ObjectType.values().length)]);

	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#isNullId(fi.sardion.varasto.data.VUUID)}. */
	@Test
	public void testIsNullId() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#main(java.lang.String[])}. */
	@Test
	public void testMain() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#newVUIID(java.lang.String, fi.sardion.varasto.data.VUUID.ObjectType)}. */
	@Test
	public void testNewVUIID() {
		Assert.fail("Not yet implemented");
	}

	/** Test method for {@link fi.sardion.varasto.data.VUUID#toString()}. */
	@Test
	public void testToString() {
		Assert.fail("Not yet implemented");
	}

}
