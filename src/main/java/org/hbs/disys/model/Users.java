package org.hbs.disys.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "usUsersType", discriminatorType = DiscriminatorType.STRING)
public class Users extends CommonUsersBase {

	private static final long serialVersionUID = 8724774711417136041L;

	public Users() {
		super();
	}

}
