package fi.sardion.varasto.data;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import fi.sardion.general.util.General;
import fi.sardion.varasto.data.VUUID.ObjectType;
import fi.sardion.varasto.data.observer.PersistentObserver;

/** <p>
 * TODO: Write a brief description of the type here.
 * </p>
 * <p>
 * <ul>
 * <li>Created: Jul 2, 2016 3:21:51 PM</li>
 * <li>Project: Communication</li>
 * <li>File: fi.wn.jasper.data.Persistent</li>
 * </ul>
 * </p>
 * @author Christopher Harper, account: chris
 * @version %version%
 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
 *        (built by: chris) */
public abstract class Persistent extends Observable implements Serializable {
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2016 8:57:51 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_MILLIS = "millis";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ATTRIBUTE_MILLIS	= "millis";											//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:03:37 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_NAME = "name";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ATTRIBUTE_NAME		= "name";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 10, 2016 7:41:37 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_ORIGIN = "origin";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ATTRIBUTE_ORIGIN	= "origin";											//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 12, 2016 3:10:36 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_TIME = "time";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ATTRIBUTE_TIME		= "time";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:02:09 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_UUID = "uuid";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ATTRIBUTE_UUID		= "uuid";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 8:59:04 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_VERSION = "version";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ATTRIBUTE_VERSION	= "version";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Sep 7, 2016 1:28:25 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>CONTINUE = "Continue";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				CONTINUE			= "Continue";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 25, 2016 3:00:06 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>DESTROY_OK = "Destroy OK";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				DESTROY_OK			= "Destroy OK";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 7:30:29 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_ACL = "acl";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ELEMENT_ACL			= "acl";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:12:53 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_CREATED = "created";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ELEMENT_CREATED		= "created";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:13:04 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_CREATOR = "creator";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ELEMENT_CREATOR		= "creator";										//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 12, 2016 3:23:18 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_MODIFIED = "modified";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ELEMENT_MODIFIED	= "modified";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 12, 2016 2:37:01 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_MODIFIER = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ELEMENT_MODIFIER	= "modifier";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:01:33 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_OWNER = "owner";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final String				ELEMENT_OWNER		= "owner";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Aug 25, 2016 11:27:28 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>MAINTAINER_OK = "Maintainer OK";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final Object				MAINTAINER_OK		= "Maintainer OK";									//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: May 12, 2016 6:41:44 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * <li><code>TIMESTAMP = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSSZ");</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public static final SimpleDateFormat	TIMESTAMP			= new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSSZ");	//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2016 4:29:55 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_PROPERTIES = "properties";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected static final String			ELEMENT_PROPERTIES	= "properties";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2016 9:43:18 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_DIGEST = "sha-256";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final String						ATTRIBUTE_DIGEST	= "sha-256";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 4:03:50 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_VALUE = "value";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final String						ATTRIBUTE_VALUE		= "value";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 6:28:07 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>BUILDER = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final DocumentBuilder			BUILDER;
	/** <p>
	 * <ul>
	 * <li>Created: May 8, 2016 10:58:44 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * <li><code>ELEMENT_ATTRIBUTES = Persistent.ELEMENT_ATTRIBUTE + 's';</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final String						ELEMENT_ATTRIBUTES	= Persistent.ELEMENT_ATTRIBUTE + 's';

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 8:38:38 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_NEW = "new";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final String						ELEMENT_NEW			= "new";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 8:38:42 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_OLD = "old";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final String						ELEMENT_OLD			= "old";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: May 13, 2016 1:28:34 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * <li><code>ELEMENT_USER = "j_user";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final String						ELEMENT_USER		= "user";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 4:08:06 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_VALUE = Persistent.ATTRIBUTE_VALUE;</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final String						ELEMENT_VALUE		= Persistent.ATTRIBUTE_VALUE;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 17, 2016 11:40:00 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>TRANSFORMER = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static final TransformerFactory			TRANSFORMER;
	/** <p>
	 * <ul>
	 * <li>Created: May 17, 2016 4:54:00 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * <li><code>ATTRIBUTE = "attribute";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String				ATTRIBUTE			= "attribute";										//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 9, 2016 10:19:48 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_INDEX = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String				ATTRIBUTE_INDEX		= "index";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 9, 2016 10:19:54 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ATTRIBUTE_TARGET = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String				ATTRIBUTE_TARGET	= "target";											//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: May 8, 2016 10:58:16 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * <li><code>ELEMENT_ATTRIBUTE = Persistent.ATTRIBUTE;</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String				ELEMENT_ATTRIBUTE	= Persistent.ATTRIBUTE;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:12:57 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_CREATE = "create";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String				ELEMENT_CREATE		= "create";											//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 12, 2016 2:22:48 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_MODIFY = "modify";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String				ELEMENT_MODIFY		= "modify";											//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 24, 2016 3:04:21 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>PUBLIC_ATTRIBUTE = "*";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String				PUBLIC_ATTRIBUTE	= "*";												//$NON-NLS-1$

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 3:21:58 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>serialVersionUID = 8226901284918077441L;</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final long				serialVersionUID	= 8226901284918077441L;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 4:21:59 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>serialVersionUID = 8226901284918077441L;</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	static {
		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		final Logger logger = Logger.getLogger(Persistent.class.getName());
		try {
			logger.log(Level.INFO, "Initializing document builder."); //$NON-NLS-1$
			BUILDER = builderFactory.newDocumentBuilder();
			logger.log(Level.INFO, "Document builder initialised."); //$NON-NLS-1$
			logger.log(Level.INFO, "Initializing transformer."); //$NON-NLS-1$
			TRANSFORMER = TransformerFactory.newInstance();
			logger.log(Level.INFO, "Transformer initialized."); //$NON-NLS-1$
		} catch (final ParserConfigurationException pcex) {
			logger.log(Level.SEVERE, "Failure loading document parser and transformer.", pcex); //$NON-NLS-1$
			throw new RuntimeException("Failure loading document parser and transformer.", pcex); //$NON-NLS-1$
		}
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 2, 2016 6:31:51 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param documentElement
	 *            the document element to convert.
	 * @return the XML string.
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected static String toString(final Element documentElement) {
		try {
			final Source source = new DOMSource(documentElement);
			final StringWriter stringWriter = new StringWriter();
			final Result result = new StreamResult(stringWriter);
			final Transformer transformer = Persistent.TRANSFORMER.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); //$NON-NLS-1$
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
			transformer.transform(source, result);
			return stringWriter.getBuffer().toString();
		} catch (final TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (final TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** <p>
	 * <ul>
	 * <li>Created: Jul 16, 2016 3:48:48 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>attributes = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient Document		attributes;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 24, 2016 3:04:42 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>attributesXML = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private String					attributesXML;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 16, 2016 3:48:52 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>created = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient Calendar		created		= null;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 16, 2016 3:48:56 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>creatorName = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient String		creatorName	= null;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:16:21 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>creatorUUID = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient VUUID			creatorUUID;

	/** <p>
	 * <ul>
	 * <li>Created: Aug 12, 2016 11:51:56 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>destroyed = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private boolean					destroyed;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 8:36:35 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>document = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient Document		document;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 17, 2016 11:26:40 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>documentXML = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private String					documentXML	= null;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 16, 2016 3:49:01 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>history = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient List<Audit>	history;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 3:28:54 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>logger = Logger.getLogger(Persistent.class.getName());</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient Logger		logger		= Logger.getLogger(Persistent.class.getName());

	/** <p>
	 * <ul>
	 * <li>Created: Jul 16, 2016 11:04:58 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>modified = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient Calendar		modified;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 12, 2016 2:39:28 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>modifierName = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient String		modifierName;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 16, 2016 11:05:03 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>modifierUUID = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient VUUID			modifierUUID;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:16:34 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>newData = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient Document		newData;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 17, 2016 11:26:24 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>newXML = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private String					newXML;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 17, 2016 10:09:17 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>objectName = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient String		objectName	= null;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:16:45 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>oldData = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient Document		oldData;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 17, 2016 11:26:18 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>oldXML = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private String					oldXML;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 17, 2016 10:09:22 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ownerName = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient String		ownerName	= null;
	/** <p>
	 * <ul>
	 * <li>Created: Jul 17, 2016 10:09:40 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ownerUUID = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient VUUID			ownerVUUID	= null;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 8:51:36 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>version = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient int			version		= 0;

	/** <p>
	 * <ul>
	 * <li>Created: Jul 2, 2016 9:00:39 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>juuid = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private transient VUUID			vuuid		= null;

	/** <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: Jul 2, 2016 3:21:51 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param documentInfo
	 *            the object XML string.
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected Persistent(final String documentInfo) {
		try {
			this.document = Persistent.BUILDER.parse(new InputSource(new StringReader(documentInfo)));
			this.oldData = Persistent.BUILDER.parse(new InputSource(new StringReader('<' + Persistent.ELEMENT_OLD + "/>"))); //$NON-NLS-1$
			this.newData = Persistent.BUILDER.parse(new InputSource(new StringReader('<' + Persistent.ELEMENT_NEW + "/>"))); //$NON-NLS-1$
			this.addObserver(new PersistentObserver());
		} catch (final Exception ex) {
			final String message = String.format("Failed to parse object from:\n%s\n.", documentInfo); //$NON-NLS-1$
			this.getLogger().log(Level.WARNING, message, ex);
			throw new IllegalArgumentException(message, ex);
		}
	}

	/** <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: Jul 16, 2016 3:49:50 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param object
	 *            the object whose values to copy.
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	Persistent(final Persistent object) {
		this.document = object.document;
		this.documentXML = Persistent.toString(object.getDocument().getDocumentElement());
		this.oldData = object.oldData;
		this.oldXML = Persistent.toString(object.getOldData().getDocumentElement());
		this.newData = object.newData;
		this.newXML = Persistent.toString(object.getNewData().getDocumentElement());
		if (object.getAttributes() != null) {
			this.attributes = object.attributes;
			this.attributesXML = Persistent.toString(object.getAttributes().getDocumentElement());
		}
		this.addObserver(new PersistentObserver());
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 11:12:05 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param values
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void addValues(final Map<String, List<String>> values) {
		this.setValues(values, false, null);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 11:12:09 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param values
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void addValues(final Map<String, List<String>> values, final Acl acl) {
		this.setValues(values, false, acl);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2016 9:00:48 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public Calendar getCreated() {
		if (this.created == null) {
			try {
				this.created = Calendar.getInstance();
				this.created.setTimeInMillis(
						Long.parseLong(((Element) this.getCreate().getElementsByTagName(Persistent.ELEMENT_CREATED).item(0))
								.getAttribute(Persistent.ATTRIBUTE_MILLIS)));
			} catch (final Throwable t) {
				final String message = String.format("Failure returning %s value from element %s.", Persistent.ATTRIBUTE_MILLIS, //$NON-NLS-1$
						Persistent.ELEMENT_CREATED);
				this.getLogger().log(Level.WARNING, message, t);
				throw new RuntimeException(message, t);
			}
		}
		final Calendar returnValue = Calendar.getInstance();
		returnValue.setTimeInMillis(this.created.getTimeInMillis());
		return returnValue;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 13, 2016 8:24:20 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public String getCreatorName() {
		if (this.creatorName == null) {
			this.creatorName = ((Element) this.getCreate().getElementsByTagName(Persistent.ELEMENT_CREATOR).item(0))
					.getAttribute(Persistent.ATTRIBUTE_NAME);
		}
		return this.creatorName;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 7, 2016 4:34:49 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public VUUID getCreatorUUID() {
		if (this.creatorUUID == null) {
			try {
				this.creatorUUID = VUUID.getVUUID(((Element) this.getCreate().getElementsByTagName(Persistent.ELEMENT_CREATOR).item(0))
						.getAttribute(Persistent.ATTRIBUTE_UUID));
			} catch (final Throwable t) {
				final String message = String.format("Failure returning creator uuid %s value from element %s.", Persistent.ATTRIBUTE_NAME, //$NON-NLS-1$
						Persistent.ELEMENT_CREATED);
				this.getLogger().log(Level.WARNING, message, t);
				throw new RuntimeException(message, t);
			}
		}
		return VUUID.getVUUID(this.creatorUUID.toString());
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 1:29:00 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public Document getExtraAttributes() {
		try {
			final Document extraAttributes = Persistent.BUILDER
					.parse(new InputSource(new StringReader('<' + Persistent.ELEMENT_PROPERTIES + "/>"))); //$NON-NLS-1$
			if (this.getAttributes() != null) {
				final NodeList childNodes = this.getAttributes().getDocumentElement().getChildNodes();
				for (int index = 0, count = childNodes.getLength(); index < count; index++) {
					final Node node = childNodes.item(index);
					if (Node.ELEMENT_NODE == node.getNodeType() && !Persistent.ELEMENT_ATTRIBUTES.equals(node.getNodeName())) {
						final Node importNode = extraAttributes.importNode(node, true);
						extraAttributes.getDocumentElement().appendChild(importNode);
					}
				}
			}
			return extraAttributes;
		} catch (final Exception ex) {
			final String message = String.format(
					"Failed to create a document with document element %s for extra attributes. Message recieved: %s", //$NON-NLS-1$
					Persistent.ELEMENT_PROPERTIES, ex.getMessage());
			this.getLogger().log(Level.WARNING, message, ex);
			throw new RuntimeException(message, ex);
		}
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2016 6:59:42 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public List<Audit> getHistory() {
		if (this.history == null) {
			final String message = "The history is null which means that it needs to be returned from the server first."; //$NON-NLS-1$
			this.getLogger().log(Level.WARNING, message);
			throw new IllegalArgumentException(message);
		}
		return this.history;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 2:50:25 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public Calendar getModified() {
		if (this.modified == null) {
			try {
				final NodeList modifieds = this.getModify().getElementsByTagName(Persistent.ELEMENT_MODIFIED);
				this.modified = Calendar.getInstance();
				if (modifieds.getLength() > 0) {
					this.modified.setTimeInMillis(Long.parseLong(((Element) modifieds.item(0)).getAttribute(Persistent.ATTRIBUTE_MILLIS)));
				} else {
					this.modified = this.getCreated();
				}
			} catch (final Throwable t) {
				final String message = String.format("Failure returning %s value from element %s.", Persistent.ATTRIBUTE_MILLIS, //$NON-NLS-1$
						Persistent.ELEMENT_CREATED);
				this.getLogger().log(Level.WARNING, message, t);
				throw new RuntimeException(message, t);
			}
		}
		final Calendar returnCalendar = Calendar.getInstance();
		returnCalendar.setTimeInMillis(this.modified.getTimeInMillis());
		return returnCalendar;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 2:39:14 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public String getModifierName() {
		if (this.modifierName == null) {
			final NodeList modifiers = this.getModify().getElementsByTagName(Persistent.ELEMENT_MODIFIER);
			if (modifiers.getLength() > 0) {
				this.modifierName = ((Element) modifiers.item(0)).getAttribute(Persistent.ATTRIBUTE_NAME);
			} else {
				this.modifierName = this.getCreatorName();
			}
		}
		return this.modifierName;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 2:57:21 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public VUUID getModifierUUID() {
		if (this.modifierUUID == null) {
			final NodeList modifiers = this.getModify().getElementsByTagName(Persistent.ELEMENT_MODIFIER);
			if (modifiers.getLength() > 0) {
				try {
					this.modifierUUID = VUUID.getVUUID(((Element) modifiers.item(0)).getAttribute(Persistent.ATTRIBUTE_UUID));
				} catch (final Throwable t) {
					final String message = String.format("Failure returning creator uuid %s value from element %s.", //$NON-NLS-1$
							Persistent.ATTRIBUTE_NAME, Persistent.ELEMENT_CREATED);
					this.getLogger().log(Level.WARNING, message, t);
					throw new RuntimeException(message, t);
				}
			} else {
				this.modifierUUID = this.getCreatorUUID();
			}
		}
		return VUUID.getVUUID(this.modifierUUID.toString());
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 7, 2016 8:59:41 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public String getName() {
		if (this.objectName == null) {
			this.objectName = this.getDocument().getDocumentElement().getAttribute(Persistent.ATTRIBUTE_NAME);
		}
		return this.objectName;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 4, 2016 2:23:15 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public String getOwnerName() {
		if (this.ownerName == null) {
			this.ownerName = this.getOwnerElement().getAttribute(Persistent.ATTRIBUTE_NAME);
		}
		return this.ownerName;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 14, 2016 6:50:18 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public VUUID getOwnerUUID() {
		if (this.ownerVUUID == null) {
			this.ownerVUUID = VUUID.getVUUID(this.getOwnerElement().getAttribute(Persistent.ATTRIBUTE_UUID));
		}
		return this.ownerVUUID;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 5, 2016 5:42:23 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public String getValue(final String name) {
		final List<String> values = this.getValues(name);
		if (values != null && values.size() > 0) {
			return values.get(0);
		}
		return null;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Sep 21, 2016 10:15:00 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public List<String> getValueNames() {
		final List<String> names = new ArrayList<>();
		if (this.getAttributes() != null) {
			final NodeList attributesList = this.getAttributes().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_ATTRIBUTES);
			for (int attributesIndex = 0, attributesCount = attributesList
					.getLength(); attributesIndex < attributesCount; attributesIndex++) {
				final NodeList attribute = ((Element) attributesList.item(attributesIndex))
						.getElementsByTagName(Persistent.ELEMENT_ATTRIBUTE);
				for (int attributeIndex = 0, attributeCount = attribute.getLength(); attributeIndex < attributeCount; attributeIndex++) {
					final Element attributeElement = (Element) attribute.item(attributeIndex);
					if (attributeElement.hasAttribute(Persistent.ATTRIBUTE_NAME)) {
						final String name = attributeElement.getAttribute(Persistent.ATTRIBUTE_NAME);
						if (!names.contains(name)) {
							names.add(name);
						}
					}
				}
			}
		}
		return names;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 5, 2016 5:42:17 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public List<String> getValues(final String name) {
		final NodeList attributesList = this.getAttributes().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_ATTRIBUTES);
		final Map<Integer, String> values = new HashMap<>();
		for (int attributesIndex = 0, attributesCount = attributesList.getLength(); attributesIndex < attributesCount
				&& values.size() <= 0; attributesIndex++) {
			final NodeList attribute = ((Element) attributesList.item(attributesIndex)).getElementsByTagName(Persistent.ELEMENT_ATTRIBUTE);
			for (int attributeIndex = 0, attributeCount = attribute.getLength(); attributeIndex < attributeCount
					&& values.size() <= 0; attributeIndex++) {
				final Element attributeElement = (Element) attribute.item(attributeIndex);
				if (attributeElement.hasAttribute(Persistent.ATTRIBUTE_NAME)
						&& attributeElement.getAttribute(Persistent.ATTRIBUTE_NAME).equals(name)) {
					final NodeList value = attributeElement.getElementsByTagName(Persistent.ELEMENT_VALUE);
					for (int valueIndex = 0, valueCount = value.getLength(); valueIndex < valueCount; valueIndex++) {
						final Element valueElement = (Element) value.item(valueIndex);
						Integer index = null;
						try {
							index = new Integer(valueElement.getAttribute(Persistent.ATTRIBUTE_INDEX));
						} catch (final NumberFormatException nfexSwallow) {
							index = new Integer(valueIndex);
						}
						while (values.keySet().contains(index)) {
							index = new Integer(index.intValue() + 1);
						}
						valueElement.setAttribute(Persistent.ATTRIBUTE_INDEX, index.toString());
						values.put(index, valueElement.getAttribute(Persistent.ATTRIBUTE_VALUE));
					}
				}
			}
		}
		final List<Integer> keys = new ArrayList<>(values.keySet());
		Collections.sort(keys);
		final List<String> returnValues = new ArrayList<>(keys.size());
		for (final Integer key : keys) {
			returnValues.add(values.get(key));
		}
		return returnValues;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 14, 2016 5:50:15 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public int getVersion() {
		if (0 == this.version) {
			try {
				this.version = Integer.parseInt(this.getDocument().getDocumentElement().getAttribute(Persistent.ATTRIBUTE_VERSION));
			} catch (final NumberFormatException nfexSwallow) {
				this.getLogger().log(Level.WARNING,
						String.format("Object with id %s din't have a numeric version. Setting it to zero(0).", this.getVUUID()), //$NON-NLS-1$
						nfexSwallow);
				this.version = 0;
				this.getDocument().getDocumentElement().setAttribute(Persistent.ATTRIBUTE_VERSION, String.valueOf(this.version));
			}
		}
		return this.version;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 7, 2016 10:17:33 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public VUUID getVUUID() {
		if (this.vuuid == null) {
			this.vuuid = VUUID.getVUUID(this.getDocument().getDocumentElement().getAttribute(Persistent.ATTRIBUTE_UUID));
		}
		return this.vuuid;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 25, 2016 3:04:24 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean isDestroyed() {
		return this.destroyed;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 11:16:13 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param names
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void removeValues(final List<String> names) {
		this.removeValues(names, null);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 5, 2016 6:21:55 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param names
	 * @param values
	 * @param removeOldValues
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void removeValues(final List<String> names, final Acl acl) {
		final NodeList attributesList = this.getAttributes().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_ATTRIBUTES);
		for (int attributesIndex = 0, attributesCount = attributesList.getLength(); attributesIndex < attributesCount; attributesIndex++) {
			final Element attributesElement = (Element) attributesList.item(attributesIndex);
			final String tempUUID = ((Element) attributesElement.getElementsByTagName(Persistent.ELEMENT_ACL).item(0))
					.getAttribute(Persistent.ATTRIBUTE_UUID);
			if (acl == null && Persistent.PUBLIC_ATTRIBUTE.equals(tempUUID) || acl != null && tempUUID.equals(acl.getVUUID())) {
				final NodeList attributeList = attributesElement.getElementsByTagName(Persistent.ELEMENT_ATTRIBUTE);
				for (int attributeIndex = 0, attributeCount = attributeList
						.getLength(); attributeIndex < attributeCount; attributeIndex++) {
					final Element attribute = (Element) attributeList.item(attributeIndex);
					if (attribute.hasAttribute(Persistent.ATTRIBUTE_NAME)
							&& names.contains(attribute.getAttribute(Persistent.ATTRIBUTE_NAME))) {
						final Node old = this.getOldData().importNode(attribute, true);
						this.getOldData().getDocumentElement().appendChild(old);
						attributesElement.removeChild(attribute);
					}
				}
			}
		}
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 24, 2016 3:19:16 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void removeValues(final String name) {
		this.removeValues(Arrays.asList(new String[] { name }));
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 11:17:42 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void removeValues(final String name, final Acl acl) {
		this.removeValues(Arrays.asList(new String[] { name }), acl);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 12:34:15 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param theAttributes
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void setExtraAttributes(final Document theAttributes) {
		if (this.attributes == null) {
			try {
				this.attributes = Persistent.BUILDER.parse(new InputSource(new StringReader('<' + Persistent.ELEMENT_PROPERTIES + "/>"))); //$NON-NLS-1$
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		final String attributesName = theAttributes.getDocumentElement().getNodeName();
		final NodeList elements = this.getAttributes().getDocumentElement().getElementsByTagName(attributesName);
		for (int index = 0, count = elements.getLength(); index < count; index++) {
			final Node oldNode = elements.item(index);
			final Node node = this.getOldData().importNode(oldNode, true);
			this.getOldData().getDocumentElement().appendChild(node);
		}
		final Node node = this.getAttributes().importNode(theAttributes.getDocumentElement(), true);
		this.getAttributes().getDocumentElement().appendChild(node);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 4, 2016 1:07:03 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param aName
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void setName(final String aName) {
		this.getOldData().getDocumentElement().setAttribute(Persistent.ATTRIBUTE_NAME, this.getName());
		this.getDocument().getDocumentElement().setAttribute(Persistent.ATTRIBUTE_NAME, aName);
		this.objectName = null;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 5, 2016 6:46:35 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @param value
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void setValue(final String name, final String value) {
		this.setValues(name, Arrays.asList(new String[] { value }), null);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 5, 2016 6:46:40 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @param value
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void setValue(final String name, final String value, final Acl acl) {
		this.setValues(name, Arrays.asList(new String[] { value }), acl);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 11:10:01 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param values
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void setValues(final Map<String, List<String>> values) {
		this.setValues(values, true, null);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 11:12:00 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param values
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void setValues(final Map<String, List<String>> values, final Acl acl) {
		this.setValues(values, true, acl);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 5, 2016 6:46:51 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @param values
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void setValues(final String name, final List<String> values) {
		this.setValues(name, values, true, null);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 5, 2016 6:46:45 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @param values
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public void setValues(final String name, final List<String> values, final Acl acl) {
		this.setValues(name, values, true, acl);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 2, 2016 6:32:01 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	@Override
	public String toString() {
		final StringBuilder toString = new StringBuilder("<object>\n"); //$NON-NLS-1$
		if (this.getDocument() != null) {
			toString.append(Persistent.toString(this.getDocument().getDocumentElement())).append('\n');
		}
		if (this.getAttributes() != null) {
			toString.append(Persistent.toString(this.getAttributes().getDocumentElement())).append('\n');
		}
		toString.append("</object>"); //$NON-NLS-1$
		return toString.toString();
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 4, 2016 2:21:37 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected Document getAttributes() {
		if (this.attributes == null) {
			if (this.attributesXML == null) {
				this.getLogger().log(Level.INFO, String.format("%s Object's attributes not retrieved from the server.", this.getVUUID())); //$NON-NLS-1$
			} else {
				try {
					this.attributes = Persistent.BUILDER.parse(new InputSource(new StringReader(this.attributesXML)));
					this.attributesXML = null;
				} catch (final Exception ex) {
					final String message = String.format("Failed to parse document from %s.", this.attributesXML); //$NON-NLS-1$
					this.getLogger().log(Level.WARNING, message, ex);
					throw new RuntimeException(message, ex);
				}
			}
		}
		return this.attributes;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 2, 2016 6:31:46 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected Document getDocument() {
		if (this.document == null) {
			try {
				if (this.documentXML == null) {
					final String message = "Cannot parse document from null."; //$NON-NLS-1$
					this.getLogger().log(Level.WARNING, message);
					throw new RuntimeException(message);
					// return null;
				}
				this.document = Persistent.BUILDER.parse(new InputSource(new StringReader(this.documentXML)));
				this.documentXML = null;
			} catch (final Exception ex) {
				final String message = String.format("Failed to parse document from %s.", this.documentXML); //$NON-NLS-1$
				this.getLogger().log(Level.WARNING, message, ex);
				throw new RuntimeException(message, ex);
			}
		}
		return this.document;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 12, 2016 7:24:57 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected final Document getNewData() {
		if (this.newData == null) {
			try {
				this.newData = Persistent.BUILDER.parse(new InputSource(new StringReader(this.newXML)));
				this.newXML = null;
			} catch (final Exception ex) {
				final String message = String.format("Failed to parse document from %s.", this.newXML); //$NON-NLS-1$
				this.getLogger().log(Level.WARNING, message, ex);
				throw new RuntimeException(message, ex);
			}
		}
		return this.newData;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 12, 2016 7:25:01 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected final Document getOldData() {
		if (this.oldData == null) {
			try {
				this.oldData = Persistent.BUILDER.parse(new InputSource(new StringReader(this.oldXML)));
				this.oldXML = null;
			} catch (final Exception ex) {
				final String message = String.format("Failed to parse document from %s.", this.oldXML); //$NON-NLS-1$
				this.getLogger().log(Level.WARNING, message, ex);
				throw new RuntimeException(message, ex);
			}
		}
		return this.oldData;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 12, 2016 11:46:34 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris)
	 * @see Observable#setChanged() */
	@Override
	protected synchronized void setChanged() {
		super.setChanged();
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2016 6:59:56 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param events
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected void setHistory(final List<Audit> events) {
		this.history = events;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 19, 2016 11:08:32 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param values
	 * @param removeOldValues
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected void setValues(final Map<String, List<String>> values, final boolean removeOldValues, final Acl acl) {
		this.setValues(values, removeOldValues, acl, true);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Sep 29, 2016 1:59:42 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param values
	 * @param removeOldValues
	 * @param acl
	 * @param keepDuplicates
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected void setValues(final Map<String, List<String>> values, final boolean removeOldValues, final Acl acl,
			final boolean keepDuplicates) {
		final NodeList attributesList = this.getAttributes().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_ATTRIBUTES);
		Element attributesElement = null;
		for (int attributesIndex = 0, attributesCount = attributesList.getLength(); attributesIndex < attributesCount; attributesIndex++) {
			final Element temp = (Element) attributesList.item(attributesIndex);
			final String tempUUID = ((Element) temp.getElementsByTagName(Persistent.ELEMENT_ACL).item(0))
					.getAttribute(Persistent.ATTRIBUTE_UUID);
			if (acl == null && Persistent.PUBLIC_ATTRIBUTE.equals(tempUUID) || acl != null && tempUUID.equals(acl.getVUUID())) {
				attributesElement = temp;
				break;
			}
		}
		boolean notCreated = true;
		if (attributesElement == null) {
			notCreated = false;
			attributesElement = this.getAttributes().createElement(Persistent.ELEMENT_ATTRIBUTES);
			attributesElement.setAttribute(Persistent.ATTRIBUTE_UUID,
					VUUID.newVUIID(this.getVUUID().getRepository(), ObjectType.ATTRIBUTES).toString());
			attributesElement.setAttribute(Persistent.ATTRIBUTE_TARGET, this.getVUUID().toString());
			attributesElement.setAttribute(Persistent.ATTRIBUTE_NAME, this.getName());
			final Element aclElement = this.getAttributes().createElement(Persistent.ELEMENT_ACL);
			if (acl == null) {
				aclElement.setAttribute(Persistent.ATTRIBUTE_UUID, "*"); //$NON-NLS-1$
				aclElement.setAttribute(Persistent.ATTRIBUTE_NAME, "Public"); //$NON-NLS-1$
			} else {
				aclElement.setAttribute(Persistent.ATTRIBUTE_UUID, acl.getVUUID().toString());
				aclElement.setAttribute(Persistent.ATTRIBUTE_NAME, acl.getName());

			}
			attributesElement.appendChild(aclElement);
			this.getAttributes().getDocumentElement().appendChild(attributesElement);
		}
		for (final String name : values.keySet()) {
			Element attribute = null;
			if (notCreated) {
				final NodeList attributeList = attributesElement.getElementsByTagName(Persistent.ELEMENT_ATTRIBUTE);
				for (int attributeIndex = 0, attributeCount = attributeList.getLength(); attributeIndex < attributeCount
						&& attribute == null; attributeIndex++) {
					final Element temp = (Element) attributeList.item(attributeIndex);
					if (temp.hasAttribute(Persistent.ATTRIBUTE_NAME) && temp.getAttribute(Persistent.ATTRIBUTE_NAME).equals(name)) {
						attribute = temp;
					}
				}
			}
			if (attribute == null) {
				notCreated = false;
				attribute = this.getAttributes().createElement(Persistent.ELEMENT_ATTRIBUTE);
				attribute.setAttribute(Persistent.ATTRIBUTE_NAME, name);
				attributesElement.appendChild(attribute);
			}
			final List<String> storedValues = new ArrayList<>();
			int index = 0;
			if (removeOldValues) {
				if (notCreated) {
					final Node old = this.getOldData().importNode(attribute, true);
					this.getOldData().getDocumentElement().appendChild(old);
				}
				while (attribute.hasChildNodes()) {
					attribute.removeChild(attribute.getFirstChild());
				}
			} else {
				final NodeList attributeValues = attribute.getElementsByTagName(Persistent.ELEMENT_VALUE);
				for (final int count = attributeValues.getLength(); index < count; index++) {
					final Element valueElement = (Element) attributeValues.item(index);
					if (valueElement.hasAttribute(Persistent.ATTRIBUTE_VALUE)) {
						storedValues.add(valueElement.getAttribute(Persistent.ATTRIBUTE_VALUE));
					}
				}
			}
			for (final String value : values.get(name)) {
				if (keepDuplicates || !storedValues.contains(value)) {
					final Element valueElement = this.getAttributes().createElement(Persistent.ELEMENT_VALUE);
					valueElement.setAttribute(Persistent.ATTRIBUTE_INDEX, String.valueOf(storedValues.size()));
					valueElement.setAttribute(Persistent.ATTRIBUTE_VALUE, value);
					attribute.appendChild(valueElement);
					storedValues.add(value);
				}
			}
			final Node newNode = this.getNewData().importNode(attribute, true);
			this.getNewData().getDocumentElement().appendChild(newNode);
		}
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 24, 2016 3:11:04 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param name
	 * @param values
	 * @param removeOldValues
	 * @param acl
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected void setValues(final String name, final List<String> values, final boolean removeOldValues, final Acl acl) {
		this.setValues(new HashMap<String, List<String>>() {
			/** <p>
			 * <ul>
			 * <li>Created: Aug 19, 2016 11:07:44 AM</li>
			 * <li>Author: Christopher Harper, account: chris</li>
			 * <li><code>serialVersionUID = -2154098902376265492L;</code></li>
			 * </ul>
			 * </p>
			 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
			 *        (built by: chris) */
			private static final long serialVersionUID = -2154098902376265492L;
			{
				this.put(name, values);
			}
		}, removeOldValues, acl);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 4, 2016 6:43:39 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param aVersion
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	protected void setVersion(final int aVersion) {
		this.getDocument().getDocumentElement().setAttribute(Persistent.ATTRIBUTE_VERSION, String.valueOf(aVersion));
		this.version = aVersion;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 4, 2016 2:21:28 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param newOwner
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	void changeOwner(final Accessor newOwner) {
		final Element oldOwner;
		final NodeList owners = this.getOldData().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_OWNER);
		if (owners.getLength() == 0) {
			oldOwner = this.getOldData().createElement(Persistent.ELEMENT_OWNER);
			this.getOldData().getDocumentElement().appendChild(oldOwner);
		} else {
			oldOwner = (Element) owners.item(0);
		}
		oldOwner.setAttribute(Persistent.ATTRIBUTE_NAME, this.getOwnerName());
		oldOwner.setAttribute(Persistent.ATTRIBUTE_UUID, this.getOwnerUUID().toString());
		this.getOwnerElement().setAttribute(Persistent.ATTRIBUTE_UUID, newOwner.getVUUID().toString());
		this.getOwnerElement().setAttribute(Persistent.ATTRIBUTE_NAME, newOwner.getName());
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 25, 2016 3:18:14 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	abstract Persistent copy();

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 25, 2016 3:04:29 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param isDestroyed
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	void destroyed(final boolean isDestroyed) {
		this.destroyed = isDestroyed;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 3, 2016 4:08:18 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param theAttributes
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	void setAttributes(final Document theAttributes) {
		if (this.attributes == null) {
			this.attributes = theAttributes;
		} else {
			/* Remove the current attributes if any. */
			NodeList attributesElements = this.attributes.getDocumentElement().getElementsByTagName(Persistent.ELEMENT_ATTRIBUTES);
			for (int index = 0, count = attributesElements.getLength(); index < count; index++) {
				final Node attributesElement = attributesElements.item(index);
				final Node node = this.getOldData().importNode(attributesElement, true);
				this.getOldData().getDocumentElement().appendChild(node);
				this.attributes.getDocumentElement().removeChild(attributesElement);
			}
			/* Add the new attributes. */
			attributesElements = theAttributes.getDocumentElement().getElementsByTagName(Persistent.ELEMENT_ATTRIBUTES);
			for (int index = 0, count = attributesElements.getLength(); index < count; index++) {
				final Node attributesElement = attributesElements.item(index);
				final Node node = this.attributes.importNode(attributesElement, true);
				this.attributes.getDocumentElement().appendChild(node);
			}
		}
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Aug 13, 2016 10:32:58 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param aDocument
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	void setDocument(final Document aDocument) {
		this.document = aDocument;
		this.modified = null;
		this.modifierName = null;
		this.modifierUUID = null;
		this.objectName = null;
		this.ownerName = null;
		this.ownerVUUID = null;
		this.version = 0;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 2:25:12 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param user
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	void setModified(final User user) {
		final Element modify = this.getModify();
		final NodeList modifiers = modify.getElementsByTagName(Persistent.ELEMENT_MODIFIER);
		final Element modifier;
		if (modifiers.getLength() > 0) {
			modifier = (Element) modifiers.item(0);
		} else {
			modifier = this.getDocument().createElement(Persistent.ELEMENT_MODIFIER);
			modify.appendChild(modifier);
		}
		modifier.setAttribute(Persistent.ATTRIBUTE_NAME, user.getName());
		modifier.setAttribute(Persistent.ATTRIBUTE_UUID, user.getVUUID().toString());
		final NodeList modifieds = modify.getElementsByTagName(Persistent.ELEMENT_MODIFIED);
		final Element changed;
		if (modifieds.getLength() > 0) {
			changed = (Element) modifieds.item(0);
		} else {
			changed = this.getDocument().createElement(Persistent.ELEMENT_MODIFIED);
			modify.appendChild(changed);
		}
		final long millis = System.currentTimeMillis();
		changed.setAttribute(Persistent.ATTRIBUTE_TIME, Persistent.TIMESTAMP.format(new Date(millis)));
		changed.setAttribute(Persistent.ATTRIBUTE_MILLIS, String.valueOf(millis));
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 12, 2016 6:29:20 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private Element getCreate() {
		try {
			return (Element) this.getDocument().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_CREATE).item(0);
		} catch (final Throwable t) {
			final String message = String.format("Failure returning %s element.", Persistent.ELEMENT_CREATE); //$NON-NLS-1$
			this.getLogger().log(Level.WARNING, message, t);
			throw new RuntimeException(message, t);
		}
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 16, 2016 11:23:04 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private Logger getLogger() {
		if (this.logger == null) {
			this.logger = Logger.getLogger(Persistent.class.getName());
		}
		return this.logger;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 12, 2016 2:39:19 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private Element getModify() {
		final NodeList modifyElements = this.getDocument().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_MODIFY);
		if (modifyElements.getLength() > 0) {
			return (Element) modifyElements.item(0);
		}
		final Element modify = this.getDocument().createElement(Persistent.ELEMENT_MODIFY);
		this.getDocument().getDocumentElement().appendChild(modify);
		return modify;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 4, 2016 2:48:46 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private Element getOwnerElement() {
		final NodeList owners = this.getDocument().getDocumentElement().getElementsByTagName(Persistent.ELEMENT_OWNER);
		if (owners.getLength() > 0) {
			return (Element) owners.item(0);
		}
		final Element element = this.getDocument().createElement(Persistent.ELEMENT_OWNER);
		this.getDocument().getDocumentElement().appendChild(element);
		element.setAttribute(Persistent.ATTRIBUTE_NAME, General.EMPTY);
		element.setAttribute(Persistent.ATTRIBUTE_UUID, VUUID.NULL_ID.toString());
		return element;
	}

}
