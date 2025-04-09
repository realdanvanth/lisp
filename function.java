import java.util.*;
class function
{
  HashMap<String ,String> varMap= new HashMap<>();
  function(HashMap<String ,String> varMap)
  {
    this.varMap = varMap;
  }
  String parseString(String x)
  {
    /*if(x.charAt(0)=='(')
    {
      //exec.atom...();
    }*/
    if(x.charAt(0)!='\'')
    {
      return varMap.get(x).substring(1,varMap.get(x).length()-1);
    } 
    else
    {
      return x;
    }
  }
  void print(String x)
  {
    System.out.println(parseString(x));
  }
}
