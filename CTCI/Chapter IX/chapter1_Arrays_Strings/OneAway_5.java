package chapter1_Arrays_Strings;
public class OneAway_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isOneAway("pale","bale"));
	}
	public static boolean isOneAway(String s, String t){
		int length1 = s.length();
		int length2 = t.length();
		
		return check(s,t,length1-1,length2-1,false);
		
	}
	public static boolean check(String  s, String t, int l1, int l2, boolean done){
	
		if(l1==l2 && l2 ==-1) return true;
		if(l1==-1 || l2==-1) return (Math.max(l1, l2)+1) ==1;
		if(Math.abs(l1-l2)>1) return false;
		if(s.charAt(l1) == t.charAt(l2)) return check(s,t,l1-1,l2-1,done);
		if(!done) return (check(s,t,l1,l2-1,true) || check(s,t,l1-1,l2,true) || check(s,t,l1-1,l2-1,true));
		return false;	
	}

}
