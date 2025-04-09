# Telos
# A Tiny Lisp-like Language Built on Strings

**Telos** is a minimalist Lisp dialect where **everything is an atom** and **everything evaluates to a string**.

At its core:
- Programs are composed of **tuples** (Python-style), where the first element is variable declaration , N for no variables.
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

