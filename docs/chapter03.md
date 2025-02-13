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

## Middle Man

## Insider Trading

## Large Class

## Alternative Classes with Different Interfaces

## Data Class

## Refused Bequest

## Comments

Comments are often used as a deodorant.

- If you need a comment to explain what a block of code does, try _Extract Function_.
- If the method is already extracted but you still need a comment to explain what it does, use _Change Function Declaration_ to rename it.
- If you need to state some rules about the required state of the system, use _Introduce Assertion_.

A good time to use a comment is when you don't know what to do. Comments can indicate areas in which you aren't sure. A comment can also explain why you did something.
