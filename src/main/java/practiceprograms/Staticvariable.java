package practiceprograms;

public class Staticvariable {
	
	int i = 0; // Instance Variable: Memory is allocated every time a object is called.
	static int j = 0; //Static Variable: Memory is allocated only once for Static Variable
	
	Staticvariable(){
		i++;
		j++;
		System.out.println("_____________________________");
		System.out.println("i value is: "+i);
		System.out.println("Static Variable value is: ...");
		System.out.println("j value is: "+j);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Staticvariable obj = new Staticvariable();
		Staticvariable obj1 = new Staticvariable();
		Staticvariable obj2 = new Staticvariable();

	}

}
