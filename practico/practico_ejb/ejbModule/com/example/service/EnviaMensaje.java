package com.example.service;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


@Stateless
@LocalBean
public class EnviaMensaje {
	
//	@Resource(lookup = "java:jboss/DefaultJMSConnectionFactory")
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
	
	public void lleve() throws NamingException, JMSException{
		Context context = new InitialContext();
        QueueConnectionFactory factory = 
            (QueueConnectionFactory)context.lookup("ConnectionFactory");
        QueueConnection connection = factory.createQueueConnection();
        QueueSession session = 
            connection.createQueueSession(false, 
                QueueSession.AUTO_ACKNOWLEDGE);
         
        Queue queue = (Queue)context.lookup("queue/MyQueue");
        QueueSender sender = session.createSender(queue);
         
        //1. Sending TextMessage to the Queue 
        TextMessage message = session.createTextMessage();
        message.setText("Hello EJB3 MDB Queue!!!");
        sender.send(message);
         
        //2. Sending ObjectMessage to the Queue
        ObjectMessage objMsg = session.createObjectMessage();
         
         
        session.close();
	}
	
	public void enviarMensaje(String texto) throws NamingException, JMSException {
      
       
            String queueName = "jms/queue/MyQueue";
            Context ctx = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx
                    .lookup("ConnectionFactory");
            QueueConnection queueConnection = null;
            QueueSession queueSession = null;
            Queue queue = null;
            QueueSender queueSender = null;

            queue = (Queue) ctx.lookup(queueName);
            queueConnection = queueConnectionFactory.createQueueConnection();
            queueSession = queueConnection.createQueueSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            
            TextMessage message = queueSession.createTextMessage();
            message.setText(texto);
            queueSender = queueSession.createSender(queue);
            queueSender.send(message);
            queueSession.close();
            
//            ObjectMessage objMessage = queueSession.createObjectMessage();
//            objMessage.setIntProperty("codigoInstancia",
//                    this.codigoInstanciaSeleccionada);
//            objMessage.setIntProperty("codigoServicio", this.getDatosSesion()
//                    .getCodigoServicio());
//            objMessage.setObject(this.getDatosSesion());
//
//            queueSender = queueSession.createSender(queue);
//            queueSender.send(objMessage);
            queueSession.close();
       
    }


}
