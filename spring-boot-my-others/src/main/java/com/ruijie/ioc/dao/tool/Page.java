
package com.ruijie.ioc.dao.tool;

import java.util.List;



public class Page<T> implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	

	private Integer page = 1;

    private Integer pageSize = 10;  //每页显示多少条

    private Integer total = 0;  //总行数

    private Integer totalPage = 0;  //总页数
    //private Integer[] perPageOption =  new Integer[]{1,2,5,10,20,50,100,150,200};

    private List<T> pagelist;
    //private String pageSpliter;//分页条HTML
    
    public Page(){
    	
    }
    public Page(Integer currentPage,Integer numPerPage){
		if(currentPage !=null){
			this.page=currentPage;//默认第一页
		}
		if(numPerPage != null){
			this.pageSize=numPerPage;//默认每页10条
		}

    }

    
    public List<T> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<T> pagelist) {
		this.pagelist = pagelist;
	}

	public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    



}

