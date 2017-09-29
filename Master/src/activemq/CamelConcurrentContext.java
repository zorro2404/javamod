package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CamelConcurrentContext {
	private JndiContext jndiContext;
	private CamelContext camelContext;

	public CamelConcurrentContext(String clientID, String user, String pass, String accessPoint) throws Exception {
		jndiContext = new JndiContext();
		jndiContext.bind("testBean", new TestBean());
		camelContext = new DefaultCamelContext(jndiContext);
		if (user != null) {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			connectionFactory.setBrokerURL(accessPoint);
			connectionFactory.setUserName(user);
			connectionFactory.setPassword(pass);
			connectionFactory.setClientID(clientID);
			camelContext.addComponent("activemq", ActiveMQComponent.jmsComponent(connectionFactory));
		}
	}

	public CamelContext getCamelContext() {
		return camelContext;
	}

	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}
}
