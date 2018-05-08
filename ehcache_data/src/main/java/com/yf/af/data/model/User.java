package com.yf.af.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.management.relation.Role;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer comId;

    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String account;

    /**
     * 用于数据上传时自动填充明文密码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String plainPassword;

    /**
     * 从数据库获得的、加密后的密码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nickName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String photo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String date;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Byte sex;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String position;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int thirdManufactoryId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imUserId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean deleted;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int departmentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Company company;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrganizationStructure companyOrganization;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Department department;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dmName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Role> roles;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer safetyId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public OrganizationStructure getCompanyOrganization() {
        return companyOrganization;
    }

    public void setCompanyOrganization(OrganizationStructure companyOrganization) {
        this.companyOrganization = companyOrganization;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    /* public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account == null ? null : account.trim();
        }
    */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    //    @XmlAttribute(name = "password")
    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword == null ? null : plainPassword.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public int getThirdManufactoryId() {
        return thirdManufactoryId;
    }

    public void setThirdManufactoryId(int thirdManufactoryId) {
        this.thirdManufactoryId = thirdManufactoryId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getImUserId() {
        return imUserId;
    }

    public void setImUserId(String imUserId) {
        this.imUserId = imUserId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDmName() {
        return dmName;
    }

    public void setDmName(String dmName) {
        this.dmName = dmName;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public Integer getSafetyId() {
        return safetyId;
    }

    public void setSafetyId(Integer safetyId) {
        this.safetyId = safetyId;
    }



    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", comId=" + comId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", sex=" + sex +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", imUserId='" + imUserId + '\'' +
                ", departmentId=" + departmentId +
                ", company=" + company +
                ", companyOrganization=" + companyOrganization +
                ", department=" + department +
                ", roles=" + roles +
                '}';
    }
}