package ch14;

import java.util.*;

public class MyArrayList2_Generic<T> {
    private Object[] arr ;
    private int capacity = 10;
    private int size = 0;

    public MyArrayList2_Generic() {
        arr = new Object[capacity];
    }

    public void add(T value) {
        if (size >= capacity) 
        	increaseCapacity();
        
        arr[size++] = value;
    }
    
    public void add(int idx, T value) {
    	// 용량 MAX -> 50% 증가 & 값 복사
    	
		if(size==capacity) {
			increaseCapacity();
		}
		
    	for(int i=size-1;i>=idx;i--) {
    		arr[i+1]=arr[i];
    	}
    	
    	//idx 자리에 value를 넣는
    	arr[idx] = value;
    	size++;
    }
    
    private void increaseCapacity() {
    	 // 수용 X -> arr 크기 UP
    	 capacity = capacity + capacity/2;
        Object[] tmp = new Integer[capacity];
         // 원래 값 tmp로 카피
         for (int i = 0; i < size; i++) {
             tmp[i] = arr[i];
         }
         // arr이 용량이 늘어난 배열을 가리키도록 함
         arr = tmp;
     
    }

    
    private void remove() {
    	// 하나씩 줄여가자
    	if(size>0) size--;
    }
    
    private void remove(int idx) {
    // 3번이라면, 4번을 앞으로 쭊쭊떙겨야함. [3]=4 [4]=5... size까지
    // 해당 인덱스 제거, 한 칸씩 당기기
    	for(int i=idx;i<size;i++)
    		arr[i]=arr[i+1];
    	size--;
    }
    
    
    public T get(int idx) {
        return (T)arr[idx];
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
//         ArrayList<Integer> list = new ArrayList<>();
//        MyArrayList2_Generic list = new MyArrayList2_Generic();
//        System.out.println(list);
        
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//        }

        // list.get(100);

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//         System.out.println(list.toString());
//        
//
//         list.add(3, 100);
//         System.out.println(list);

        // list.remove(3);
        // list.remove(Integer.valueOf(100));
        // System.out.println(list);
        
//          MyArrayList2_Generic list2 = new MyArrayList2_Generic();
          MyArrayList2_Generic<String> list2 = new MyArrayList2_Generic();
          list2.add("문자열은 못 하잖아?");
          list2.add("아쉽네");
          list2.add("하지만 Generic<String>을 사용하면 할 수 있지!");
          list2.add("하나 둘씩 잊어가");
          list2.add("네 기억들을 묻어놔");
          System.out.println(list2.toString());
          
          //list2.remove();
          list2.remove(2);
          System.out.println(list2.toString());
    }

	@Override
	public String toString() {
		if (size == 0) return "[]";
		String result ="[";
		
		for(int i=0;i<size-1;i++) {	
			result += arr[i] +", ";
			if((i+1)%10==0) result+="\n";
		}
		result += arr[size-1];
		result += "]";
		return result;
		//return "MyArrayList2 [arr=" + Arrays.toString(arr) + ", capacity=" + capacity + ", size=" + size + "]";
	}
    
}

