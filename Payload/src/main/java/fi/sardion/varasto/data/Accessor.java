package fi.sardion.varasto.data;

/** <p>
 * Class representing an accessor (group, role or user) in the repository.
 * </p>
 * <p>
 * <ul>
 * <li>Created: Jul 3, 2012 2:31:32 PM</li>
 * <li>Project: Communication</li>
 * <li>File: fi.wn.jasper.data.Accessor</li>
 * </ul>
 * </p>
 * @author Christopher Harper, account: chris
 * @version %version%
 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
 *        (built by: chris) */
public abstract class Accessor extends Persistent {

	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 2:33:09 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>serialVersionUID = -6705457789382634089L;</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final long serialVersionUID = -6705457789382634089L;

	/** <p>
	 * Copy constructor.
	 * <ul>
	 * <li>Created: Jul 16, 2012 3:54:19 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param accessor
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	Accessor(final Accessor accessor) {
		super(accessor);
	}

	/** <p>
	 * XML constructor.
	 * <ul>
	 * <li>Created: Jul 3, 2012 2:31:32 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param documentInfo
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	Accessor(final String documentInfo) {
		super(documentInfo);
	}
}
