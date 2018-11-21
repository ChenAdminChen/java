package cas.oauth2.test;


import org.springframework.security.access.prepost.PreAuthorize;

//
//@PermissionModule(id = "company",name = "公司管理",parent = "device",dependencies = {
//        @PermissionDependency(value = "industry",permission = PermissionRequest.Permission.Read),
//        @PermissionDependency(value = "label",permission = PermissionRequest.Permission.Read)
//})
//value-name: company
//@PermissionDependency(value = {"company.industry.read", "company.label.read"})
public class TestPermission2 {

    @PreAuthorize("hasAuthority('company.user.read')")
    public void getString() {
    }

    @PreAuthorize("hasAuthority('company.user.write')")
//    @PermissionDependency({"user.read"})
    public void getString1() {
    }

    @PreAuthorize("hasAuthority('company.user.new')")
    public void getString2() {
    }

    @PreAuthorize("hasAuthority('company.user.new')")
    public void getString3() {
    }
}
