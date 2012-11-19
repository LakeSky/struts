package thettian.study.struts.application.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import thettian.study.struts.application.model.enumtype.Role;

@Entity
@Table(name = "course_user")
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	@Column(name = "username" , nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String Password;
	@Column(name = "role", nullable = false)
	private Role Role;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Department department;	
	@OneToMany(mappedBy = "exeuser", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<Task> tasks = new HashSet<Task>();
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Role getRole() {
		return Role;
	}
	public void setRole(Role role) {
		Role = role;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	
}
