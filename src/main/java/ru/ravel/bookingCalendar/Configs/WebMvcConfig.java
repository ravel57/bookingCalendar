package ru.ravel.bookingCalendar.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webapp/**")
                .addResourceLocations("/webapp/").setCachePeriod(null);
    }

//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerCustomizer(){
//        return container -> {
//            container.addErrorPages(new ErrorPage( HttpStatus.NOT_FOUND, "/"));
//        };
//    }


//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("Main");
//        registry.addViewController("/login").setViewName("login");
//    }
}
