package mk.com.apartmentdesign.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        Path apartmentUploadDir = Paths.get ("./src/main/resources/static/img/apartments");
        String apartmentUploadPath = apartmentUploadDir.toFile ().getAbsolutePath ();

        registry.addResourceHandler ("./src/main/resources/static/img/apartments")
                .addResourceLocations ("file:/"+apartmentUploadPath+"/");
    }
}
