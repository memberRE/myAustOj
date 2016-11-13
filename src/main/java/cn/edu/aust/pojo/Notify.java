package cn.edu.aust.pojo;

public class Notify {
    private Integer notifyId;

    private String notifycontent;

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifycontent() {
        return notifycontent;
    }

    public void setNotifycontent(String notifycontent) {
        this.notifycontent = notifycontent == null ? null : notifycontent.trim();
    }
}