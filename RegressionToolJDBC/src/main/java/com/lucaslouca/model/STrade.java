package com.lucaslouca.model;

public class STrade extends AbstractTrade {
	public long techKey;

	public String instrumentName;

	public String isin;

	public long getTechKey() {
		return techKey;
	}

	public void setTechKey(long techKey) {
		this.techKey = techKey;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	@Override
	public String toString() {
		return "STrade [techKey=" + techKey + ", instrumentName=" + instrumentName + ", isin=" + isin + "]";
	}
}
