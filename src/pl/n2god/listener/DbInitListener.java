package pl.n2god.listener; /**
 * @project listeners
 * @author n2god on 18/12/2019
 */

import pl.n2god.db.DbUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener()
public class DbInitListener implements ServletContextListener {

	// Public constructor is required by servlet spec
	public DbInitListener() {
	}

	// -------------------------------------------------------
	// ServletContextListener implementation
	// -------------------------------------------------------
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Context initialized");
		try {
			DbUtil.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DbUtil.getInstance().close();
		System.out.println("Context Destroyed");
	}


}
