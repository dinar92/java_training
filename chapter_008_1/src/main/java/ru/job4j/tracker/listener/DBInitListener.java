package ru.job4j.tracker.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Initialized actions for the system's database.
 */
public class DBInitListener implements ServletContextListener {

    /**
     * A logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DBInitListener.class);

    /**
     * Initializes the first user in the system. Takes data from the
     * property file.
     * @param sce - servlet context event.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Validate<Role> roleValidate = RoleValidateService.getInstance();
        Validate<User> userValidate = ValidateService.getInstance();
        InputStream inputStream = sce.getServletContext().getResourceAsStream("WEB-INF/application.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            String login = properties.getProperty("app.admin.login");
            String password = properties.getProperty("app.admin.password");
            String email = properties.getProperty("app.admin.email");
            String name = properties.getProperty("app.admin.name");


            Role adminRole = new Role();
            adminRole.setName("admin");
            adminRole.setId(new Integer(1));
            roleValidate.add(adminRole);

            User admin = new User();
            admin.setId(new Integer(1));
            admin.setRole(adminRole);
            admin.setLogin(login);
            admin.setPassword(password);
            admin.setCreateDate(LocalDate.now());
            admin.setEmail(email);
            admin.setName(name);
            userValidate.add(admin);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
