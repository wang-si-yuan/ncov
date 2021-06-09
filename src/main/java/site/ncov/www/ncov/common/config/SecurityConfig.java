package site.ncov.www.ncov.common.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import sun.security.util.SecurityProperties;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public CookieSerializer httpSessionIdResolver(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("SESSION");
        cookieSerializer.setUseHttpOnlyCookie(false);
        cookieSerializer.setSameSite("None");
        cookieSerializer.setUseSecureCookie(true);
        return cookieSerializer;
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration =new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:8070","https://www.2019-ncov.site","118.195.156.48:443"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE","OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final String contentType = "application/json;charset=utf-8";

        http.authenticationProvider(authenticationProvider())
                .httpBasic()
                .authenticationEntryPoint((request,response,authException) -> {
                    response.setContentType(contentType);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(HttpResult.error("未登录")));
                    out.flush();
                    out.close();
                })

                .and()
                .authorizeRequests()
                .antMatchers("/ping","/img/**","/swagger-ui/","/dev/**","/weixin/register")
                .permitAll()

                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .permitAll()
                .failureHandler((request,response,ex) -> {
                    response.setContentType(contentType);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    HttpResult httpResult =HttpResult.error("登陆失败");
                    if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                        httpResult.setMsg("用户名或密码错误");
                    } else if (ex instanceof DisabledException) {
                        httpResult.setMsg("账户被禁用");
                    }
                    out.write(objectMapper.writeValueAsString(httpResult));
                    out.flush();
                    out.close();
                })
                .successHandler((request,response,authentication) -> {
                    response.setContentType(contentType);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(HttpResult.ok(authentication)));
                    out.flush();
                    out.close();
                })

                .and()
                .exceptionHandling()
                //没有权限，返回json
                .accessDeniedHandler((request,response,ex) -> {
                    response.setContentType(contentType);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(HttpResult.error("权限不足")));
                    out.flush();
                    out.close();
                })

                .and()
                .logout()
                .logoutSuccessHandler((request,response,authentication) -> {
                    response.setContentType(contentType);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(HttpResult.ok("退出成功")));
                    out.flush();
                    out.close();
                })
                .permitAll();
        //开启跨域访问
        http.cors();
        http.csrf().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }
}
