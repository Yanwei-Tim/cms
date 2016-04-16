package com.hzjava.monitorcenter.domain;

/**
 * Created with IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-8-10
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
public class SysQueryService {
    Long id;
    String idSystem;
    String objectName;
    String ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(String idSystem) {
        this.idSystem = idSystem;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
