# Telos
# A Tiny Language Built on Strings

**Telos** is a minimal interpreter dialect where **everything is an atom** and **everything evaluates to a string**.

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

# To-Do List
- [x] 1. Print Hello World
- [x] 2. Variable Declaration and Print
- [ ] 3. If condition
- [ ] 5. User-Defined Functions
- [ ] 6. Prime Number Program 


