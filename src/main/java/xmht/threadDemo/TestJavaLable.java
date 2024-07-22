package xmht.threadDemo;

/**
 * @author shengjk1
 * @date 6/27/2024
 */

/*
在 Java 中，标签（Label）本质上并不是一种语言结构，而是一种在编译器级别进行的标记，用于在代码中标识特定的代码块，使得可以通过标签来控制代码流程。

在 Java 编译器的实现中，标签的处理方式如下：

1. **编译阶段**：当 Java 源代码被编译时，编译器会识别标签，并为每个标签赋予一个唯一的标识符（label identifier）。

2. **生成字节码**：编译器将源代码转换为 Java 字节码时，会在字节码中加入对标签的支持。具体地，在生成的字节码中，标签会被转换为跳转指令（jump instructions）来实现跳转的功能。

3. **运行时执行**：在程序运行时，Java 虚拟机（JVM）会按照字节码中的跳转指令来执行代码，包括对标签的跳转进行处理。

4. **控制流跳转**：当执行到带有标签的代码块时，通过标签实现的跳转指令可以让程序在执行过程中跳到指定的位置，以控制代码的流程。

虽然 Java 中标签提供了一种在循环和代码块中进行跳转的机制，但由于其使用并不常见，且容易导致代码的可读性下降，一般建议在正常编程中尽量避免过度依赖标签。在大多数情况下，使用传统的控制流语句（如 `break` 和 `continue`）可以更清晰地表达逻辑，避免引入不必要的复杂性。

总的来说，标签是在 Java 编译器层面实现的一种跳转标记，通过在字节码中转换成对应的跳转指令来实现程序执行时的控制流跳转。



标签在某些情况下仍然有其独特的优势，尤其是在需要在多层嵌套循环中直接跳出外部循环时。虽然使用 `return` 可以在一定程度上替代标签的功能，但以下是一些情况下标签的使用更为合适的原因：

1. **多层嵌套循环**：在多层嵌套循环中，如果需要在内层循环中直接跳出外层循环，使用 `return` 就只能结束当前方法的执行，而不方便只跳出外层循环而保持方法继续执行。此时，标签可以更直接地控制外部循环的执行。

2. **可读性**：在某些情况下，使用标签能够使代码更具可读性。当需要在嵌套结构中的深层循环中有特定逻辑时，通过标签来显示地标识、命名和控制循环更直观。

3. **灵活性**：标签提供了程序员对代码流程更细粒度控制的方式。有些情况下，可能需要更加精准地控制循环的终止或跳转，标签提供了这种细致控制的能力。

4. **习惯和惯例**：在一些编程风格和团队中，使用标签来控制循环是一种常见的做法，因为标签可以提供一种直接、清晰地控制循环的方法。

虽然在许多情况下可以通过其他方式避免使用标签，但在特定场景下仍然会有需求使用标签来更灵活、直接地控制循环。因此，在选择是否使用标签时，需要权衡代码的清晰性、可读性、灵活性和团队的编程习惯等因素。
 */
public class TestJavaLable {
    public static void test(){
        retry:
        for (int i = 0; i <= 100; i++) {
            System.out.println("============= "+i);
            if (i==90){
                break retry;
            }
            if (i!=90){
                continue retry;
            }
        }
    }

    public static void nestedLoopWithLabel() {
        outerLoop:
        for (int i = 0; i < 5; i++) {
            System.out.println("Outer Loop: " + i);
            for (int j = 0; j < 3; j++) {
                System.out.println("Inner Loop: " + j);
                if (j == 2) {
                    break outerLoop;
                }
            }
        }
    }

    public static void nestedLoopWithoutLabel() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Outer Loop: " + i);
            for (int j = 0; j < 3; j++) {
                System.out.println("Inner Loop: " + j);
                if (j == 2) {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        // test();
        // nestedLoopWithLabel();
        // System.out.println("=============");
        // nestedLoopWithoutLabel();

        outerLoop:
        for (int i = 0; i < 5; i++) {
            System.out.println("outer Loop: " + i);
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    continue outerLoop; // 继续外部循环
                }
                System.out.println("Inner Loop: " + j);
            }
        }


        System.out.println("======================");
        for (int i = 0; i < 5; i++) {
            System.out.println("outer Loop: " + i);
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    break; // 继续外部循环
                }
                System.out.println("Inner Loop: " + j);
            }
        }

    }
}
