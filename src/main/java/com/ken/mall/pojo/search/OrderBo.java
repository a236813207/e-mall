package com.ken.mall.pojo.search;

import java.io.Serializable;
import java.util.Objects;

/**
 * 排序
 * 
 * 
 * @version 1.0
 */
public class OrderBo implements Serializable {

	private static final long serialVersionUID = -3078342809727773232L;

	/**
	 * 方向
	 */
	public enum Direction {

		/** 递增 */
		asc,

		/** 递减 */
		desc;

		/**
		 * 从String中获取Direction
		 * 
		 * @param value
		 *            值
		 * @return String对应的Direction
		 */
		public static Direction fromString(String value) {
			return Direction.valueOf(value.toLowerCase());
		}
	}

	/** 默认方向 */
	private static final Direction DEFAULT_DIRECTION = Direction.desc;

	/** 属性 */
	private String property;

	/** 方向 */
	private Direction direction = DEFAULT_DIRECTION;

	/**
	 * 初始化一个新创建的Order对象
	 */
	public OrderBo() {
	}

	/**
	 * @param property
	 *            属性
	 * @param direction
	 *            方向
	 */
	public OrderBo(String property, Direction direction) {
		this.property = property;
		this.direction = direction;
	}

	/**
	 * 返回递增排序
	 * 
	 * @param property
	 *            属性
	 * @return 递增排序
	 */
	public static OrderBo asc(String property) {
		return new OrderBo(property, Direction.asc);
	}

	/**
	 * 返回递减排序
	 * 
	 * @param property
	 *            属性
	 * @return 递减排序
	 */
	public static OrderBo desc(String property) {
		return new OrderBo(property, Direction.desc);
	}

	/**
	 * 获取属性
	 * 
	 * @return 属性
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * 设置属性
	 * 
	 * @param property
	 *            属性
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * 获取方向
	 * 
	 * @return 方向
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * 设置方向
	 * 
	 * @param direction
	 *            方向
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	/**
	 * 排序表达式
	 * @return
	 */
	public  String getOrderExpression(){
		return this.property+" "+this.direction.name();
	}

	public boolean isValid(){
		return this.property != null && !this.property.equals("");
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderBo)) return false;
		OrderBo order = (OrderBo) o;
		return Objects.equals(property, order.property) &&
				Objects.equals(direction, order.direction);
	}

	@Override
	public int hashCode() {
		return Objects.hash(property, direction);
	}
}