package websockets;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
 
@ServerEndpoint(value = "/chat/{username}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndpoint {
	
	private final Logger log = Logger.getLogger(getClass().getName());
 
//	@OnOpen
//	public void open(final Session session, @PathParam("room") final String room) {
//		log.info("session openend and bound to room: " + room);
//		session.getUserProperties().put("room", room);
//	}
	
	private List<String> usersGeneral;
	
	public ChatEndpoint() {
		usersGeneral = new ArrayList<String>();
	}
	
	public List<String> getUsersGeneral() {
		return usersGeneral;
	}

	public void setUsersGeneral(List<String> usersGeneral) {
		this.usersGeneral = usersGeneral;
	}

	@OnOpen
	public void open(final Session session, @PathParam("username") final String username) {
//		log.info("session openend and bound to room: " + room);
		session.getUserProperties().put("username", username);
//		session.getUserProperties().put("room", room);
		usersGeneral.add(username);
	}
 
	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {
//		String room = (String) session.getUserProperties().get("room");
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen() && (!s.getUserProperties().get("username").equals(chatMessage.getSender()))) {
					s.getBasicRemote().sendObject(chatMessage);
				}
			}
		} catch (IOException | EncodeException e) {
			log.log(Level.WARNING, "onMessage failed", e);
		}
	}
}