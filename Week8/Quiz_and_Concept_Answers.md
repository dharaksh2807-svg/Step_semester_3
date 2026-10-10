# Week 8: Object-Oriented Programming — Polymorphism & Inheritance

---

## Part 1: Quiz Questions and Answers

### Question 1
**Question:** A system processes Document objects. It initially had PDFDocument and WordDocument classes, both inheriting from Document and implementing a render() method. A developer observes repeated if-else if blocks checking the document type before calling render(). What is the most appropriate OOP principle to apply to eliminate this conditional logic?  
**Answer: C. Polymorphism**  
**Explanation:** Polymorphism allows calling `document.render()` on a base `Document` reference, dynamically dispatching to the subclass's concrete `render()` implementation at runtime and eliminating conditional type checks.

---

### Question 2
**Question:** Which of the following scenarios represent a genuine 'is-a' relationship, making inheritance a suitable design choice? (Select all that apply)  
**Answer: A, B, C**  
- **A.** A `Car` is a `Vehicle`. (Valid: Car is a specialized Vehicle)
- **B.** A `Rectangle` is a `Shape`. (Valid: Rectangle is a geometric Shape)
- **C.** A `DatabaseConnection` is a `NetworkResource`. (Valid: A database connection is a specialized type of network resource)  
*(Option D describes a 'has-a' / composition relationship, not inheritance).*

---

### Question 3
**Question:** Consider a Vehicle base class with a method startEngine() and two derived classes, Car and Motorcycle, both overriding startEngine() with their specific engine start sounds. If you have a list of Vehicle objects, some being Car instances and some Motorcycle instances, and you iterate through this list calling startEngine() on each object, what mechanism ensures the correct startEngine() implementation is called for each vehicle type?  
**Answer: C. Runtime method dispatch**  
**Explanation:** Java uses dynamic method dispatch (vtable lookup) at runtime to inspect the actual underlying object instance and execute the overridden method.

---

### Question 4
**Question:** A Shape base class has a method calculateArea(). Circle and Rectangle are derived classes, each with their own implementation of calculateArea(). If a program stores various Shape objects in a list and then calls calculateArea() on each, what concept is being demonstrated?  
**Answer: C. Inheritance-based polymorphism**  
**Explanation:** Subclasses inherit the abstract contract from `Shape` and supply concrete polymorphic implementations of `calculateArea()`.

---

### Question 5
**Question:** A software component is designed to manage various LibraryItem types like Book and DVD. Both Book and DVD inherit from LibraryItem. The LibraryItem class has a getLoanPeriod() method. The Book class extends this method by adding a special rule for new releases, while the DVD class sets a fixed loan period. Which of the following statements accurately describe the behavior in this system? (Select all that apply)  
**Answer: A, C, D**  
- **A.** `getLoanPeriod()` in `Book` is an example of extending inherited behavior.
- **C.** The `LibraryItem` class defines the common behavior for all library items.
- **D.** A `DVD` object can be processed as a `LibraryItem` through a common reference.

---

### Question 6
**Question:** Consider a base class Animal with a method makeSound() and derived classes Dog and Cat, which override makeSound() to produce 'Woof' and 'Meow' respectively. Which of the following statements about the behavior of these classes are correct? (Select all that apply)  
**Answer: A, C**  
- **A.** If a `Dog` object is referred to by an `Animal` reference, calling `makeSound()` will execute `Dog`'s `makeSound()`.
- **C.** The `makeSound()` method in `Dog` is an example of overridden behavior.

---

### Question 7
**Question:** A PaymentProcessor system processes CardPayment and BankTransferPayment objects. Both CardPayment and BankTransferPayment derive from a Payment base class and override a calculateFee() method. If the PaymentProcessor maintains a collection of Payment references, what is the primary benefit of this design when adding a new WalletPayment type?  
**Answer: C. It allows adding `WalletPayment` without modifying the existing `PaymentProcessor`'s iteration logic.**  
**Explanation:** This satisfies the Open-Closed Principle (open for extension, closed for modification); the central loop processes `Payment` polymorphically.

---

### Question 8
**Question:** What is the primary reason why using inheritance solely for superficial code reuse between unrelated classes is generally considered an inappropriate design choice?  
**Answer: A. It leads to tighter coupling and incorrect 'is-a' relationships, making the design rigid.**  
**Explanation:** Misusing inheritance breaks encapsulation, exposes unnecessary base methods, violates Liskov Substitution Principle, and creates fragile architectures.

---

### Question 9
**Question:** A Notification base class has a send() method. Derived classes EmailNotification, SMSNotification, and PushNotification each override send() to implement channel-specific delivery logic. What are the advantages of using inheritance and polymorphism in this notification system design? (Select all that apply)  
**Answer: A, C**  
- **A.** It allows a generic `NotificationSender` to send various types of notifications without knowing their concrete types.
- **C.** It simplifies the process of adding a new notification channel, like `InAppNotification`, without altering existing sender logic.

---

### Question 10
**Question:** Which of the following are benefits of using polymorphic collections (e.g., a list of base class references holding derived class objects)? (Select all that apply)  
**Answer: A, B, C**  
- **A.** It simplifies iterating over diverse but related objects.
- **B.** It allows for uniform processing of objects with specialized behavior.
- **C.** It reduces the need for explicit type casting in common processing loops.

---

## Part 2: Concept Questions and Comprehensive Answers

### Question 1: Inheritance for Reuse and Extension
**Explanation:** Inheritance allows derived classes to inherit common properties and behaviors defined in a base class, eliminating code duplication, while allowing subclasses to override or introduce specialized behaviors.  
**Business Example:** In a banking system, `BankAccount` contains common attributes (`accountNumber`, `balance`) and methods (`deposit()`, `getBalance()`). `SavingsAccount` inherits these common features and extends behavior by adding `calculateInterest()`, while `CheckingAccount` overrides `withdraw()` to enforce overdraft limit rules.

### Question 2: The 'is-a' Relationship
**Explanation:** The 'is-a' relationship signifies that every instance of a subclass is a genuine specialized member of the superclass category. Correctly identifying 'is-a' ensures semantic correctness, preserves the Liskov Substitution Principle (LSP), and guarantees that derived classes honor the contract of their base class. If an entity merely *uses* or *contains* another entity, composition ('has-a') must be used instead.

### Question 3: Method Overriding
**Explanation:** Method overriding occurs when a subclass provides its own specific implementation of a method that is already declared in its superclass, keeping the same method signature.  
**Business Example:** An `Employee` base class defines `calculatePay()`. A `SalariedEmployee` subclass overrides `calculatePay()` to return `annualSalary / 12`, while an `HourlyEmployee` subclass overrides `calculatePay()` to compute `hoursWorked * hourlyRate + overtime`. A payroll loop iterates over `List<Employee>` calling `emp.calculatePay()` without knowing the concrete type.

### Question 4: Runtime Polymorphism & Dynamic Dispatch
**Explanation:** Runtime polymorphism (dynamic method dispatch) allows a call to an overridden method to be resolved at runtime rather than compile-time. When a method is invoked on a base-type reference (`Base obj = new Derived(); obj.action();`), the JVM examines the object's virtual method table (vtable) associated with the actual runtime class of the instance and invokes the overridden subclass implementation.

### Question 5: Polymorphic Collections
**Explanation:** A polymorphic collection is a data structure (such as `List<Shape>` or `PaymentMethod[]`) typed to a base class or interface that stores instances of multiple different subclasses.  
**Advantages:** Enables client code to loop through diverse objects uniformly (e.g. `for (Shape s : shapes) s.draw();`), eliminating `instanceof` checks, reducing boilerplate, and decoupling processing algorithms from concrete classes.

### Question 6: Polymorphism vs. Repeated Type-Based Conditional Logic
**Comparison:**
- **Conditional Logic (`if-else` / `switch` on type):** Violates the Open-Closed Principle. Every time a new type is introduced, every switch statement across the codebase must be located and modified, resulting in high bug risk and tight coupling.
- **Polymorphism:** Encapsulates variation inside subclasses. Adding a new type requires creating a new subclass implementing the common method, with zero edits to client code or loops.

### Question 7: Extensibility & Open-Closed Principle
**Explanation:** When systems depend on base abstractions, new types can be integrated seamlessly.  
**Example:** If a payment processor operates on `PaymentMethod.calculateFee()`, introducing a new `CryptoPayment` class requires only subclassing `PaymentMethod` and implementing `calculateFee()`. The central `PaymentProcessor` loop processes `CryptoPayment` instances immediately without modifying a single line of processing logic.

### Question 8: Inherited vs. Overridden Behavior
- **Inherited Behavior:** Used when the default logic in the base class is completely appropriate and universal for all subclasses without modification (e.g., `getId()` or `getCreationDate()`).
- **Overridden Behavior:** Used when a subclass requires specialized, domain-specific execution for a shared contract (e.g., `calculateTax()`, `draw()`, or `render()`).

### Question 9: Pitfalls of Inheritance Solely for Code Reuse
**Explanation:** Inheriting solely to reuse code when there is no genuine 'is-a' relationship violates encapsulation and couples subclasses to implementation details of the parent. Changes in the parent class can silently break child classes (the "fragile base class" problem), and subclasses inherit inapplicable methods (e.g., inheriting `Stack` from `Vector` exposed `insertElementAt()`, breaking LIFO invariants). Composition ("has-a") should always be preferred for code reuse between unrelated concepts.

### Question 10: VehicleRental Case Analysis
- **Common Behaviors (`VehicleRental` base class):** `getRentalId()`, `getBaseRate()`, `calculateBaseDurationFee()`, `checkAvailability()`.
- **Specialized Behaviors:**
  - `CarRental`: Overrides fee calculation to factor in child safety seat add-ons and luxury tiers.
  - `TruckRental`: Overrides fee calculation to factor in cargo tonnage, axle count, and commercial driver insurance surcharges.  
Inheritance cleanly separates shared booking workflows into the superclass while delegating vehicle-specific calculations to respective derived classes.
