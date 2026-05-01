# Setup Instructions

## JDK Version Used
JDK used for this project

- `openjdk version "17.0.12"`
- `javac "17.0.12"`

## Verify Java Installation
```powershell
java -version
javac -version
```

## Running a "Hello World" Program
1. Create a file named `HelloWorld.java` with the following content:
   ```java
   public class HelloWorld {
       public static void main(String[] args) {
           System.out.println("Hello, World!");
       }
   }
   ```
2. Compile the program:
   ```powershell 
   javac HelloWorld.java
   ```
   This generates a `HelloWorld.class` file containing bytecode.

3. Run the program:
    ```powershell 
   java HelloWorld
   ```
   If the program prints `Hello, World!`, your JDK setup is working correctly and you can compile/run LearnTrack.
