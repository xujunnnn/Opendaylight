package Vtopo;

import java.util.ArrayList;
import java.util.HashSet;

import Exception.Mac_MapFailException;
import Exception.VbridgeFailExpection;
import Exception.VtnFailExpection;
import NetWork_Topology.Host;
import NetWork_Topology.TopoUtil;
import Vtn.OperationType;
import Vtn.UpDate_Mode;
import Vtn.Vtn;

import com.alibaba.fastjson.JSONObject;


public class VTopo {
	
	private HashSet<VGroup> vGroups=new HashSet<VGroup>();
	private HashSet<VLink> vLinks=new HashSet<VLink>();
	private String Vtopo_name;
	public HashSet<VGroup> getvGroups() {
		return vGroups;
	}
	public VTopo setvGroups(HashSet<VGroup> vGroups) {
		this.vGroups = vGroups;
		return this;
	}
	public HashSet<VLink> getvLinks() {
		return vLinks;
	}
	public VTopo setvLinks(HashSet<VLink> vLinks) {
		this.vLinks = vLinks;
		return this;
	}
	public String getVtopo_name() {
		return Vtopo_name;
	}
	public VTopo setVtopo_name(String vtopo_name) {
		Vtopo_name = vtopo_name;
		return this;
	}
	
	public VTopo addGroup(VGroup vGroup){
		this.vGroups.add(vGroup);
		return this;
	}
	
	public VTopo addLink(VLink vLink){
		this.vLinks.add(vLink);
		return this;
	}
	
	public void create() throws VbridgeFailExpection, Mac_MapFailException, VtnFailExpection{
		Vtn vtn=new Vtn();
		vtn.setTenant_name(Vtopo_name)
		   .setHard_timeout("10000").setIdle_timeout("1000")
		   .setOperation(OperationType.SET)
		   .setUpdate_mode(UpDate_Mode.CREATE)
		   .Send();
		for(VGroup vGroup:vGroups){
			vGroup.create();
		}
		for(VLink vLink:vLinks){
			vLink.create();
		}
	}
	public static void main(String args[]) throws VtnFailExpection{ 
		VTopo vTopo=new VTopo();
	
		vTopo.setVtopo_name("vtopo14");
		VTopoUtil.initVlan(vTopo.getVtopo_name());
		ArrayList<Host> hosts=new ArrayList<Host>(TopoUtil.get_hosts());
		VGroup vGroup=new VGroup().addHost(hosts.get(0).getHost_name()).addHost(hosts.get(1).getHost_name()).setGroup_id("g1").setVtoponame(vTopo.getVtopo_name());
		VGroup vGroup2=new VGroup().addHost(hosts.get(2).getHost_name()).setGroup_id("g2").setVtoponame(vTopo.getVtopo_name());
		VLink vLink=new VLink();
		vLink.setGroupA(vGroup.getGroup_id()).setGroupB(vGroup2.getGroup_id()).setLink_id(vLink.getGroupA()+"_"+vLink.getGroupB()).setVtoponame(vTopo.getVtopo_name());
		vTopo.addGroup(vGroup).addGroup(vGroup2).addLink(vLink);
		try {
			vTopo.create();
		} catch (VbridgeFailExpection | Mac_MapFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObject=(JSONObject)JSONObject.toJSON(vTopo);
		System.out.println(jsonObject.toJSONString());
	}
	
	

}
