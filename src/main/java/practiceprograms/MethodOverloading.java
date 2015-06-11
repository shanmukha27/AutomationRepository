package practiceprograms;

public class MethodOverloading { // Method Overloading is having multiple methods by same name but with different parameters

	void sum(int a, int b){
		System.out.println("The sum is: "+(a+b));
	}
	void sum(int a, int b, int c){
		System.out.println("The sum is:"+(a+b+c));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MethodOverloading obj = new MethodOverloading();
		obj.sum(2, 3);
		obj.sum(2, 5, 9);
	}

}
