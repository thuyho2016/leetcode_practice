package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Employees can have a list of other employees reporting to him. at least one employee report. 
 * An employee with at least one report is called a manager.
 * 
 * Implement closestCommonManager method to find the closest manager to employees.
 * (Find number of Employees Under every Employee)
 * 
 * Tree structure:
 * Bill -> Dom, Samir, Michael
 * Dom -> Bob, Peter, Porter
 * Peter -> Milton, Nina
 *  
 *  Example:
 *  closestCommonManager(Milton, Nina) = Peter
 *  
 * Similar with Given values of two values n1 and n2 in a Binary Search Tree, 
 * find the Lowest Common Ancestor (LCA)
*/


public class ClosestCommonManager {
	
	static class Node {
	    public int val;       // data stored in this node is integer type
	    public Node next;  // link to next node in the list

	   
	    public Node(int x, Node next) {
	        this.val = x;
	        this.next = next;
	    }
	}
	
	static class Employee {
		String name;
		List<Employee> reporters;
		
		public Employee(String name) {
	        this.name = name;
	    }
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Employee> getEmployees() {
			return reporters;
		}
		
		public void setEmployees(List<Employee> employees) {
			this.reporters = employees;
		}
	}
	    
	//same with lca method
	private static Employee closestCommonManager2(Employee ceo, Employee nodeA, Employee nodeB) {
		if (ceo == null || nodeA == null || nodeB == null)
			return null;
		
		if (ceo.name == nodeA.name || ceo.name == nodeB.name)
			return ceo;

		List<Employee> children = ceo.getEmployees();
		int count = 0;
		
		Employee closestManager = null;
		
		for (Employee n : children) {
			Employee tmpNode = closestCommonManager2(n, nodeA, nodeB);

			if (tmpNode != null) {
				closestManager = tmpNode;
				count++;
			}
		}
		if (count == 2) {
			return ceo;
		}

		return closestManager;
	}


	public static Employee closestCommonManager(Employee ceo, Employee firstEmployee, Employee secondEmployee) {
        if (ceo == null || firstEmployee == null || secondEmployee == null)
            return null;
        
        if (!covers(ceo, firstEmployee) && covers(ceo, secondEmployee))
            return null;
        
        Queue<Employee> workingQueue = new LinkedList<>();
        workingQueue.offer(ceo);  //add
        
        Employee closestKnownManager = null;
        
        while (!workingQueue.isEmpty()) {
            Employee employee = workingQueue.poll();  //remove
            
            if (covers(employee, firstEmployee) && covers(employee, secondEmployee)) {
                closestKnownManager = employee;
                
                for (Employee e : employee.reporters) {
                    workingQueue.offer(e);  //insert
                }
            }
        }
        return closestKnownManager;
    }

    public static boolean covers(Employee manager, Employee employee) {
        if (manager == null)
            return false;
        
        if (manager.name.equals(employee.name))
            return true;
        
        if (manager.reporters == null)
            return false;

        boolean cover = false;
        for (Employee e : manager.reporters) {
            cover = cover || covers(e, employee);
        }
        return cover;
    }

	    
	public static void main(String[] args) {
	    Employee samir = new Employee("samir");
	    Employee dom = new Employee("dom");
	    Employee michael = new Employee("michael");
	
	
	    Employee peter = new Employee("peter");
	    Employee porter = new Employee("porter");
	    Employee bob = new Employee("bob");
	
	    dom.reporters = Arrays.asList(bob, peter, porter);
	
	    Employee milton = new Employee("milton");
	    Employee nina = new Employee("nina");
	
	    peter.reporters = Arrays.asList(milton, nina);
	
	    Employee bill = new Employee("bill");
	    bill.reporters = Arrays.asList(dom, samir, michael);
	
	    //ceo = bill
	    Employee manager = closestCommonManager(bill, milton, nina);
	    System.out.println(manager.name);  //peter
	    System.out.println(closestCommonManager(bill, nina, porter).name); //dom
	    System.out.println(closestCommonManager(bill, nina, samir).name);  //bill
	    System.out.println(closestCommonManager(bill, peter, nina).name); //peter
	}
}
