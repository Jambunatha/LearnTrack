# Design Notes

## Why ArrayList Instead of Array
- **Flexibility:** Unlike arrays, `ArrayList` can grow or shrink dynamically. This avoids the need to predefine a fixed size.
- **Convenience:** Provides built-in methods such as `add()`, `remove()`, and `contains()` which simplify common operations.
- **Maintainability:** Easier to manage collections of objects without worrying about manual resizing or index management.

## Use of Static Members
- **Utility Methods:** Static methods were used in the `InputValidator` class (e.g., `readInt`, `readChoice`) because they don’t depend on instance state. This makes them accessible without creating an object.
- **Constants:** Static constants were used to represent fixed values (like status codes or menu options) to ensure consistency across the application.

## Use of Inheritance
- **Shared Behavior:** Inheritance was applied where multiple classes shared common properties or methods. For example, `Person` could be a base class for `Student` and `Instructor`.
- **Code Reuse:** By inheriting from a base class, subclasses avoid duplicating code for shared attributes (like `id`, `name`) and behaviors.
- **Extensibility:** Inheritance allows new specialized classes to be added easily while still leveraging the base functionality. This makes the design more scalable and easier to maintain.