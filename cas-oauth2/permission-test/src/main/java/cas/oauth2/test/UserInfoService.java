package cas.oauth2.test;

import com.yf.af.anno.PermissionDependency;
import com.yf.af.anno.Service;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;


//value-name: company
//PreAuthorize("hasAuthority('xxx.xxx.xxx')") xxx.xxx.xxx => [parent-module-name...].module-name.authority(read/write)

//PermissionDependency({"xxx.xxx"}) xxx.xxx => [dependency-module-name.authority...]
@PermissionDependency({"test.read"})
@Service("test")
public class UserInfoService {

//    @Role("normal")
//    @PreAuthorize("hasAuthority('user.read')")
    public void getUser() {

    }

    //    @Role("admin")
//    @PreAuthorize("hasAuthority('test.read')")
    public void updateUser() {
    }

    //    @Role("admin")
//    @PreAuthorize("hasAuthority('user.test.read')")
    public void updateUser2() {
    }

//    code:test 1
//    code:test.user 2 1
//    code:test.user.extra 3 2

//    @Role("normal")
    @PreAuthorize("hasAuthority('test.user.extra.read')")
    @PermissionDependency({"specialist.read"})
    public void getFollowed() {

    }

//    @Role("normal")
//    @PreAuthorize("hasAuthority('user.extra.write')")
    @PermissionDependency({"department.read"})
    public void getDepartment() {
    }
}
