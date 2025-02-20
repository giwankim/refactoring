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

- If you can obtain one parameter by asking another parameter for it, you can use _Replace Parameter with Query_ to remove the second parameter.
- Rather than pulling lots of data out of an existing data structure, you can use _Preserve Whole Object_ to pass the original data structure.
- If several parameters always fit together, combine them with _Introduce Parameter Object_.
- If a parameter is used as a flag to dispatch different behavior, use _Remove Flag Argument_.

Classes are a great way to reduce parameter list sizes. They are particularly useful when multiple functions share several parameter values. Then, you can use _Combine Functions into Class_ to capture those common values as fields.

## Global Data

The problem with global data is that it can be modified from anywhere in the code base, and there's no mechanism to discover which bit of code touched it.

Our key defense here is _Encapsulate Variable_. At least when you have it wrapped by a function, you can start seeing where it's modified and start to control its access. Then, it's good to limit its scope as much as possible by moving it within a class or module where only that module's code can see it.

## Mutable Data

- Use _Encapsulate Variable_ to ensure that all updates occur through narrow functions that can be easier to monitor and evolve.
- If a variable is being updated to store different things, use _Split Variable_ both to keep them separate and avoid risky update.
- Try as much as possible to move logic out of code that processes the update by using _Slide Statements_ and _Extract Function_ to separate the side-effect-free code from anything that performs the update.
- In APIs, use _Separate Query from Modifier_ to ensure callers don't need to call code that has side effects unless they really need to.
- _Remove Setting Method_ as soon as we can-sometimes, just trying to find clients of a setter helps spot opportunities to reduce the scope of variable.

Mutable data that can be calculated elsewhere is particularly pungent. Apply __Replace Derived Variable with Query__.

Mutable data isn't a big problem when it's a variable whose scope is just a couple of lines-but its risk increases as its scope grows.

- Use _Combine Functions into Class_ or _Combine Functions into Transform_ to limit how much code needs to update a variable.
- If a variable contains some data with internal structure, it's usually better to replace the entire structure rather than modify it in place, using _Change Reference to Value_.

## Divergent Change

When we make a change, we want to be able to jump to a single clear point in the system and make the change. When you can't do this, you are smelling one of two closely related pungencies.

Divergent change occurs when one module is often changed in different ways for different reasons.

- If two aspects naturally form a sequence, then _Split Phase_ separates the two with a clear data structure between them.
- If there's more back-and-forth in the calls, then create appropriate modules and use _Move Function_ to divide the processing up.
- If function mix the two types of processing within themselves, use _Extract Function_ to separate them before moving.
- If the modules are classes, then _Extract Class_ helps formalize how to do the split.

## Shotgun Surgery

Shotgun surgery is similar to divergent change but is the opposite. You whiff this when, every time you make a change, you have to make a lot of little edits to a lot of different classes.

- Use _Move Function_ and _Move Field_ to put all the changes into a single module.
- If you have a bunch of functions operating on similar data, use _Combine Functions into Class_.
- If you have functions that are transforming or enriching a data structure, use _Combine Functions into Transform_.
- _Split Phase_ is often useful here if the common functions can combine their output for a consuming phase of logic.

## Feature Envy

A classic case of Feature Envy occurs when a function in one module spends more time communicating with functions or data inside another module than it does within its own module. The function clearly wants to be with the data, so use _Move Function_ to get it there. Sometimes, only a part of a function suffers from envy, in which case use _Extract Function_ on the jealous bit, and _Move Function_ to give it a dream home.

TODO

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

The problem with repeated switch, where the same conditional switching logic pops up in different places, is that, whenever you add a clause, you have to find all the switches and update them. Apply _Replace Conditional with Polymorphism_.

## Loops

Loops have been a core part of programming since the earliest languages.

These days, however, first-class functions are widely supported, so we can use _Replace Loop with Pipeline_ to retire those anachronisms.

We find that pipeline operations, such as filter and map, help us quickly see the elements that are included in the processing and what is done with them.

## Lazy Element

Sometimes structure isn't needed.

Use _Inline Function_ or _Inline Class_. With inheritance, you can use _Collapse Hierarchy_ to remove unnecessary structure.

## Speculative Generality

You get Speculative Generality when people say, "I think we'll need the ability to do this kind of thing someday" and thus add all sorts of hooks and special cases to handle things that aren't required.

- If you have abstract classes that aren't doing much, use _Collapse Hierarchy_.
- Unnecessary delegation can be removed with _Inline Function_ and _Inline Class_.
- Functions with unused parameters should be subject to _Change Function Declaration_.
- Also apply _Change Function Declaration_ to remove any unneeded parameters, which often get tossed in for future variations that never come to pass.

Speculative generality can be spotted when the only users of a function or class are test cases. If you find such a case, delete the test case and apply _Remove Dead Code_.

## Temporary Field

Sometimes you see a class in which a field is set only in certain circumstances.

Use _Extract Class_ to create a home for the orphan variables. Use _Move Function_ to put all the code that concerns the fields into this new class. You may also be able to eliminate conditional code by using _Introduce Special Case_ to create an alternative class for when the variables aren't valid.

Below is an example in Kotlin that demonstrates the “Temporary Field” code smell and how you can refactor it using the Extract Class and Move Function techniques. In this example, we start with a `Booking` class that has a temporary field (`cancellationReason`) which is only meaningful when a booking is cancelled.

### **Before Refactoring**

In the code below, the field `cancellationReason` exists in every `Booking` instance—even though it’s only used when the booking is cancelled. This can be confusing because the field is irrelevant for active bookings.

```kotlin
class Booking(
    val customerName: String,
    val date: LocalDate,
    val cancelled: Boolean,
    // This field is only valid when cancelled is true.
    val cancellationReason: String? = null
) {
    fun getStatus(): String {
        return if (cancelled) {
            "Cancelled: $cancellationReason"
        } else {
            "Active"
        }
    }
}

fun main() {
    val booking1 = Booking("Alice", LocalDate.now(), cancelled = false)
    val booking2 = Booking("Bob", LocalDate.now(), cancelled = true, cancellationReason = "Weather issues")
    println(booking1.getStatus()) // Output: Active
    println(booking2.getStatus()) // Output: Cancelled: Weather issues
}
```

### **After Refactoring**

Using **Extract Class**, we create a dedicated `CancellationInfo` class to hold cancellation details. Then, with **Move Function**, we shift the code that deals with cancellation into that class. This removes the temporary field from the main `Booking` class and eliminates the need for conditional logic in `getStatus()`.

```kotlin
class Booking(
    val customerName: String,
    val date: LocalDate,
    // Instead of a temporary field, we now hold cancellation details in its own class.
    val cancellationInfo: CancellationInfo? = null
) {
    fun getStatus(): String {
        // Delegates status formatting to CancellationInfo if present.
        return cancellationInfo?.getStatus() ?: "Active"
    }
}

class CancellationInfo(private val reason: String) {
    // This function encapsulates the logic related to cancellation details.
    fun getStatus(): String {
        return "Cancelled: $reason"
    }
}

fun main() {
    val booking1 = Booking("Alice", LocalDate.now())
    val booking2 = Booking("Bob", LocalDate.now(), CancellationInfo("Weather issues"))
    println(booking1.getStatus()) // Output: Active
    println(booking2.getStatus()) // Output: Cancelled: Weather issues
}
```

---

### **Explanation**

- **Before Refactoring:**  
  The `Booking` class holds both the booking details and the cancellation details, even though cancellation details are only valid when a booking is cancelled. This makes the code harder to understand and maintain.

- **After Refactoring:**  
  - **Extract Class:** The cancellation-related data and behavior are moved into a new class, `CancellationInfo`.  
  - **Move Function:** The method `getStatus()` in `CancellationInfo` now handles the cancellation-specific logic, removing conditionals from `Booking`.
  - **Result:** The main `Booking` class is simplified and no longer contains orphan fields that are only relevant in some circumstances.

## Message Chains

You see message chains when a client asks one object for another object, which the client then asks for yet another object, and so on. Navigating this way means the client is coupled to the structure of the navigation.

- The move to use here is _Hide Delegate_. In principle, you can do this to every object in the chain, but doing this often turns every intermediate object into a middle man.
- Often, a better alternative is to see what the resulting object is used for. See whether you can use _Extract Function_ to take a piece the code that uses it and then _Move Function_ to push it down the chain. If several clients of one of the objects in the chain want to navigate the rest of the way, add a method to do that.

## Middle Man

One of the prime features of objects is encapsulation. Encapsulation often comes with delegation.

However, this can go too far. 

- _Remove Middle Man_.
- Use _Inline Function_ to inline them into the caller.
- If there is additional behavior, you can use _Replace Superclass with Delegate_ or _Replace Subclass with Delegate_ to fold the middle man into the real object.

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

Subclasses get to inherit the methods and data of their parents. But what if they don't want or need what they are given?

The traditional story is that this means the hierarchy is wrong. You need to create a new sibling class and use _Push Down Method_ and _Push Down Field_ to push all the unused code to the sibling.

Nine times out of ten this smell is too faint to be worth cleaning.

The smell of refused bequest is much stronger if the subclass is reusing behavior but does not want to support the interface of the superclass. In this case, however, don't fiddle with the hierarchy; you want to gut it by applying _Replace Subclass with Delegate_ or _Replace Superclass with Delegate_.

Below is an example in Kotlin that shows how to avoid forcing a subclass to support an interface it doesn’t really need by using delegation rather than inheritance. In the classic “refused bequest” scenario, you might have a base class like this:

```kotlin
open class Bird {
    open fun layEggs() {
        println("Laying eggs.")
    }

    open fun fly() {
        println("Flying!")
    }
}

class Penguin : Bird() {
    // Penguins don't fly, so we have to override and disable fly()
    override fun fly() {
        throw UnsupportedOperationException("Penguins can't fly!")
    }
}
```

This design forces every bird to have a `fly()` method—even when some birds (like penguins) don’t fly. One way to fix this is to separate the concerns into different interfaces and use delegation to share common behavior. This is the essence of **Replace Subclass with Delegate / Replace Superclass with Delegate**.

### Example: Using Delegation to Avoid a Refused Interface

We can define two interfaces: one for basic bird behavior (e.g., laying eggs) and another for flying behavior. Then we create a delegate class for flying behavior and let only those birds that actually fly implement the flying interface. This way, a penguin only implements the basic bird interface without being forced to support flying.

```kotlin
// Define an interface for common bird behavior.
interface Bird {
    fun layEggs()
}

// Define an interface only for birds that can fly.
interface Flyer {
    fun fly()
}

// A delegate that encapsulates flying behavior.
class FlyingDelegate : Flyer {
    override fun fly() {
        println("Flying high in the sky!")
    }
}

// A bird that flies: it implements both Bird and Flyer.
// It delegates the flying behavior to an instance of FlyingDelegate.
class FlyingBird(private val flyingDelegate: Flyer = FlyingDelegate()) : Bird, Flyer {
    override fun layEggs() {
        println("Laying eggs.")
    }

    override fun fly() {
        flyingDelegate.fly()
    }
}

// A Penguin only implements Bird; it does not need to support flying.
class Penguin : Bird {
    override fun layEggs() {
        println("Laying eggs.")
    }
}

fun main() {
    // Using a bird that can fly.
    val flyingBird: Bird = FlyingBird()
    flyingBird.layEggs()
    if (flyingBird is Flyer) {
        flyingBird.fly()
    }

    // Using a penguin that does not support flying.
    val penguin: Bird = Penguin()
    penguin.layEggs()
    // penguin.fly() // Not allowed because Penguin doesn't implement Flyer.
}
```

### Explanation

- **Separate Interfaces:**  
  We define `Bird` for shared behavior (laying eggs) and `Flyer` for flying behavior. This separation ensures that only birds that actually fly need to implement `Flyer`.

- **Delegation:**  
  The class `FlyingBird` implements both `Bird` and `Flyer` but delegates the flying behavior to a `FlyingDelegate`. This keeps flying logic separate and reusable.

- **No Forced Implementation:**  
  The `Penguin` class implements only the `Bird` interface. There’s no `fly()` method in its API, so there’s no need to override or disable unwanted behavior.

This design adheres to the “Replace Subclass with Delegate” or “Replace Superclass with Delegate” idea by using composition to share common behavior rather than forcing a subclass to inherit methods (and an interface) it doesn’t want.

## Comments

Comments are often used as a deodorant.

- If you need a comment to explain what a block of code does, try _Extract Function_.
- If the method is already extracted but you still need a comment to explain what it does, use _Change Function Declaration_ to rename it.
- If you need to state some rules about the required state of the system, use _Introduce Assertion_.

A good time to use a comment is when you don't know what to do. Comments can indicate areas in which you aren't sure. A comment can also explain why you did something.
