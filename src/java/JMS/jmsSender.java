
package JMS;

import Order.Order.Order;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


public class jmsSender {
    private JmsTemplate jmsTemplate;
        public void setJmsTemplate(JmsTemplate jmsTemplate){this.jmsTemplate=jmsTemplate;}
	public void send(Destination destination,final Order message){
			jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
	}

}
