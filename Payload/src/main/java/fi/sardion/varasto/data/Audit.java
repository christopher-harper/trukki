package fi.sardion.varasto.data;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/** <p>
 * TODO: Write a brief description of the type here.
 * </p>
 * <p>
 * <ul>
 * <li>Created: Jul 3, 2012 8:55:22 AM</li>
 * <li>Project: Communication</li>
 * <li>File: fi.wn.jasper.data.Audit</li>
 * </ul>
 * </p>
 * @author Christopher Harper, account: chris
 * @version %version%
 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
public class Audit extends Object implements Serializable {
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 8:55:36 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>serialVersionUID = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private static final long	serialVersionUID	= -6863837608794083798L;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:54:26 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>auditDocument = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private final Document		auditDocument;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:54:20 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>auditted = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private Persistent			auditted;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:54:15 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>created = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private Calendar			created				= null;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:54:08 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>digest = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private String				digest				= null;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 9, 2012 10:52:59 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>logger = Logger.getLogger(Audit.class.getName());</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private transient Logger	logger				= Logger.getLogger(Audit.class.getName());
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:54:04 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>name = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private String				name				= null;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:53:42 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>newElement = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private Element				newElement			= null;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:53:32 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>oldElement = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private Element				oldElement			= null;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:53:37 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>userName = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private String				userName			= null;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:53:24 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>uuid = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private VUUID				uuid				= null;

	/** <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:53:15 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param xml
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	Audit(final String xml) {
		try {
			this.auditDocument = Persistent.BUILDER.parse(new InputSource(new StringReader(xml)));
		} catch (final Exception ex) {
			final String message = String.format("Failed to parse a document from %s.", xml); //$NON-NLS-1$
			getLogger().log(Level.WARNING, message, ex);
			throw new RuntimeException(message, ex);
		}
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:59 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	public Persistent getAuditted() {
		return this.auditted;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:55 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	public Calendar getCreated() {
		if (this.created == null) {
			this.created = Calendar.getInstance();
			this.created.setTimeInMillis(Long.parseLong(((Element) getAuditDocument().getDocumentElement()
					.getElementsByTagName(Persistent.ELEMENT_CREATED).item(0)).getAttribute(Persistent.ATTRIBUTE_MILLIS)));
		}
		return this.created;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:49 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	public String getDigest() {
		if (this.digest == null) {
			this.digest = getAuditDocument().getDocumentElement().getAttribute(Persistent.ATTRIBUTE_DIGEST);
		}
		return this.digest;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:46 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	public String getName() {
		if (this.name == null) {
			this.name = getAuditDocument().getDocumentElement().getAttribute(Persistent.ATTRIBUTE_NAME);
		}
		return this.name;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:38 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	public Element getNew() {
		if (this.newElement == null) {
			this.newElement = (Element) getAuditDocument().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_NEW).item(0);
		}
		return this.newElement;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:33 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	public Element getOld() {
		if (this.oldElement == null) {
			this.oldElement = (Element) getAuditDocument().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_OLD).item(0);
		}
		return this.oldElement;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:29 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	public String getUserName() {
		if (this.userName == null) {
			this.userName = ((Element) getAuditDocument().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_USER).item(0))
					.getAttribute(Persistent.ATTRIBUTE_NAME);
		}
		return this.userName;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:22 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	public VUUID getUUID() {
		if (this.uuid == null) {
			this.uuid = VUUID.getVUUID(getAuditDocument().getDocumentElement().getAttribute(Persistent.ATTRIBUTE_UUID));
		}
		return this.uuid;
	}

	@Override
	public String toString() {
		return Persistent.toString(getAuditDocument().getDocumentElement());
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:52:13 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param anAuditted
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	protected void setAuditted(final Persistent anAuditted) {
		this.auditted = anAuditted;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2012 6:53:06 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris) */
	private Document getAuditDocument() {
		return this.auditDocument;
	}

	private Logger getLogger() {
		if (this.logger == null) {
			this.logger = Logger.getLogger(Audit.class.getName());
		}
		return this.logger;
	}

}
