package com.ruijie.ioc.dao;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruijie.ioc.bean.BaseBean;
import com.ruijie.ioc.dao.annotation.Join;
import com.ruijie.ioc.dao.annotation.Key;
import com.ruijie.ioc.dao.annotation.Where;
import com.ruijie.ioc.dao.tool.Page;
import com.ruijie.ioc.dao.where.WhereFactory;
import com.ruijie.ioc.dao.where.WhereInter;


@Repository
public class BaseDao<T>{
	
	private static String beanPacket = "com.ruijie.ioc.bean";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addObject(T object){
    	Class<?> clazz = object.getClass();
    	String tableName =java2DataBase(clazz.getName().replace(beanPacket+".", ""));
    	StringBuilder sql = new StringBuilder();
    	StringBuilder sqlColumn = new StringBuilder();
    	StringBuilder sqlValue = new StringBuilder();
    	//sql.append("nextval('student_seq'),'aaa'");
    	Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			field.setAccessible(true);
			Object fieldValue = null;
			try {
				fieldValue = field.get(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Key key = field.getAnnotation(Key.class);
			if(key!=null && !"".equals(key.seq())){
				sqlColumn.append(java2DataBase(fieldName)+",");
				sqlValue.append("nextval('"+key.seq()+"')"+",");
			}else if(fieldValue!=null && !"".equals(fieldValue.toString())){
				sqlColumn.append(java2DataBase(fieldName)+",");
				sqlValue.append("'"+fieldValue+"'"+",");
			}
			
		};
    	sql.append("INSERT into "+tableName+"("+sqlColumn.substring(0, sqlColumn.length()-1).toString()+")"+" VALUES(");
    	sql.append(sqlValue.substring(0, sqlValue.length()-1).toString());
		sql.append(")");
		System.out.println(sql.toString());
    	int resRow=jdbcTemplate.update(sql.toString());  
    	if(resRow!=1){
    		throw new RuntimeException("新增失败");
    	}
    }
    public void deleteObject(T object){
    	Class<?> clazz = object.getClass();
    	String tableName =java2DataBase(clazz.getName().replace(beanPacket+".", ""));
    	StringBuilder sql = new StringBuilder();
    	Object idName = null;
    	Object id = null;
    	Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object fieldValue = null;
			try {
				fieldValue = field.get(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Key key = field.getAnnotation(Key.class);
			if(key!=null){
				idName = field.getName();
				id=fieldValue;
				break;
			}
			
		};
    	sql.append("delete from "+tableName+" where "+idName+" = "+id);
		//System.out.println(sql.toString());
    	int resRow=jdbcTemplate.update(sql.toString());  
    	if(resRow!=1){
    		throw new RuntimeException("删除失败");
    	}
    }
    public void updateObject(T object){
    	Class<?> clazz = object.getClass();
    	String tableName =java2DataBase(clazz.getName().replace(beanPacket+".", ""));
    	StringBuilder sql = new StringBuilder();
    	StringBuilder updateSql = new StringBuilder();
    	Object idName = null;
    	Object id = null;
    	Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object fieldValue = null;
			try {
				fieldValue = field.get(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Key key = field.getAnnotation(Key.class);
			if(key!=null){
				idName = field.getName();
				id=fieldValue;
			}else if(fieldValue!=null && !"".equals(fieldValue.toString())){
				updateSql.append(java2DataBase(field.getName())+"='"+fieldValue+"',");

			}
			
		};
    	sql.append("update "+tableName+" set "+updateSql.substring(0,updateSql.length()-1)+" where "+idName+" = "+id);
		System.out.println(sql.toString());
    	int resRow=jdbcTemplate.update(sql.toString());  
    	if(resRow!=1){
    		throw new RuntimeException("更新失败");
    	}
    }    
    public T findByObject(T queryObject){
    	List<T> list = jdbcTemplateQuery(null,null,queryObject);
    	if(list.size()>1){
    		throw new RuntimeException("查询不唯一");
    	}
    	if(list.size()==0){
    		throw new RuntimeException("无查询结果");
    	}
    	return list.get(0);
    	
    }
	public Page<T> queryByObjectPage(Integer currentPage,Integer numPerPage,T queryObject){
		Page<T> page = new Page<T>(currentPage,numPerPage);
		List<T> list = jdbcTemplateQuery(currentPage,numPerPage,queryObject);
		Integer count = jdbcTemplateQueryCount(queryObject);
		page.setPagelist(list);
		page.setTotal(count);
		page.setTotalPage(count/page.getPageSize()+1);
		return page;
	}
    public List<T> queryByObject(T queryObject){
    	return jdbcTemplateQuery(null,null,queryObject);
    }
    private List<T> jdbcTemplateQuery(Integer currentPage,Integer numPerPage,T queryObject){
    	final Class<?> clazz = queryObject.getClass();
    	final T queryObjectFinal = queryObject;
    	StringBuilder sqlColumn = new StringBuilder();
    	sqlColumn(sqlColumn,queryObject,"");
    	StringBuilder sqlWhere = new StringBuilder();
    	sqlWhere(sqlWhere,queryObject,"");
    	StringBuilder join = new StringBuilder();
    	join(join,queryObject,"");
    	
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(sqlColumn.substring(0, sqlColumn.length()-1));
        sql.append(" from "+java2DataBase(clazz.getName().replace(beanPacket+".", ""))+" "+clazz.getName().replace(beanPacket+".", ""));
        sql.append(join.toString());
        sql.append(" where 1=1");
        sql.append(sqlWhere);
        if(currentPage!=null && numPerPage!=null){
        	Integer limit =numPerPage;
        	Integer offset = 0;
        	if(currentPage>1){
        		offset = numPerPage*(currentPage-1);
        	}
        	sql.append(" limit "+limit+" offset "+offset);
        }
        System.out.println(sql.toString());
        List<T> list = jdbcTemplate.query(sql.toString(), new RowMapper<T>(){
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Object obj = null;
				try {
					obj = clazz.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
		        fillRsToObj(rs, obj, queryObjectFinal,"");
                return (T) obj;
            }

        });
        return list;
    }
    private Integer jdbcTemplateQueryCount(T queryObject){
//    	final Class<?> clazz = queryObject.getClass();
//    	Field[] fields = clazz.getDeclaredFields();
//    	StringBuilder sqlWhere = new StringBuilder();
//		for (Field field : fields) {
//			String fieldName = field.getName();
//			field.setAccessible(true);
//			Object fieldValue = null;
//			try {
//				fieldValue = field.get(queryObject);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if(fieldValue!=null && !"".equals(fieldValue.toString())){
//				sqlWhere.append(" and "+java2DataBase(fieldName)+"='"+fieldValue+"'");
//			}
//			
//		};
//        StringBuilder sql = new StringBuilder();
//        sql.append("select ");
//        sql.append("count(1)");
//        sql.append(" from "+java2DataBase(clazz.getName().replace("com.ruijie.iotp.model.", "")));
//        sql.append(" where 1=1");
//        sql.append(sqlWhere);
    	Class<?> clazz = queryObject.getClass();
    	StringBuilder sqlColumn = new StringBuilder(" count(1) ");
    	StringBuilder sqlWhere = new StringBuilder();
    	sqlWhere(sqlWhere,queryObject,"");
    	StringBuilder join = new StringBuilder();
    	join(join,queryObject,"");
    	
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(sqlColumn.substring(0, sqlColumn.length()-1));
        sql.append(" from "+java2DataBase(clazz.getName().replace(beanPacket+".", ""))+" "+clazz.getName().replace(beanPacket+".", ""));
        sql.append(join.toString());
        sql.append(" where 1=1");
        sql.append(sqlWhere);
       
        System.out.println(sql.toString());
		Integer count= jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		return count;
    }

	/**
	 * 获取T的类型
	 * @return
	 */
	protected Class<?> getclassT() {
    	ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
    	//System.out.println(type.getActualTypeArguments()[0]);
    	Class<?> classT =  (Class<?>) type.getActualTypeArguments()[0];
    	return classT;
	}
	/**
	 * java字段转数据库字段
	 */
	private String java2DataBase(String str){ 
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) { 
        	char item =  str.charAt(i);
        	if(item>='A'&&item<='Z'&&i!=0){
        		sb.append("_"+item);
        	}else{
        		sb.append(item);
        	}
        }
		return sb.toString();
		
	}
	
	private void sqlColumn(StringBuilder sb,Object obj,String root){
    	Class<?> clazz = obj.getClass();
    	//String clazzShortName = java2DataBase(clazz.getName().replace(beanPacket+".", ""));
    	String clazzShortName = clazz.getName().replace(beanPacket+".", "");
    	//System.out.println(clazzShortName);
    	Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			Where where = field.getAnnotation(Where.class);
			if(where != null){
				continue;
			}
			Class<?> fieldType = field.getType();
			
			if(fieldType==String.class || fieldType==Integer.class){
				sb.append(root+clazzShortName+"."+java2DataBase(field.getName())+" as "+
						root+clazzShortName+"$"+field.getName()+",");
			}else{
				field.setAccessible(true);
				Object fieldValue = null;
				try {
					fieldValue = field.get(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(fieldValue!=null && fieldValue instanceof BaseBean){
					sqlColumn(sb,fieldValue,root+clazzShortName+"$");
				}
			}
		};
		
	}
	private void sqlWhere(StringBuilder sb,Object obj,String root){
    	Class<?> clazz = obj.getClass();
    	String clazzShortName = clazz.getName().replace(beanPacket+".", "");
    	
    	Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();	
			field.setAccessible(true);
			Object fieldValue = null;
			try {
				fieldValue = field.get(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(fieldValue==null || "".equals(fieldValue.toString())){
				continue;
			}
			if(fieldType==String.class || fieldType==Integer.class){
				//sb.append(" and "+root+clazzShortName+"."+java2DataBase(fieldName)+"='"+fieldValue+"'");
				String whereType = "Eq";
				String cloumn = fieldName;
				Where where = field.getAnnotation(Where.class);
				if(where!=null){
					whereType = where.condition();
					cloumn = where.target();
				}
				WhereInter whereInter = WhereFactory.getInstance(whereType);
				sb.append(whereInter.getSqlCondtion(root+clazzShortName+"."+java2DataBase(cloumn), fieldValue+""));
				
			}else if(fieldValue instanceof BaseBean){
				String rootSub = new String(root+clazzShortName+"$");
				sqlWhere(sb,fieldValue,rootSub);
			}
			
		};
	}
	/**
	 * 获取inner join
	 */
	private StringBuilder join(StringBuilder sb,Object obj,String root){
    	Class<?> clazz = obj.getClass();
    	String clazzShortName = clazz.getName().replace(beanPacket+".", "");
    	String tableName =java2DataBase(clazz.getName().replace(beanPacket+".", ""));
    	String rootThis = new String(root+clazzShortName);
    	String rootSub = new String(root+clazzShortName+"$");
    	Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();	
			
			field.setAccessible(true);
			Object fieldValue = null;
			try {
				fieldValue = field.get(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(fieldValue!=null && fieldValue instanceof BaseBean){
				String clazzShortNameSub = fieldType.getName().replace(beanPacket+".", "");
		    	String tableNameSub =java2DataBase(fieldType.getName().replace(beanPacket+".", ""));
		    	
				Join joinAnnotation = field.getAnnotation(Join.class);
				String thisIdDataBase=java2DataBase(joinAnnotation.thisId());
				String thatIdDataBase=java2DataBase(joinAnnotation.thatId());
				sb.append(" LEFT JOIN "+tableNameSub+" "+rootSub+clazzShortNameSub+
						" on "+rootThis+"."+thisIdDataBase+"="+rootSub+clazzShortNameSub+"."+thatIdDataBase+" ");
				join(sb,fieldValue,rootSub);
			}
		};
		return sb;
	}
	private void fillRsToObj(ResultSet rs,Object obj,Object model,String root){	
		Class<?> clazz = obj.getClass();
		String clazzShortName = clazz.getName().replace(beanPacket+".", "");
		String rootSub = new String(root+clazzShortName+"$");
    	Field[] fields = obj.getClass().getDeclaredFields();
    	for(Field field : fields){
			Where where = field.getAnnotation(Where.class);
			if(where != null){
				continue;
			}
    		field.setAccessible(true);
    		Class<?> fieldtype = field.getType();
    		String fieldName = field.getName();
    		try {
    			Object modelSub = field.get(model);
    			if(fieldtype ==  java.lang.String.class){
    				field.set(obj, rs.getString(rootSub+fieldName));
        		}else if(fieldtype == java.lang.Integer.class){
        			if (rs.getObject(rootSub+fieldName) != null) {
        				field.set(obj, rs.getInt(rootSub+fieldName));
        			} else {
        				field.set(obj, null);
        			}
        			//field.set(obj, rs.getInt(rootSub+fieldName));
        		}else if(modelSub!=null && modelSub instanceof BaseBean){
        			Object objSub = modelSub.getClass().newInstance();
        			field.set(obj, objSub);
        			fillRsToObj(rs,objSub,modelSub,rootSub);
        		}
    		} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}
	
	public void test(){
		System.out.println("test dao");
		String sql="select count(*) from test";
		int count= jdbcTemplate.queryForObject(sql, Integer.class);
	    System.out.println(count);
	}
//	public void test(){
//		String sql="select count(*) from test";
//		int count= jdbcTemplate.queryForObject(sql, Integer.class);
//	    System.out.println(count);
//	}

}
