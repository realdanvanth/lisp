import java.util.*;
class function {
     HashMap<String, String> varMap = new HashMap<>();
     function(HashMap<String, String> varMap) {
          this.varMap = varMap;
          System.out.println(varMap);
     }
     public String strip(String s) {
          if (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') {
               return s.substring(1, s.length() - 1);
          }
          return s;
     }
     String parseString(String x) {
          if (x.charAt(0) == '(') {
               atom subatom = new atom(strip(x));
               subatom.inherit(varMap);
               String out = subatom.exec();
               update(varMap, subatom.varMap);
               return out;
          } else if (x.charAt(0) != '\'') {
               return varMap.get(x);
          } else {
               return x.substring(1, x.length() - 1);
          }
     }
     public static void update(HashMap<String, String> a,
                               HashMap<String, String> b) {
          for (String name : a.keySet()) {
               a.put(name, b.get(name));
          }
     }

     public String add(String x) {
          ArrayList<String> parameters = depthSplit(x, ' ');
          System.out.println("Parameters :" + parameters);
          if (parameters.size() != 2) {
               return "Parameters error";
          } else {
               return Integer.toString(
                   Integer.parseInt(get(parameters.get(0))) +
                   Integer.parseInt(get(parameters.get(1))));
          }
     }
     public String mul(String x) {
          ArrayList<String> parameters = depthSplit(x, ' ');
          System.out.println("Parameters :" + parameters);
          if (parameters.size() != 2) {
               return "Parameters error";
          } else {
               return Integer.toString(
                   Integer.parseInt(get(parameters.get(0))) *
                   Integer.parseInt(get(parameters.get(1))));
          }
     }
     public String sub(String x) {
          ArrayList<String> parameters = depthSplit(x, ' ');
          System.out.println("Parameters :" + parameters);
          if (parameters.size() != 2) {
               return "Parameters error";
          } else {
               return Integer.toString(
                   Integer.parseInt(get(parameters.get(0))) -
                   Integer.parseInt(get(parameters.get(1))));
          }
     }
     public String mod(String x) {
          ArrayList<String> parameters = depthSplit(x, ' ');
          System.out.println("Parameters :" + parameters);
          if (parameters.size() != 2) {
               return "Parameters error";
          } else {
               return Integer.toString(
                   Integer.parseInt(get(parameters.get(0))) %
                   Integer.parseInt(get(parameters.get(1))));
          }
     }
     public String equal(String x) {
          ArrayList<String> parameters = depthSplit(x, ' ');
          System.out.println("Parameters :" + parameters);
          if (parameters.size() != 2) {
               return "Parameters error";
          } else {
               int a = Integer.parseInt(get(parameters.get(0)));
               int b = Integer.parseInt(get(parameters.get(1)));
               return (a == b) ? "'1'" : "'0'";
          }
     }
     public String greater(String x) {
          ArrayList<String> parameters = depthSplit(x, ' ');
          System.out.println("Parameters :" + parameters);
          if (parameters.size() != 2) {
               return "Parameters error";
          } else {
               int a = Integer.parseInt(get(parameters.get(0)));
               int b = Integer.parseInt(get(parameters.get(1)));
               return (a > b) ? "'1'" : "'0'";
          }
     }
     public void terminate(int x) {
          System.out.println("Invalid Function Syntax (" + x + ") : Exiting");
          System.exit(0);
     }
     String get(String x) { return parseString(x); }
     ArrayList<String> depthSplit(String code, char a) // give with the spaces
     {
          int depth = 0;
          int start = 0;
          ArrayList<String> entries = new ArrayList<>();
          for (int i = 0; i < code.length(); i++) {
               if (code.charAt(i) == '(')
                    depth++;
               else if (code.charAt(i) == ')')
                    depth--;
               if (code.charAt(i) == a && depth == 0) {
                    if (start >= i) {
                         terminate(1);
                    }
                    entries.add(code.substring(start, i));
                    start = i + 1;
               }
          }
          entries.add(code.substring(start));
          return entries;
     }
     public String condition(String x) {
          ArrayList<String> entries = depthSplit(x, ' ');
          System.out.println("Variables : " + varMap);
          if (entries.size() != 3) {
               return "condition error";
          } else {
               atom subatom = new atom(entries.get(0));
               subatom.inherit(varMap);
               String out = subatom.exec();
               update(varMap, subatom.varMap);
               if (out.equals("'1'")) {
                    atom subatomCond = new atom(entries.get(1));
                    subatomCond.inherit(varMap);
                    out = subatomCond.exec();
                    update(varMap, subatomCond.varMap);
                    return out;
               } else {
                    atom subatomCond = new atom(entries.get(2));
                    subatomCond.inherit(varMap);
                    out = subatomCond.exec();
                    update(varMap, subatomCond.varMap);
                    return out;
               }
          }
     }
     public String loop(String x) {
          ArrayList<String> entries = depthSplit(x, ' ');
          System.out.println("Variables : " + varMap);
          if (entries.size() != 2) {
               return "loop error";
          }
          //(N; equal '0'(N; mod x '2')) atom sub = new atom(entries.get(0));
          atom sub = new atom(entries.get(0));
          sub.inherit(varMap);
          String out = sub.exec();
          String output = "";
          update(varMap, sub.varMap);
          while (out.equals("'0'")) { 
               atom subatom = new atom(entries.get(1));
               subatom.inherit(varMap);
               output+= subatom.exec();
               update(varMap, subatom.varMap);

              //executing the condition
               sub = new atom(entries.get(0));
               sub.inherit(varMap);
               out = sub.exec();
               update(varMap, sub.varMap);
          }
          return output;
     }
     public static void main(String args[]) {
          function a = new function(null);
          System.out.println(a.depthSplit("(x=2;get x) 3", ' '));
          System.out.println(a.greater("'4' '3'"));
     }
}
