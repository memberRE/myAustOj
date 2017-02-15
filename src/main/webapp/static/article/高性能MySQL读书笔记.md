# 高性能mysq读书笔记

标签（空格分隔）： mysql
---

## 一、Mysql基本架构
###（一）两种类型锁：
1. 共享锁（读锁）
2. 排它锁（写锁）

锁粒度：
1. 表锁：是最基本的锁策略，并且是开销最小的策略。  加锁机制是锁定整张表。
2. 行级锁：行级锁可以最大程度的支持并发处理，但同时也带来最大的锁开销。（行级锁只在存储引擎层实现，而mysql服务器层没有实现）

MVCC（多版本并发控制）：可以认为MVCC行级锁的一个变种，他在很多情况下避免了加锁操作，因此开销更低，大都实现了非阻塞的读操作，写操作也只是锁定必要的行。  
    MVCC是通过保存数据在某个时间点的快照来实现的。

###（二）事务：
事务就是一组原子性的SQL查询，或者说一个独立的工作单元，如果数据库引擎能够成功的对数据库应用该组查询的全部语句，那么就执行该组查询。如果有任何一条语句因为崩溃或者其他原因无法执行，那么所有的语句都不会执行，也就是说，事务内的语句要么全部执行成功，要么全部执行失败。
一个运行良好的事务处理系统必须符合ACID标准。（原子性，一致性，隔离性，持久性）
Atomicity（原子性）：一个事务必须被视为一个不可分割的最小工作单元，整个事务的所有操作要么全部提交成功，要么全部失败回滚，对于一个事务来说，不可能只执行一个事务其中的一部分操作，这就是事务的原子性。
Consistency（一致性）：数据库总是从一个一致性的状态转换到另一个一致性的状态。
Isolation（隔离性）：一个事务所做的修改在最终提交以前对其他事务是不可见的。
Durability（持久性）：一旦事务提交，则其所做的修改就会永久的保存到数据库中，此时即使系统崩溃，修改的数据也不会丢失。

四种隔离级别：
* Read uncommitted（未提交读）：此级别中事务的修改即使没有提交，对其他事务也是可见的，事务可以读取未提交的数据，也叫脏读。
* Read committed（提交读）：此级别中一个事务开始时只能看见已经提交的事务所做的修改，也就是说一个事务从开始直到提交之前，多做的修改对其他任何事务都是不可见的。也叫不可重复读。（这个是大多数数据库的隔离级别，mysql不是）
* Repeatable read（可重复读）：该级别保证了在同一事务中多次读取同样的记录的结果是一致的。理论上这个隔离界别我发解决另一个幻读的问题。（幻读：当某个事务在读取某个范围内的记录时，另一个事务又在该范围类插入了新的数据，当之前的事务再次读取该范围内的记录时会产生幻行。InnoDB和XtraDB存储引擎通过多版本并发控制解决了幻读的问题）  这个隔离界别是mysql的默认隔离界别
* Serializable（可串行化）：这是最高的隔离界别，他通过强制事务串行执行，避免了前面所读的幻读问题。

死锁：死锁是指两个或多个失误在同一个资源上相互占用，并请求锁定对方占用的资源，从而导致恶性循环的现象。
InnoDB处理死锁：将持有最少行级排它锁的事务进行回滚。
死锁产生原因：有些是因为真正的数据冲突，有些是由于存储引擎的实现方式导致的。

事务日志：使用事务日志时，存储引擎在修改表的数据时只需要修改其内存拷贝，再把该修改记录拷贝到硬盘上的事务日志中，而不是每次都将修改的数据本身持久到磁盘上。这个提高了事务的效率。

mysql事务：mysql默认采用自动提交模式，也就是说如果不是显示的开始一个事务，则每个查询都被当做一个事务提交操作。

-----

##二、mysql基准测试
测试指标： 吞吐量、响应时间或延迟、并发性、可扩展性
* 吞吐量：指单位时间内事物处理数。
* 响应时间或延迟：测试任务所需的整体时间。（tps:每秒事务数，tpm:每分钟事务数）

数据库优化方向：
1. 优化数据库的设计，尽量复合三范式。
2. 优化sql语句，适当添加索引。
3. 添加缓存。
4. 读写分离。（可以在应用层做，也可以用第三方工具）
5. 表分离。（垂直拆分，水平拆分）
6. 编写存储过程。（模块化编程）
7. 配置优化。
8. 硬件优化。


大批量插入数据优化插入速度：
>1. 对于MyISAM存储引擎可以通过打开或关闭表非唯一索引的更新来提升插入速度。
2. 对于InnoDB引擎，可以按照主键的顺序进行导入，因为InnoDB引擎是按照主键的顺序进行保存的。
3. 在导入数据之前执行set uniqui_checks=0关闭唯一性校验来提升插入速度，导入完毕之后在开启唯一性校验。
4. 如果应用使用自动提交，再导入之前执行set aurocommit=0来关闭自动提交也可以提升导入效率。


----
## 三、多表连接查询

[多表连接查询](http://www.dedecms.com/knowledge/data-base/sql-server/2012/0709/2872.html)

左连接
```
select * from student left join course on student.ID=course.ID
```

右连接
```
select * from student right join course on student.ID=course.ID
```

完全外连接
```
select * from student full join course on student.ID=course.ID
```

内连接
```
select * from student inner join course on student.ID=course.ID

```

交叉连接(笛卡尔积)
```
select * from student cross join course
```

##四、存储过程和函数
[存储过程](http://www.cnblogs.com/lyhabc/p/3793524.html)

```
DROP PROCEDURE IF EXISTS Proc; 

DELIMITER //
CREATE PROCEDURE Proc() 
BEGIN
  SELECT * FROM t3;
END//
DELIMITER ;

CALL Proc();
```
>注意：“DELIMITER //”语句的作用是将MYSQL的结束符设置为//，因为MYSQL默认的语句结束符为分号;，为了避免与存储过程中SQL语句结束符相冲突，需要使用DELIMITER 改变存储过程的结束符，并以“END//”结束存储过程。存储过程定义完毕之后再使用DELIMITER ;恢复默认结束符。DELIMITER 也可以指定其他符号为结束符！
当使用DELIMITER命令时，应该避免使用反斜杠（\）字符，因为反斜杠是MYSQL的转义字符！

```
DELIMITER //
CREATE PROCEDURE CountProc(OUT param1 INT，IN param2 INT)
BEGIN
SELECT    COUNT(*) INTO  param1 FROM t3 where id = param2;
END//
DELIMITER ;

CALL countProc(@a,1);
select @a;
```
>方法参数说明
1. 第一个可取值out、in、inout  out表示输出，in表示输入，inout表示既可以输入也可以输出
2. 第二个为参数名称
3. 第三个为参数类型，以是MYSQL数据库中的任意类型

查看存储过程状态：
```
show procedure status like 'CountProc' \G
```
查看存储过程定义：
```
show create procedure CountProc \G
```

**变量的使用**
我们可以通过DECLARE定义一个局部变量，该变量的作用范围只能在begin...end块里。

定义变量：
```
DECLARE name INT;
```
给变量赋值：
```
set name = 2;

select count(*) from table into name;
```
定义条件处理：
```

```


[存储过程和函数的区别](http://www.gooseeker.com/cn/node/Fuller/2010061201)

## 五 函数

```
DELIMITER //

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    FUNCTION `shirospring`.`function`()
    RETURNS TYPE
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
        /*函数体*/
    END$$

DELIMITER ;
```


##六、创建视图
```
CREATE VIEW stu_view(id,NAME,glass) 
AS 
SELECT * FROM usertable,usermessage;
```

## 七、触发器

触发器只能建立在永久表上不能对临时表创建触发器。

```
DELIMITER $$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    TRIGGER `shirospring`.`trigger` BEFORE/AFTER INSERT/UPDATE/DELETE
    ON `shirospring`.`<Table Name>`
    FOR EACH ROW BEGIN

    END$$

DELIMITER ;
```



