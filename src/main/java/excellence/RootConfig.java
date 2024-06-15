package excellence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@EnableWebMvc
@Configuration
@EnableCaching
//@EnableTransactionManagement
@ComponentScan("excellence")
//@EntityScan(basePackages = ["com.itsdryft.entity", "com.itsdryft.entity.*"])
//@EnableJpaRepositories(["com.itsdryft.repository", "com.itsdryft.repository.*"], entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class RootConfig implements WebMvcConfigurer {


    @Bean
    public TomcatServletWebServerFactory server(){ return new TomcatServletWebServerFactory(8080); }

    @Bean
    @Description("Thymeleaf Template Resolver")
    public SpringResourceTemplateResolver templateResolver() {
        var templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ClassLoaderTemplateResolver templateResolver2() {
        var templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("templates/");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }


    @Bean
    @Description("Thymeleaf Template Engine")
    public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver2());
        //templateEngine.setTemplateEngineMessageSource(messageSource());
        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf View Resolver")
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        registry.viewResolver(viewResolver());

    }

}
