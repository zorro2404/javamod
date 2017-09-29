package activemq;

import org.apache.camel.Processor;

public class TopicConfiguration {

	private String topicName;
	private Processor topicProcessor;

	public TopicConfiguration(String topicName, Processor topicProcessor) {
		super();
		this.topicName = topicName;
		this.topicProcessor = topicProcessor;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Processor getTopicProcessor() {
		return topicProcessor;
	}

	public void setTopicProcessor(Processor topicProcessor) {
		this.topicProcessor = topicProcessor;
	}
	
	
}
