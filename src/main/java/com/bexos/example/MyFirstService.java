package com.bexos.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySources({
        @PropertySource("classpath:custom.properties"),
        @PropertySource("classpath:custom2.properties")
})
public class MyFirstService {

    private final MyFirstClass myFirstClass;
    private Environment environment;

    public MyFirstService(@Qualifier("myFirstBean") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Value("${my.prop}")
    private String customPropertyFromAnotherFile;

    @Value("${my.prop2}")
    private String customProperty2;



    public String tellAStory(){
        return "The dependency is saying: " + myFirstClass.sayHello();
    }

    public String getCustomPropertyFromAnotherFile() {
        return customPropertyFromAnotherFile;
    }

    public String getCustomProperty2() {
        return customProperty2;
    }

    public String getOsName(){
        return environment.getProperty("os.name");
    }

}
