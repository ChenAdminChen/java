package com.chen.annotation.annotationdemo.controller;

import com.chen.annotation.anno.PermissionDependency;
import com.chen.annotation.anno.PermissionModule;
import com.chen.annotation.anno.PermissionRequest;
import com.chen.annotation.annotationdemo.entity.Company;

import java.util.ArrayList;
import java.util.List;

@PermissionModule(id = "company", name = "公司模块", dependencies = {
        @PermissionDependency(module = "industry", permission = PermissionRequest.Permission.Read),
        @PermissionDependency(module = "label", permission = PermissionRequest.Permission.Read)
})
public class CompanyController {
    @PermissionRequest(PermissionRequest.Permission.Read)
    //company.read
    public List<Company> getCompanys() {
      return null;
    }

    @PermissionRequest(PermissionRequest.Permission.Read)
    public Company getCompanyById() {
        return new Company(1, "company1", "test");
    }

    @PermissionRequest(PermissionRequest.Permission.Update)
    //company.write
    public Company updateCompanyById() {
        return new Company(1, "company1", "test");
    }

    //
}
