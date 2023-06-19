package de.hsrm.mi.web.projekt.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class FrontendNachrichtService {
    @Autowired
    private SimpMessagingTemplate messaging;

    Logger logger = LoggerFactory.getLogger(FrontendNachrichtService.class);

    public void sendEvent(FrontendNachrichtEvent e) {
        messaging.convertAndSend("/topic/quiz", e);
        logger.info(
                String.format("Nachricht verschickt: %s %s[id=%d]", e.getOperationTyp(), e.getEventTyp(),
                        e.getEventID()));
    }
}
