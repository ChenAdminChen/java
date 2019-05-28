package com.chen.annotation.annotationdemo.controller;

import com.chen.annotation.anno.PermissionModule;
import com.chen.annotation.anno.PermissionRequest;
import com.chen.annotation.annotationdemo.entity.Company;

import java.util.ArrayList;
import java.util.List;

@PermissionModule(id = "specialist", name = "专家模块")
public class SpecialistController {
    @PermissionRequest(PermissionRequest.Permission.Read)
    public List<Company> getCompanys() {
        List list = new ArrayList<Company>();
        Company company1 = new Company(1, "company1", "test1");
        Company company2 = new Company(2, "company2", "test2");

        list.add(company1);
        list.add(company2);
        return list;
    }

    @PermissionRequest(PermissionRequest.Permission.Read)
    public Company getCompanyById() {
        return new Company(1, "company1", "test");
    }

    @PermissionRequest(PermissionRequest.Permission.Update)
    public Company updateCompanyById() {
        return new Company(1, "company1", "test");
    }
}
