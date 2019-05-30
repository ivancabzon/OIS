package package1;

import java.util.ArrayList;
import java.util.List;

public class BlockMaker {
	
	List<Integer> blocklist = new ArrayList<Integer>();	
	List<Integer> listIdOut = new ArrayList<Integer>();
	List<Integer> listIdIn = new ArrayList<Integer>();
	
	
	List<Integer> setBlock(int numberBlock,List<Integer> listIdIn1, List<Integer> listIdOut1) {
		blocklist.clear();
		listIdIn = listIdIn1;
		listIdOut = listIdOut1;
		//int helper = 0;
		int counter = 0;
		int helper2 = 0;
		int countInterfIn1 = 0;
		int countInterfIn2 = 0;
		int countInterfIn = 0;
		int countInterfOut1 = 0;
		int countInterfOut2 = 0;
		int countInterfOut = 0;
		
		
		
		//---------------------------1---------------
			blocklist.add((int) (Math.random()*3)+1);
			blocklist.add((int) (Math.random()*3)+1);
			for (int i = 0; i < blocklist.get(0); i++ ) {
				blocklist.add((int) (Math.random()*3)+1);
			}
			for (int i = 0; i < blocklist.get(1); i++ ) {
				blocklist.add((int) (Math.random()*3)+1);
			}
			
			
			countInterfIn1 = blocklist.get(0);
			
			countInterfIn = 1;
			countInterfIn2 = blocklist.get(2);
			counter = 2+blocklist.get(0)+blocklist.get(1);
			
			for(int numPars=0; numPars < (blocklist.get(0)+blocklist.get(1)); numPars++) {
				for (int i = 0; i < blocklist.get(2 + numPars); i++ ) {
					int a1 = (int)(Math.random()*3)+1;
					blocklist.add(a1);
					int a2 = (int)(Math.random()*3)+1;
					blocklist.add(a2);
					
					counter += 2;
					helper2 = 2 + blocklist.get(0) + blocklist.get(1);
					for (int k = 2; k < 2 + blocklist.get(0);k++) {
						helper2+=(blocklist.get(k)*2);
					}
						if(counter<= helper2) {
								
								listIdIn1.add(a1);
								listIdIn1.add(a2);
								listIdIn1.add(numberBlock);
								
								if(countInterfIn1 != 0) {
									if(countInterfIn2 !=0 ) {
										countInterfIn2--;
										//countInterfIn1--;
										listIdIn1.add(countInterfIn);
										//outp
									}
								}
								if(countInterfIn2 == 0) {
									countInterfIn++;
									countInterfIn1--;
									countInterfIn2 = blocklist.get(1 + countInterfIn);
								}
								if(countInterfIn1 == 0) {
									countInterfIn1 = blocklist.get(1);
									countInterfIn2 = blocklist.get(2 + blocklist.get(0));
									countInterfIn = 1;
									
								}
						}
						if(counter> helper2) {
							listIdOut1.add(a1);
							listIdOut1.add(a2);
							listIdOut1.add(numberBlock);
							
							if(countInterfIn1 != 0) {
								if(countInterfIn2 !=0 ) {
									countInterfIn2--;
									listIdOut.add(countInterfIn);
									//outp
								}
							}
							
							if(countInterfIn2 == 0) {
								countInterfIn++;
								countInterfIn1--;
								countInterfIn2 = blocklist.get(1 + blocklist.get(0) + countInterfIn);
							}
						}
				}
			}
		System.out.println("block"+numberBlock+": "+blocklist);
		return blocklist;
	}
}
