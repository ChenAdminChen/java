package com.yf.af.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class Company implements Serializable{
    private Integer id;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer subIndustryId;


    private String address;

    private Integer parent;

    private Integer userId;

    private User user;

    private String phone;

    private String landline;

    private String postcode;

    private String fax;

    private String email;

    private String registerTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getSubIndustryId() {
        return subIndustryId;
    }

    public void setSubIndustryId(Integer subIndustryId) {
        this.subIndustryId = subIndustryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    @Override
	public String toString() {
		return "Company [id=" + id + ", subIndustryId="+subIndustryId+", userId=" + userId + ", address=" + address + ", name=" + name
				+ ", phone=" + phone + ", landline=" + landline + ", postcode=" + postcode + ", fax=" + fax + ", email="
				+ email + ", registerTime=" + registerTime + "]";
	}
    
}