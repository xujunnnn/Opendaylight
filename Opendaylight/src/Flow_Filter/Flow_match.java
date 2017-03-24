package Flow_Filter;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Flow_match {
	private String name;
	private ArrayList<Vtn_flow_match> flow_matchs=new ArrayList<>();
	public String getName() {
		return name;
	}
	public Flow_match setName(String name) {
		this.name = name;
		return this;
	}
	public ArrayList<Vtn_flow_match> getFlow_matchs() {
		return flow_matchs;
	}
	public Flow_match setFlow_matchs(ArrayList<Vtn_flow_match> flow_matchs) {
		this.flow_matchs = flow_matchs;
		return this;
	}
	public JSONObject build(){
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		for(Vtn_flow_match vtn_flow_match:flow_matchs){
			jsonArray.add(vtn_flow_match.build());
		}
		jsonObject.put("vtn-flow-match", jsonArray);
		jsonObject.put("name", name);
		JSONObject jsonobj=new JSONObject();
		jsonobj.put("input", jsonObject);
		return jsonobj;
		
		
		
	}
	

}
