package excellence;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() { return new Class[]{RootConfig.class}; }

    @Override
    protected Class<?>[] getServletConfigClasses() { return new Class[]{RootConfig.class}; }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}