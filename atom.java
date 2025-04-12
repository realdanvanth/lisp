import java.util.*;
class atom {
     HashMap<String, String> varMap = new HashMap<>();
     String code;
     atom(String varCode, String code) {
          if (varCode.charAt(0) != 'N')
               initVar(varCode);
          this.code = code;
     }
     atom(String Code) {
          Code = strip(Code);
          String[] subcode = splitFirst(Code, ';');
          if (subcode[0].charAt(0) != 'N')
               initVar(subcode[0]);
          this.code = subcode[1];
     }
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
     public void terminate(int x) {
          System.out.println("Invalid Syntax (" + x + ") : Exiting");
          System.exit(0);
     }
     public String strip(String s) {
          if (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') {
               return s.substring(1, s.length() - 1);
          }
          return s;
     }
     public String[] splitFirst(String code, char a) {
          int depth = 0;
          int split = 0;
          for (int i = 0; i < code.length(); i++) {
               if (code.charAt(i) == '(')
                    depth++;
               else if (code.charAt(i) == ')')
                    depth--;
               else if (depth == 0 && code.charAt(i) == a) {
                    split = i;
                    break;
               }
          }
          String out[] = new String[2];
          out[0] = code.substring(0, split);
          out[1] = code.substring(split + 1);
          return out;
     }
     void initVar(String code) {
          ArrayList<String> entries = depthSplit(code, ',');
          for (int i = 0; i < entries.size(); i++) {
               String varDat[] = splitFirst(entries.get(i), '=');
               System.out.println("Entry " + i + " : " + entries.get(i));
               if (varDat.length != 2) {
                    terminate(2);
               }
               if (varDat[1].charAt(0) == '(') {
                    if (varDat[1].charAt(varDat[1].length() - 1) != ')') {
                         terminate(3);
                    }
                    varDat[1] = strip(varDat[1]);
                    String[] subcode = splitFirst(varDat[1], ';');
                    atom sub = new atom(
                        subcode[0], subcode[1]); // add the global variabless
                                                 // here(important)
                    sub.inherit(varMap);
                    System.out.println("subAtom: " + subcode[0] + ";" +
                                       subcode[1]);
                    varDat[1] = sub.exec();
               }
               varMap.put(varDat[0].trim(), varDat[1].trim());
          }
     }
     String exec() {
          String output = "";
          if (code.charAt(0) != '(') {
               ArrayList<String> steps = depthSplit(code, ';');
               for (int i = 0; i < steps.size(); i++) {
                    if (steps.get(i).charAt(0) != '=') {
                         String func[] = splitFirst(steps.get(i), ' ');
                         function inst = new function(varMap);
                         switch (func[0]) {
                         case "get":
                              System.out.println("Passing " + func[1] +
                                                 " to get");
                              output += inst.get(func[1]) + "\n";
                              break;
                         case "add":
                              System.out.println("Passing " + func[1] +
                                                 " to add");
                              output += inst.add(func[1]) + '\n';
                              break;
                         case "mul":
                              System.out.println("Passing " + func[1] +
                                                 " to mul");
                              output += inst.mul(func[1]) + '\n';
                              break;
                         case "sub":
                              System.out.println("Passing " + func[1] +
                                                 " to sub");
                              output += inst.sub(func[1]) + '\n';
                              break;
                         case "mod":
                              System.out.println("Passing " + func[1] +
                                                 " to mod");
                              output += inst.mod(func[1]) + '\n';
                              break;
                         case "greater":
                              System.out.println("Passing " + func[1] +
                                                 " to greater");
                              output += inst.greater(func[1]) + '\n';
                              break;
                         case "equal":
                              System.out.println("Passing " + func[1] +
                                                 " to equal");
                              output += inst.equal(func[1]) + '\n';
                              break;
                         case "if":
                              System.out.println("Passing " + func[1] +
                                                 " to if");
                              output += inst.condition(func[1]) + '\n';
                              break;
                         default:
                              output += "No such funtion\n";
                              break;
                         }
                    } else {
                         ArrayList<String> instruction = depthSplit(steps.get(i),' ');
                         System.out.println(instruction.get(0)+" "+instruction.get(1)+" "+instruction.get(2));
                         if (instruction.get(2).charAt(0) == '(') {
                              if (instruction.get(2).charAt(instruction.get(2).length() - 1) !=
                                  ')') {
                                   terminate(30);
                              }
                              instruction.set(2,strip(instruction.get(2)));
                              String[] subcode = splitFirst(instruction.get(2), ';');
                              atom sub = new atom(
                                  subcode[0],
                                  subcode[1]); // add the global variabless
                                               // here(important)
                              sub.inherit(varMap);
                              System.out.println("subAtom: " + subcode[0] +
                                                 ";" + subcode[1]);
                              instruction.set(2, sub.exec());
                         }
                         varMap.put(instruction.get(1),instruction.get(2));
                    }
               }
               return output.substring(0, output.length() - 1);
          } else {
               code = strip(code);
               String[] subcode = splitFirst(code, ';');
               atom sub = new atom(subcode[0], subcode[1]);
               sub.inherit(varMap);
               System.out.println("subAtom: " + subcode[0] + ";" + subcode[1]);
               return sub.exec();
          }
     }
     public void inherit(HashMap<String, String> Parent) {
          for (String name : Parent.keySet()) {
               varMap.put(name, Parent.get(name));
          }
     }
     void printVariables() { System.out.println(varMap); }
     public static void main(String args[]) {
          // atom a = new atom("x=2,y=3","[square {1} = mul 1 1];print (square
          // x)"); atom a = new atom("(N;mod '2' '1')"); atom a = new
          // atom("x=2;if (N;greater x '2') (N;add x '1') (N;sub x '1')"); atom
          // a = new atom("N;greater '3' '2'"); atom a = new atom("x=3;mul x
          // x;add x (N;mul (N;mul '2' '-2') (N;sub '1' '3'));get 'hello
          // world'");
          //atom a = new atom("x=2;= x (N;add x '1');get x");
          a.printVariables();
          System.out.println(a.exec());
     }
}
