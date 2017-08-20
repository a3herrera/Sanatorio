/**
 * 
 */
package app.security;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import app.schema.embedable.Audit;
import app.schema.enumerated.UserStates;
import app.schema.persons.Person;

/**
 * @author Angel Alfaro
 * @since v.1.0
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = User.TABLE_NAME, uniqueConstraints = {
		@UniqueConstraint(columnNames = "USER_NAME", name = "CNN_UN_USER_NAME") })
@TableGenerator(name = User.SEQUENCE_NAME, table = User.SEQUENCE_NAME, pkColumnName = "USER_KEY", valueColumnName = "USER_VALUE", pkColumnValue = "USER_ID", allocationSize = 1)
public class User extends Audit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3073672547941581169L;
	public static final String TABLE_NAME = "USERS";
	public static final String SEQUENCE_NAME = "SEQ_USER_ID";

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQUENCE_NAME)
	private long ID;

	
	@Size(max = 50, min = 4, message = "{field.size.error}")
	@NotNull(message = "{field.not.null}")
	@Column(name = "USER_NAME", nullable = false, length = 50)
	private String userName;

	@NotNull(message = "{field.not.null}")
	@Size(max = 150, min = 4, message = "{field.size.error}")
	@Column(name = "PASSWORD", nullable = false, length = 150) 
	private String passWord;

	@NotNull(message = "{field.not.null}")
	@NotEmpty(message = "{field.not.null}")
	@Enumerated(EnumType.STRING)
	@Column(name = "STATE", nullable = false)
	private UserStates state;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "PROFILE_ID", updatable = true, insertable = true)
	private Profile profile;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PERSON_ID", nullable = true)
	private Person person;

	@Version
	private long version;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserStates getState() {
		return state;
	}

	public void setState(UserStates state) {
		this.state = state;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
