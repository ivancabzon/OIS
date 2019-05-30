package package1;

import java.util.ArrayList;
import java.util.List;

public class Multithread extends Thread {
	
	BlockConnector block = new BlockConnector();
	private List<Integer> pairs;
	private List<Integer> LoP;
	public List<Integer> KO;
	
	Multithread(List<Integer> listic2, List<Integer> LoP,List<Integer> KO){
		this.pairs = listic2;
		this.LoP = LoP;
		this.KO = KO;
	}
	
	@Override
	public void run(){
			for (int i = 0; i < pairs.size();i++) {
				//System.out.println(1);
				block.recursion(pairs.get(i)+1,0, LoP,KO);
			}
			try {
					//sleep(0);
				
			} catch(Exception e) {
				System.out.println("exc"+e);
			}
	}
	
}
