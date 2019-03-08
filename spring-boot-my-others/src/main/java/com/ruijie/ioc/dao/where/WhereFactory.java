package com.ruijie.ioc.dao.where;

public class WhereFactory {
	public static WhereInter getInstance(String queryType){

		WhereInter queryCondtion=null;
		try {
			queryCondtion=(WhereInter) Class.forName("com.ruijie.ioc.dao.where."+queryType).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return queryCondtion;

	}
}
