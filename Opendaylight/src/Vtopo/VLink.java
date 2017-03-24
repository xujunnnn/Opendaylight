package Vtopo;

import java.util.HashSet;

import javax.crypto.Mac;

import Exception.Mac_MapFailException;
import Exception.VbridgeFailExpection;
import Match.Vlan_Match;
import Vtn.Allowed_Hosts;
import Vtn.Hostmc;
import Vtn.Mac_Map_Config;
import Vtn.Mac_Mapped_Host;
import Vtn.OperationType;
import Vtn.UpDate_Mode;
import Vtn.VTNUtil;
import Vtn.Vbridge;

public class VLink {
	private String link_id;
	private String groupA;
	private String groupB;
	
	private String vtoponame;
	public String getLink_id() {
		return link_id;
	}
	public String getGroupA() {
		return groupA;
	}
	public VLink setGroupA(String groupA) {
		this.groupA = groupA;
		return this;
	}
	public String getGroupB() {
		return groupB;
	}
	public VLink setGroupB(String groupB) {
		this.groupB = groupB;
		return this;
	}
	public VLink setLink_id(String link_id) {
		this.link_id = link_id;
		return this;
	}	
	public  void create() throws VbridgeFailExpection, Mac_MapFailException{
		Allowed_Hosts allowed_Hosts=new Allowed_Hosts();
		Mac_Map_Config mac_Map_Config=VTNUtil.readMac_Map_Config(vtoponame,groupA);
		int vlanid=VTopoUtil.get_Vlan(vtoponame);
		for(Hostmc host:mac_Map_Config.getAllowedHosts().getVlan_host_desc_list()){
			allowed_Hosts.addHost(host.getHost().split("@")[0]+"@"+vlanid);
		}
		mac_Map_Config=VTNUtil.readMac_Map_Config(vtoponame,groupB);
		for(Hostmc host:mac_Map_Config.getAllowedHosts().getVlan_host_desc_list()){
			allowed_Hosts.addHost(host.getHost().split("@")[0]+"@"+vlanid);
		}
		Vbridge vbridge=new Vbridge();
		vbridge.setTenant_name(vtoponame)
			   .setUpdate_mode(UpDate_Mode.CREATE)
			   .setOperation(OperationType.SET)
			   .setBridge_name(link_id)
			   .Set_Mac_Map(allowed_Hosts,null)
			   .send();
		
	}
	public String getVtoponame() {
		return vtoponame;
	}
	public VLink setVtoponame(String vtoponame) {
		this.vtoponame = vtoponame;
		return this;
	}

}
