package com.yf.af.data.model;

import java.io.Serializable;

public class Department implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer companyId;

    private String cpName; //公司名字

    private String name;

    private String address;

    private Integer userId;

    private String userName;

    private Integer parentId;

    private String phone;

    private String landline;

    private String postcode;

    private String email;

    private String fax;

    private String hs;// 组织结构信息

    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline == null ? null : landline.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHs() {
        return hs;
    }

    public void setHs(String hs) {
        this.hs = hs;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", cpName='" + cpName + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                ", parentId=" + parentId +
                ", phone='" + phone + '\'' +
                ", landline='" + landline + '\'' +
                ", postcode='" + postcode + '\'' +
                ", email='" + email + '\'' +
                ", fax='" + fax + '\'' +
                ", department=" + department +
                '}';
    }
}