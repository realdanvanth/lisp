# Telos
# A Tiny Language Built on Strings

**Telos** is a minimal interpreter dia-lect where **everything is an atom** and **everything evaluates to a string**.

At its core:
- Atoms are composed of **tuples** with two strings, where the first element is for variable declaration , N for no variables
and the second element is function code or subatom that must be evaluated.
- Subsequent elements are **arguments** — either sub atoms or user defined or inbuilt functions.
- All values are represented as **strings** internally and externally.
- Arithmetic, logic, and other operations **parse and return updated strings**.

---

## Philosophy
- **Code is data, and data is string.**
- Instead of traditional typed values, everything is stored and manipulated as a **string**.
- Atoms are the primary unit: that can be evaluated
- Evaluation always yields a **string result**, even for numbers or booleans.
- Airthmetic functions take strings parse it to integers and store the result in a string

## Examples
- ``("N ; get 'hello'")`` prints hello.
- ``("x=(x=5;get x);get x")`` prints 5
- ``("x=3;add x (N;add '1' '1')")`` outputs 5
- ``("N;if (N;greater '3' '2') (N;add '4' '1') (N;sub '5' '1')") ``outputs 5
- ``(x=2;= x (N;add x '1');get x)`` increments x by 1 and prints the answer

### for loop possible syntax:
  ("x=(x=0; while (lt x 5) (x=add x 1; print x))")

![image](https://github.com/user-attachments/assets/1128fbaf-3e7a-41a0-bc35-3d12dbd7d778)

# To-Do List
- [x] 1. Print Hello World
- [x] 2. Variable Declaration and Display
- [x] 3. Nested functions as parameters
- [x] 4. Conditional statements
- [ ] 5. Loops and recursion
- [ ] 6. User-Defined Functions
- [ ] 7. Prime Number Program 


