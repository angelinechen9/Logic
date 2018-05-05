
public class Main {
	public static void main(String[] args) {
		System.out.println("(~p|q)&(p|~q)" + " " + INDO.simplify("(~p|q)&(p|~q)"));
		System.out.println("~p|q" + " " + INDO.simplify("~p|q"));
		System.out.println("~p|~q" + " " + INDO.simplify("~p|~q"));
		System.out.println("~p&~q" + " " + INDO.simplify("~p&~q"));
		System.out.println("(p|q)&(p|r)" + " " + INDO.simplify("(p|q)&(p|r)"));
		System.out.println("(p&q)|(p&r)" + " " + INDO.simplify("(p&q)|(p&r)"));
		System.out.println("p|q|r" + " " + INDO.simplify("p|q|r"));
		System.out.println("p&q&r" + " " + INDO.simplify("p&q&r"));
	}
}
