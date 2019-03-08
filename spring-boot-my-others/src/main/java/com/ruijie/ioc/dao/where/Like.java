package com.ruijie.ioc.dao.where;

public class Like implements WhereInter{
	@Override
	public String getSqlCondtion(String cloumn,String value){
		return " and "+cloumn+" like '%"+value+"%'";
		
	}
}
