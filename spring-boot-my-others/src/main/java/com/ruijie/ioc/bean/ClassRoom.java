package com.ruijie.ioc.bean;

import com.ruijie.ioc.dao.annotation.Join;



public class ClassRoom extends BaseBean{

    private Integer id;

    private String name;
    
    private String remark;
    
    private Integer schoolId;
    
    @Join(thisId="schoolId",thatId="id")
    private School school;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}





 
}