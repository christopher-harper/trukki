package fi.sardion.varasto.data;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fi.sardion.varasto.data.VUUID.ObjectType;

/** <p>
 * TODO: Write a brief description of the type here.
 * </p>
 * <p>
 * <ul>
 * <li>Created: Jul 3, 2016 2:32:23 PM</li>
 * <li>Project: Communication</li>
 * <li>File: fi.wn.jasper.data.User</li>
 * </ul>
 * </p>
 * @author Christopher Harper, account: chris
 * @version %version%
 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
 *        (built by: chris) */
public class User extends Accessor {
	/** <p>
	 * TODO: Write a brief description of the type here.
	 * </p>
	 * <p>
	 * <ul>
	 * <li>Created: Jul 8, 2016 4:56:45 PM</li>
	 * <li>Project: Communication</li>
	 * <li>File: fi.wn.jasper.data.UserPrivilege</li>
	 * </ul>
	 * </p>
	 * @author Christopher Harper, account: chris
	 * @version %version%
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public enum UserPrivilege {
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:40:07 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>all = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		all,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:36:23 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>all_objects = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		all_objects,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:36:27 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>change_owner = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		change_owner,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:35:58 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>create_acl = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		create_acl,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:19:35 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>create_container = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		create_container,

		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:19:43 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>create_document = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		create_document,

		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:19:52 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>create_file = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		create_file,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:36:38 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>create_group = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		create_group,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:36:54 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>create_root = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		create_root,

		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:19:17 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>create_storage = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		create_storage,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:36:59 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>create_user = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		create_user,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 20, 2016 10:31:28 AM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>destroy_acl = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		destroy_acl,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:18:41 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>destroy_container = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		destroy_container,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:18:52 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>destroy_document = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		destroy_document,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:37:06 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>destroy_group = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		destroy_group,

		/** <p>
		 * <ul>
		 * <li>Created: Jul 20, 2016 10:39:04 AM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>destroy_root = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		destroy_root,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:18:26 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>destroy_storage = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		destroy_storage,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:37:14 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>destroy_user = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		destroy_user,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:18:09 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>modify_acl = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		modify_acl,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:18:03 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>modify_container = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		modify_container,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:17:44 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>modify_document = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		modify_document,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:17:38 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>modify_file = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		modify_file,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:37:25 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>modify_group = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		modify_group,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:17:08 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>modify_storage = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		modify_storage,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 24, 2016 2:17:01 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>modify_system = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		modify_system,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:37:30 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>modify_user = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		modify_user,
		/** <p>
		 * <ul>
		 * <li>Created: Jul 10, 2016 2:37:38 PM</li>
		 * <li>Author: Christopher Harper, account: chris</li>
		 * <li><code>view_history = </code></li>
		 * </ul>
		 * </p>
		 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
		 *        (built by: chris) */
		view_history,
	}

	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 3:18:14 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_PRIVILEGE = "privilege";</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String	ELEMENT_PRIVILEGE	= "privilege";					//$NON-NLS-1$
	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 3:18:29 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>ELEMENT_PRIVILEGES = User.ELEMENT_PRIVILEGE + 's';</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final String	ELEMENT_PRIVILEGES	= User.ELEMENT_PRIVILEGE + 's';
	/** <p>
	 * <ul>
	 * <li>Created: Jul 3, 2016 2:32:36 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>serialVersionUID = 7594274865108822319L;</code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private static final long	serialVersionUID	= 7594274865108822319L;

	public static final void main(final String... arguments) {
		for (final ObjectType type : ObjectType.values()) {
			for (final String operation : new String[] { "modify_", "create_", "destroy_" }) {
				try {
					final UserPrivilege privilege = UserPrivilege.valueOf(operation + type);
					System.out.println("OK  : " + privilege);
				} catch (final Exception ex) {
					System.out.println("NOK : " + operation + type);
				}
			}
		}
	}

	/** <p>
	 * <ul>
	 * <li>Created: Jul 4, 2016 3:15:43 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * <li><code>privileges = </code></li>
	 * </ul>
	 * </p>
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private List<UserPrivilege> privileges = null;

	/** <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: Jul 3, 2016 2:32:18 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param documentInfo
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	User(final String documentInfo) {
		super(documentInfo);
	}

	/** <p>
	 * Sole constructor.
	 * <ul>
	 * <li>Created: Jul 16, 2016 3:56:51 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param user
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private User(final User user) {
		super(user);
		this.privileges = user.privileges;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 14, 2016 6:45:24 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean canChangeOwner() {
		return this.can(UserPrivilege.change_owner);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 24, 2016 11:59:17 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param type
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean canCreate(final ObjectType type) {
		return this.can(UserPrivilege.valueOf("create_" + type)); //$NON-NLS-1$
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jun 8, 2016 2:08:36 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean canCreateRoot() {
		return this.can(UserPrivilege.create_root);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 20, 2016 10:30:50 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param type
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean canDestroy(final ObjectType type) {
		return this.can(UserPrivilege.valueOf("destroy_" + type)); //$NON-NLS-1$
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 24, 2016 11:47:17 AM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean canDestroyRoot() {
		return this.can(UserPrivilege.destroy_root);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 18, 2016 5:32:05 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @param type
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean canModify(final ObjectType type) {
		return this.can(UserPrivilege.valueOf("modify_" + type)); //$NON-NLS-1$
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 10, 2016 2:35:33 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public boolean canViewHistory() {
		return this.can(UserPrivilege.view_history);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 24, 2016 12:01:47 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	@Override
	public Persistent copy() {
		return new User(this);
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: May 8, 2016 6:51:31 PM</li>
	 * <li>Author: Christopher Harper, account: dmadmin</li>
	 * </ul>
	 * </p>
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	public List<UserPrivilege> getPrivileges() {
		if (this.privileges == null) {
			final NodeList nodes = ((Element) this.getDocument().getElementsByTagName(User.ELEMENT_PRIVILEGES).item(0))
					.getElementsByTagName(User.ELEMENT_PRIVILEGE);
			final int count = nodes == null ? 0 : nodes.getLength();
			this.privileges = new ArrayList<>(count);
			for (int index = 0; index < count && nodes != null; index++) {
				final Element privilege = (Element) nodes.item(index);
				final String name = privilege.getAttribute(Persistent.ATTRIBUTE_NAME);
				this.privileges.add(UserPrivilege.valueOf(name));
			}
		}
		return this.privileges;
	}

	/** <p>
	 * TODO Write a method comment.
	 * <ul>
	 * <li>Created: Jul 4, 2016 3:19:02 PM</li>
	 * <li>Author: Christopher Harper, account: chris</li>
	 * </ul>
	 * </p>
	 * @param privilege
	 * @return
	 * @since 0.0.1-SNAPSHOT-b1-24.10.2016 15:41:48.273
	 *        (built by: chris) */
	private boolean can(final UserPrivilege privilege) {
		return this.getPrivileges().contains(privilege) || this.getPrivileges().contains(UserPrivilege.all);
	}

}
