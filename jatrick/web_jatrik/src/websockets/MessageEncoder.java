package websockets;


import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;
 
/**
 * A Simple {@link org.primefaces.push.Encoder} that decode a {@link Message} into a simple JSON object.
 */
public final class MessageEncoder {
 
    //@Override
    public String encode(Message message) {
        return new JSONObject(message).toString();
    }
}
