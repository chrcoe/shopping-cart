package action;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import policy.TransactionPolicy;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {
	public static final String policyFile = "POLICYFILE";

    /**
     * Default constructor. 
     */
    public AppContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	InputStream is = ctx.getResourceAsStream(ctx.getInitParameter(policyFile));
    	JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(TransactionPolicy.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TransactionPolicy pol  = (TransactionPolicy) jaxbUnmarshaller.unmarshal(is);
	    	ctx.setAttribute(CartAppActionBeanContext.policyAttribute, pol);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
