＃Rand

注意:插件未经长期测试，请谨慎使用

标准格式:

    #rand[a,b]
    #rand[a,b]{returnType} (*1)
    #rand[a,b]{returnType,groupType,groupName}
    #rand[a,b]{returnType,groupType,groupName,doAction} (*2)
    
        a: 一个符合Double要求范围内的数字
        b: 同a
        a,b不做大小顺序要求
        returnType: 结果类型;数字0或1，代表小数(double)和整数(long)
        groupType: 组类型;数字0或1，代表不同或相同;在两组参数相同(*3)时将会有如下规则：
            不同组(0)：返回相异的数直到无法返回一个相异的数值
            相同组(1)：返回同一个数
        groupName: 一个组名，字符串
        doAction: 数字0或1，当设为1时重新设置组的各项参数 (*4)
        *1: 在未设置groupType和groupName时，解析结果完全随机，不属于相同或不同组，只有给出groupName才会建立组
        *2: returnType和doAction的默认值为0
        *3: 两组参数相同要求两组参数的a,b,returnType,groupType,groupName相同但不要求doAction相同;特别的，[1,5]与[5,1]被认为是相同的
        *4: 当重新设置时，组的a,b,returnType,groupType都有可能被改变但groupName不会被改变;在解析时，组的重置早于数字的解析
        
        在解析{}中内容时，若出现的任意问题，则{}中内容将会被忽视;可能出现的错误包括且不限于:
            1.要求无法达成，例如#rand[0.1,0.2]{1}
            2.输入的数值不在规定范围内或无法解析成数字，例如#rand[0.1,0.2]{3,w,n,9}
        在解析[]中内容时，若出现任意问题，该带解析字符将被跳过，例如#rand[2.4.3.m]

使用方式:

    对于玩家和控制台:
        举例来讲，你可以亲自去尝试这个指令：/say #rand[3,-1]{1} #rand[1,3]{1,1,test,0} #rand[1,3]{1,1,test,0} #rand[1,3]{1,1,test,1}
        除此之外 /rand指令将会返回所有当前已存在的组名,/rand 后的所有字符将被无视
    对于命令方块:
        举例，/rand /say #rand[-3,1]{1}
    其它:
        i'm sorry,i don't know.

ps.如果找到任意bug,你可以报告给我 ,或者放轻松,把插件移除：）
