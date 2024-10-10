package com.zbbmeta.config;

import com.zbbmeta.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: TODO
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        //密码加密方式
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器，登录的时候参数会传给 authenticationManager
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * spring Security 配置
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                //未授权处理
                .exceptionHandling()


                .and()
                // 基于token，因此不须要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .sessionFixation().none()

                .and()
                .authorizeRequests()
                // 跨域的调用
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 把不需要认证的接口暴露出去。登录，刷新token，
                .antMatchers("/auth/**").permitAll()
                //  获取白名单（不进行权限验证）
//                .antMatchers(props.getExcludePath()).permitAll()
                //  固定的白名单
                .antMatchers(
                        "/actuator/**",
                        "/v2/api-docs",
                        "/doc.html",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/webjars/**",
                        "/swagger-resources/configuration/ui",
                        "/swagger-ui.html",
                        "/error",
                        "/error/**"
                ).permitAll();
        http.authenticationProvider(authenticationProvider());


        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .headers()
                .cacheControl();;

        return http.build();
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.addAllowedOrigin("*");  在springboot2.4之后替换成addAllowedOriginPattern
        //不然报错java.lang.IllegalArgumentException: When allowCredentials is true, allowedOrigins cannot contain the special value "*" since that cannot be set on the "Access-Control-Allow-Origin" response header. To allow credentials to a set of origins, list them explicitly
        // or consider using "allowedOriginPatterns" instead.
        // 跨域配置报错，将.allowedOrigins替换成.allowedOriginPatterns即可。
        // 设置允许跨域请求的域名
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        // 设置允许的方法
        corsConfiguration.addAllowedMethod("*");
        // 是否允许证书
        corsConfiguration.setAllowCredentials(true);
        // 跨域允许时间
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}