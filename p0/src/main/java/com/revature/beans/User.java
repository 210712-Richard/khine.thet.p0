package com.revature.beans;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
	static final long serialVersionUID = 1L;
	private int id;
	private UserType type;
	private AccountType accountType;
	private String username;
	private String email;
	private LocalDate birthday;
	private String address;

	public User() {
		super();
	}
	
	public User(int id, String username, String email, LocalDate birthday, String address) {
		this();
		this.id = id;
		this.username = username;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public UserType getTypes() {
		return type;
	}
	
	public void setType(UserType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", birthday=" + birthday
				+ ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, birthday, email, id, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthday, other.birthday)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(username, other.username);
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
}
	

