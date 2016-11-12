package fi.sardion.varasto.data.observer;

import java.util.Observable;
import java.util.Observer;

/** <p>
 * TODO: Write a brief description of the type here.
 * </p>
 * <p>
 * <ul>
 * <li>Created: Aug 12, 2016 12:19:52 PM</li>
 * <li>Project: Communication</li>
 * <li>File: fi.wn.jasper.data.observer.PersistentObserver</li>
 * </ul>
 * </p>
 * @author Christopher Harper, account: chris
 * @version %version%
 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
 *        (built by: chris) */
public class PersistentObserver implements Observer {
	/** <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: Aug 12, 2016 12:20:19 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public PersistentObserver() {
		super();
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 12, 2016 12:20:30 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param o
	 * @param arg
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris)
	 * @see Observer#update(Observable, Object) */
	@Override
	public void update(final Observable o, final Object arg) {
	}

}
