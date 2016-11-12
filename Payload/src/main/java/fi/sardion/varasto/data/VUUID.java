package fi.sardion.varasto.data;


import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * <p>
 * TODO: Write a brief description of the type here.
 * </p>
 * <p>
 * <ul>
 * <li>Created: May 17, 2016 10:38:38 AM</li>
 * </ul>
 * </p>
 * 
 * @author Christopher Harper, account: dmadmin
 * @version %version%
 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
 */
public class VUUID extends Object implements Serializable {

	/**
	 * <p>
	 * The object type enumeration.</p>
	 * <p>
	 * <ul>
	 * <li>Created: Jun 3, 2016 6:13:18 PM</li>
	 * </ul>
	 * </p>
	 * 
	 * @author Christopher Harper, account: dmadmin
	 * @version %version%
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public enum ObjectType {
		/**
		 * <code></code>
		 */
		ACCESSOR(1)
		/**
		 * <code></code>
		 */
		, USER(3)
		/**
		 * <code></code>
		 */
		, GROUP(5)
		/**
		 * <code></code>
		 */
		, ROLE(7)
		/**
		 * <code></code>
		 */
		, SECURABLE(9)
		/**
		 * <code></code>
		 */
		, DOCUMENT(12)
		/**
		 * <code></code>
		 */
		, DIRECTORY(15)
		/**
		 * <code></code>
		 */
		, ATTRIBUTES(18)
		/**
		 * <code></code>
		 */
		, ACL(21)
		/**
		 * <code></code>
		 */
		, AUDIT(24)
		/**
		 * <code></code>
		 */
		, CONTENT(27)
		/**
		 * <code></code>
		 */
		, FILE(30)
		/**
		 * <code></code>
		 */
		, STORAGE(33)
		/**
		 * <code></code>
		 */
		, SYSTEM(36);
		/**
		 * The integer value of the type.
		 */
		private int type;

		/**
		 * The object type to set.
		 * @param aType the type number.
		 */
		private ObjectType(final int aType) {
			this.setType(aType);
		}

		/**
		 * <p>
		 * Check whether a given type is instance of something.<ul>
		 * <li>Created: Aug 5, 2016 3:35:20 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * </ul>
		 * </p>
		 * 
		 * @param type the type to check.
		 * @return true if the type is an instance of this type. This would be the exact type or a sub type of it.
		 */
		public boolean isInstance(final ObjectType type) {
			if (equals(type)) {
				return true;
			}
			if (equals(SECURABLE) && (equals(DIRECTORY) || equals(DOCUMENT))) {
				return true;
			}
			if (type.equals(ACCESSOR) && (equals(GROUP) || equals(USER) || equals(ROLE))) {
				return true;
			}
			return false;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
	}

	/**
	 * <p>
	 * <ul>
	 * <li>Created: Oct 17, 2016 10:39:55 AM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * <li><code>LOGGER = Logger.getLogger(VUUID.class.getName());</code></li>
	 * </ul>
	 * </p>
	 * 
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	private static final Logger LOGGER = Logger.getLogger(VUUID.class.getName());
	
	/**
	 * <p>
	 * <ul>
	 * <li>Created: Jun 9, 2016 4:45:42 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * <li><code>NULL_ID = </code></li>
	 * </ul>
	 * </p>
	 * 
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public static final VUUID NULL_ID = VUUID.getVUUID("mm-mmmm-00000000-0000-0000-0000-000000000000"); //$NON-NLS-1$

	/**
	 * <p>
	 * <ul>
	 * <li>Created: May 17, 2016 10:39:55 AM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * <li><code>serialVersionUID = 7509025102169208622L;</code></li>
	 * </ul>
	 * </p>
	 * 
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	private static final long serialVersionUID = 7509025102169208622L;


	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 17, 2016 5:31:46 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @param jUUID
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public static final VUUID getVUUID(final String vUUID) {
		final int separator = 10;
		final String uuid = vUUID.substring(separator);
		if(LOGGER==null){
			Logger.getLogger(VUUID.class.getName()).debug(String.format("Validating UUID %s portion of the VUUID %s.", uuid, vUUID)); //$NON-NLS-1$
		} else {
			LOGGER.debug(String.format("Validating UUID %s portion of the VUUID %s.", uuid, vUUID)); //$NON-NLS-1$			
		}
		try {
			/* This is just a check to ensure that the UUID is valid. */
			UUID.fromString(uuid);
			return new VUUID(vUUID);
		} catch (final Exception ex) {
			final String message = String.format("Failed to validate given UUID %s", uuid); //$NON-NLS-1$
			LOGGER.warn(message, ex);
			throw new RuntimeException(message, ex);
		}
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 5, 2016 9:38:03 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @param aJUUID
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public static String getRepository(final VUUID aVUUID) {
		if (aVUUID.repository == null) {
			final int start = aVUUID.toString().indexOf('-') + 1;
			final int end = aVUUID.toString().indexOf('-', start);
			final String repo = aVUUID.toString().substring(start, end);
			aVUUID.repository = repo;
		}
		return aVUUID.repository;
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 9, 2016 10:25:23 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * 
	 * @param jUUID
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public static final boolean isNullId(final VUUID jUUID) {
		return (jUUID == null) || VUUID.NULL_ID.equals(jUUID);
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 17, 2016 11:09:59 AM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @param arguments
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public static final void main(final String... arguments) {

		// final JasperSession session = new JasperSession();
		// session.setLogin("testing", "jaa", "ei", "m�hk�", 1897);
		// //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		// JUUID juuid = JUUID.newJUIID(session.getRepository(),
		// ObjectType.document);
		// System.out.println(JUUID.newJUIID(session.getRepository(),
		// ObjectType.system));
		//
		// System.out.println(JUUID.getJUIID(session.getRepository() +
		// "-null-00000000-0000-0000-0000-000000000000"));
		//
		// System.out.println(juuid);
		// juuid = JUUID.newJUIID(session.getRepository(),
		// ObjectType.attributes);
		// System.out.println(JUUID.getType(juuid));
		//
		// System.out.println(ObjectType.acl);
		// System.out.println(ObjectType.attributes);
		// System.out.println(ObjectType.audit);

		// System.out.println(new UUID(Long.parseLong("jasperacl", 32),
		// System.currentTimeMillis()).toString()); //$NON-NLS-1$
		// System.out.println(new UUID(Long.parseLong("jasperacl", 32),
		// System.currentTimeMillis()).toString()); //$NON-NLS-1$
		// System.out.println(new UUID(Long.parseLong("jasperacl", 32),
		// System.currentTimeMillis()).toString()); //$NON-NLS-1$
		// System.out.println(new UUID(Long.parseLong("jasperacl", 32),
		// System.currentTimeMillis()).toString()); //$NON-NLS-1$
		// System.out.println(new UUID(Long.parseLong("jasperacl", 32),
		// System.currentTimeMillis()).toString()); //$NON-NLS-1$

		for(int type = 499, typeCount = Integer.parseInt("mm", 23); type<=typeCount;type++){
			
			final String typeS= StringUtils.leftPad(Integer.toString(type, 23),2, '0');
			for(int repo = 99999, repoCount = Integer.parseInt("mmmmm", 23); repo<=repoCount;repo++){
				final String repoS = StringUtils.leftPad(Integer.toString(repo, 23),5, '0');;
				final String vUUID = new StringBuilder(typeS).append('-').append(repoS).append('-').append(UUID.randomUUID().toString()).toString();
				VUUID vuuid = new VUUID(vUUID);
				System.out.println(vuuid.getType());
				System.out.println(vuuid.getRepository());				
				System.out.println(vuuid.getUUID());
				System.out.println(vuuid);
			}
		}
	}

	private UUID uuid = null;
	public UUID getUUID() {
		if(this.uuid == null){
			synchronized(VUUID.class){
				final String temp = this.vUuid.substring(9);
				this.uuid = UUID.fromString( temp );
				VUUID.class.notify();
			}
		}
		return this.uuid;
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 3, 2016 6:38:01 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @param repository
	 * @param type
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public static final VUUID newVUIID(final String repository, final ObjectType type) {
		String message = null;
		if (((repository == null) || (repository.length() <= 0)) && (repository.indexOf('-') >= -1)) {
			message = String.format("Invalid repository %s given.", repository); //$NON-NLS-1$
		}
		if (type == null) {
			message = "Null type given."; //$NON-NLS-1$
		}
		if (message == null) {
			return new VUUID(repository + '-' + type + '-' + UUID.randomUUID());
		}
		LOGGER.warn(message);
		throw new RuntimeException(message);
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 7, 2016 4:51:09 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @param aVUUID
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public static String getType(final VUUID aVUUID) {
		if (aVUUID.type == null) {
			final int end = aVUUID.toString().indexOf('-');
			aVUUID.type = aVUUID.toString().substring(0, end);
		}
		return aVUUID.type;
	}

	/**
	 * <p>
	 * <ul>
	 * <li>Created: Jul 9, 2016 10:30:17 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>repository = </code></li>
	 * </ul>
	 * </p>
	 * 
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	private String repository = null;

	/**
	 * <p>
	 * <ul>
	 * <li>Created: Jul 9, 2016 10:30:25 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>type = </code></li>
	 * </ul>
	 * </p>
	 * 
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	private String type = null;

	/**
	 * <p>
	 * <ul>
	 * <li>Created: Jun 3, 2016 6:11:11 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	private final String vUuid;

	/**
	 * <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: May 17, 2016 10:38:38 AM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @param aVUUID
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	private VUUID(final String aVUUID) {
		super();
		this.vUuid = aVUUID;
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 5, 2016 9:37:17 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @param obj
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	@Override
	public boolean equals(final Object obj) {
		return toString().equals(obj.toString());
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 7, 2016 4:45:01 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public String getRepository() {
		return VUUID.getRepository(this);
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 5, 2016 9:37:39 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	public String getType() {
		return VUUID.getType(this);
	}

	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 5, 2016 9:37:07 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	/**
	 * <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 17, 2016 11:09:30 AM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
										(built by: chris)
	 */
	@Override
	public final String toString() {
		return this.vUuid;
	}
}
