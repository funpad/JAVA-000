# 字节码反汇编分析
## javap命令
```
用法: javap <options> <classes>
其中, 可能的选项包括:
  -? -h --help -help               输出此帮助消息
  -version                         版本信息
  -v  -verbose                     输出附加信息
  -l                               输出行号和本地变量表
  -public                          仅显示公共类和成员
  -protected                       显示受保护的/公共类和成员
  -package                         显示程序包/受保护的/公共类
                                   和成员 (默认)
  -p  -private                     显示所有类和成员
  -c                               对代码进行反汇编
  -s                               输出内部类型签名
  -sysinfo                         显示正在处理的类的
                                   系统信息（路径、大小、日期、SHA-256 散列）
  -constants                       显示最终常量
  --module <模块>, -m <模块>       指定包含要反汇编的类的模块
  --module-path <路径>             指定查找应用程序模块的位置
  --system <jdk>                   指定查找系统模块的位置
  --class-path <路径>              指定查找用户类文件的位置
  -classpath <路径>                指定查找用户类文件的位置
  -cp <路径>                       指定查找用户类文件的位置
  -bootclasspath <路径>            覆盖引导类文件的位置
  --multi-release <version>        指定要在多发行版 JAR 文件中使用的版本
```

## 执行javap

```
$ javap -p -c -s -v HelloByteCode
```


## 输出
```
Classfile 绝对路径/HelloByteCode.class  // class文件
  Last modified 2020年10月16日; size 1052 bytes
  SHA-256 checksum 8c9f48899a2fe637185e77c7c0c4d5f3644ad8315bce132856a81b077cfe735d // 校验和，之前的Java版本使用的MD5
  Compiled from "HelloByteCode.java"
public class HelloByteCode
  minor version: 0   // 次版本号
  major version: 57  // 主版本号
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER // 类的访问标志
  this_class: #7                          // HelloByteCode
  super_class: #2                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 4, attributes: 1 // 0个接口，0个字段，4个方法（包括默认的构造方法），attributes？
Constant pool:  // 常量池，理解为class文件中的资源仓库，存放字面量(Literal)和符号引用(Symbolic References)
   #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
   #2 = Class              #4             // java/lang/Object
   #3 = NameAndType        #5:#6          // "<init>":()V
   #4 = Utf8               java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Class              #8             // HelloByteCode
   #8 = Utf8               HelloByteCode
   #9 = Methodref          #7.#3          // HelloByteCode."<init>":()V
  #10 = Methodref          #7.#11         // HelloByteCode.calculate:()I
  #11 = NameAndType        #12:#13        // calculate:()I
  #12 = Utf8               calculate
  #13 = Utf8               ()I
  #14 = Methodref          #7.#15         // HelloByteCode.control:()Z
  #15 = NameAndType        #16:#17        // control:()Z
  #16 = Utf8               control
  #17 = Utf8               ()Z
  #18 = Fieldref           #19.#20        // java/lang/System.out:Ljava/io/PrintStream;
  #19 = Class              #21            // java/lang/System
  #20 = NameAndType        #22:#23        // out:Ljava/io/PrintStream;
  #21 = Utf8               java/lang/System
  #22 = Utf8               out
  #23 = Utf8               Ljava/io/PrintStream;
  #24 = Methodref          #25.#26        // java/io/PrintStream.println:(I)V
  #25 = Class              #27            // java/io/PrintStream
  #26 = NameAndType        #28:#29        // println:(I)V
  #27 = Utf8               java/io/PrintStream
  #28 = Utf8               println
  #29 = Utf8               (I)V
  #30 = Class              #31            // java/lang/RuntimeException
  #31 = Utf8               java/lang/RuntimeException
  #32 = String             #33            // Flag not allowed.
  #33 = Utf8               Flag not allowed.
  #34 = Methodref          #30.#35        // java/lang/RuntimeException."<init>":(Ljava/lang/String;)V
  #35 = NameAndType        #5:#36         // "<init>":(Ljava/lang/String;)V
  #36 = Utf8               (Ljava/lang/String;)V
  #37 = Utf8               Code
  #38 = Utf8               LineNumberTable
  #39 = Utf8               LocalVariableTable
  #40 = Utf8               this
  #41 = Utf8               LHelloByteCode;
  #42 = Utf8               main
  #43 = Utf8               ([Ljava/lang/String;)V
  #44 = Utf8               args
  #45 = Utf8               [Ljava/lang/String;
  #46 = Utf8               helloByteCode
  #47 = Utf8               a
  #48 = Utf8               I
  #49 = Utf8               b
  #50 = Utf8               c
  #51 = Utf8               d
  #52 = Utf8               e
  #53 = Utf8               f
  #54 = Utf8               flag
  #55 = Utf8               StackMapTable
  #56 = Utf8               SourceFile
  #57 = Utf8               HelloByteCode.java
{
  public HelloByteCode(); // 构造方法
    descriptor: ()V // V表示返回void
    flags: (0x0001) ACC_PUBLIC // 访问标志为public
    Code:
      // 操作数栈最大深度为1，局部变量占用1个Slot，方法参数个数为1
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:  // 行号表
        line 6: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LHelloByteCode;

  public static void main(java.lang.String[]);  // main方法
    descriptor: ([Ljava/lang/String;)V  // [表示以为数组，L表示对象类型，V表示返回值为void类型
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1  // 操作数栈最大深度为2，局部变量占用2个槽Slot，参数个数为1
         0: new           #7                  // class HelloByteCode
         3: dup
         4: invokespecial #9                  // Method "<init>":()V
         7: astore_1
         8: aload_1
         9: invokevirtual #10                 // Method calculate:()I
        12: pop
        13: aload_1
        14: invokevirtual #14                 // Method control:()Z
        17: pop
        18: return
      LineNumberTable:
        line 9: 0
        line 10: 8
        line 11: 13
        line 12: 18
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      19     0  args   [Ljava/lang/String;
            8      11     1 helloByteCode   LHelloByteCode;

  public int calculate();
    descriptor: ()I
    flags: (0x0001) ACC_PUBLIC
    Code:
      // 操作数栈深度为2，局部变量表有7个槽位，
      // 参数为1个（当前对象实例的引用：this）
      stack=2, locals=7, args_size=1
         0: iconst_4
         1: istore_1
         2: bipush        6
         4: istore_2
         5: iload_1
         6: iload_2
         7: iadd
         8: istore_3
         9: iload_3
        10: iload_2
        11: isub
        12: istore        4
        14: iload_1
        15: iload         4
        17: idiv
        18: istore        5
        20: iload         5
        22: iload_2
        23: imul
        24: istore        6
        26: getstatic     #18                 // Field java/lang/System.out:Ljava/io/PrintStream;
        29: iload         6
        31: invokevirtual #24                 // Method java/io/PrintStream.println:(I)V
        34: iload         6
        36: ireturn
      // 行号表：源码行号与字节码行号（偏移量）的映射，在debug时或是获取发生Exception的源码行号时用到
      LineNumberTable:
        line 15: 0
        line 16: 5
        line 17: 9
        line 18: 14
        line 19: 20
        line 20: 26
        line 21: 34
      // 局部变量表：描述栈帧中局部变量的信息
      LocalVariableTable:
        // Start与Length描述了变量（Name列）的作用域，
        // 数值对应的上方“Code:”区域的字节码“行号”，
        // Slot列显示变量占用的槽位数，Signature描述变量的类型。
        Start  Length  Slot  Name   Signature
            // this变量作用域是整个方法体：对应字节码行号为0～(0+37-1)，
            // 即0～36 => 0: iconst_4 <-> 36: ireturn
            0      37     0  this   LHelloByteCode;
            2      35     1     a   I
            5      32     2     b   I
            9      28     3     c   I
           14      23     4     d   I
           20      17     5     e   I
           26      11     6     f   I

  boolean control();
    descriptor: ()Z
    flags: (0x0000)
    Code:
      stack=3, locals=2, args_size=1
         0: iconst_1
         1: istore_1
         2: iload_1
         3: ifne          8
         6: iconst_0
         7: ireturn
         8: iload_1
         9: iconst_1
        10: if_icmpne     15
        13: iconst_1
        14: ireturn
        15: new           #30                 // class java/lang/RuntimeException
        18: dup
        19: ldc           #32                 // String Flag not allowed.
        21: invokespecial #34                 // Method java/lang/RuntimeException."<init>":(Ljava/lang/String;)V
        24: athrow
      LineNumberTable:
        line 25: 0
        line 26: 2
        line 27: 6
        line 28: 8
        line 29: 13
        line 31: 15
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      25     0  this   LHelloByteCode;
            2      23     1  flag   I
      StackMapTable: number_of_entries = 2  // 操作数栈映射表
        frame_type = 252 /* append */
          offset_delta = 8
          locals = [ int ]
        frame_type = 6 /* same */
}
SourceFile: "HelloByteCode.java"
```

