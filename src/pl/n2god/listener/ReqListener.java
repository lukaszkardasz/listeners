package pl.n2god.listener; /**
 * @project listeners
 * @author n2god on 18/12/2019
 */

import javax.servlet.*;
import javax.servlet.annotation.WebListener;


@WebListener()
public class ReqListener implements ServletRequestListener {

	// Public constructor is required by servlet spec
	public ReqListener() {
	}

	public void requestInitialized(ServletRequestEvent sre){
		System.out.println("Request initialized");
	}


	public void requestDestroyed(ServletRequestEvent sre){
		System.out.println("Request destroyed");
	}

}
