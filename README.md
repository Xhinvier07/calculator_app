# C4LCUL4TOR

C4LCUL4TOR is a simple, hacker-styled calculator application developed in Java using Swing for the graphical user interface.

## Features

- Basic arithmetic operations: addition, subtraction, multiplication, division
- Additional operations: exponentiation, modulo
- Retro/hacker-style green-on-black interface
- Error handling for syntax errors and division by zero
- I wonder what displays after inputting `69696969`...?

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Ways of Running the Application

1. Clone/download this repo then run `Main`

2. Download only the `calculator_app.jar` file and run this at the jar's directory:
   ```
   java -jar calculator_app.jar
   ```

## Coding Conventions

The following coding conventions were used in the development of this application:

1. **Naming Conventions**:
   - Classes: PascalCase (e.g., `Screen`, `Main`)
   - Methods and variables: camelCase (e.g., `createDisplay()`, `firstOperand`)
   - Constants: UPPER_SNAKE_CASE (e.g., `WINDOW_WIDTH`, `BG_COLOR`)

2. **Package Structure**:
   - All classes are in the `com.calcuapp` package

3. **Access Modifiers**:
   - Classes are declared as `public`
   - The `Screen` class is declared as `final` to prevent inheritance
   - Fields are generally `private` unless needed otherwise
   - Constants are declared as `private static final`

4. **Code Organization**:
   - Related methods are grouped together (e.g., UI creation methods, calculation methods)
   - Constants are declared at the top of the class

5. **Comments**:
   - Single-line comments are used for brief explanations
   - Method-level comments are omitted in favor of self-explanatory method names

6. **Error Handling**:
   - Try-catch blocks are used for potential exceptions (e.g., `NumberFormatException`)
   - Custom error messages are displayed for syntax errors and division by zero

7. **UI Design**:
   - Consistent color scheme using predefined color constants
   - Uniform sizing and spacing of UI elements

8. **Event Handling**:
   - Lambda expressions are used for ActionListeners

9. **Data Types**:
   - Appropriate primitive types are used (e.g., `double` for operands, `char` for operators)

10. **Method Design**:
    - Methods are kept short and focused on a single responsibility
    - Private helper methods are used to break down complex operations

11. **Constants**:
    - Magic numbers and strings are avoided in favor of named constants

12. **Serialization**:
    - `serialVersionUID` is defined for the `Screen` class which extends `JFrame`

13. **Null Handling**:
    - Null checks are implicit through the use of the Swing framework

14. **Resource Management**:
    - Swing components are used, which handle resource management automatically

15. **Formatting**:
    - Consistent indentation (4 spaces)
    - Braces on the same line as control statements
    - Spaces around operators and after commas

These conventions contribute to the readability, maintainability, and consistency of the codebase.

## Authors

- [d-quint](https://github.com/d-quint)
- [Xhinvier07](https://github.com/Xhinvier07)
- [Jccc07](https://github.com/Jccc07)
- [DSandhu12](https://github.com/DSandhu12)
