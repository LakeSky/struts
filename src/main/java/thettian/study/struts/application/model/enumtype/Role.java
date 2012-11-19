package thettian.study.struts.application.model.enumtype;

public enum Role {
	ADMIN("管理员"),MANAGER("部门经理"),USER("普通职工");
	
	private final String roleType;
	
	Role(String roleType){
		this.roleType = roleType;
	}
	
	public String getRoleType(){
		return this.roleType;
	}
}
