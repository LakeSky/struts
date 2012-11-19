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

@Entity
@Table(name = "course_department")
public class Department {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	@Column(name = "name")
	private String Name;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private User Manager;
	@OneToMany(mappedBy = "department", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<User> staffs = new HashSet<User>();
	@OneToMany(mappedBy = "exedep", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<Task> tasks = new HashSet<Task>();
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public User getManager() {
		return Manager;
	}
	public void setManager(User manager) {
		Manager = manager;
	}
	
	public Set<User> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<User> staffs) {
		this.staffs = staffs;
	}
	
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	
}
