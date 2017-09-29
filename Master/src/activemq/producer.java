package activemq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class producer {
	private Connection connection;
	private Session session;
	private MessageProducer producer;

	public producer(String accessPoint, String topic, String user, String pass, String id, int nProccessors) {
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(accessPoint);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
			connectionFactory.setUserName(ActiveMQConnection.DEFAULT_USER);
			connectionFactory.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
			connectionFactory.setClientID(id);

			connection = connectionFactory.createConnection();
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createTopic(topic);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		public void send(String message) throws Exception {
		try {
			// System.out.println(SecurityUtils.getInstance().encrypt(message));
			TextMessage mensage = session.createTextMessage(message);
			producer.send(mensage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
