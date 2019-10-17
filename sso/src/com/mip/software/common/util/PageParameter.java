package com.mip.software.common.util;

/**
 * 分页参数类
 * 
 */
public class PageParameter {

    private Integer currentPage = 1; // 当前页
	private Integer count = 0; // 总行数
	private Integer pageSize = 10; // 页大小
	private Integer totalPage = 0; // 总页数
	private Integer pageOffset = 0;// 当前页起始记录
	private Integer pageTail = 0; // 当前页到达的记录
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}
	public Integer getPageTail() {
		return pageTail;
	}
	public void setPageTail(Integer pageTail) {
		this.pageTail = pageTail;
	}
}
