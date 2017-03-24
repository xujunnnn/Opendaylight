package Flow_Filter;


import net.sf.json.JSONObject;

public class Vtn_flow_filter {
    private int index;
    private String condname;
    private Filter_Action_Type filter_Action_Type;
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public Vtn_flow_filter setIndex(int index) {
		this.index = index;
		return this;
	}
	/**
	 * @return the condname
	 */
	public String getCondname() {
		return condname;
	}
	/**
	 * @param condname the condname to set
	 */
	public Vtn_flow_filter setCondname(String condname) {
		this.condname = condname;
		return this;
	}
   public Filter_Action_Type geFilter_Action_Type(){
	   return filter_Action_Type;
   }
   public Vtn_flow_filter setFilter_action(Filter_Action_Type filter_Action_Type){
	   this.filter_Action_Type=filter_Action_Type;
	   return this;
   }
   

	public JSONObject build(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("index", index);
		jsonObject.put("condition", condname);
		if(filter_Action_Type==Filter_Action_Type.drop){
			jsonObject.put("vtn-drop-filter", new JSONObject());
		}
		else if(filter_Action_Type==Filter_Action_Type.pass){
			jsonObject.put("vtn-pass-filter", new JSONObject());
		}
		return jsonObject;
	
		
	}

}
