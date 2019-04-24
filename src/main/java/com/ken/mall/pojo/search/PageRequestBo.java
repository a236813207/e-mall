package com.ken.mall.pojo.search;

import java.io.Serializable;
import java.util.Objects;

/**
 * 分页信息
 * 
 * 
 * @version 1.0
 */
public class PageRequestBo implements Serializable {

	private static final long serialVersionUID = -3930180379790344299L;

	/** 默认页码 */
	private static final int DEFAULT_PAGE_NUMBER = 1;

	/** 默认每页记录数 */
	private static final int DEFAULT_PAGE_SIZE = 10;

	/** 最大每页记录数 */
	private static final int MAX_PAGE_SIZE = 1000;

	/** 页码 */
	private int page = DEFAULT_PAGE_NUMBER;

	/** 每页记录数 */
	private int rows = DEFAULT_PAGE_SIZE;


	/**
	 * 初始化一个新创建的Pageable对象
	 */
	public PageRequestBo() {
	}

	/**
	 * 初始化一个新创建的Pageable对象
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 */
	public PageRequestBo(Integer pageNumber, Integer pageSize) {
		if (pageNumber != null && pageNumber >= 1) {
			this.page = pageNumber;
		}
		if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
			this.rows = pageSize;
		}
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 设置页码
	 * 
	 * @param pageNumber
	 *            页码
	 */
	public void setPage(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		this.page = pageNumber;
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * 设置每页记录数
	 * 
	 * @param pageSize
	 *            每页记录数
	 */
	public void setRows(int pageSize) {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.rows = pageSize;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PageRequestBo)) return false;
		PageRequestBo that = (PageRequestBo) o;
		return Objects.equals(page, that.page) &&
				Objects.equals(rows, that.rows);
	}

	@Override
	public int hashCode() {
		return Objects.hash(page, rows);
	}
}