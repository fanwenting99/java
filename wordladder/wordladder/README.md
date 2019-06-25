# Worderladder用户手册
1. 引言
   * 尝试使用java语言重新编写lab的wordladder程序，了解maven框架以及junit功能
   * 项目作者：BlackAngle
2. 功能概述
   * 目标：寻找两个给定单词间的一条最短转换路径
   * 转换规则：一次只能变更原单词的一个字母
   * 可用函数：

    | 函数名 | 释义 | 参数 |
    | ---- | ---- | ---- |
    | `Wordladder()` | 构造函数 | 无参数 |
    | `readEnglish()` | 读取字典文件 | 在控制台输入目标文件路径 |
    | `inputWord()` | 设定初始单词及目标单词 | 在控制台输入两个单词 |
    | `showAns()` | 查找并输出路径 | 无参数 |
3. 运行环境
   * maven 4.0.0
   * jdk 1.8.172
   * junit 5.4.0