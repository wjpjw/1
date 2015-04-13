package modeling.mybishe;

import java.util.ArrayList;

import model.State;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean first = false;
		boolean two = false;
		
		System.out.println(first & two);	//false
		
		ArrayList<State> list = new ArrayList<State>();
		
		State s = new State();
		s.setName("hello");
		s.setIs_init(true);
		s.setIs_exception(false);
		State m = new State();
		m.setName("hello");
		m.setIs_init(true);
		m.setIs_exception(false);
		
		list.add(s);
		if(list.contains(m)){
			System.out.println("list.contains(m),list.indexOf(m)"+list.indexOf(m));	//false
		}
		System.out.println("s.equals(m)"+s.equals(m));		//true
		
		
		ArrayList<String> stringList = new ArrayList<String>();
		String s1 = "nihao";
		String s2 = "nihao";
		stringList.add(s1);
		
		System.out.println("s1.equals(s2)"+s1.equals(s2));	
		System.out.println("stringList.contains(s2)"+stringList.contains(s2));
		
		Test t = new Test();
		t.hello(stringList);
		System.out.println("stringList.size()"+stringList.size());
		
		ArrayList<State> statelist = new ArrayList<State>();
		State state1 = new State(false,true,"nihao");
		State state2 = new State(false,true,"nihao");
		statelist.add(state1);
		statelist.add(state2);
		System.out.println("statelist.size()"+statelist.size());
	}

	public void hello(ArrayList<String> stringList){
		stringList.add("balll");
	}
}
