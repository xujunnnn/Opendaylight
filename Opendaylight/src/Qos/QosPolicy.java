package Qos;



import java.util.HashSet;

import javax.print.attribute.ResolutionSyntax;
import javax.print.attribute.standard.NumberOfDocuments;

import com.sun.org.apache.regexp.internal.REUtil;

import Exception.FlowFailException;
import Exception.MeterFailException;
import FLow.Flow;
import Instruction.Instruction;
import Instruction.Instructions;
import Match.Ethernet_source;
import Match.Match;
import Meter.Meter;
import NetMonitor.Protocol_Type;
import NetWork_Topology.TopoUtil;
import NetWork_Topology.Host;
import NetWork_Topology.Node;


public class QosPolicy {
	private String qos_id;
	private String drop_rate;
	private String queue_id;
	private String Srchost;
	private String Desthost;
	private Protocol_Type ip_Protocol;
	private String Tcp_srcport;
	private String Tcp_destport;
	private String Udp_srcport;
	private String Udp_destPort;
	private String flowid;
	public String getSrchost() {
		return Srchost;
	}
	public QosPolicy setSrchost(String Srchost) {
		this.Srchost = Srchost;
		return this;
	}
	public String getFlowid() {
		return flowid;
	}
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}
	public String getDesthost() {
		return Desthost;
	}
	public QosPolicy setDesthost(String Desthost) {
		this.Desthost = Desthost;
		return this;
	}

	public String getTcp_srcport() {
		return Tcp_srcport;
	}
	public QosPolicy setTcp_srcport(String tcp_srcport) {
		Tcp_srcport = tcp_srcport;
		return this;
	}
	public String getTcp_destport() {
		return Tcp_destport;
	}
	public QosPolicy setTcp_destport(String tcp_destport) {
		Tcp_destport = tcp_destport;
		return this;
	}
	public String getUdp_srcport() {
		return Udp_srcport;
	}
	public QosPolicy setUdp_srcport(String udp_srcport) {
		Udp_srcport = udp_srcport;
		return this;
	}
	public String getUdp_destPort() {
		return Udp_destPort;
	}
	public QosPolicy setUdp_destPort(String udp_destPort) {
		Udp_destPort = udp_destPort;
		return this;
	}
	public String getQos_id() {
		return qos_id;
	}
	public QosPolicy setQos_id(String qos_id) {
		this.qos_id = qos_id;
		return this;
	}
	public String getDrop_rate() {
		return drop_rate;
	}
	public QosPolicy setDrop_rate(String drop_rate) {
		this.drop_rate = drop_rate;
		return this;
	}
	public String getQueue_id() {
		return queue_id;
	}
	public QosPolicy setQueue_id(String queue_id) {
		this.queue_id = queue_id;
		return this;
	}	
	public Protocol_Type getIp_Protocol() {
		return ip_Protocol;
	}
	public QosPolicy setIp_Protocol(Protocol_Type ip_Protocol) {
		this.ip_Protocol = ip_Protocol;
		return this;
	}
	public void apply() throws MeterFailException, FlowFailException{
		//if the match contains host information,add the meter and flow
		//to the access node 
		HashSet<String> nodes=new HashSet<>();
		Match match=new Match();
		if(this.Srchost!=null && this.Desthost!=null){
			Host Srchost=TopoUtil.get_host_from_name(this.Srchost);
			Host Desthost=TopoUtil.get_host_from_name(this.Desthost);
			match.Set_Mac_Match(Srchost.getMac(), Desthost.getMac(), "2048");
		    nodes.add(Srchost.getAccess_node());
		    nodes.add(Desthost.getAccess_node());
		}
		else if(this.Srchost==null && this.Desthost!=null){
			Host Desthost=TopoUtil.get_host_from_name(this.Desthost);
			match.Set_Mac_Match(null, Desthost.getMac(), "2048");
			nodes.add(Desthost.getAccess_node());
		}
		else if(this.Srchost!=null && this.Desthost==null){
			Host Srchost=TopoUtil.get_host_from_name(this.Srchost);
			match.Set_Mac_Match(Srchost.getMac(), null, "2048");
			nodes.add(Srchost.getAccess_node());
		}
		else{
			match.Set_Mac_Match( null, null, "2048");
			for(Node n:TopoUtil.get_access_node()){
				nodes.add(n.getNode_id());
			}
		}
		
		if(this.ip_Protocol!=null){
			match.Set_Ip_Match(String.valueOf(ip_Protocol.value()), null, null, null);
		}
		
		if(this.Tcp_srcport!=null){
			match.Set_Ip_Match(String.valueOf(Protocol_Type.TCP.value()), null, null, null);
			match.setTcp_source_port(this.Tcp_srcport);
		}
		if(this.Tcp_destport!=null){
			match.Set_Ip_Match(String.valueOf(Protocol_Type.TCP.value()), null, null, null);
			match.setTcp_destination_port(this.Tcp_destport);
		}
		if(this.Udp_srcport!=null){
			match.Set_Ip_Match(String.valueOf(Protocol_Type.UDP.value()), null, null, null);
			match.setUdp_source_port(this.Udp_srcport);			
		}
		if(this.Udp_destPort!=null){
			match.Set_Ip_Match(String.valueOf(Protocol_Type.UDP.value()), null, null, null);
			match.setUdp_source_port(this.Udp_destPort);			
		}
		for(String node:nodes){
		Meter meter=new Meter();
		meter.setMeter_id(qos_id)
			 .setMeter_name("qos"+this.getQos_id())
			 .Set_drop_rate(drop_rate)
			 .setContainer_name(meter.getMeter_name())
			 .Send(node);
		Flow flow=new Flow();
		Instructions instructions=new Instructions();
		Instruction instruction=new Instruction();
		instruction.Set_Go_To_Table_Id("3").setOrder("1");
		instructions.addInstruction(instruction);
		Instruction instruction2=new Instruction();
		instruction2.Set_Meter(meter.getMeter_id()).setOrder("0");
		instructions.addInstruction(instruction2);
		
	
			flow.setId(this.qos_id)
				.setFlow_name("qosflow"+this.qos_id)
				.setCookie(this.qos_id)
				.setTable_id("0")
				.setPriority("210")
				.setHard_timeout("0")
				.setIdle_timeout("0")
				.setMatch(match)
				.setInstructions(instructions)
				.Send(node);
		}	
	
	  }
}
