package restclient.restclient.models;

import java.util.ArrayList;

public class CombinedResponseMessage {
    private ArrayList<ResponseMessage> responses = new ArrayList<ResponseMessage>();

    public void addResponses (ResponseMessage... responseMessages) {
        for (ResponseMessage responseMessage : responseMessages) {
            responses.add(responseMessage);
        }
    }

    public ArrayList<ResponseMessage> getResponses () {
        return responses;
    } 
}
