import java.util.*;
class atom
{
  HashMap<String ,String> varMap= new HashMap<>();
  String code;
  atom(String varCode,String code)
  {
    if(varCode.charAt(0)!='N')
    initVar(varCode);
    this.code = code;
  }
  public void terminate(int x)
  {
    System.out.println("Invalid Syntax ("+x+") : Exiting");
    System.exit(0);
  }
  public String strip(String s)
  {
    if(s.charAt(0)=='('&&s.charAt(s.length()-1)==')')
    {
      return s.substring(1,s.length()-1);
    }
    return s;
  }
  public String[] splitFirst(String code,char a)
  {
    String out [] = new String[2];
    for(int i = 0;i<code.length();i++)
    {
      if(code.charAt(i)==a)
      {
        out[0]=code.substring(0,i);
        if(i+1>code.length())
          terminate(4);
        out[1]=code.substring(i+1);
        return out;
      }
    }
    return out;
  }
  void initVar(String code)
  {
    ArrayList<String> entries = new ArrayList<>();
    int depth = 0;
    int start = 0;
    for(int i = 0;i<code.length();i++)
    {
      if(code.charAt(i)=='(')
        depth++;
      else if(code.charAt(i)==')')
        depth--;
      if (code.charAt(i) == ','&& depth == 0)
      {
        if(start>=i)
        {
          terminate(1);
        }
        entries.add(code.substring(start,i));
        start = i + 1;
      }
    }
   entries.add(code.substring(start));
   for(int i=0;i<entries.size();i++)
    {
      String varDat [] = splitFirst(entries.get(i),'=');
      System.out.println("Entry "+i+" : "+entries.get(i));
      if(varDat.length!=2)
      {
        terminate(2);
      }
      if(varDat[1].charAt(0)=='(')
      {
        if(varDat[1].charAt(varDat[1].length()-1)!=')')
        {
          terminate(3);
        }
        varDat[1] = strip(varDat[1]);
        String []subcode = splitFirst(varDat[1],';');
        atom sub = new atom(subcode[0],subcode[1]);
        System.out.println("subAtom: "+subcode[0]+";"+subcode[1]);
        //varDat[1] = sub.exec();
      }
      varMap.put(varDat[0].trim(),varDat[1].trim());
    }
  }
  String exec()
  {
    System.out.println("CODE :"+code);
    if(code.charAt(0)!='(')
    {
      String steps[] = code.split(";");
      for(int i=0;i<steps.length;i++)
      {
        String func[] = splitFirst(steps[i],' ');
        function inst = new function(varMap);
        switch(func[0])
        {
          case "print": 
            inst.print(func[1]);
          break;
          default:
            inst.print("No such funtion");
          break;
        }
      }
      return null;
    }
    else
    {
      code = strip(code);
      String []subcode =splitFirst(code,';');
      atom sub = new atom(subcode[0],subcode[1]);
      System.out.println("subAtom: "+subcode[0]+";"+subcode[1]);
      return sub.exec();
    }
  }
  void printVariables()
  {
    System.out.println(varMap);
  }
  public static void main(String args[])
  {
    //atom a = new atom("x=2,y=3","[square {1} = mul 1 1];print (square x)");
    atom a = new atom("x='hello world',y=0","print x");
    a.printVariables();
    a.exec();
    //System.out.println(a.exec());
  }
}
