package com.lucaslouca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lucaslouca.mapping.SFieldMatch;

@Entity
@Table(name = "T_TRD_TRADE")
public class CTrade extends AbstractTrade {
	@Id
	@GeneratedValue
	@Column(name = "TRD_TECHKEY")
	public long id;

	@SFieldMatch(name = "instrumentName")
	@Column(name = "TRD_INSTR_NAME")
	public String instrName;

	@SFieldMatch(name = "isin")
	@Column(name = "TRD_ISIN")
	public String cIsin;

	@Override
	public String toString() {
		return "CTrade [id=" + id + ", instrName=" + instrName + ", cIsin=" + cIsin + "]";
	}

}
