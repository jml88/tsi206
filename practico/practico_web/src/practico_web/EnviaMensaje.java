//package practico_web;
//
//import javax.annotation.Resource;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Named;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSContext;
//import javax.jms.Queue;
//
//@Named
//@RequestScoped
//public class EnviaMensaje {
//	@Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
//	private static ConnectionFactory connectionFactory;
//
//	@Resource(lookup = "jms/queue/MyQueue")
//	private static Queue queue;
//
//
//	private String text;
//	final int NUM_MSGS = 3;
//
//	public void publicaMensaje(){
//		try (JMSContext context = connectionFactory.createContext();) {
//			for (int i = 0; i < NUM_MSGS; i++) {
//			    text = "This is message " + (i + 1);
//			    System.out.println("Sending message: " + text);
//			    context.createProducer().send(queue, text);
//			}
//		}
//	}
//
//
//}
