package com.spring.boot.cas.casclient.config;

import com.spring.boot.cas.casclient.service.CustomUserDetailsService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorageImpl;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.authentication.EhCacheBasedTicketCache;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.cas.web.authentication.ServiceAuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

//securedEnabled =true @secured()
// prePostEnabled = true

//可查看 spring security中的
//        34.3.3 Authenticating to a Stateless Service with CAS
//        34.3.4 Proxy Ticket Authentication
@Configuration
@EnableWebSecurity //启用web权限
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //启用方法验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CasProperties casProperties;

    @Autowired
    private ProxyGrantingTicketStorage proxyGrantingTicketStorage;

    @Autowired
    private EhCacheBasedTicketCache ehCacheBasedTicketCache;

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

        auth.authenticationProvider(casAuthenticationProvider(proxyGrantingTicketStorage, ehCacheBasedTicketCache));

        //inMemoryAuthentication 从内存中获取
        //auth.inMemoryAuthentication().withUser("chengli").password("123456").roles("USER")
        //.and().withUser("admin").password("123456").roles("ADMIN");

        //jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构
        //usersByUsernameQuery 指定查询用户SQL
        //authoritiesByUsernameQuery 指定查询权限SQL
        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);

        //注入userDetailsService，需要实现userDetailsService接口
        //auth.userDetailsService(userDetailsService);
    }

    /**
     * 定义安全策略
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//配置安全策略
                .antMatchers("/", "/hello").permitAll()//定义/请求不需要验证
//                .anyRequest().authenticated()//其余的所有请求都需要验证
//                .permitAll()
                .and()

                .logout()
                .permitAll()//定义logout不需要验证
                .and()
                .formLogin();//使用form表单登录

        http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint())
                .and()
                .addFilter(casAuthenticationFilter(proxyGrantingTicketStorage))

                .addFilterBefore(casLogoutFilter(), LogoutFilter.class)
                .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);

        //http.csrf().disable(); //禁用CSRF
    }

    /**
     * 认证的入口
     */
    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casProperties.getCasServerLoginUrl());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

    /**
     * 指定service相关信息
     */
    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();

        serviceProperties.setService(casProperties.getAppServerUrl() + casProperties.getAppLoginUrl());

        //使用proxy时，要设置
        serviceProperties.setAuthenticateAllArtifacts(true);

        return serviceProperties;
    }

    /**
     * CAS认证过滤器
     */
    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(ProxyGrantingTicketStorage proxyGrantingTicketStorage) throws Exception {

        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();

        casAuthenticationFilter.setAuthenticationManager(authenticationManager());

        casAuthenticationFilter.setFilterProcessesUrl(casProperties.getAppLoginUrl());

        //启用代理设置
        casAuthenticationFilter.setProxyGrantingTicketStorage(proxyGrantingTicketStorage);

        casAuthenticationFilter.setProxyReceptorUrl("/login/cas/proxyreceptor");   //代理服务地址,

        casAuthenticationFilter.setServiceProperties(serviceProperties());

        //serviceAuthentication
        ServiceAuthenticationDetailsSource serviceAuthenticationDetailSource = new ServiceAuthenticationDetailsSource(serviceProperties());
        casAuthenticationFilter.setAuthenticationDetailsSource(serviceAuthenticationDetailSource);

        return casAuthenticationFilter;
    }

    /**
     * cas 认证 Provider
     */
    @Bean
    public CasAuthenticationProvider casAuthenticationProvider(ProxyGrantingTicketStorage proxyGrantingTicketStorage, EhCacheBasedTicketCache ehCacheBasedTicketCache) {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();

        casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());

        casAuthenticationProvider.setServiceProperties(serviceProperties());

        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());

        //用于无状态的访问
        Cas20ProxyTicketValidator cas20ProxyTicketValidator = new Cas20ProxyTicketValidator(casProperties.getCasServerUrl());
        cas20ProxyTicketValidator.setAcceptAnyProxy(true);

//        ProxyGrantingTicketStorageImpl proxyGrantingTicketStorage = new ProxyGrantingTicketStorageImpl();
        cas20ProxyTicketValidator.setProxyGrantingTicketStorage(proxyGrantingTicketStorage);

        cas20ProxyTicketValidator.setProxyCallbackUrl(casProperties.getProxyCallbackUrl());  //代理器返回地址

        casAuthenticationProvider.setTicketValidator(cas20ProxyTicketValidator);


        casAuthenticationProvider.setStatelessTicketCache(ehCacheBasedTicketCache);

        casAuthenticationProvider.setKey("casAuthenticationProviderKe");

        return casAuthenticationProvider;
    }

    /**
     * 用户自定义的AuthenticationUserDetailsService
     */
    @Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(casProperties.getCasServerUrl());
    }


    /**
     * 单点登出过滤器
     */
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casProperties.getCasServerUrl());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    /**
     * 请求单点退出过滤器
     */
    @Bean
    public LogoutFilter casLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(casProperties.getCasServerLogoutUrl(), new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(casProperties.getAppLogoutUrl());
        return logoutFilter;
    }

    //代理服务器使用到了ehcache2，因为需实例化该对象
    @Bean
    public EhCacheBasedTicketCache ehCacheBasedTicketCache(Cache cache) {
        EhCacheBasedTicketCache ehCacheBasedTicketCache = new EhCacheBasedTicketCache();

        ehCacheBasedTicketCache.setCache(cache);

        return ehCacheBasedTicketCache;
    }

    //初始化时调用Cache中的 initialise() ,结束时调用dispose()
    @Bean(initMethod = "initialise",destroyMethod = "dispose")
    public Cache cache(){
        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();

        CacheManager cacheManager = new CacheManager(configuration);

        Cache cache = new Cache("casTickets", 50, true, false, 3600, 900);
        cache.setCacheManager(cacheManager);

        return cache;
    }

    @Bean
    public ProxyGrantingTicketStorageImpl proxyGrantingTicketStorage() {
        return new ProxyGrantingTicketStorageImpl();
    }
}
