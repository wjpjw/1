package model;

public class State{
	boolean is_exception;
	String name;
}


//每个函数前的注释格式改成下面这种
/*
* Full >> push(size==MAX_INT) >> OF 
* Empty >> push() >> Full
* */

/*
 *  Full --> push(size==MAX_INT)--> OF 
 *  这里Full为preState，OF为postState
 *  size==MAX_INT为condition。
 *  
 */
