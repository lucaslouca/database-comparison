package com.lucaslouca.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_TRD_TRADE")
public class STrade extends AbstractTrade implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "TRD_TECHKEY")
	public long techKey;

	@Column(name = "TRD_INSTR_NAME")
	public String instrumentName;

	@Column(name = "TRD_ISIN")
	public String isin;

	@Override
	public String toString() {
		return "STrade [techKey=" + techKey + ", instrumentName=" + instrumentName + ", isin=" + isin + "]";
	}
}
