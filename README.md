文件结构如下：
> Exam1
>>Main

> Exam2
>>FileClient  
>>FileServer 

>Exam3
>>Test3

可以运行run.sh,选择你想要运行的程序  
但运行Exam2时需先打开FileServer，另打开一个控制台打开FileClient  
  
还可以有以下方法：
运行build.sh之后可指定运行的类  
运行Exam1中的Main,输入以下指令：  
java -cp Exam1/target/Exam1.jar Main 
 
运行Exam2中的FileServer,输入以下指令：  
java -cp Exam2/target/Exam2.jar FileServer  

运行Exam2中的FileClient ,输入以下指令：  
java -cp Exam2/target/Exam2.jar FileClient   

运行Exam3中的Test3,输入以下指令：  
java -cp Exam3/target/Exam3.jar Test3  


