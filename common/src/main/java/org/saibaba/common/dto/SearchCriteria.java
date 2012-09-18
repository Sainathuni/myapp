package org.saibaba.common.dto;

import org.saibaba.fw.domain.BaseEntity;

public abstract class SearchCriteria extends BaseEntity{

	private static final long serialVersionUID = -7312800337518529485L;
	public static final String LOGIC_GATE_AND = "AND";
	public static final String LOGIC_GATE_OR = "OR";
	
	// default logic gate
	protected String logicGate = SearchCriteria.LOGIC_GATE_AND;

	
	public SearchCriteria() {}

	public String getLogicGate() {
		return logicGate;
	}


	public void setLogicGate(String logicGate) {
		this.logicGate = logicGate;
	}
}
