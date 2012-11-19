package thettian.study.struts.application.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course_task")
public class Task {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	@Column(name = "name")
	private String Name;
	@Column(name = "status")
	private String Status;
	@Column(name = "lastTime")
	private Date LastTime;
	@Column(name = "finTime")
	private Date finTime;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Department exedep;	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private User exeuser;
	
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
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Date getLastTime() {
		return LastTime;
	}
	public void setLastTime(Date lastTime) {
		LastTime = lastTime;
	}
	public Date getFinTime() {
		return finTime;
	}
	public void setFinTime(Date finTime) {
		this.finTime = finTime;
	}
	
	public Department getExedep() {
		return exedep;
	}
	public void setExedep(Department exedep) {
		this.exedep = exedep;
	}
	
	public User getExeuser() {
		return exeuser;
	}
	public void setExeuser(User exeuser) {
		this.exeuser = exeuser;
	}
}
