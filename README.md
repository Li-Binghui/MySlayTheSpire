# 杀戮尖塔反编译

#### 简介
对杀戮尖塔进行逆向工程，单纯图一乐

#### 介绍
使用jadx-gui与JD-Core对"Steam\steamapps\common\SlayTheSpire"目录下的
desktop-1.0.jar进行反编译，即将.class文件转回.java。使用maven导入项目所
需要的依赖，但有些依赖因为不知道版本或各种报错，我直接将依赖反编译放在main\
java的目录下，实际上该游戏的源码只有com\megacrit\cardcrawl文件夹下的内容,
其他均为依赖的源码。

该项目单纯图一乐，可以定制各种魔改版，比如贴吧津津乐道的给战士哥一个XX，或者重刃调成一费
有多强，或者恶魔形态怎么改之类的脑洞都可以随便实现，只要修改相应的类即可。

但还是学习用途吧，毕竟steam还在卖40呢，自己拿来玩玩得了，搞太多盗版就不好了。



#### 笔者环境

1.  **jdk17** _实在不想用1.8的老古董了，不过没用什么新语法，用1.8编译也行_
2.  **Apache Maven 3.8.1** 
3.  **Git** _version 2.33.0.windows.2_

#### 使用说明

1. 先从gitee上拉代码，加载maven脚本 如果左下角没提示在项目中找到pom.xml,右键加载
2. idea->文件->设置->构建、执行、部署->构建工具->maven 设置maven的仓库位置、版本、setting.xml
3. idea->文件->设置->构建、执行、部署->构建工具->编译器->设置编译器版本为1.8或17
4. idea->文件->项目结构->设置相应的jdk版本，如果idea识别不出.java文件可以在模块中标识源代码与资源
5. idea中Ctrl+N搜索DesktopLauncher类,编译后启动游戏。

#### 缺陷 & bug
现在能进入游戏首页，但进入游戏加载地图时会报错 2022.11.20
#### 说明
author：谢文

email：mistletoe_wen@yeah.net

last update：2022.11.20

version：冬 霜降


