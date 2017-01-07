## Monster 是什么

------



> * 简单的 mysql 增查删改，实现客户管理系统
> * 原生 JDBC 和 Servlet 构建的轻量级 RESTful 接口
> * 学习 JDBC 和 Servlet 的不错起点
> * json 支持使用谷歌提供的 Gson




## 数据库创建

```
 create database cute;
 create table manager(
    id int unsigned not null auto_increment primary key,
    username char(20) not null unique,
    age int unsigned not null,
    email cahr(20) not null,
    sex char(12) not null,
    address char(20) not null,
    tel char(20) not null;
    descript text
 );
 
 
```
## 前端约定



```js
let xhr = new XMLHttpRequest();
xhr.onreadustatechange =()=>{
    if(xhr.readyState == 4){
        if(xhr.status >=200 && xhr.status< 300)||xhr.status == 304){
            //Todo
            //处理json数据
        }else{
            // elseTodo
        }
    }
}
let data = {
    "username":"Yocy",
    "age": 21,
    "tel":"18792471625",
    "sex":"male",
    "address":"CN",
    "descript":"manager",
    "email":"codehuman@163.com"  //顺序无关，但有的域不能为空，具体看数据库的设计部分
}
xhr.open('post', '/add',true);
xhr.send(JSON.stringify(data));//序列化json字符串
```

## 返回的数据格式

```
//查询

[
    {
        username:"Yocy",
        sex:"male",
        age:21,
        addesss:"CN",
        descript:"manager",
        tel:"18792476125",
        email:"codehuman@163.com"
    }，
    {
    ...
    }
    
]
```


