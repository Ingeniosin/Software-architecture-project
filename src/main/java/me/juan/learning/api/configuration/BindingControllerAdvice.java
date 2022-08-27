package me.juan.learning.api.configuration;

import com.google.gson.reflect.TypeToken;
import me.juan.learning.api.rest.Request;
import me.juan.learning.api.rest.SortInfo;
import me.juan.learning.api.rest.Utils;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
class BindingControllerAdvice {


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();

        Request target = (Request) binder.getTarget();
        if(target == null) return;

        binder.registerCustomEditor(List.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if(getValue() == target.getFilter()) {
                    setValue(Utils.getGson().fromJson(text, List.class));
                } else {
                    Type listType = new TypeToken<ArrayList<SortInfo>>(){}.getType();
                    setValue(Utils.getGson().fromJson(text, listType));
                }
            }
        });
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "|{}[]"));
        return factory;
    }

}