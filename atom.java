import java.util.*;
class atom
{
  String code;
  HashMap<String ,Double> varMap= new HashMap<>();
  String subcode [];
  atom(String code)
  {
    this.code = code;
    if (code.startsWith("(") && code.endsWith(")")) {
            code = code.substring(1, code.length() - 1); 
    }
    subcode = code.split("\\)\\(");
    subcode[0]=subcode[0].substring(1);
    subcode[subcode.length-1]=subcode[subcode.length-1].substring(0,subcode[subcode.length-1].length()-1);
    for(int i = 0; i <subcode.length ; i++)
    {
      System.out.println(subcode[i]);
    }
    if(subcode[0].charAt(0)!='V')
    {
      System.out.println("Invalid Syntax");
      System.exit(0);
    }
    else
    initVariables(subcode[0].substring(2)); 
  }
  void initVariables(String variables)
  {
    String[] subVar = variables.split(",");
    for(int i=0;i<subVar.length;i++)
    { 
      String varDat[] = subVar[i].split("=");
      varMap.put(varDat[0],Double.parseDouble(varDat[1]));
    }
  }
  void printVariables()
  {
    System.out.println(varMap);
  }
  String exec()
  {
    if(subcode[1].charAt(0)!='A')
    {
      function a = new function(varMap,subcode[1].substring(2));
      System.out.println(subcode[1].substring(2));
      return a.execFunc();
    }
    else
    {
      atom a = new atom(code.substring(2));
      return a.exec();
    }
  }
  public static void main(String args[]) 
  {
    atom a= new atom("((V x=2,y=3)((V x=2,y=3)(print x)))");
    a.printVariables();
    System.out.println(a.exec());
  }
}
