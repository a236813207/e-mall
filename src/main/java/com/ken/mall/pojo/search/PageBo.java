package com.ken.mall.pojo.search;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 * 
 * 
 * @version 1.0
 */
public class PageBo<T> implements Serializable {

	private static final long serialVersionUID = -2053800594583879853L;

	/** 内容 */
	private final List<T> content = new ArrayList<T>();

	/** 总记录数 */
	private final long total;

	/** 分页信息 */
	private final PageRequestBo pageable;

	/**
	 * 初始化一个新创建的Page对象
	 */
	public PageBo() {
		this.total = 0L;
		this.pageable = new PageRequestBo();
	}

	/**
	 * @param content
	 *            内容
	 * @param total
	 *            总记录数
	 * @param pageable
	 *            分页信息
	 */
	public PageBo(List<T> content, long total, PageRequestBo pageable) {
		this.content.addAll(content);
		this.total = total;
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
		return (int) Math.ceil((double) getTotal() / (double) getRows());
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public long getTotal() {
		return total;
	}

}