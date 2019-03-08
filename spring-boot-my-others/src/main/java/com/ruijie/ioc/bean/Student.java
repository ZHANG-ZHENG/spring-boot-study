package com.ruijie.ioc.bean;

import com.ruijie.ioc.dao.annotation.Join;
import com.ruijie.ioc.dao.annotation.Key;
import com.ruijie.ioc.dao.annotation.Where;



public class Student extends BaseBean{
	@Key(seq="student_seq")            //create sequence student_seq increment by 1 minvalue 1 no maxvalue start with 1; 
    private Integer id;

    private String name;
    
    private String remark;
    
    private Integer classRoomId;
    
    @Join(thisId="classRoomId",thatId="id")
    private ClassRoom classRoom;
    
//    @Where(condition="MoreThan",target="id")
//    private Integer idMoreThan;
    
    @Where(condition="Like",target="remark")
    private String remarkLike;

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

	public Integer getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public String getRemarkLike() {
		return remarkLike;
	}

	public void setRemarkLike(String remarkLike) {
		this.remarkLike = remarkLike;
	}



//	public Integer getIdMoreThan() {
//		return idMoreThan;
//	}
//
//	public void setIdMoreThan(Integer idMoreThan) {
//		this.idMoreThan = idMoreThan;
//	}






 
}