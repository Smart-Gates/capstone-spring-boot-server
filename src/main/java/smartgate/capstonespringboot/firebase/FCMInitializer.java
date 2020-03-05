package smartgate.capstonespringboot.firebase;


import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
@PropertySource("classpath:application.properties")
@Service
public class FCMInitializer {
	@Autowired
	private Environment env;    
    Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

    @PostConstruct
    public void initialize() {           
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
            		.setCredentials(GoogleCredentials.fromStream(new ClassPathResource(env.getProperty("app.firebase-configuration-file")).getInputStream()))
            		.setDatabaseUrl(env.getProperty("app.firebase-database-url"))
                    .setStorageBucket(env.getProperty("app.firebase-bucket"))
            		.build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
