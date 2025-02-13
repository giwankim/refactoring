# Chapter 3 - Bad Smells in Code

Deciding when to start refactoring-and when to stop-is just as important to refactoring as knowing when to operate the mechanics it.

We have come up with the notion of describing the "when" of refactoring in terms of smells.

## Mysterious Name

## Duplicated Code

## Long Function

## Long Parameter List

## Global Data

## Mutable Data

## Divergent Change

## Shotgun Surgery

## Feature Envy

## Data Clumps

## Primitive Obsession

## Repeated Switches

## Loops

Loops have been a core part of programming since the earliest languages.

These days, however, first-class functions are widely supported, so we can use _Replace Loop with Pipeline_ to retire those anachronisms.

We find that pipeline operations, such as filter and map, help us quickly see the elements that are included in the processing and what is done with them.

## Lazy Element

## Speculative Generality

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
