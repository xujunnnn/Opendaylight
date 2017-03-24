package NetMonitor;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

import Match.Ethernet_destination;
import Match.Ethernet_source;
import NetWork_Topology.Termination_point;
import NetWork_Topology.TopoUtil;
import NetWork_Topology.Topology;

public class HostMonitor {
	private static boolean isactive=false;
	static HashSet<Termination_point> ports=TopoUtil.get_ports_to_switch();
	private static HostMonitorTask hostMonitorTask=new HostMonitorTask();
	private static Thread thread=new Thread(hostMonitorTask); 
	public static void begin(){
		isactive=true;
		if(isactive=true){
		Topology topology=new Topology().update();
		hostMonitorTask.setNodes(TopoUtil.get_access_node());
		thread.start();
		}		
	}
	/**
	 * get the dataMap
	 * @return
	 */
	public static Map<Triple<String, Ethernet_source, Ethernet_destination>, Pair<Long, Long>> getHostSpeedMap() {
		return hostMonitorTask.getHostSpeedMap();
	}
	/**
	 * get local switch data
	 * @return
	 */
	public static Map<Triple<String, Ethernet_source, Ethernet_destination>, Pair<Long,Long>> getLocalMap(){
		Map<Triple<String, Ethernet_source, Ethernet_destination>, Pair<Long,Long>> allDataMap=hostMonitorTask.getHostSpeedMap();
		Map<Triple<String, Ethernet_source, Ethernet_destination>, Pair<Long,Long>> LocalDataMap=new HashMap<Triple<String,Ethernet_source,Ethernet_destination>, Pair<Long,Long>>();
		
		HashSet<String> portnames=new HashSet<String>();
		for(Termination_point port:ports){
			portnames.add(port.getTp_id());
		}
		
		for(Triple<String, Ethernet_source, Ethernet_destination> t:allDataMap.keySet()){						
			if(!portnames.contains(t.getLeft())){
				LocalDataMap.put(t, allDataMap.get(t));
				
			}
		}
		return LocalDataMap;
		
	}
	
	public static void stop(){
		isactive=false;
		thread.interrupt();
	}
	
	public boolean getStats(){
		return isactive;
	}
}
