package practiceprograms;

public class Staticmethod { //Static method belongs to a class rather than object of a class. Static Method can
									// access Static members and can alter data in them
	
	int i = 0;
	static String name = "Santosh";
	
	static void changemethod(){
		name = "Santosh Kumar";
		System.out.println("The changed name is: "+name);	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Staticmethod obj = new Staticmethod();
		obj.changemethod();

	}

}
