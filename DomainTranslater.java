package package1;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;	
public class DomainTranslater {
	void setDomainInOutList(List<Integer> listIdIn, List<Integer> listIdOut, List<Integer> listDomainIn, List<Integer> listDomainOut) {
		setDomainList(listIdIn,listDomainIn);
		setDomainList(listIdOut,listDomainOut);
	}
	private void setDomainList(List<Integer> listId, List<Integer> listDomain) {		//вставили первый параметр
		int counter = 0;
		while (listId.size() > 2) {
			copying(listId,listDomain,4,0);
			for (int i = 0; i < listId.size(); i += 4) {
				
				for(int j = 0; (j + 2) < listId.size(); j += 4) {
					if((listId.get(j) == listDomain.get(listDomain.size() - 4 - 2 * counter)) && (listId.get(j + 1) == listDomain.get(listDomain.size() - 3 - 2 * counter))) {	
						copying(listId, listDomain, 2, j + 2);
						listId.remove(j);
						listId.remove(j);
						j -= 4;
						counter++;
					}
				}
				
			}
			listDomain.add(0);
			counter = 0;
		}
	}
	
	
	
	
	
	private void copying(List<Integer> listDonor, List<Integer> listAcceptor, int numIter,int positionDonor) {
		for (int i = 0; i < numIter; i++) {
			listAcceptor.add(listDonor.get(positionDonor));
			listDonor.remove(positionDonor);
		}
	}
}
