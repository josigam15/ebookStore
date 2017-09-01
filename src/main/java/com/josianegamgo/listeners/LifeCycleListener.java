package com.josianegamgo.listeners;



import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Phase Listener
 *
 * @author Ken
 */
public class LifeCycleListener implements PhaseListener {

    // Default logger is java.util.logging
    private static final Logger log = Logger.getLogger("LifeCycleListener.class");

    /**
     * Which phase to listen for
     *
     * @return
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        log.log(Level.INFO, "START PHASE {0}", event.getPhaseId());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        log.log(Level.INFO, "END PHASE {0}", event.getPhaseId());
    }

}
