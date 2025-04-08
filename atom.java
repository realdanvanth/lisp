import java.util.*;
class atom
{
  String code;
  HashMap<String ,Double> varMap= new HashMap<>();
  ArrayList<String> subcode = new ArrayList<>();
  atom(String code)
  {
    this.code = code;
    System.out.println("code: "+code);
    if (code.startsWith("(") && code.endsWith(")")) {
            code = code.substring(1, code.length() - 1); 
    }
    else
    {
      System.out.println("Invalid Syntax");
      System.exit(0);
    }
    int start = 0;
    int depth = 0;
    for(int i=0;i<code.length();i++)
    {
      if(code.charAt(i)=='(')
      depth++;
      else if(code.charAt(i)==')')
      depth--;
      if(depth == 0)
      {
        subcode.add(code.substring(start+1,i));
        start = i+1;
      }
    }
    for(int i = 0;i<subcode.size();i++)
    {
      System.out.println(subcode.get(i));
    }
    if(subcode.get(0).charAt(0)!='V'&&subcode.get(0).charAt(0)!='N')
    {
      System.out.println("Invalid Syntax");
      System.exit(0);
    }
    if(subcode.get(0).charAt(0)=='V')
    initVariables(subcode.get(0).substring(1));
  }
  void initVariables(String variables)
  {
    String[] subVar = variables.split(",");
    for(int i=0;i<subVar.length;i++)
    { 
      String varDat[] = subVar[i].split("=");
      varMap.put(varDat[0].trim(),Double.parseDouble(varDat[1]));
    }
  }
  void printVariables()
  {
    System.out.println(varMap);
  }
  String exec()
  {
    if(subcode.get(1).charAt(0)!='(')
    {
      function a = new function(varMap,subcode.get(1).substring(2));
      //System.out.println(subcode.get(1));
      return a.execFunc();
    }
    else
    {
      atom a = new atom("("+subcode.get(1)+")");
      a.printVariables();
      return a.exec();
    }
  }
  public static void main(String args[]) 
  {
    //atom a= new atom("((V x=2)(F print x,print x))");
    //atom a = new atom("((V x=2)(F print x))");
    atom a = new atom("((N)((V x=3,y=2)(F mul x y)))");
    a.printVariables();
    System.out.println(a.exec());
  }
}
