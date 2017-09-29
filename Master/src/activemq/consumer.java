package activemq;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public class consumer {

	protected CamelContext camelContext;
	private List<TopicConfiguration> topics;
	private String accessPoint;

	public consumer(String accessPoint, String user, String pass, String id, List<TopicConfiguration> ltopics) {
		this.topics = ltopics;
		this.accessPoint = accessPoint;
		try {
			camelContext = new CamelConcurrentContext(id, user, pass, accessPoint).getCamelContext();
			camelContext.addRoutes(new RouteBuilder() {
				public void configure() {
					for (TopicConfiguration topic : ltopics)
						from("activemq:topic:" + topic.getTopicName()).process(topic.getTopicProcessor());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			System.out.println("Iniciando Consumidor");
			camelContext.start();
			while (true)
				;
		} catch (Exception e) {
			stop();
		}
	}

	public void stop() {
		try {
			System.out.println("Finalizando Consumidor");
			camelContext.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAccessPoint() {
		return accessPoint;
	}

	public void setAccessPoint(String accessPoint) {
		this.accessPoint = accessPoint;
	}

	public List<TopicConfiguration> getTopics() {
		return topics;
	}
}
