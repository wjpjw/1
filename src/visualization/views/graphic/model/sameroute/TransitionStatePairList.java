package visualization.views.graphic.model.sameroute;

import java.util.ArrayList;
import java.util.HashMap;

import modeling.model.Transition;

public class TransitionStatePairList {
	private ArrayList<TransitionStatePair> pairs=new ArrayList<TransitionStatePair>();
	private HashMap<Transition, TransitionStatePair> map=new HashMap<Transition, TransitionStatePair>();
	public TransitionStatePairList(){}
	public TransitionStatePair search(TransitionStatePair pair){
		for (int i = 0; i < pairs.size(); i++) {
			TransitionStatePair pair2=pairs.get(i);
			if(pair2.equals(pair)){
				return pair2;
			}
		}
		return null;
	}
	public void init_pairs_from(ArrayList<Transition> transitions){
		for (int i = 0; i < transitions.size(); i++) {
			create_pair_or_add_to_existed_pair(transitions.get(i));
		}
	}
	private void create_pair_or_add_to_existed_pair(Transition transition){
		TransitionStatePair pair=new TransitionStatePair(transition);
		TransitionStatePair same_pair=this.search(pair);
		if(same_pair!=null){
			pair=null;
			same_pair.add_transition(transition);
			map.put(transition, same_pair);
		}
		else{
			pairs.add(pair);
			pair.add_transition(transition);
			map.put(transition, pair);
		}
	}
	public TransitionStatePair get_pair(Transition t){
		return map.get(t);
	}
}
