package practiceprograms;

public class ParameterizedConstructor { // Parameterized Constructor is used to provide different values to distinct objects.
	int id;
	String name;
	ParameterizedConstructor(int i, String str){
		id = i;
		name = str;
	}
	void display(){
		System.out.println("The values are:"+id+" "+name);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParameterizedConstructor parm = new ParameterizedConstructor(200, "Santosh");
		ParameterizedConstructor parm1 = new ParameterizedConstructor(300, "Kumar");
		parm.display();
		parm1.display();
	}

}
