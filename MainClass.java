
package package1;
import static java.lang.System.out;					//для System.out

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainClass {
	public static void main (String[] args) {
		BlockMaker block = new BlockMaker();
		DomainTranslater domain = new DomainTranslater();
		BlockConnector blockConnector = new BlockConnector();
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		List<Integer> list4 = new ArrayList<Integer>();
		List<Integer> list5 = new ArrayList<Integer>();
		List<Integer> list6 = new ArrayList<Integer>();
		List<Integer> list7 = new ArrayList<Integer>();
		List<Integer> list8 = new ArrayList<Integer>();
		List<Integer> list9 = new ArrayList<Integer>();
		List<Integer> list10 = new ArrayList<Integer>();
		
		List<Integer> listIdIn = new ArrayList<Integer>();
		List<Integer> listIdOut = new ArrayList<Integer>();
		List<Integer> listDomainIn = new ArrayList<Integer>();
		List<Integer> listDomainOut = new ArrayList<Integer>();
		List<Integer> blockChainList = new ArrayList<Integer>();
		
		list1 = block.setBlock(1,listIdIn,listIdOut);
		list2 = block.setBlock(2,listIdIn,listIdOut);
		list3 = block.setBlock(3,listIdIn,listIdOut);
		list4 = block.setBlock(4,listIdIn,listIdOut);
		int numMass =(int) ( Math.random() * 8) + 3; //3-10
		int numBlocks = 3;
		domain.setDomainInOutList(listIdIn,listIdOut,listDomainIn,listDomainOut);
		
		System.out.println("listDomainIn("+listDomainIn.size()+"): " + listDomainIn);
		System.out.println("listDomainOut:("+listDomainOut.size()+"): "+ listDomainOut);
		blockConnector.connection(listDomainIn, listDomainOut, blockChainList,numBlocks);
	
	}
}

