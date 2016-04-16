package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import com.hzjava.monitorcenter.domain.EquipmentPosition;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-4
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public  interface EquipmentPositionDao extends BaseDao {

    public void addEquipmentPositionDao(EquipmentPosition equipmentPositionDao);

    public void updEquipmentPositionDao(EquipmentPosition equipmentPosition);

    public void Delete(EquipmentPosition  equipmentPositionDao);

    public EquipmentPosition selectById(int id);

    public ArrayList<EquipmentPosition> selectEquipmentPositionById(int id);
}
