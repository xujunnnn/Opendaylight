package NetMonitor;

import java.util.HashSet;

import java.util.Map;


import Match.Ethernet_destination;
import Match.Ethernet_source;
import NetWork_Topology.Node;
import NetWork_Topology.Topology;

public class HostMonTest {
	public static void main(String [] args){
		HostMonitorTask hostMonitorTask=new HostMonitorTask();
		Topology topology=new Topology().update();
		HashSet<Node> nodes=topology.get_access_node();
		hostMonitorTask.setNodes(nodes);
		Thread thread=new Thread(hostMonitorTask);
		thread.start();
		Map<Triple<String, Ethernet_source, Ethernet_destination>, Long> map=hostMonitorTask.getHostSpeedMap();
		Map<Triple<String, Ethernet_source, Ethernet_destination>, Long> map2=hostMonitorTask.getHostByteMap(); 
		for(int i=0;i<1000;i++){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for(Triple<String, Ethernet_source,Ethernet_destination> t:map.keySet()){
			System.out.println(t.getLeft()+"<><><><><><><>"+t.getMid().getAddress()+"<<<<<<<<>>>>>>>>"+t.getRight().getAddress()+" = "+map.get(t)+"total=  "+map2.get(t));
		}
		}
		
	}

}
