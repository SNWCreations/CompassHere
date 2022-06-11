# CompassHere

就是一个可以通过指令让所有玩家的指南针统一指向一个位置的插件。

命令是: /compasshere [位置]
* (位置可选，当提供时不使用执行者的位置)

需要 CommandAPI v7.0 运行此插件，服务端需要 Java 17 ，Paper 绕过 Java 检测的方法是向命令行提供特殊参数，如:

    java -DPaper.IgnoreJavaVersion=true -jar paper-server.jar

特别感谢: [CommandAPI by JorelAli](https://github.com/JorelAli/CommandAPI)
