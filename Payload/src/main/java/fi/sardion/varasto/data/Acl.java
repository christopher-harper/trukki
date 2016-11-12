package fi.sardion.varasto.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import data.Securable;
import fi.sardion.general.util.General;
import fi.sardion.varasto.data.VUUID.ObjectType;

/** <p>
 * An access control list object.
 * </p>
 * <p>
 * <ul>
 * <li>Created: Jul 4, 2016 3:52:22 PM</li>
 * <li>Project: Communication</li>
 * <li>File: fi.wn.jasper.data.Acl</li>
 * </ul>
 * </p>
 * @author Christopher Harper, account: chris
 * @version %version%
 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
 *        (built by: chris) */
public class Acl extends Persistent {
	/** <p>
	 * A permit given in the ACL.
	 * </p>
	 * <p>
	 * <ul>
	 * <li>Created: May 7, 2016 9:45:35 PM</li>
	 * <li>Project: Beryl</li>
	 * <li>File: fi.sardion.beryl.session.jasper.data.Permit</li>
	 * </ul>
	 * </p>
	 * @author Christopher Harper, account: dmadmin
	 * @version %version%
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public class AccessorPermit implements Serializable {
		/** <p>
		 * <ul>
		 * <li>Created: May 7, 2016 9:45:21 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * <li><code>PERMIT = "permit";</code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		public static final String	PERMIT				= "permit";				//$NON-NLS-1$
		/** <p>
		 * <ul>
		 * <li>Created: May 8, 2016 6:16:15 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * <li><code>serialVersionUID = -1656353216673254027L;</code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		private static final long	serialVersionUID	= -1656353216673254027L;
		/** <p>
		 * <ul>
		 * <li>Created: Jun 4, 2016 9:45:22 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		private List<ExtraPermit>	extraPermits		= new ArrayList<>();
		/** <p>
		 * <ul>
		 * <li>Created: May 7, 2016 10:06:01 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		private final String		name;
		/** <p>
		 * <ul>
		 * <li>Created: May 7, 2016 10:06:05 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		private Permit				permit				= Permit.none;

		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 4:26:18 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>toString = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		private String				toString			= null;

		/** <p>
		 * <ul>
		 * <li>Created: May 7, 2016 10:06:12 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		private final VUUID			uuid;

		/** <p>
		 * Constructor for the name .
		 * <ul>
		 * <li>Created: Jul 12, 2016 11:05:19 AM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * </ul>
		 * </p>
		 * @param aName
		 * @param aUuid
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		AccessorPermit(final String aName, final VUUID aUuid) {
			this.name = aName;
			this.uuid = aUuid;
		}

		/** <p>
		 * Sole constructor.
		 * <ul>
		 * <li>Created: May 7, 2016 10:01:17 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @param aName
		 *            name of the accessor.
		 * @param aUuid
		 *            the uuid of the accessor.
		 * @param theEtraPermits
		 * @param aPermit
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		AccessorPermit(final String aName, final VUUID aUuid, final List<String> theEtraPermits, final int aPermit) {
			this(aName, aUuid, Permit.valueOf(aPermit), Acl.getExtraPermits(theEtraPermits));
		}

		/** <p>
		 * Sole constructor.
		 * <ul>
		 * <li>Created: Jun 4, 2016 5:49:19 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @param aName
		 * @param aUuid
		 * @param aPermit
		 * @param theExtraPermits
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		AccessorPermit(final String aName, final VUUID aUuid, final Permit aPermit, final List<ExtraPermit> theExtraPermits) {
			this.name = aName;
			this.uuid = aUuid;
			this.permit = aPermit;
			this.extraPermits = theExtraPermits;
			Collections.sort(this.extraPermits);
		}

		/** <p>
		 * Get the ID of the accessor.
		 * <ul>
		 * <li>Created: May 7, 2016 10:05:47 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @return
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		public VUUID getAccessorUUID() {
			return this.uuid;
		}

		/** <p>
		 * Get accessors extra permits.
		 * <ul>
		 * <li>Created: Jun 4, 2016 9:45:55 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @return
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		public List<ExtraPermit> getExtraPermits() {
			/* Create a defensive copy */
			final List<ExtraPermit> copy = new ArrayList<>(this.extraPermits);
			Collections.sort(copy);
			return copy;

		}

		/** <p>
		 * Get the name of the
		 * <ul>
		 * <li>Created: May 7, 2016 10:06:21 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @return
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		public String getName() {
			return this.name;
		}

		/** <p>
		 * TODO Write a method comment.
		 * <ul>
		 * <li>Created: May 7, 2016 10:06:34 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @return the permit
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		public Permit getPermit() {
			/* Defensive copy. */
			return Permit.valueOf(this.permit.getPermit());
		}

		/** <p>
		 * TODO Write a method comment.
		 * <ul>
		 * <li>Created: May 7, 2016 10:06:46 PM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @return
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		public VUUID getUuid() {
			return this.uuid;
		}

		@Override
		public String toString() {
			if (this.toString == null) {
				this.toString = new StringBuilder().append(this.getName()).append(':').append(this.getPermit()).append(':')
						.append(this.extraPermits).toString();
			}
			return this.toString;
		}

		/** <p>
		 * TODO Write a method comment.
		 * <ul>
		 * <li>Created: Jul 10, 2016 4:32:49 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * </ul>
		 * </p>
		 * @param theExtraPermits
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		void addExtraPermits(final List<ExtraPermit> theExtraPermits) {
			for (final ExtraPermit extra : theExtraPermits) {
				if (!this.getExtraPermits().contains(extra)) {
					this.extraPermits.add(extra);
				}
			}
			Collections.sort(this.extraPermits);
		}

		/** <p>
		 * TODO Write a method comment.
		 * <ul>
		 * <li>Created: Jul 12, 2016 9:18:03 AM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * </ul>
		 * </p>
		 * @param aPermit
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		void setPermit(final Permit aPermit) {
			this.permit = aPermit;
		}
	}

	/** <p>
	 * TODO: Write a brief description of the type here.
	 * </p>
	 * <p>
	 * <ul>
	 * <li>Created: Jun 10, 2016 10:45:26 AM</li>
	 * <li>Project: Beryl</li>
	 * <li>File: fi.sardion.beryl.session.jasper.data.ExtraPermit</li>
	 * </ul>
	 * </p>
	 * @author Christopher Harper, account: dmadmin
	 * @version %version%
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public enum ExtraPermit {
		all, delete, link, move, unlink;
	}

	/** <p>
	 * TODO: Write a brief description of the type here.
	 * </p>
	 * <p>
	 * <ul>
	 * <li>Created: Jul 25, 2016 12:35:10 PM</li>
	 * <li>Project: Communication</li>
	 * <li>File: fi.wn.jasper.data.Permit</li>
	 * </ul>
	 * </p>
	 * @author Christopher Harper, account: chris
	 * @version %version%
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public enum Permit {
		/** <p>
		 * <ul>
		 * <li>Created: Jul 25, 2016 12:35:03 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>content = 2</code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		content(2),
		/** <p>
		 * <ul>
		 * <li>Created: Jul 25, 2016 12:35:21 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>delete = 5</code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		delete(5),
		/** <p>
		 * <ul>
		 * <li>Created: Jul 25, 2016 12:35:31 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>metadata = 1</code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		metadata(1),
		/** <p>
		 * <ul>
		 * <li>Created: Jul 25, 2016 12:35:44 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>none = 0</code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		none(0),
		/** <p>
		 * <ul>
		 * <li>Created: Jul 25, 2016 12:35:53 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>version = 3</code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		version(3),
		/** <p>
		 * <ul>
		 * <li>Created: Jul 25, 2016 12:36:03 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>write = 4</code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		write(4),;
		/** <p>
		 * TODO Write a method comment.
		 * <ul>
		 * <li>Created: Jun 10, 2016 10:44:21 AM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @param aPermit
		 * @return
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		public static Permit valueOf(final int aPermit) {
			for (final Permit permit : Permit.values()) {
				if (permit.getPermit() == aPermit) {
					return permit;
				}
			}
			final String message = String.format("Unknown permit %s given.", String.valueOf(aPermit)); //$NON-NLS-1$
			Logger.getLogger(Acl.class.getName()).log(Level.WARNING, message);
			throw new RuntimeException(message);
		}

		/** <p>
		 * <ul>
		 * <li>Created: Jun 10, 2016 10:44:28 AM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		private int permit;

		/** <p>
		 * Sole constructor.
		 * <ul>
		 * <li>Created: Jun 10, 2016 10:44:25 AM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @param aPermit
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		private Permit(final int aPermit) {
			this.permit = aPermit;
		}

		/** <p>
		 * TODO Write a method comment.
		 * <ul>
		 * <li>Created: Jun 10, 2016 10:44:34 AM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @return
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		public int getPermit() {
			return this.permit;
		}

		/** <p>
		 * TODO Write a method comment.
		 * <ul>
		 * <li>Created: Jun 10, 2016 10:44:43 AM</li>
		 * <li>Author: Christopher Harper, account: dmadmin</li>
		 * </ul>
		 * </p>
		 * @return
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		@Override
		public String toString() {
			return String.valueOf(this.getPermit());
		}
	}

	/** <p>
	 * <ul>
	 * <li>Created: Jul 10, 2016 11:42:07 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>EVERYONE = "everyone";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String	EVERYONE			= "everyone";					//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 25, 2016 9:27:32 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ON_THE_FLY = "On the fly";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String	ON_THE_FLY			= "On the fly";					//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 10, 2016 11:41:13 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>OWNER = "owner";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String	OWNER				= "owner";						//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 16, 2016 2:26:51 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_PERMIT = "permit";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String	ATTRIBUTE_PERMIT	= "permit";						//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 4:06:21 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_ACCESSOR = "accessor";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String	ELEMENT_ACCESSOR	= "accessor";					//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 16, 2016 4:06:21 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_ACCESSOR = Acl.ELEMENT_ACCESSOR + 's';</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String	ELEMENT_ACCESSORS	= Acl.ELEMENT_ACCESSOR + 's';

	/** <p>
	 * <ul>
	 * <li>Created: Jul 21, 2016 2:14:20 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_DIGEST = "digest";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String	ELEMENT_DIGEST		= "digest";						//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 25, 2016 9:18:18 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_USAGE = "usage";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String	ELEMENT_USAGE		= "usage";						//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 3:55:02 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>serialVersionUID = -3871083992917676908L;</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final long	serialVersionUID	= -3871083992917676908L;

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 4, 2016 5:49:01 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @param theEtraPermits
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final List<ExtraPermit> getExtraPermits(final List<String> theEtraPermits) {
		final List<ExtraPermit> extras = new ArrayList<>();
		for (final String extraPermit : theEtraPermits) {
			final ExtraPermit extra = ExtraPermit.valueOf(extraPermit);
			if (!extras.contains(extra)) {
				extras.add(extra);
			}
		}
		return extras;
	}

	/** <p>
	 * <ul>
	 * <li>Created: Jul 25, 2016 12:33:54 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>digest = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient String			digest	= null;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 9, 2016 10:58:32 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>logger = Logger.getLogger(Acl.class.getName());</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient Logger			logger	= Logger.getLogger(Acl.class.getName());

	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 4:04:38 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private Map<String, AccessorPermit>	permits	= null;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 25, 2016 12:33:10 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>usage = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient int				usage	= 0;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 12, 2016 1:06:13 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>userPermit = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private AccessorPermit				userPermit;

	/** <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: Jul 4, 2016 3:52:23 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param documentInfo
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	Acl(final String documentInfo) {
		super(documentInfo);
	}

	/** <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: Jul 16, 2016 3:42:44 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private Acl(final Acl acl) {
		super(acl);
		this.permits = acl.permits;
		this.userPermit = acl.userPermit;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 23, 2016 10:33:23 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public String computeDigest() {
		final List<String> names = new ArrayList<>(this.getPermits().keySet());
		Collections.sort(names);
		final StringBuilder raw = new StringBuilder();
		for (final String name : names) {
			raw.append(this.getPermits().get(name).toString()).append('|');
		}
		final NodeList elements = this.getDocument().getDocumentElement().getElementsByTagName(Acl.ELEMENT_DIGEST);
		final Element digestElement;
		if (elements.getLength() > 0) {
			digestElement = (Element) elements.item(0);
		} else {
			digestElement = this.getDocument().createElement(Acl.ELEMENT_DIGEST);
			this.getDocument().getDocumentElement().appendChild(digestElement);
		}
		final String newDigest = General.getDigest(raw.toString());
		digestElement.setAttribute(Persistent.ATTRIBUTE_VALUE, newDigest);
		this.digest = newDigest;
		this.setChanged();
		return this.getDigest();
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 23, 2016 10:40:09 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public String getDigest() {
		if (this.digest == null) {
			final NodeList elements = this.getDocument().getDocumentElement().getElementsByTagName(Acl.ELEMENT_DIGEST);
			if (elements.getLength() > 0) {
				final Element digestElement = (Element) elements.item(0);
				this.digest = digestElement.getAttribute(Persistent.ATTRIBUTE_VALUE);
			} else {
				this.computeDigest();
			}
		}
		return this.digest;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 1:04:45 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param securable
	 * @param accessor
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public AccessorPermit getPermit(final Securable securable, final Accessor accessor) {
		final AccessorPermit foundPermit;
		if (this.userPermit.getAccessorUUID().equals(accessor.getVUUID())) {
			foundPermit = this.userPermit;
		} else if (this.getPermits().keySet().contains(accessor.getName())) {
			foundPermit = this.getPermits().get(accessor.getName());
		} else if (securable != null && securable.getOwnerUUID().equals(accessor.getVUUID())
				&& this.getPermits().keySet().contains(Acl.OWNER)) {
			foundPermit = this.getPermits().get(Acl.OWNER);
		} else if (this.getPermits().keySet().contains(Acl.EVERYONE)) {
			foundPermit = this.getPermits().get(Acl.EVERYONE);
		} else {
			foundPermit = new AccessorPermit(accessor.getName(), accessor.getVUUID());
		}
		return foundPermit;

	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 7, 2016 9:46:06 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return a map where the accessor uuid values are the keys and the accessor permits as the values.
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public Map<String, AccessorPermit> getPermits() {
		if (this.permits == null) {
			final NodeList accessors = this.getAccessors().getElementsByTagName(Acl.ELEMENT_ACCESSOR);
			final int permitCount = accessors.getLength();
			this.permits = new HashMap<>(permitCount);
			for (int permitIndex = 0; permitIndex < permitCount; permitIndex++) {
				final Element accessorPrivilege = (Element) accessors.item(permitIndex);
				final String name = accessorPrivilege.getAttribute(Persistent.ATTRIBUTE_NAME);
				try {
					final String id = accessorPrivilege.getAttribute(Persistent.ATTRIBUTE_UUID);
					final VUUID uuid;
					if (id != null && id.length() > 0) {
						uuid = VUUID.getVUUID(id);
					} else {
						uuid = VUUID.NULL_ID;
					}
					final NodeList extraPermits = accessorPrivilege.getElementsByTagName(Persistent.ELEMENT_VALUE);
					final int extraPermitCount = extraPermits.getLength();
					final List<ExtraPermit> extras = new ArrayList<>(extraPermitCount);
					for (int extraPermitIndex = 0; extraPermitIndex < extraPermitCount; extraPermitIndex++) {
						final Element extraPermit = (Element) extraPermits.item(extraPermitIndex);
						extras.add(ExtraPermit.valueOf(extraPermit.getAttribute(Persistent.ATTRIBUTE_VALUE)));
					}
					final AccessorPermit permit = new AccessorPermit(name, uuid,
							Permit.valueOf(Integer.parseInt(accessorPrivilege.getAttribute(AccessorPermit.PERMIT))), extras);
					this.permits.put(name, permit);
					this.getLogger().finer(
							String.format("Resolved permit %s for %s of ACL %s(%s).", permit, name, this.getName(), this.getVUUID())); //$NON-NLS-1$
				} catch (final Exception ex) {
					this.getLogger().log(Level.WARNING, String.format("Failed to resolve permits for %s.", name), ex); //$NON-NLS-1$
				}
			}
			this.getLogger().fine(String.format("Resolved permits of ACL %s(%s).", this.getName(), this.getVUUID())); //$NON-NLS-1$
		}
		return this.permits;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 24, 2016 6:42:06 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public int getUsage() {
		if (0 == this.usage) {
			final NodeList elements = this.getDocument().getDocumentElement().getElementsByTagName(Acl.ELEMENT_USAGE);
			final Element usageElement;
			if (elements.getLength() > 0) {
				usageElement = (Element) elements.item(0);
			} else {
				usageElement = this.getDocument().createElement(Acl.ELEMENT_USAGE);
				this.getDocument().getDocumentElement().appendChild(usageElement);
				usageElement.setAttribute(Persistent.ATTRIBUTE_VALUE, String.valueOf(-1));
			}
			this.usage = Integer.parseInt(usageElement.getAttribute(Persistent.ELEMENT_VALUE));
		}
		return this.usage;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 4:27:29 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param accessorName
	 * @param accessorUUID
	 * @param aPermit
	 * @param theExtraPermits
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void grant(final String accessorName, final VUUID accessorUUID, final Permit aPermit, final List<ExtraPermit> theExtraPermits) {
		if (ObjectType.GROUP.equals(accessorUUID.getType()) || ObjectType.USER.equals(accessorUUID.getType())) {
			final AccessorPermit permit;
			if (this.getPermits().keySet().contains(accessorName)) {
				permit = this.getPermits().get(accessorName);
				this.getLogger().log(Level.FINE,
						String.format("Modifying %s's(%s) permits to %s and extended %s.", accessorName, accessorUUID, aPermit, //$NON-NLS-1$
								theExtraPermits));
			} else {
				permit = new AccessorPermit(accessorName, accessorUUID, aPermit, theExtraPermits);
				this.getLogger().log(Level.FINE,
						String.format("Granting %s's(%s) permit %s and extended %s.", accessorName, accessorUUID, aPermit, //$NON-NLS-1$
								theExtraPermits));
			}
			permit.setPermit(aPermit);
			permit.addExtraPermits(theExtraPermits);
			this.remove(accessorName);
			this.getPermits().put(accessorName, permit);
			final Element accessor = this.getDocument().createElement(Acl.ELEMENT_ACCESSOR);
			this.getAccessors().appendChild(accessor);
			accessor.setAttribute(Persistent.ATTRIBUTE_NAME, accessorName);
			accessor.setAttribute(Acl.ATTRIBUTE_PERMIT, permit.getPermit().toString());
			accessor.setAttribute(Persistent.ATTRIBUTE_UUID, accessorUUID.toString());
			for (final ExtraPermit extraPermit : theExtraPermits) {
				final Element value = this.getDocument().createElement(Persistent.ELEMENT_VALUE);
				accessor.appendChild(value);
				value.setAttribute(Persistent.ATTRIBUTE_VALUE, extraPermit.toString());
			}
			this.computeDigest();
		} else {
			final String message = String.format("The given UUID %s is neither for a group nor a user.", accessorUUID); //$NON-NLS-1$
			this.getLogger().log(Level.WARNING, message);
			throw new RuntimeException(message);
		}
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 4:00:53 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param accessor
	 * @param aPermit
	 * @param theExtraPermits
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void grant(final User accessor, final Permit aPermit, final List<ExtraPermit> theExtraPermits) {
		this.grant(accessor.getName(), accessor.getVUUID(), aPermit, theExtraPermits);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 20, 2016 10:55:27 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param securable
	 * @param accessor
	 * @param extraPermit
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean hasExtraPermit(final Securable securable, final Accessor accessor, final ExtraPermit extraPermit) {
		return this.getPermit(securable, accessor).getExtraPermits().contains(extraPermit);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 1:04:34 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param securable
	 * @param accessor
	 * @param permit
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean hasPermit(final Securable securable, final Accessor accessor, final Permit permit) {
		return this.getPermit(securable, accessor).getPermit().getPermit() >= permit.getPermit();
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 4:27:58 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param accessor
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void revoke(final Accessor accessor) {
		this.revoke(accessor.getName());
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 16, 2016 4:21:47 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param accessorName
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void revoke(final String accessorName) {
		this.remove(accessorName);
		this.computeDigest();
	}

	@Override
	Acl copy() {
		return new Acl(this);
	}

	@Override
	void setDocument(final Document aDocument) {
		super.setDocument(aDocument);
		this.digest = null;
		this.permits = null;
		this.usage = 0;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 24, 2016 6:39:39 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param theUsage
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	void setUsage(final int theUsage) {
		final NodeList elements = this.getDocument().getDocumentElement().getElementsByTagName(Acl.ELEMENT_USAGE);
		final Element usageElement;
		if (elements.getLength() > 0) {
			usageElement = (Element) elements.item(0);
		} else {
			usageElement = this.getDocument().createElement(Acl.ELEMENT_USAGE);
			this.getDocument().getDocumentElement().appendChild(usageElement);
		}
		usageElement.setAttribute(Persistent.ATTRIBUTE_VALUE, String.valueOf(theUsage));
		this.usage = theUsage;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 9:17:21 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param theUserPermit
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	void setUserPermits(final AccessorPermit theUserPermit) {
		this.userPermit = theUserPermit;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 20, 2016 2:38:53 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private Element getAccessors() {
		final NodeList accessorsElement = this.getDocument().getDocumentElement().getElementsByTagName(Acl.ELEMENT_ACCESSORS);
		if (accessorsElement.getLength() > 0) {
			return (Element) accessorsElement.item(0);
		}
		final String message = String.format("Invalid ACL! The document element must contain a %s element which is missing.", //$NON-NLS-1$
				Acl.ELEMENT_ACCESSORS);
		this.getLogger().log(Level.WARNING, message);
		throw new RuntimeException(message);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 16, 2016 4:21:56 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private Logger getLogger() {
		if (this.logger == null) {
			this.logger = Logger.getLogger(Acl.class.getName());
		}
		return this.logger;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 16, 2016 3:28:34 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param accessorName
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private void remove(final String accessorName) {
		if (this.getPermits().keySet().contains(accessorName)) {
			this.getPermits().remove(accessorName);
			final NodeList accessors = this.getAccessors().getElementsByTagName(Acl.ELEMENT_ACCESSOR);
			final List<Element> accessorElements = new ArrayList<>();
			for (int permitIndex = 0, permitCount = accessors.getLength(); permitIndex < permitCount; permitIndex++) {
				final Element accessorPrivilege = (Element) accessors.item(permitIndex);
				final String name = accessorPrivilege.getAttribute(Persistent.ATTRIBUTE_NAME);
				if (accessorName.equals(name)) {
					accessorElements.add(accessorPrivilege);
				}
			}
			for (final Element accessor : accessorElements) {
				final Node old = this.getOldData().importNode(accessor, true);
				this.getOldData().getDocumentElement().appendChild(old);
				this.getAccessors().removeChild(accessor);
			}
		}
	}

}
