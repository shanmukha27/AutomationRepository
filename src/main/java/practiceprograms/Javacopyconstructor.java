package practiceprograms;

public class Javacopyconstructor {
	
	int id;
	String name;
	
	Javacopyconstructor(int i, String str){
		id = i;
		name = str;
		System.out.println("First Constructor:"+id +" "+name);
	}
	
	Javacopyconstructor(Javacopyconstructor s){
		id = s.id;
		name = s.name;
		System.out.println("Second Constructor:"+id +" "+name);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Javacopyconstructor obj =  new Javacopyconstructor(1, "Santosh Kumar");
		Javacopyconstructor obj1 = new Javacopyconstructor(obj);
	}

}
