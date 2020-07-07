# background
将人脸检测的数据整理计算输出到excel中

# 运行命令说明

### 当前版本和上个版本比较

```
java -jar faceTest-1.1-jar-with-dependencies.jar 0 旧版本算法目录 新版本算法目录 输出目录 阈值
```



### 当前版本和gt比较

```
java -jar faceTest-1.1-jar-with-dependencies.jar 0 gt目录 算法目录 输出目录 阈值
```



### 当前版本和上个版本以及gt比较

```
java -jar faceTest-1.1-jar-with-dependencies.jar 2 gt目录 旧版本算法目录 新版本算法目录 输出目录 阈值
```


### 输入样例
```
gt目录
D:\test\gt
算法目录
D:\test\algorithm
输出目录
D:\test\output

gt文件
conformance_1_1_3072x4096.pnt
conformance_1_1_3072x4096.txt
算法文件
conformance_1_1_3072x4096.jpg.Res.txt
```
