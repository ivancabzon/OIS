package package1;

import java.util.ArrayList;
import java.util.List;
public class BlockConnector extends Thread{
//	RecursiveClass recursiveClass = new RecursiveClass();
	int a1 = 0;
	int a2 = 0;
	int b1 = 0;
	int b2 = 0;
	int i = 0;
	int j = 0;
	int k = 0;		//счётчик позиции In
	int counterI = 0;
	List<Integer> dirtyListPairsOfBlocks = new ArrayList<Integer>();	
	List<Integer> pureListPairsOfBlocks = new ArrayList<Integer>();
	List<Integer> correctListPairsOfBlocks = new ArrayList<Integer>();
	List<Integer> stopList = new ArrayList<Integer>();
	public List<Integer> listic = new ArrayList<Integer>();
	List<Integer> listOfZero = new ArrayList<Integer>();
	//листы для мультипоточности
	List<Integer> listic1 = new ArrayList<Integer>();
	List<Integer> listic2 = new ArrayList<Integer>();
	List<Integer> listic3 = new ArrayList<Integer>();
	List<Integer> listic4 = new ArrayList<Integer>();
	
	
	void connection(List<Integer> listDomainIn, List<Integer> listDomainOut, List<Integer> blockChainList, int numBlocks) {
		//--------------составление связей между блоками-----------------
		for(i = 0; i + 1 < listDomainOut.size(); i += 4) {
			//if(listDomainOut.get(i)==0) i++;						//если напоролись на 0 в listDomainOut
			k = 0;
			for(int j = 0; j + 1 < listDomainIn.size(); j += 4) {
				if(listDomainIn.get(j)==0) j++;					//по аналогии.
				a1 = listDomainOut.get(i-counterI);						//первый аргумент
				a2 = listDomainOut.get(i + 1-counterI);						//второй
				b1 = listDomainIn.get(j);					
				b2 = listDomainIn.get(j+1);
				if(a1 == b1 && a2 == b2) {			//ЭТОТ БЛОК ЗАМЕНИТЬ МЕТОДОМ?
					dirtyListPairsOfBlocks.add(a1);
					dirtyListPairsOfBlocks.add(a2);
					dirtyListPairsOfBlocks.add(listDomainOut.get(i + 2));
					dirtyListPairsOfBlocks.add(listDomainOut.get(i + 3));
					dirtyListPairsOfBlocks.add(listDomainIn.get(j + 2));
					dirtyListPairsOfBlocks.add(listDomainIn.get(j + 3));
					dirtyListPairsOfBlocks.add(0);						//разделитель пар
					while(listDomainIn.get(j + 4) != 0) {
						j += 2;
						dirtyListPairsOfBlocks.add(a1);
						dirtyListPairsOfBlocks.add(a2);
						dirtyListPairsOfBlocks.add(listDomainOut.get(i+2));
						dirtyListPairsOfBlocks.add(listDomainOut.get(i+3));
						dirtyListPairsOfBlocks.add(listDomainIn.get(j+2));
						dirtyListPairsOfBlocks.add(listDomainIn.get(j+3));
						dirtyListPairsOfBlocks.add(0);
					}
				}
				else while(listDomainIn.get(j+4) != 0)	j += 2;
				j++;
			}
			
			if (listDomainOut.get(i+4) != 0) {
				counterI += 2;
				i-=2;
			}
			else if (listDomainOut.get(i+4) == 0) {
				i++;
				counterI = 0;
			}
		}
		//System.out.println("dirtyListPairsOfBlocks("+dirtyListPairsOfBlocks.size()+"): " + dirtyListPairsOfBlocks);
		
		//----------------перевод в список блоков без связей с самим собой и без атрибутов------------------
		//удаление атрибутов и соединений с собой
		for (int i = 0; i < dirtyListPairsOfBlocks.size(); i += 7) {
			if(dirtyListPairsOfBlocks.get(i + 2) != dirtyListPairsOfBlocks.get(i + 4)) {	//если блок соединяется не с собой
				//pureListPairsOfBlocks.add(dirtyListPairsOfBlocks.get(i));
				//pureListPairsOfBlocks.add(dirtyListPairsOfBlocks.get(i+1));
				pureListPairsOfBlocks.add(dirtyListPairsOfBlocks.get(i+2));
				pureListPairsOfBlocks.add(dirtyListPairsOfBlocks.get(i+3));
				pureListPairsOfBlocks.add(dirtyListPairsOfBlocks.get(i+4));
				pureListPairsOfBlocks.add(dirtyListPairsOfBlocks.get(i+5));
				pureListPairsOfBlocks.add(0);
			}
		}
		//----------------удаление одинаковых свзяей (разные параметры)-------------
		correctListPairsOfBlocks.add(pureListPairsOfBlocks.get(0));
		correctListPairsOfBlocks.add(pureListPairsOfBlocks.get(1));
		correctListPairsOfBlocks.add(pureListPairsOfBlocks.get(2));
		correctListPairsOfBlocks.add(pureListPairsOfBlocks.get(3));
		correctListPairsOfBlocks.add(0);
		
		for (int i = 5; i < pureListPairsOfBlocks.size(); i += 5) {
			int coincidenceCounter = 0;
			iterationMetka:
			for (int j = 0; j < correctListPairsOfBlocks.size(); j += 5) {
				if ((pureListPairsOfBlocks.get(i)==correctListPairsOfBlocks.get(j)) && (pureListPairsOfBlocks.get(i+1) == correctListPairsOfBlocks.get(j+1)) && (pureListPairsOfBlocks.get(i+2) == correctListPairsOfBlocks.get(j+2)) && (pureListPairsOfBlocks.get(i+3)==correctListPairsOfBlocks.get(j+3))) {
					coincidenceCounter++;
					break iterationMetka;
				}
			}
			if (coincidenceCounter == 0) {
				correctListPairsOfBlocks.add(pureListPairsOfBlocks.get(i));
				correctListPairsOfBlocks.add(pureListPairsOfBlocks.get(i+1));
				correctListPairsOfBlocks.add(pureListPairsOfBlocks.get(i+2));
				correctListPairsOfBlocks.add(pureListPairsOfBlocks.get(i+3));
				correctListPairsOfBlocks.add(0);
			}
		}
		System.out.println("correctListPairsOfBlocks("+correctListPairsOfBlocks.size()+"): " + correctListPairsOfBlocks);
		
		//-------------конструктор цепей blockChainList--------------
		
		//если нет пар блоков
		if(correctListPairsOfBlocks.size()==0) {
			System.out.println("no block chains");
		}
		//если только 1 пара
		if(correctListPairsOfBlocks.size()==5) {
			System.out.println(correctListPairsOfBlocks.get(0)+"("+correctListPairsOfBlocks.get(1)+") -> ("+correctListPairsOfBlocks.get(3)+")"+correctListPairsOfBlocks.get(2));
		}
		//если пар > 1
		listic.add(0);
		//однопоточная реализация
		//for (int i = 0; i < correctListPairsOfBlocks.size(); i += 5) recursion(i,0);
		
		//многопоточная реализация
		multithreadedRecursion();
		//многопоточная реализация КОНЕЦ
		
		clearing();
		blockChainList.addAll(listic);
		System.out.println("blockChainList("+blockChainList.size() +"):"+ blockChainList);
		//System.out.println("listOfZero("+listOfZero.size() +"):"+ listOfZero);
		
		outPrint();
		
	}
	//--------------метод многопоточной реализации-----------------------
	private void multithreadedRecursion() {
		//разбиение по 0
		//Multithread multithread = new Multithread();
		
		List<Integer> listOfPairsZero = new ArrayList<Integer>();
		for (int i = 0; i < correctListPairsOfBlocks.size(); i++) {
			if (correctListPairsOfBlocks.get(i) == 0) {
				listOfPairsZero.add(i);
			}
		}
		//System.out.println("listOfPairsZero:"+listOfPairsZero);
		int list1Helper = (int)listOfPairsZero.size()/4;
		listic1.add(-1);
		Multithread listic111 = new Multithread(listOfPairsZero,correctListPairsOfBlocks,listic);
		
		for (int i = 0; i < list1Helper; i++) {
			listic1.add(listOfPairsZero.get(0));
			listOfPairsZero.remove(0);
		}
		for (int i = 0; i < list1Helper; i++) {
			listic2.add(listOfPairsZero.get(0));
			listOfPairsZero.remove(0);
		}
		for (int i = 0; i < list1Helper; i++) {
			listic3.add(listOfPairsZero.get(0));
			listOfPairsZero.remove(0);
		}
		int number = listOfPairsZero.size();
		for (int i = 0; i < number-1; i++) {
			listic4.add(listOfPairsZero.get(0));
			listOfPairsZero.remove(0);
		}
		
		//System.out.println("listic1:"+listic1);
		//System.out.println("listic2:"+listic2);
		//System.out.println("listic3:"+listic3);
		//System.out.println("listic4:"+listic4);
		
		Multithread listic11 = new Multithread(listic1,correctListPairsOfBlocks,listic);
		Multithread listic22 = new Multithread(listic2,correctListPairsOfBlocks,listic);
		Multithread listic33 = new Multithread(listic3,correctListPairsOfBlocks,listic);
		Multithread listic44 = new Multithread(listic4,correctListPairsOfBlocks,listic);
		
		//System.out.println("cLPOB:"+correctListPairsOfBlocks);
		//System.out.println("listic:"+listic);
		long start1 = System.currentTimeMillis();
		listic11.start();
		//System.out.println("listic11:"+listic11);
		listic22.start();
		listic33.start();
		listic44.start();
		
		try {
			
			System.out.println("Ожидание завершения потоков.");
			listic11.join();
			listic22.join();
			listic33.join();
			listic44.join();
			
			
		}catch(Exception f) {}
		long finish1 = System.currentTimeMillis();
		System.out.println("time: "+ (finish1-start1));
		
		listic.addAll(listic11.KO);
		listic.addAll(listic22.KO);
		listic.addAll(listic33.KO);
		listic.addAll(listic44.KO);
		
	}

//----------------------------------------START------------------------------
	void recursion(int position1, int position2, List<Integer> correctListPairsOfBlocks,List<Integer> KO) {
			//System.out.println(correctListPairsOfBlocks);
			//synchronized(listic) {
			listic.add(correctListPairsOfBlocks.get(position1));
			//System.out.println(0);
			listic.add(correctListPairsOfBlocks.get(position1+1));
			listic.add(correctListPairsOfBlocks.get(position1+2));
			listic.add(correctListPairsOfBlocks.get(position1+3));
			stopList.add(correctListPairsOfBlocks.get(position1));
			stopList.add(correctListPairsOfBlocks.get(position1+2));
			for (int j = position2; j < correctListPairsOfBlocks.size(); j += 5) {
				
				stopPoint:
				if (correctListPairsOfBlocks.get(j) == correctListPairsOfBlocks.get(position1 + 2)) {
					for (int k = 0; k < stopList.size(); k++) {
						if (correctListPairsOfBlocks.get(j + 2) == stopList.get(k)) {
							break stopPoint;
						}
					}
					int counter = j;
					recursion(counter,0,correctListPairsOfBlocks,KO);
					listic.add(correctListPairsOfBlocks.get(position1));
					listic.add(correctListPairsOfBlocks.get(position1+1));
					listic.add(correctListPairsOfBlocks.get(position1+2));
					listic.add(correctListPairsOfBlocks.get(position1+3));
					stopList.add(correctListPairsOfBlocks.get(position1));
					stopList.add(correctListPairsOfBlocks.get(position1+2));
				}
			}
			listic.add(0);
//----------------------------------------------------END-------------------------
			if(listic.get(listic.size()-1)==0 && listic.get(listic.size()-2)==0) {
				listic.remove(listic.size()-1);
			}
			int positionOfZero = 0;
			aPoint:
			for (int cleaner = 2; cleaner < listic.size(); cleaner++) {
				if (listic.get(listic.size()-cleaner) == 0) {
					positionOfZero = listic.size()-cleaner;
					break aPoint;
				}
			}
			
			int lengthOfWord = listic.size()-positionOfZero-2;
			listCycle:
			for (int cleaner3 = 0; cleaner3 < positionOfZero; cleaner3 += 1) {
				int cleaner2 = 0;
				bPoint:
				for (int cleaner4 = 0; cleaner4 < lengthOfWord;cleaner4++) {
					if (listic.get(positionOfZero+cleaner4+1) != listic.get(cleaner3+cleaner4)) {
						break bPoint;
					}
					if (listic.get(positionOfZero+cleaner4+1) == listic.get(cleaner3+cleaner4)) {
						cleaner2++;
					}
					if (cleaner2 == lengthOfWord) {
						for(int i1 = 0; i1 < lengthOfWord+1;i1++) {
							listic.remove(listic.size()-1);
						}
						break listCycle;
					}
				}
			}
			stopList.clear();
			//System.out.println("listic:"+listic);
			//Multithread multithread = new Multithread();
			//multithread.Ko.add(listic);
			//}
			KO.addAll(listic);
		}
//-------------подчистка blockChainList от повторений в начале-------------
		private void clearing() {
			for (int i = 0; i < listic.size(); i++) {
				if(listic.get(i) == 0) {
					listOfZero.add(i);
				}
			}
			for (int i = 0; i < listOfZero.size() - 2; i++) {
				sumCycle:
				for (int j = i + 1; j < listOfZero.size() - 1; j++) {
					int counter = 0;
					cycle:
					for (int k = 1; k < listOfZero.get(i+1) - listOfZero.get(i); k++) {
						if (listic.get(listOfZero.get(i+1) - k) == listic.get(listOfZero.get(j+1)-k)) {
							counter++;
						}
						if (listic.get(listOfZero.get(i+1) - k) != listic.get(listOfZero.get(j+1)-k)) {
							break cycle;
						}
					}
					
					if (counter == (listOfZero.get(i+1) - listOfZero.get(i) - 1)) {
						for (int c = 0; c < listOfZero.get(i+1) - listOfZero.get(i); c++) {
							listic.remove(listOfZero.get(i+1)-c);
						}
						i-=1;
						listOfZero.clear();
						for (int ii = 0; ii < listic.size(); ii++) {
							if(listic.get(ii) == 0) {
								listOfZero.add(ii);
							}
						}
						break sumCycle;
					}
				}
			
			}
			for (int i = 0; i < listOfZero.size() - 1; i++) {
				sumCycle:
				for (int j = 0; j < i; j++) {
					int counter = 0;
					cycle:
					for (int k = 1; k < listOfZero.get(i+1) - listOfZero.get(i); k++) {
						if (listic.get(listOfZero.get(i+1) - k) == listic.get(listOfZero.get(j+1)-k)) {
							counter++;
						}
						if (listic.get(listOfZero.get(i+1) - k) != listic.get(listOfZero.get(j+1)-k)) {
							break cycle;
						}
					}
					
					if (counter == (listOfZero.get(i+1) - listOfZero.get(i) - 1)) {
						for (int c = 0; c < listOfZero.get(i+1) - listOfZero.get(i); c++) {
							listic.remove(listOfZero.get(i+1)-c);
						}
						i-=1;
						listOfZero.clear();
						for (int ii = 0; ii < listic.size(); ii++) {
							if(listic.get(ii) == 0) {
								listOfZero.add(ii);
							}
						}
						break sumCycle;
					}
				}
			
			}
			
		}
		private void outPrint() {
			for (int i = 0; i < listOfZero.size()-1; i++) {
				int length = listOfZero.get(i + 1) - listOfZero.get(i) - 1;
				int counter = 0;
				System.out.print(listic.get(listOfZero.get(i) + 1) + "(" + listic.get(listOfZero.get(i) + 2) + ") -> (" + listic.get(listOfZero.get(i)+4) + ")"+listic.get(listOfZero.get(i)+3));
				length -= 4;
				while(length > 3) {
					counter++;
					System.out.print("("+listic.get(listOfZero.get(i)+2+counter*4)+") - > ("+listic.get(listOfZero.get(i)+4+counter*4)+")"+listic.get(listOfZero.get(i)+3+counter*4));
					length -= 4;
				}
				System.out.println();
			}
			listic.clear();
		}

		
	}

