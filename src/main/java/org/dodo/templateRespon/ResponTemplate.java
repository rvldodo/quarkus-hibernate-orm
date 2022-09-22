package org.dodo.templateRespon;

public class ResponTemplate {

    public Integer status;
    public String message;
    public Object object;

    public ResponTemplate(Integer status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }
}
