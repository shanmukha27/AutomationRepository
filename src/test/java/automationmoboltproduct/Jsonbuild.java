package automationmoboltproduct;

public class Jsonbuild {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Test Message: Welcome to Hyderabad! Testing Split Function";
		String sp[] = str.split("Message:");
		System.out.println(sp[0]);
		System.out.println(sp[1]);
		String teststr = sp[1];
		if(sp[1].contains("sequence")){
			System.out.println("If is true");
		}
		

	}

}
