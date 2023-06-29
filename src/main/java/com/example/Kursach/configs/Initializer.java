package com.example.Kursach.configs;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    } //устанавливаем сервлету наш web конфиг

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    } //указываем сервлету путь
}
