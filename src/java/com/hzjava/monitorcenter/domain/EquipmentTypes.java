package com.hzjava.monitorcenter.domain;

/**
 * 设备类型
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="equipment_types"
 */
public class EquipmentTypes {
	/**
	 * 设备类型编号
	 *
	 * @hibernate.id column="type_code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String typeCode;

	/**
	 * 设备名称
	 *
	 * @hibernate.property column="tpye_name" type="java.lang.String"
	 */
	String typeName;

	public EquipmentTypes() {
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
