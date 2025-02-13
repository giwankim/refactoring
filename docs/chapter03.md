# Chapter 3 - Bad Smells in Code

Deciding when to start refactoring-and when to stop-is just as important to refactoring as knowing when to operate the mechanics it.

We have come up with the notion of describing the "when" of refactoring in terms of smells.

## Mysterious Name

Naming is one of the [two hard things](https://martinfowler.com/bliki/TwoHardThings.html) in programming.

- _Change Function Declaration_
- _Rename Variable_
- _Rename Field_

Renaming is not just an exercise in changing names. When you can't think of a good name for something, it's often a sign of a deeper design malaise.

## Duplicated Code

- The simplest duplicated code problem is when you have the same expression in tow methods of the same class. Then all you have to do is _Extract Function_.
- If you have code that's similar,
  1. see if you can use _Slide Statements_ to arrange the code so the similar items are all together for easy extraction.
  2. If the duplicate fragments are in subclasses of a common base class, you can use _Pull Up Method_ to avoid calling one from another.

### **Example 1: Duplicated Code in the Same Class (Extract Function)**

Imagine you have an `OrderProcessor` class where two methods contain the same expression for calculating a total amount (by adding tax). The duplicated code makes maintenance error‐prone. We can refactor it by extracting the duplicated code into its own helper method.

#### **Before Refactoring**

```kotlin
data class Order(val id: Int, val amount: Double)

class OrderProcessor {
    fun processOrder(order: Order) {
        // Duplicated calculation:
        val tax = order.amount * 0.1
        val total = order.amount + tax
        println("Processing order ${order.id}: total amount is $total")
        // ... additional processing logic ...
    }
    
    fun refundOrder(order: Order) {
        // Same duplicated calculation:
        val tax = order.amount * 0.1
        val total = order.amount + tax
        println("Refunding order ${order.id}: refund amount is $total")
        // ... additional refund logic ...
    }
}
```

#### **After Refactoring (Extract Function)**

We extract the tax and total calculation into its own private function, `calculateTotal()`, then call that function from both methods:

```kotlin
data class Order(val id: Int, val amount: Double)

class OrderProcessor {
    // Extracted function that encapsulates the duplicated logic.
    private fun calculateTotal(order: Order): Double {
        val tax = order.amount * 0.1
        return order.amount + tax
    }
    
    fun processOrder(order: Order) {
        val total = calculateTotal(order)
        println("Processing order ${order.id}: total amount is $total")
        // ... additional processing logic ...
    }
    
    fun refundOrder(order: Order) {
        val total = calculateTotal(order)
        println("Refunding order ${order.id}: refund amount is $total")
        // ... additional refund logic ...
    }
}
```

### **Example 2: Duplicated Code in Subclasses (Pull Up Method)**

Sometimes duplicate code appears in similar methods across different subclasses. In this example, both `FullTimeEmployee` and `ContractEmployee` calculate a bonus in the same way. By pulling up the common method to a superclass, you eliminate duplication and standardize the behavior.

#### **Before Refactoring**

```kotlin
// Two separate classes with similar bonus calculations.
class FullTimeEmployee(val baseSalary: Double) {
    fun computeBonus(): Double {
        // Duplicated bonus calculation
        return baseSalary * 0.1
    }
}

class ContractEmployee(val baseSalary: Double) {
    fun computeBonus(): Double {
        // Duplicated bonus calculation
        return baseSalary * 0.1
    }
}
```

#### **After Refactoring (Pull Up Method with an Extracted Superclass)**

We create a common superclass `Employee` that defines `computeBonus()`, then have both subclasses inherit from it:

```kotlin
open class Employee(val baseSalary: Double) {
    // Common bonus calculation moved to the superclass.
    fun computeBonus(): Double {
        return baseSalary * 0.1
    }
}

class FullTimeEmployee(baseSalary: Double) : Employee(baseSalary)

class ContractEmployee(baseSalary: Double) : Employee(baseSalary)
```

## Long Function

Older languages carried an overhead in subroutine calls. Development environments allow you to quickly jump between a function call and its declaration.

The net effect is that you should be much more aggressive about decomposing functions.

- A heuristic we follow is that whenever we feel the need to comment something, we write a function instead.
- Ninety-nine percent of the time, all you have to do to shorten a function is _Extract Function_.

### Function with lots of parameters and temporary variables

If you have a function with lots of parameters and temporary variables, they get in the way of extracting.

- You can often use _Replace Temp with Query_ to eliminate the temps.
- Long list of parameters can be slimmed down with _Introduce Parameter Object_ and _Preserve Whole Object_.
- If you've tried that and you still have too many temps and parameters, it's time to get out the heavy artillery: _Replace Function with Command_.

### Identifying clumps of code to extract

A good technique to identify clumps of code to extract is to look for comments.

Conditionals and loops also give signs for extractions.

#### Conditionals

- Use _Decompose Conditional_ to deal with conditional expressions.
- A big switch statement should have its legs turned into single function calls with _Extract Function_.
- If there's more than one switch statement switching on the same condition, you should apply _Replace Conditional with Polymorphism_.

#### Loops

- With loops, extract the loop and the code within the loop into its own method.
- If you find it hard to given an extracted loop a name, that may be because it's doing two different things-in which case don't be afraid to use _Split Loop_ to break out the separate tasks.

## Long Parameter List

## Global Data

## Mutable Data

## Divergent Change

## Shotgun Surgery

## Feature Envy

## Data Clumps

Bunches of data that hang around together really ought to find a home together.

1. The first step is to look for where the clumps appear as fields. Use _Extract Class_ on the fields to turn the clumps into an object.
2. Then turn your attention to method signatures using _Introduce Parameter Object_ or _Preserve Whole Object_.

A good test is to consider deleting one of the data values. If the other data don't make sense, it's a sign that you have an object that's dying to be born.

We advocate creating a class here, not a simple record structure. You can now look for cases of feature envy, which will suggest behavior that can be moved into your new classes.

## Primitive Obsession

We find programmers are curiously reluctant to create their own fundamental types which are useful for their domain-such as money, coordinates, or ranges.

- You can move out of the primitive cave into the world of meaningful types by using _Replace Primitive with Object_.
- If the primitive is a type code controlling conditional behavior, use _Replace Type Code with Subclasses_ followed by _Replace Conditional with Polymorphism_.
- Groups of primitives that commonly appear together are data clumps and should be civilized with _Extract Class_ and _Introduce Parameter Object_.

## Repeated Switches

## Loops

Loops have been a core part of programming since the earliest languages.

These days, however, first-class functions are widely supported, so we can use _Replace Loop with Pipeline_ to retire those anachronisms.

We find that pipeline operations, such as filter and map, help us quickly see the elements that are included in the processing and what is done with them.

## Lazy Element

## Speculative Generality

You get Speculative Generality when people say, "I think we'll need the ability to do this kind of thing someday" and thus add all sorts of hooks and special cases to handle things that aren't required.

- If you have abstract classes that aren't doing much, use _Collapse Hierarchy_.
- Unnecessary delegation can be removed with _Inline Function_ and _Inline Class_.
- Functions with unused parameters should be subject to _Change Function Declaration_.
- Also apply _Change Function Declaration_ to remove any unneeded parameters, which often get tossed in for future variations that never come to pass.

Speculative generality can be spotted when the only users of a function or class are test cases. If you find such a case, delete the test case and apply _Remove Dead Code_.

## Temporary Field

## Message Chains

You see message chains when a client asks one object for another object, which the client then asks for yet another object, and so on. Navigating this way means the client is coupled to the structure of the navigation.

- The move to use here is _Hide Delegate_. In principle, you can do this to every object in the chain, but doing this often turns every intermediate object into a middle man.
- Often, a better alternative is to see what the resulting object is used for. See whether you can use _Extract Function_ to take a piece the code that uses it and then _Move Function_ to push it down the chain. If several clients of one of the objects in the chain want to navigate the rest of the way, add a method to do that.

## Middle Man

## Insider Trading

## Large Class

## Alternative Classes with Different Interfaces

One of the great benefits of using classes is the support for substitution, allowing one class to swap in for another in times of need.

- Use _Change Function Declaration_ to make functions match up.
- Keep using _Move Function_ to move behavior into classes until the protocols match.
- If this leads to duplication, you may be able to use _Extract Superclass_ to atone.

### Example

#### **1. Before Refactoring: Alternative Classes with Different Interfaces**

Imagine you have two logger classes. One logs messages to a file and the other logs to a database. However, they use different method names:

```kotlin
// Logs messages to a file
class FileLogger {
    fun logMessage(message: String) {
        println("FileLogger: $message")
    }
}

// Logs messages to a database but uses a different method name
class DatabaseLogger {
    fun recordMessage(message: String) {
        println("DatabaseLogger: $message")
    }
}

// Client code (suppose we want to swap these loggers later)
fun performLogging(logger: Any, message: String) {
    when (logger) {
        is FileLogger -> logger.logMessage(message)
        is DatabaseLogger -> logger.recordMessage(message)
        else -> println("Unknown logger")
    }
}
```

*Problem:*  
Clients must know about the different method names (`logMessage` vs. `recordMessage`), making substitution cumbersome.

---

#### **2. Step One: Change Function Declaration**

First, we change the declaration in `DatabaseLogger` so its interface matches `FileLogger`—both now provide a `logMessage` method:

```kotlin
class DatabaseLogger {
    // Changed method name from recordMessage to logMessage
    fun logMessage(message: String) {
        println("DatabaseLogger: $message")
    }
}
```

Now both loggers share the same public method name:
```kotlin
fun performLogging(logger: Any, message: String) {
    when (logger) {
        is FileLogger -> logger.logMessage(message)
        is DatabaseLogger -> logger.logMessage(message)
        else -> println("Unknown logger")
    }
}
```

---

#### **3. Step Two: Move Function (and Introduce Shared Behavior)**

Suppose you decide that both loggers should add a timestamp to every message. Initially, you might add this logic to each class separately:

```kotlin
class FileLogger {
    fun logMessage(message: String) {
        val timestampedMessage = "[${System.currentTimeMillis()}] $message"
        println("FileLogger: $timestampedMessage")
    }
}

class DatabaseLogger {
    fun logMessage(message: String) {
        val timestampedMessage = "[${System.currentTimeMillis()}] $message"
        println("DatabaseLogger: $timestampedMessage")
    }
}
```

*Issue:*  
The logic for adding a timestamp is duplicated.

To remove duplication, you can use **Move Function** by shifting the shared behavior (adding a timestamp) into a common place.

---

#### **4. Step Three: Extract Superclass to Share Common Protocol and Behavior**

Create an abstract superclass (or base class) that encapsulates the common behavior. In our case, the superclass provides the unified `logMessage` method and a helper for adding the timestamp. It then delegates the actual logging to subclasses via an abstract method.

```kotlin
abstract class BaseLogger {
    // Public API: the unified logging method
    fun logMessage(message: String) {
        val timestampedMessage = addTimestamp(message)
        doLog(timestampedMessage)
    }
    
    // Private helper function to add a timestamp
    private fun addTimestamp(message: String): String {
        return "[${System.currentTimeMillis()}] $message"
    }
    
    // Abstract method that subclasses must implement for actual logging
    protected abstract fun doLog(message: String)
}
```

Now, have both loggers extend this superclass and provide their specific logging implementation:

```kotlin
class FileLogger : BaseLogger() {
    override fun doLog(message: String) {
        println("FileLogger: $message")
    }
}

class DatabaseLogger : BaseLogger() {
    override fun doLog(message: String) {
        println("DatabaseLogger: $message")
    }
}
```

With this refactoring:

- **Change Function Declaration** was used to align method names.
- **Move Function** was used to move the timestamping behavior into the `BaseLogger`.
- **Extract Superclass** was used to encapsulate the common protocol, ensuring both loggers have the same interface and shared behavior.

Now, client code can use either logger interchangeably:

```kotlin
fun performLogging(logger: BaseLogger, message: String) {
    logger.logMessage(message)
}

fun main() {
    val fileLogger = FileLogger()
    val databaseLogger = DatabaseLogger()
    
    performLogging(fileLogger, "This is a file log.")
    performLogging(databaseLogger, "This is a database log.")
}
```

## Data Class

## Refused Bequest

## Comments

Comments are often used as a deodorant.

- If you need a comment to explain what a block of code does, try _Extract Function_.
- If the method is already extracted but you still need a comment to explain what it does, use _Change Function Declaration_ to rename it.
- If you need to state some rules about the required state of the system, use _Introduce Assertion_.

A good time to use a comment is when you don't know what to do. Comments can indicate areas in which you aren't sure. A comment can also explain why you did something.
