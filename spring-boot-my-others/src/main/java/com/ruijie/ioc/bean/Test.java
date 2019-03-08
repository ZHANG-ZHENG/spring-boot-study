package com.ruijie.ioc.bean;

import com.ruijie.ioc.dao.annotation.Join;



public class Test extends BaseBean{


    private Integer id;

    private String remark;
    
    private Integer myTestId;
    
    @Join(thisId="myTestId",thatId="myTestId")
    private MyTest myTest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	public Integer getMyTestId() {
		return myTestId;
	}

	public void setMyTestId(Integer myTestId) {
		this.myTestId = myTestId;
	}

	public MyTest getMyTest() {
		return myTest;
	}

	public void setMyTest(MyTest myTest) {
		this.myTest = myTest;
	}


 
}