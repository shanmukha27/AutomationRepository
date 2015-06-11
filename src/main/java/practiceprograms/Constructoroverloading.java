package practiceprograms;

public class Constructoroverloading { //Constructor Overloading is a technique in Java in which a class can have any number of constructors 
									  // prior all of them have different number of paramenters.

	Constructoroverloading(int i, String a, int j, String b){
		System.out.println("Printing the values: "+i+" "+a+" "+j+" "+b);
	}
	
	Constructoroverloading(int i, String a, int j, String b, int k, String c){
		System.out.println("Printing the values: "+i+" "+a+" "+j+" "+b+" "+c);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Constructoroverloading cons = new Constructoroverloading(1, "Kunapareddy", 2, "Shanmukha");
		Constructoroverloading cons1 = new Constructoroverloading(1, "Kunapareddy", 2, "Shanmukha", 3, "Santosh");

	}

}
