package com.example;

import static org.junit.Assert.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	
	@Test
	public void TestSplit() {
		String parameters = "some.id=OrderItemId_1&referenceId=123=456&some.name=Line_Type1=Line_Type";
		
		String[] array = parameters.split("&(?=[^&]+=)");
		

		
		System.out.println(Arrays.toString(array));
		
	}
	
	@Test
	public void TestQueue() {
		//longestSubstringWithoutRepeating("eddy");
		
		//reverseList(new LinkedList2(new Node(1,new Node(2,new Node(3)))));
		
		OffsetDateTime date1 = OffsetDateTime.parse("2023-07-25T18:00:00Z");
		OffsetDateTime date2 = OffsetDateTime.parse("2023-07-25T21:00:00+03:00");
		int comparisonResult = date1.compareTo(date2);
		OffsetDateTime dateSameTimeZone2 = date2.atZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime();
		comparisonResult = date1.compareTo(dateSameTimeZone2);
		Assert.assertNotSame(date1,date2);
		Assert.assertEquals(date1, dateSameTimeZone2);
		
		List<Item1> lst=new ArrayList<>(Arrays.asList(new Item1("suspend"),new Item1("terminate"),new Item1("resume")));
		
		for(int i=0;i<lst.size();i++) {
			Item1 item=lst.get(i);
			if(item.name.equals("suspend")) {
				lst.get(1).show=false;
			}
			if(item.show) {
				System.out.println(item.name);
			}
			if(item.name.equals("resume")) {
				lst.get(1).show=true;
				lst.add(lst.get(1));
			}
			
		}
		Assert.assertTrue("InvalidProductOfferingActionValidationMessage-aggregate".contains("aggregate"));
		
		
	}
	
	 static class Item1{
		 public Item1(String name) {
			 this.name=name;
		 }
		 boolean show=true;
		 String name;
	 }
	
	 static class Node{
		    int data;
		        Node next;
		        Node(int data){this.data = data; this.next = null;}
		        Node(int data, Node next){this.data = data; this.next = next;}
		    }
		  
		    static class LinkedList2{
		        Node head;
		        LinkedList2(){this.head = null;}
		        LinkedList2(Node head){this.head = head;}
		    }
		    
	
		    public void reverseList(LinkedList2 list){
		        Stack<Node> stack=new Stack<>();
		        Node currNode=list.head;
		        while(currNode!=null) {
		        	stack.add(currNode);
		        	currNode=currNode.next;
		        }
		        list.head=null;
		        currNode=null;
		        while(!stack.isEmpty()){
		            Node elem = stack.pop();
		            elem.next=null;
		            if(list.head == null){
		                list.head=elem;
		                currNode=list.head;
		            }
		            else {
		            	currNode.next=elem;
		            	currNode=currNode.next;
		            }
		        }

		    }
	
	 public int maximumSubarray(int[] arr){
	      int max=Integer.MIN_VALUE;
	      for(int i=0;i<arr.length;i++) {
	    	  int localSum=0;
	    	  for(int j=i;j<arr.length;j++) {
	    		  localSum+=arr[j];
	    		  max=Math.max(max, localSum);
	    	  }
	    	  
	      }
	      
	      return max;
	 
	 }
	
	
	@Test
	public void TestListContains() {
		List<String> lst=new ArrayList();
		lst.add("a");
		lst.add("b");
		lst.add("c");
		lst.add(null);
		
		
		assertTrue(lst.contains(null));
		//assertFalse(lst.contains(null));
		
		
	}
	
	@Test
	public void TestPathsInMatrix() {
		int[][] matrix= {
				  {0,0,1,0,1},
				  {0,0,0,0,1},
				  {0,0,1,0,0},
				  {1,0,0,0,0},
				};	
		System.out.println(pathsInMatrix(matrix, 0,0));
	}
	
	int pathsInMatrix(int[][] matrix, int iIndex, int jIndex) {
		if (iIndex == matrix.length || jIndex == matrix[0].length || matrix[iIndex][jIndex] == 1) {
			return 0;
		}
		if (iIndex == matrix.length - 1 && jIndex == matrix[0].length - 1) {
			return 1;
		}
		return pathsInMatrix(matrix, iIndex + 1, jIndex) + pathsInMatrix(matrix, iIndex, jIndex + 1);

	}

	@Test
	public void TestWaysToClimb() {
		int[] jumps = { 2, 4, 5, 8 };
		System.out.println(waysToClimb(jumps, 10, " "));
	}

	int waysToClimb(int[] jumps, int totalOfSteps, String space) {
		int count = 0;
		if (totalOfSteps < 0) {
			return 0;
		}
		if (totalOfSteps == 0) {
			return 1;
		}
		for (int jump : jumps) {
			if (jump <= totalOfSteps) {
				System.out.println(space + "jump :" + jump + " totalOfSteps: " + totalOfSteps);
				count += waysToClimb(jumps, totalOfSteps - jump, space + " ");
			}
		}
		System.out.println("return " + count);
		return count;
	}

	@Test
	public void TestSeeBattle() {
		SeeBattle seeBattle = new SeeBattle();
		for (int i = 0; i < 10; i++) {
			seeBattle.arrangeShips();
		}
	}
	@Test
	public void shouldAnswerWithTrue() {

		int[] arr = { 4, 5, 1, -3, 6 };

		System.out.println(findPair(arr, 11));
		assertTrue(true);
	}

	static boolean findPair(int[] arr, int k) {
		Map<Integer, Boolean> visited = new HashMap<>();
		for (int element : arr) {
			if (visited.containsKey(k - element)) {
				return true;
			} else {
				visited.put(element, true);
			}
		}
		return false;
	}

	@Test
	public void whenItemsIsAddedToCircularQueue_thenNoArrayIndexOutOfBounds() {
		int QUEUE_CAPACITY = 10;
		int[] circularQueue = new int[QUEUE_CAPACITY];
		int itemsInserted = -1;
		for (int value = 0; value < 100; value++) {
			int writeIndex = ++itemsInserted % QUEUE_CAPACITY;
			System.out.println(itemsInserted + " % 10 =" + writeIndex);
			circularQueue[writeIndex] = value;
		}
	}

	@Test
	public void TreeMapTest() {
		String[] cats = new String[] { "Fluffy", "Abby", "Boris", "Ginger", "Grey", "Snowy", "Boss", "Waldo", "Tom",
				"Garfield" };

		TreeMap<String, Cat> treeMap = addCatsToTreeMap(cats);
		System.out.println(treeMap.subMap("Boris", true, "Snowy", true));
	}

	public static int ubound(LinkedList<Integer> ln, int x) {
		int l = 0;
		int h = ln.size();
		while (l < h) {
			int mid = (l + h) / 2;
			if (ln.get(mid) <= x)
				l = mid + 1;
			else
				h = mid;
		}
		return l;
	}

	@Test
	public void addToSortedList() {
		LinkedList<Integer> ln = new LinkedList<>();
		ln.add(4);
		ln.add(6);
		ln.add(ubound(ln, 5), 5);
		System.out.println(ln);

	}

	public static TreeMap<String, Cat> addCatsToTreeMap(String[] cats) {
		TreeMap<String, Cat> myCats = new TreeMap<String, Cat>();
		for (int i = 0; i < cats.length; i++) {
			Cat cat = new Cat(cats[i]);
			myCats.put(cat.name, cat);
		}
		return myCats;
	}

	public static class Cat {
		String name;

		public Cat(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name != null ? name.toUpperCase() : null;
		}
	}
	
	
	@Test
	public void loopNotRecursion() {
		Item root = new Item("root");
		createChildren(root, root.id+"first_level");
		root.items.forEach(item -> {
			createChildren(item, item.id+"second_level");
			item.items.forEach(item2 -> createChildren(item2, item2.id+"third_level"));
		});
		List<Item> items = collectChildrenNoRecursion(root);
		System.out.println(items.size());
		System.out.println(items);
		
		List<Item> items2 = collectChildren(root);
		System.out.println(items2.size());
		System.out.println(items2);
		

		
	}
	
	@Test
	public void itemRecursion() {
		Item root = new Item("a");
		Item child_a1=new Item("child_a1");
		Item child_a2=new Item("child_a2");
		child_a2.primary=true;
		root.items.add(child_a1);
		root.items.add(child_a2);
		root.items.add(new Item("child_a3"));
		
		createChildren(child_a1, child_a1.id+"_third_level");
		createChildren(child_a2, child_a2.id+"_third_level");


		//child_a2_third_level3
		System.out.println(getParentPrimary(root, "child_a2_third_level3"));
		
	}
	
	
	
	private Item getParentPrimary(Item rootItem, String id) {
		List<Item> parents = new ArrayList<>();
		getParentsPrimary(rootItem, id, parents);
		return parents.get(0);
	}
	private boolean getParentsPrimary(Item rootItem, String id, List<Item> parents) {
		boolean found = false;
		if(rootItem.id.equals(id)) {
			return true;
		}
		else {
			found = rootItem.items.stream().anyMatch(orderItem -> getParentsPrimary(orderItem, id, parents));
		}
		System.out.println((found) ? "found "+rootItem.id : "### "+rootItem.id);
	
		if (found && Boolean.TRUE.equals(rootItem.primary)) {
			parents.add(rootItem);
		}
		return found;
	}
	
	
	
	List<Item> collectChildrenNoRecursion(Item root) {
		List<Item> items = new ArrayList<>();
		Stack<Item> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Item current = stack.pop();
			items.add(current);
			current.items.forEach(stack::push);
		}
		return items;
	}
	
	List<Item> collectChildren(Item root) {
		return Stream.concat(Stream.of(root), root.items.stream().map(this::collectChildren).flatMap(List::stream)).collect(Collectors.toList());
	}
	
	private void createChildren(Item root,String prefix) {
		for(int i=0;i<5;i++) {
			root.items.add(new Item(prefix+i));
		}
	}

	public static class Item {
		@Override
		public String toString() {
			return "Item [id=" + id + ", primary=" + primary + ", items=" + items + "]";
		}
		String id;
		boolean primary;
		public Item(String id) {
			this.id=id;
		}
		List<Item> items = new ArrayList<>();
	}
	
	//final int deadLock = -1;
	
	public static class Neighbors {
		List<Integer> neighbors = new ArrayList<>();

		public Neighbors(List<Integer> neighbors) {
			this.neighbors = neighbors;
		}
	}

}
