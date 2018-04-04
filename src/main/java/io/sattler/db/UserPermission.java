package io.sattler.db;


public class UserPermission {

    private Long user_id;
    private String user_uuid;
    private Long company_id;
    private String company_uuid;
    private Integer user_permission;

    public UserPermission(Long user_id, String user_uuid, Long company_id,
                          String company_uuid, Integer user_permission) {
        this.user_id = user_id;
        this.user_uuid = user_uuid;
        this.company_id = company_id;
        this.company_uuid = company_uuid;
        this.user_permission = user_permission;
    }

    public UserPermission() {}

    public String getCompany_uuid() {
        return company_uuid;
    }

    public Integer getUser_permission() {
        return user_permission;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getUser_uuid() {
        return user_uuid;
    }

}
