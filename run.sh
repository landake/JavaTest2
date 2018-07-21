#!/bin/bash
while :
do
read -p "请输入想要运行的jar包:" filename
read -p "请输入想要运行的类:" class
echo "$filename中$class类的运行结果如下:"
java -cp $filename/target/$filename.jar $class
done







