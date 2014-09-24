package com.example.service;

import javax.ejb.*;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(propertyName = "destination",
	            propertyValue = "/jms/queue/MyQueue"),
	    @ActivationConfigProperty(propertyName = "destinationType",
	            propertyValue = "javax.jms.Queue")
	})
public class RecibeMensaje implements MessageListener{

	@Override
	public void onMessage(Message inMessage) {
		if (inMessage instanceof TextMessage) {
//	            logger.log(Level.INFO,
//	                    "MESSAGE BEAN: Message received: {0}",
//	                    inMessage.getBody(String.class));
		} else {
//	            logger.log(Level.WARNING,
//	                    "Message of wrong type: {0}",
//	                    inMessage.getClass().getName());
		}
		
	}
}

