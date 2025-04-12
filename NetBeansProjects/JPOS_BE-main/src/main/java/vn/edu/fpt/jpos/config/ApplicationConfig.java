package vn.edu.fpt.jpos.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        this.register(new CorsFilter());
        packages("vn.edu.fpt.jpos.resources.apis");
    }
}
