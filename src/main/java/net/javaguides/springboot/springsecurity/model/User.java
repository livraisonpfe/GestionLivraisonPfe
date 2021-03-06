package net.javaguides.springboot.springsecurity.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
//c'est une superclasse auquelle les classes chauffeurs, admin,marchands y héritent
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "reset_token")
	private String resetToken;

	private String email;
	private String password;
	private String nomUtilisateur;

	// mapping role et user many to many
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

	private Collection<Role> roles;

	public User() {
	}

	public User(String email, String password, String nomUtilsateur) {
		this.email = email;
		this.password = password;
		this.nomUtilisateur = nomUtilsateur;
	}

	public User(String email, String password, Collection<Role> roles) {

		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	// getters et setters
	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", email='" + email + '\'' + ", password='" + "*********" + '\'' + ", roles="
				+ roles + '}';
	}
}
