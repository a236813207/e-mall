package com.ken.mall.pojo.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ken
 * @date 2019/7/28
 * @description
 */
public class PageBo<T> implements Serializable {

	private static final long serialVersionUID = -2053800594583879853L;

	/** 内容 */
	private final List<T> data = new ArrayList<T>();

	private int draw = 1;

	/** 总记录数 */
	private final long recordsTotal;

	/** 总记录数 */
	private final long recordsFiltered;

	/** 分页信息 */
	private final PageRequestBo pageable;

	/**
	 * 初始化一个新创建的Page对象
	 */
	public PageBo() {
		this.recordsTotal = 0L;
		this.recordsFiltered = 0L;
		this.pageable = new PageRequestBo();
	}

	/**
	 * @param data
	 *            内容
	 * @param recordsTotal
	 *            总记录数
	 * @param pageable
	 *            分页信息
	 */
	public PageBo(List<T> data, long recordsTotal, PageRequestBo pageable) {
		this.data.addAll(data);
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
		this.pageable = pageable;
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPage() {
		return pageable.getPage();
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getRows() {
		return pageable.getRows();
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	public int getTotalPages() {
		return (int) Math.ceil((double) getRecordsTotal() / (double) getRows());
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public long getRecordsTotal() {
		return recordsTotal;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public long getRecordsFiltered() {
		return recordsTotal;
	}


}