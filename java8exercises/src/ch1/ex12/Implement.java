package ch1.ex12;

public class Implement implements K {

	@Override
	public void f() {
		System.out.println("implemnt f()");
	}

	public static void main(String[] args) {
		Implement i = new Implement();
		i.f();
	}
}
