package websockets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
 
@Named("chatBB")
@SessionScoped
public class ChatBB implements Serializable {
     
    //private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
 
    private List<String> users;	
	private List<String> usersLiga;
	private List<String> usersPartido;
	
    private List<String> msjGeneral;	
	private List<String> msjLiga;
	private List<String> msjPartido;
 
    private String privateMessage;
     
    private String globalMessage;
     
    private String username;
     
    private boolean loggedIn;
     
    private String privateUser;
     
    private final static String CHANNEL = "/{room}/";
    
    @PostConstruct
    public void init(){
    	users = new ArrayList<String>();
    	msjGeneral = new ArrayList<String>();
    	msjLiga = new ArrayList<String>();
    	msjPartido = new ArrayList<String>();
    }
 
    public List<String> getUsers() {
        return users;
    }
 
    public void setUsers(List<String> users) {
        this.users = users;
    }
     
    public List<String> getUsersLiga() {
		return usersLiga;
	}

	public void setUsersLiga(List<String> usersLiga) {
		this.usersLiga = usersLiga;
	}

	public List<String> getUsersPartido() {
		return usersPartido;
	}

	public void setUsersPartido(List<String> usersPartido) {
		this.usersPartido = usersPartido;
	}

	public List<String> getMsjGeneral() {
		return msjGeneral;
	}

	public void setMsjGeneral(List<String> msjGeneral) {
		this.msjGeneral = msjGeneral;
	}

	public List<String> getMsjLiga() {
		return msjLiga;
	}

	public void setMsjLiga(List<String> msjLiga) {
		this.msjLiga = msjLiga;
	}

	public List<String> getMsjPartido() {
		return msjPartido;
	}

	public void setMsjPartido(List<String> msjPartido) {
		this.msjPartido = msjPartido;
	}

	public String getPrivateUser() {
        return privateUser;
    }
 
    public void setPrivateUser(String privateUser) {
        this.privateUser = privateUser;
    }
 
    public String getGlobalMessage() {
        return globalMessage;
    }
 
    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }
 
    public String getPrivateMessage() {
        return privateMessage;
    }
 
    public void setPrivateMessage(String privateMessage) {
        this.privateMessage = privateMessage;
    }
     
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
     
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public void sendGral(){
    	String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mensajeGral");
    	this.msjGeneral.add(value);
    }
 
//    public void sendGlobal() {
//        eventBus.publish(CHANNEL + "*", username + ": " + globalMessage);
//         
//        globalMessage = null;
//    }
//     
//    public void sendPrivate() {
//        eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);
//         
//        privateMessage = null;
//    }
     
    public void login() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        if(users.contains(username)) {
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
            requestContext.update("growl");
        }
        else {
            users.add(username);
            requestContext.execute("PF('subscriber').connect('/" + username + "')");
            loggedIn = true;
        }
    }
     
    public void disconnect() {
        //remove user and update ui
        users.remove(username);
        RequestContext.getCurrentInstance().update("form:users");
         
        //push leave information
//        eventBus.publish(CHANNEL + "*", username + " left the channel.");
         
        //reset state
        loggedIn = false;
        username = null;
    }
}
