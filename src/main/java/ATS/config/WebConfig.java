package ATS.config;


import ATS.interceptors.AuthInterceptor;
import ATS.interceptors.RoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public RoleInterceptor roleInterceptor() {
        return new RoleInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login",
                        "/logout", "/css/**", "/js/**", "/checkloginexist");
        registry.addInterceptor(roleInterceptor()).addPathPatterns("/reguser", "**/edit/**");
    }
}
