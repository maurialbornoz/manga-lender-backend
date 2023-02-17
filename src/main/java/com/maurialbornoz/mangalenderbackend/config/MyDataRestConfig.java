package com.maurialbornoz.mangalenderbackend.config;

import com.maurialbornoz.mangalenderbackend.entity.Manga;
import com.maurialbornoz.mangalenderbackend.entity.Message;
import com.maurialbornoz.mangalenderbackend.entity.Review;
import com.maurialbornoz.mangalenderbackend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Value("${CROSSORIGIN}")
    private String theAllowedOrigins ;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT};
        config.exposeIdsFor(Manga.class);
        config.exposeIdsFor(Review.class);
        config.exposeIdsFor(Message.class);
        disableHttpMethods(Manga.class, config, theUnsupportedActions);
        disableHttpMethods(Review.class, config, theUnsupportedActions);
        disableHttpMethods(Message.class, config, theUnsupportedActions);

        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);

    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsopportedActions){
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsopportedActions)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsopportedActions));
    }



}
