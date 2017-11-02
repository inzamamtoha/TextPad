abstract class Parent
{
	int a;
	Parent(int a)
	{
		this.a = a;
	}
	void echo()
	{
		System.out.println(a);
	}
}
class child extends Parent
{
	child()
	{
		super(3);
	}
}
public class Delete
{
	public static void main(String[] args)
	{
		Parent parent = new child();
		parent.echo();
	}
}
