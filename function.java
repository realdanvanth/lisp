import java.util.*;
class function
{
  String code;
  HashMap<String ,Double> varMap= new HashMap<>();
  String subcode [];
  function(HashMap<String,Double> varMap,String code)
  {
    this.code = code;
    this.varMap = varMap;
    subcode = code.split(",");
  }
  String print(String var)
  {
    double out = varMap.get(var);
    return  Double.toString(out);
  }
  String equal(String var1, String var2)
  {
    if(varMap.get(var1)==varMap.get(var1))
    {
      return "true";
    }
    else
    {
      return "false";
    }
  }
  String greater(String var1,String var2)
  {
    if(varMap.get(var1)>varMap.get(var2))
    {
      return "true";
    }
    else
    {
      return "false";
    }
  }
  String add(String var1,String var2)
  {
    double out = varMap.get(var1)+varMap.get(var2);
    return Double.toString(out);
  }
  String sub(String var1,String var2)
  {
    double out = varMap.get(var1)-varMap.get(var2);
    return Double.toString(out);
  }
  String mul(String var1, String var2)
  {
    double out = varMap.get(var1)*varMap.get(var2);
    return Double.toString(out);
  }
  String div(String var1, String var2)
  {
    double out = varMap.get(var1)/varMap.get(var2);
    return Double.toString(out);
  }
  String execFunc()
  {
    for(int i = 0 ; i < subcode.length ; i++)
    {
      System.out.println(subcode[i]);
    }
    return null;
  }
}
