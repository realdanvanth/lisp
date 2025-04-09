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
- Atoms are the primary unit: a symbol that triggers evaluation.
- Evaluation always resolves to a **string result**, even for numbers or booleans.

> Example: `("N ; print 'hello'")` prints `hello`.
>          `("x=(x=5;print x);print x")` prints `5`

### for loop possible syntax:
  ("x=(x=0; while (lt x 5) (x=add x 1; print x))")

# To-Do List
- [x] 1. Print Hello World
- [x] 2. Variable Declaration and Display
- [ ] 3. If condition
- [ ] 4. Loops and recursion
- [ ] 5. User-Defined Functions
- [ ] 6. Prime Number Program 


