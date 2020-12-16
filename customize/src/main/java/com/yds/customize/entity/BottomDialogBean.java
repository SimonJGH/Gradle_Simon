package com.yds.customize.entity;

/**
 * @author YDS
 * @date 2020/12/16
 * @discribe 底部对话框实体类
 */
public class BottomDialogBean {
    private int iconId;
    private String iconMsg;

    public BottomDialogBean(int iconId, String iconMsg) {
        this.iconId = iconId;
        this.iconMsg = iconMsg;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getIconMsg() {
        return iconMsg;
    }

    public void setIconMsg(String iconMsg) {
        this.iconMsg = iconMsg;
    }

    @Override
    public String toString() {
        return "BottomDialogBean{" +
                "iconId=" + iconId +
                ", iconMsg='" + iconMsg + '\'' +
                '}';
    }

}
