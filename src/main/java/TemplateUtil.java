import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.util.Properties;

/**
 * Created by tmp on 14.04.2016.
 */
public class TemplateUtil {
    static {
        Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
        properties.setProperty("class.resource.loader.class",
                ClasspathResourceLoader.class.getName());
        Velocity.mergeTemplate(
                template, "UTF-8", new VelocityContext(data), w
        );
        
    }
}

