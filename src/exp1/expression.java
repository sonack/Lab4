package exp1;

import java.util.ArrayList;

//deal with expression
public class expression {
	private ArrayList<singleTerm> polynomial = new ArrayList<>();
	
	public expression(String polynomialStr) {
		String product[] = polynomialStr.split("\\+");
		for(int i = 0; i < product.length; i++)
			polynomial.add(new singleTerm(product[i]));
		System.out.println(this);
	}
	
	@Override
	public String toString()
	{
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < polynomial.size(); i++)
		{
			if(!polynomial.get(i).toString().startsWith("0"))
			{
				s.append(polynomial.get(i).toString());
				s.append("+");
			}	
		}
		if(s.length() == 0)
			return "0";
		s.deleteCharAt(s.length() - 1);
		return s.toString();
	}
	
	public void simplify(char varName, int value)
	{
		for(int i = 0; i < polynomial.size(); i++)
			polynomial.get(i).simplify(varName, value);
	}
	
	public void derivative(char varName)
	{
		for(int i = 0; i < polynomial.size(); i++)
			polynomial.get(i).derivative(varName);
	}
}
//2.7