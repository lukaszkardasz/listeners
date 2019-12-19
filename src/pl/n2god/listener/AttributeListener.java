package pl.n2god.listener; /**
 * @project listeners
 * @author n2god on 18/12/2019
 */

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class AttributeListener implements ServletContextListener,
		HttpSessionListener, HttpSessionAttributeListener {

	// Public constructor is required by servlet spec
	public AttributeListener() {
	}

	// -------------------------------------------------------
	// HttpSessionAttributeListener implementation
	// -------------------------------------------------------

	public void attributeAdded(HttpSessionBindingEvent sbe) {
		System.out.println("Request attribute added " + sbe.getName());
	}

	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		System.out.println("Request attribute removed " + sbe.getName());
	}

	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		System.out.println("Request attribute replaced " + sbe.getName());
	}
}
