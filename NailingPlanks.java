package CodelityTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NailingPlanks {
	class Nail implements Comparable<Nail>{
		int index;
		int Value;
		public Nail(int index, int value) {
			super();
			this.index = index;
			Value = value;
		}
		@Override
		public String toString() {
			return "Nail [index=" + index + ", Value=" + Value + "]";
		}
		@Override
		public int compareTo(Nail o) {
			return Value-o.Value;
		}
		
	}
	class Plank implements Comparable<Plank>{
		int start;
		int end;
		public Plank(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public String toString() {
			return "Plank [start=" + start + ", end=" + end + "]";
		}
		@Override
		public int compareTo(Plank o) {
			return start -o.start;
		}
		
	}
	public int solution(int[]A,int[]B,int[]C) {
		Set<Integer>nailsSeen=new HashSet<>();
		List<Nail>nails=new ArrayList<>();
		for(int i=0;i<C.length;i++) {
			if(!nailsSeen.contains(C[i])) {
				nails.add(new Nail(i+1,C[i]));
				nailsSeen.add(C[i]);
			}
		}
		Collections.sort(nails);
		
		List<Plank> planks= new ArrayList<>();
		for(int i=0;i<A.length;i++) {
			planks.add(new Plank(A[i],B[i]));
			
		}
		Collections.sort(planks);
		for(int i=0;i<planks.size()-2;i++) {
			while(i>0 && planks.get(i).end>planks.get(i+1).end) {
				planks.remove(i--);
			}
		}
		int maxMin=0;
		for(Plank plank:planks) {
			int minIndex=getMinNailIndex(nails, plank);
			if(minIndex == -1) {
				return -1;
			}
			maxMin =Math.max(maxMin,minIndex);
		}
		return maxMin;
	}
	
	public int getMinNailIndex(List<Nail> nails,Plank plank) {
		int start =0;
		int end =nails.size() -1;
		int result=-1;
		while(start <= end) {
			int center =(start+end)/2;
			if(nails.get(center).Value<plank.start){
				start=center+1;
				}else if(nails.get(center).Value>plank.end) {
					end =center-1;
				}else {
					result =center;
					end=center-1;
				}
			}
			if(result == -1) {
				return -1;
			}
			int minIndex = nails.get(result).index;
			for(int i=result +1;i<nails.size();i++) {
				if(nails.get(i).Value<= plank.end){
					minIndex =Math.min(minIndex,nails.get(i).index);
				}else {
					return minIndex;
				}
			}
			return minIndex;
		}
		
	}


