package org.saiweb.mandirs.dao.api;

import org.saiweb.mandirs.model.MandirDataFlow;



public interface MandirDataFlowDAO {

public void addMandirDataFlow( MandirDataFlow mandirDataFlow);

public void getAllMandirDataFlows(int mandirId);

public void updateMandirDateFlow(MandirDataFlow mandirDataFlow);

}
