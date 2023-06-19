package de.hsrm.mi.web.projekt.messaging;

public class FrontendNachrichtEvent {

    public enum MessageEventTyp {
        QUIZ, FRAGE
    }

    public enum MessageOperationTyp {
        UPDATE, DELETE
    }

    private MessageEventTyp eventTyp;
    private long eventID;
    private MessageOperationTyp operationTyp;

    public FrontendNachrichtEvent(MessageEventTyp eventTyp, long eventID, MessageOperationTyp operation) {
        this.eventTyp = eventTyp;
        this.eventID = eventID;
        this.operationTyp = operation;
    }

    public MessageEventTyp getEventTyp() {
        return eventTyp;
    }

    public void setEventTyp(MessageEventTyp eventTyp) {
        this.eventTyp = eventTyp;
    }

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public MessageOperationTyp getOperationTyp() {
        return operationTyp;
    }

    public void setOperationTyp(MessageOperationTyp operation) {
        this.operationTyp = operation;
    }

}
