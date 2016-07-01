package com.stone.service.app;

import com.stone.rules.facts.request.Request2Route;
import com.stone.rules.facts.routing.RoutingInfo;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RoutingPassService {

    private static Logger log = LoggerFactory.getLogger(RoutingPassService.class);

    @Autowired
    private KieContainer kieContainer;
    @Autowired
    public RoutingPassService(KieContainer kieContainer) {
        log.info("Initialising a new pay pass session.");
        this.kieContainer = kieContainer;
    }


    /**
     * Create a new session, insert a investor's details and fire rules to
     * determine what kind of pay pass is to be issued.
     */
    public RoutingInfo getpayPass(Request2Route route) {
        KieSession kieSession = kieContainer.newKieSession("PayStylePassSession");
        kieSession.insert(route);

        kieSession.fireAllRules();
        RoutingInfo payPass = findpayPass(kieSession);
        kieSession.dispose();


        return payPass;
    }

    /**
     * Search the {@link KieSession} for pay passes.
     */
    private RoutingInfo findpayPass(KieSession kieSession) {
        
        // Find all PayStylePass facts and 1st generation child classes of PayStylePass.
        ObjectFilter payPassFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (RoutingInfo.class.equals(object.getClass())) return true;
                if (RoutingInfo.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };

        // printFactsMessage(kieSession);
        
        List<RoutingInfo> facts = new ArrayList<RoutingInfo>();
        for (FactHandle handle : kieSession.getFactHandles(payPassFilter)) {
            facts.add((RoutingInfo) kieSession.getObject(handle));
        }
        if (facts.size() == 0) {
            return null;
        }
        // Assumes that the rules will always be generating a single pay pass.
        return facts.get(0);
    }
    
    /**
     * Print out details of all facts in working memory.
     * Handy for debugging.
     */
    @SuppressWarnings("unused")
    private void printFactsMessage(KieSession kieSession) {
        Collection<FactHandle> allHandles = kieSession.getFactHandles();
        
        String msg = "\nAll facts:\n";
        for (FactHandle handle : allHandles) {
            msg += "    " + kieSession.getObject(handle) + "\n";
        }
        System.out.println(msg);
    }

}
