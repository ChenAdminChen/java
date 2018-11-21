package cas.oauth2.test;

//import org.springframework.security.access.prepost.PreAuthorize;


//value-name: company
//PreAuthorize("hasAuthority('xxx.xxx.xxx')") xxx.xxx.xxx => [parent-module-name...].module-name.authority(read/write)

//PermissionDependency({"xxx.xxx"}) xxx.xxx => [dependency-module-name.authority...]

import com.yf.af.anno.Service;

@Service(System.value)
public class System {

    private String serviceName ;

    public static  final String value = "test1";

    public System() {
        this.serviceName = value;
    }

    public String getServiceName() {
        return serviceName;
    }
}
