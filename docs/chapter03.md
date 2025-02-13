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

## Long Function

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
