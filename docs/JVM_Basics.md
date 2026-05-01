# JVM Basics

## JDK, JRE, JVM
- **JDK (Java Development Kit):** A toolkit for developers. It includes the compiler (`javac`), libraries, and tools needed to write and build Java programs.
- **JRE (Java Runtime Environment):** Provides the libraries and JVM needed to run Java applications, but does not include development tools like the compiler.
- **JVM (Java Virtual Machine):** The engine that executes Java bytecode. It translates bytecode into machine instructions for the host operating system.

## Bytecode
- Bytecode is the intermediate representation of Java code produced after compilation.  
- It is platform-independent and can be executed by any JVM, regardless of the underlying hardware or operating system.

## "Write Once, Run Anywhere"
- This phrase means that once you compile Java code into bytecode, it can run on any system that has a JVM.  
- Developers don’t need to rewrite or recompile their code for different operating systems. The JVM abstracts away the platform differences, ensuring portability and consistency.
