package com.hzjava.monitorcenter.entity;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-20
 * Time: 下午5:02
 * To change this template use File | Settings | File Templates.
 */
public class FileForm extends ActionForm{
    private FormFile formFile;
    public FormFile getFormFile() {
        return formFile;
    }

    public void setFormFile(FormFile formFile) {
        this.formFile = formFile;
    }
}
