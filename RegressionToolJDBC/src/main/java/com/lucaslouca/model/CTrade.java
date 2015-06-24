package com.lucaslouca.model;

import com.lucaslouca.mapping.SFieldMatch;

public class CTrade extends AbstractTrade {
	public long id;

	@SFieldMatch(name = "instrumentName")
	public String instrName;

	@SFieldMatch(name = "isin")
	public String cIsin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInstrName() {
		return instrName;
	}

	public void setInstrName(String instrName) {
		this.instrName = instrName;
	}

	public String getCalypsoIsin() {
		return cIsin;
	}

	public void setCalypsoIsin(String calypsoIsin) {
		this.cIsin = calypsoIsin;
	}

	@Override
	public String toString() {
		return "CTrade [id=" + id + ", instrName=" + instrName + ", cIsin=" + cIsin + "]";
	}

}
