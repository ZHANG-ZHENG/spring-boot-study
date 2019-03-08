package com.ruijie.ioc.dao.where;

public class Eq implements WhereInter{
	public String getSqlCondtion(String cloumn,String value){
		
		return " and "+cloumn+"='"+value+"'";
	}
}

