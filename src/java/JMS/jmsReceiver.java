
package JMS;

import Order.Order.Order;
import Order.OrderService;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class jmsReceiver implements MessageListener {
        private static OrderService service;
        public void setService(OrderService service){jmsReceiver.service=service;}
	@Override
	public void onMessage(Message message) {
            ObjectMessage temp=(ObjectMessage)message;
            Order order;
            
		try {
                    order=(Order)temp.getObject();
                    boolean res = service.AddOrder(order);
                    if(res)
                        System.out.println("jmsReceiver接收到消息");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
