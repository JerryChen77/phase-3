# 一、Vue概述

- 官网：https://cn.vuejs.org/

- vue是一个渐进式的js框架。
- vue是基于后台的MVC思想实现的。【MVVM】



# 二、MVVM

- Model：模型层，在这里表示 JavaScript 对象
- View：视图层，在这里表示 DOM（HTML 操作的元素）
- ViewModel：连接视图和数据的中间件，**Vue.js 就是 MVVM 中的 ViewModel 层的实现者**

| MVVM的原理图【双向数据绑定】                                 |
| ------------------------------------------------------------ |
| <img src="pic/image-20210731092540330.png" alt="image-20210731092540330" style="zoom:80%;" /> |



# 三、快速入门

## 1、实现步骤【参考官网】

> 1. 引入vue.js
> 2. 创建一个div，定义其id属性
> 3. 创建vue对象，绑定到有id属性的app。编写代码

## 2、具体实现

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>01-vue-quick.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id="app">
    <!-- 插值表达式 -->
    {{ message }}
</div>

</body>

<script>
    /* vue对象 */
    var app = new Vue({
        el: '#app',    //vue的核心属性 element的简写
        data: {   //数据，vue中的数据对象
            message: 'Hello Vue!'
        }
    })
</script>
</html>
```



## 3、插值表达式

- 语法

```
{{ vue的数据属性或vue的方法 }}
```

- 使用

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>01-vue-quick.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id="app">
    <!-- 插值表达式 -->
    {{ message }}  <br/>
    {{product.name}} <br/>
    {{nums[2]}} <br/>
    {{products[1].name}}<br/>
    {{say()}}
</div>

</body>

<script>
    /* vue对象 */
    var app = new Vue({
        el: '#app',    //vue的核心属性 element的简写
        data: {   //数据，vue中的数据对象
            message: 'Hello Vue!',
            product: {
                name: "联想y7000",
                price: 7000,
                proDate: "2018-06-27"
            },
            nums : [10,25,12,30],
            products : [
                {
                    name: "联想y7000",
                    price: 7000,
                    proDate: "2018-06-27"
                },
                {
                    name: "海尔冰箱1050",
                    price: 6666,
                    proDate: "2019-08-27"
                }
            ]
        },
        methods : {
            say : function () {
                return "Hello";
            }
        }
    })
</script>
</html>
```



# 四、VUE指令

## 1、v-on

- 绑定事件
- v-on: 可以简写成 @

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>02-vue-on.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id="app">

    <button onclick="test1()">原生的单击事件</button><br/>

    <button v-on:click="test2()">vue的单击事件</button><br/>

    <button @click="test3">v-on:的简写方式-- v-on:可以写成@</button><br/>
    
    <div @mousemove="test4" style="width: 300px; height: 100px; background-color:#AABBAA;">我是一个div</div>
    {{x}}

</div>

</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!',
            x : 0
        },
        methods : {
            test2 : function () {
                console.log("vue的单击事件....");
            },
            test3 : function () {
                console.log("v-on:可以写成@....");
            },
            test4 : function (event) {
                this.x = event.clientX;  //得到当前窗口的x轴坐标
            }
        }
    })

    function test1() {
        console.log("原生的单击事件....");
    }

</script>
</html>
```

- 事件细节【事件传参，事件修饰符，按键修饰符】

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>03-事件细节.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id="app">

    <button @click="test1()">事件传参1</button><br/>
    
    <button @click="test2('lucy')">事件传参2</button><br/>

    <!-- 在vue的指定中，使用vue的data属性中的变量时，不要使用插值表达式，而是直接使用 -->
    <button @click="test3(message)">事件传参3</button><br/>


    <!-- 事件修饰符 : 阻止事件冒泡 -->
    <div @mousemove="test4" style="width: 300px; height: 100px; background-color:#AABBAA;">
        我是一个div[test4]
        <div @mousemove.stop="test5" style="width: 100px; height: 50px; background-color:yellow;">我是一个div[test5]</div>

    </div>

    <br/>

    <!-- 按键修饰符 -->
    <form action="01-vue-quick.html" method="post">
        username : <input name="username" type="text"/>
        <input type="button" value="提交"/>
    </form>

    <br/>
    用户名 : <input name="username" @keyup.enter="test6()" type="text"/>

</div>

</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!'
        },
        methods : {
            test1 : function (event) {
                console.log("v-on:可以写成@....");
                console.log(event);
            },
            test2 : function (obj) {
                console.log(obj);
            },
            test3 : function (obj) {
                console.log(obj);
            },
            test4 : function () {
                console.log("test4....")
            },
            test5 : function (event) {
                console.log("test5....")
                //event.stopPropagation();  //阻止事件传播
            },
            test6 : function () {
                console.log(11111);
            }
        }
    });

</script>
</html>
```



## 2、v-bind

- 属性绑定
- v-bind: 可以简写成 :

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>04-v-bind.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id="app">

    <a href="http://www.baidu.com">baidu</a><br/>

    <a v-bind:href="link">百度</a><br/>

    <!-- v-bind: 可以简写成 : -->
    <a :href="link">百度[v-bind的简写]</a>

</div>

</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            link : "http://www.baidu.com"
        },
        methods : {
        }
    });

</script>
</html>
```



## 3、v-html、v-text、v-model

- v-html 等同于js中innerHTML 和 jQuery中的html()
- v-text 等同于js中innerText和jQuery中的text()
- v-model 相当于name属性和value属性的总和

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>05-v-html-text.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id="app">

    <span v-html="link"></span><br/>

    <span v-text="link"></span><br/>

    <hr/>

    <!-- v-model 等同于 name 和 value -->
    用户名:<input v-model="username"/><br/>
    {{username}}

</div>

</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            link:"<a href='http://www.baidu.com'>百度</a>",
            username : 'jack'
        },
        methods : {
        }
    });


</script>
</html>
```



## 4、v-if、v-else-if、v-else、v-show

- v-if：判断
- v-show：条件成立则显示

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>06-v-if-show.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id="app">

    <p v-if="age < 16 && age >= 0">未成年人，只能想想</p>
    <p v-else-if="age <= 65">中年人，要努力奋斗</p>
    <p v-else>老年人，回家享受</p>

    <input type="text" v-model="age"/><br/>

    <div v-show="age < 16">v-show 未成年人，只能想想</div>
    <!--
        v-if : 条件成立，则渲染。条件不成立，则不渲染【没有元素】
        v-show : 条件成立，则显示。条件不成立，不显示【通过css样式来隐藏元素】
     -->

</div>

</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {

            age : 12

        },
        methods : {
        }
    });


</script>
</html>
```



## 5、v-for

- 遍历json对象和数组

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>07-v-for.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id="app">

    <ul>
        <!--
            v-for遍历对象 : (v, k, i)
                第一个位置 ：值
                第二个位置 : 键
                第三个位置 : 索引
         -->
        <li v-for="(value,key,index) in user">
            {{index}} - {{key}} - {{value}}
        </li>
    </ul>
    <hr/>


    <ul>
        <!--
            v-for遍历数组[数组元素是基本类型] : (v, i)
                第一个位置 ：值
                第三个位置 : 索引
         -->
        <li v-for="(value,index) in nums">
            {{index}} - {{value}}
        </li>
    </ul>

    <hr/>


    <ul>
        <!--
            v-for遍历数组[数组元素是对象] : (v, i)
                第一个位置 ：值
                第三个位置 : 索引
         -->
        <li v-for="(p,index) in products" v-if="index % 2 == 0">
            {{index}} - {{p.name}}=={{p.price}}=={{p.proDate}}
        </li>
    </ul>
    
    <!--
        分页条
     -->
    <span v-for="num in 10">
        {{num}}&nbsp;&nbsp;&nbsp;&nbsp;
    </span>


</div>

</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            user : {
                username : "jack",
                age : 12
            },
            nums : [10,11,52,6,2,34],
            products : [
                { name:"A4纸", price:10, proDate:'2000-12-12'},
                { name:"宝宝用品", price:50, proDate:'2020-12-12'},
                { name:"餐车", price:10000, proDate:'1999-12-13'},
            ]
        },
        methods : {
        }
    });


</script>
</html>
```



# 五、监听属性、计算属性

- 监听属性

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>08-监听属性.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id = "app">
    <p style = "font-size:25px;">计数器: {{ counter }}</p>
    <button @click="counter++" style = "font-size:25px;">点我</button>
</div>

</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            counter : 0
        },
        methods : {
        },
        watch : {
            //两个参数
            //参数一：改变后的值
            //参数二：改变前的值
            counter : function (newVal, oldVal) {
                console.log('计数器值的变化 :' + oldVal + ' 变为 ' + newVal + '!');
            }
        }
    });


</script>
</html>
```

- 计算属性

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>09-计算属性.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id = "app">
    <p style = "font-size:25px;">
        time1: {{time1()}}
    </p>
    <p style = "font-size:25px;">
        time2: {{time2}}
    </p>

    <button @click="addTime1(1)">add time1</button>
    <button @click="addTime2(1)">add time2</button>
</div>

</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {

        },
        methods : {
            //time1是函数，所以每次调用都会执行
            time1 : function () {
                return Date.now();
            },
            addTime1 : function (obj) {
                console.log(this.time1() + obj);
            },
            addTime2 : function (obj) {
                console.log(this.time2 + obj);
            }
        },
        computed : {
            //time2是计算属性，所以只有第一次使用时才会执行，且会把该属性的值缓存下来，后面每次使用都使用的是缓存下来的值
            time2 : function() {
                return Date.now();
            }
        }

    });


</script>
</html>
```



# 六、组件化

- 在vue看来，万事万物皆组件。组件可以被复用

- 组件要使用，就必须先注册。
- 组件分为两类
  - 局部组件【只能在当前vue中使用】
  - 全局组件【可以在多个vue中使用】

## 1、局部组件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>09-计算属性.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id = "app">
    <!-- 使用组件 -->
    <mytitle></mytitle>
</div>

<hr/>

<div id = "app1">
    <!-- 使用组件 -->
    <!--<mytitle></mytitle>-->
    <button @click="test()">app1获取app中的内容</button>
</div>


</body>

<script>
    var vm = new Vue({
        el: '#app',
        data : {
          name : "lucy"
        },
        //组件
        components : {
            //组件注册
            //组件名 : { 组件的内容（vue对象） }
            "mytitle" : {
                //vue组件，那么data必须是函数，且有return返回值是json格式
                data : function () {
                    return {
                        message : "局部组件注册"
                    }
                },
                methods : {
                    changeMessage : function () {
                        this.message = "改改改";
                    }
                },
                //template ： vue组件需要挂载到哪个html内容上，template的内容必须有且只有一个根标准
                template : "<div><button @click='changeMessage()'>改变Message的值</button>{{message}}</div>"
            }
        }

    });

    new Vue({
        el : "#app1",
        methods : {
            test : function () {
                //在#app1中使用 #app对应的vue的中属性或者方法
                console.log("在#app1中使用 #app对应的vue的中属性：" + vm.name);
            }
        }
    });

</script>
</html>
```



## 2、全局组件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>09-计算属性.html</title>

    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>

<div id = "app">
    <!-- 使用组件 -->
    <mytitle></mytitle>
    <mytitle></mytitle>
</div>

<hr/>

<div id = "app1">
    <!-- 使用组件 -->
    <mytitle></mytitle>
    <mytitle></mytitle>
</div>


</body>

<script>

    //全局组件注册，可以在多个vue对象中去使用
    Vue.component("mytitle" , {
        //vue组件，那么data必须是函数，且有return返回值是json格式
        data : function () {
            return {
                message : "局部组件注册"
            }
        },
        methods : {
            changeMessage : function () {
                this.message = "改改改";
            }
        },
        //template ： vue组件需要挂载到哪个html内容上，template的内容必须有且只有一个根标准
        template : "<div><button @click='changeMessage()'>改变Message的值</button>{{message}}</div>"
    });


    var vm = new Vue({
        el: '#app',
        data : {
          name : "lucy"
        }

    });

    new Vue({
        el : "#app1",
        methods : {
        }
    });

</script>
</html>
```



# 七、生命周期

- vue对象在创建时，会有一套生命周期，在生命周期这个过程中，会有很多钩子函数会被触发

| 生命周期及钩子函数     |
| ---------------------- |
| ![](pic/lifecycle.png) |



# 八、主流的项目开发方式

- 前后端分离开发
  - Java 程序员 ： 前台给你JSON ----【Controller + Service + Dao + POJO＋DB】---- 给前台JSON
    - postman
  - H5：把JSON --- 【后台】--- 渲染页面【处理JSON】
    - mock假数据
- 前台端联调
  - 后台：运行项目【用tomcat】
  - 前台：运行项目【node.js】



# 九、使用vue-cli脚手架来开发vue项目

## 1、安装node.js

- 简单理解：就是前台的一个tomcat

- 安装：闭着眼睛下一步

  - 安装包：node-v14.17.1-x64.msi

  - cmd：node -v



## 2、安装vue脚手架

- vue-cli
  - 安装
    - cmd：npm install vue-cli -g
- 安装cnpm

```
打开cmd，输入命令  
	npm install -g cnpm --registry=https://registry.npm.taobao.org

安装Vue需要npm的版本大于3，所以我们先升级一下npm，输入命令
	cnpm install cnpm -g

安装vue，输入命令
	cnpm install vue

安装vue-cli，输入命令
	cnpm install --global vue-cli
```



## 3、使用vue-cli创建vue项目

- 查看vue-cli所支持模板

| vue list                                                     |
| ------------------------------------------------------------ |
| <img src="pic/image-20210731155834871.png" alt="image-20210731155834871" style="zoom:80%;" /> |

- 创建项目

| vue init webapck 项目名                                      |
| ------------------------------------------------------------ |
| <img src="pic/image-20210731160612783.png" alt="image-20210731160612783" style="zoom:80%;" /> |

- 启动项目

| npm run dev                                                  |
| ------------------------------------------------------------ |
| <img src="pic/image-20210731161007502.png" alt="image-20210731161007502" style="zoom:80%;" /> |

- 访问

| http://localhost:8080                                        |
| ------------------------------------------------------------ |
| <img src="pic/image-20210731161049055.png" alt="image-20210731161049055" style="zoom: 80%;" /> |



# 十、vue项目

## 1、使用vue-cli创建项目

> 1. vue init webpack 项目名
> 2. cnpm install
>    1. 安装当前项目所需要的模块
> 3. cd 项目目录中
> 4. cnpm run dev
>    1. 启动项目
> 5. 测试

| 执行流程                     |
| ---------------------------- |
| ![](pic/vue项目执行流程.png) |

| 目录结构                                                     |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802094504971.png" alt="image-20210802094504971" style="zoom:80%;" /> |



## 2、自己构建一个vue项目

- 需求

| 要呈现的效果                                                 |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802094902739.png" alt="image-20210802094902739" style="zoom:80%;" /> |

- 实现

  - 子组件

    - MyHeader.vue

    ```vue
    <template>
      <div>
        <h1>{{title}}</h1>
      </div>
    </template>
    
    <script>
      export default {
        name: 'MyHeader',
        data () {
          return {
            title : '千锋教育'
          }
        }
      }
    </script>
    
    <style scoped>
    
    </style>
    
    ```

    - MyBody.vue

    ```vue
    <template>
      <div>
        <h3>{{info}}</h3>
      </div>
    </template>
    
    <script>
    export default {
      name: 'MyBody',
      data () {
        return {
          info : '杭州Java2103班'
        }
      }
    }
    </script>
    
    <style scoped>
    
    </style>
    
    ```

    - MyFooter.vue

    ```vue
    <template>
      <div>
        <p>{{msg}}:{{name}}</p>
      </div>
    </template>
    
    <script>
    export default {
      name: 'MyFooter',
      data () {
        return {
          msg : '版权所属',
          name : '小婷'
        }
      }
    }
    </script>
    
    <style scoped>
    
    </style>
    
    ```

  - 父组件

    - App.vue

    ```vue
    <!-- 页面要显示的内容 -->
    <template>
      <div id="app">
        <!-- 3.使用组件 -->
        <MyHeader></MyHeader>
        <MyBody></MyBody>
        <MyFooter></MyFooter>
    
      </div>
    </template>
    
    <!-- js脚本 -->
    <script>
    //1.导入要使用文件
    import MyHeader from './components/MyHeader'
    import MyBody from './components/MyBody'
    import MyFooter from './components/MyFooter'
    
    export default {
      name: 'App',
      components: {
        //2.注册组件
        MyHeader,
        MyBody,
        MyFooter
      }
    }
    </script>
    
    <!-- css样式 -->
    <style>
    /*#app {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      margin-top: 60px;
    }*/
    </style>
    
    ```



## 3、组件之间的参数传递

### 3.1 父传子

- 父组件把参数传递给子组件

  - 子组件

  ```vue
  <template>
    <div>
      <h3>{{info}}</h3>
      <!-- counter是由父组件传过来 -->
      <h4>总人数:{{counter}}, 教室门牌号:{{classNum}}</h4>
  
    </div>
  </template>
  
  <script>
  export default {
    name: 'MyBody',
    data () {
      return {
        info : '杭州Java2103班',
        //counter : 10
      }
    },
    //用来接收其他组件传递过来的参数
    props : {
      counter:{
        type:Number,  //变量类型
        default : 10  //默认值
      },
      classNum :{
        type:String,
        required : true,
        default: "3101"
      }
    }
  }
  </script>
  
  <style scoped>
  
  </style>
  
  ```

  - 父组件

  ```vue
  <!-- 页面要显示的内容 -->
  <template>
    <div id="app">
      <!--<img src="./assets/logo.png">
      <HelloWorld/>-->
  
      <MyHeader></MyHeader>
      <!-- :counter  是指子组件中一个vue的变量 -->
      <MyBody :counter="mycounter" :class-num="'3102'"></MyBody>
      <MyFooter></MyFooter>
  
    </div>
  </template>
  
  <!-- js脚本 -->
  <script>
  //import HelloWorld from './components/HelloWorld'
  import MyHeader from './components/MyHeader'
  import MyBody from './components/MyBody'
  import MyFooter from './components/MyFooter'
  
  export default {
    name: 'App',
    data(){
      return {
        mycounter : 100
      }
    },
    components: {
      //HelloWorld
      MyHeader,
      MyBody,
      MyFooter
    }
  }
  </script>
  
  <!-- css样式 -->
  <style>
  /*#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }*/
  </style>
  
  ```

### 3.2 子父互传

| 子父互传                                                     |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802105918962.png" alt="image-20210802105918962" style="zoom:80%;" /> |

| 简化版                                                       |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802110628033.png" alt="image-20210802110628033" style="zoom:80%;" /> |
| <img src="pic/image-20210802110725193.png" alt="image-20210802110725193" style="zoom:80%;" /> |



# 十一、Axios

## 1、概述

- vue用来发送ajax请求的一个组件，但是它并不是vue的东西

## 2、官网

- https://www.kancloud.cn/yunye/axios/234845

### 2.1 axios语法

- 完整语法

```json
vm.axios({
        method : '请求方式',
        url : '请求url',
        data : 请求数据【json格式】
      }).then(function (resp) {
        //成功的回调函数
        //resp : 服务器端的返回的数据封装体，真正的数据封装在resp.data属性中
		console.log(resp.data);
      }).catch(function(error){
      	//出现错误的回调函数
      	console.log(error);
      });
```

- post请求

```json
vm.axios.post('请求url', 请求数据【json格式】)
  .then(function (response) {
      //成功的回调函数
    console.log(response);
  }).catch(function (error) {
      //出现错误的回调函数
    console.log(error);
  });
```

- get请求

```json
vm.axios.post('请求url?参数名=参数值', 请求数据【json格式】)
  .then(function (response) {
      //成功的回调函数
    console.log(response);
  }).catch(function (error) {
      //出现错误的回调函数
    console.log(error);
  });
```

```json
vm.axios.post('请求url', {
	params : {
		参数名:参数值
	}
}).then(function (response) {
      //成功的回调函数
    console.log(response);
  }).catch(function (error) {
      //出现错误的回调函数
    console.log(error);
  });
```



## 3、实现注册功能

### 3.1 搭建后台项目

- 使用postman测试【实现注册功能】

### 3.2 搭建前台项目

- 安装axios

```
cnpm install --save axios vue-axios
```

- main.js中引入

```
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)
```



- App.vue

```vue
<!-- 页面要显示的内容 -->
<template>
  <div id="app">
    <form>
      用户名:<input type="text" v-model="user.userName" /><br/>
      密码:<input type="text" v-model="user.password"/><br/>
      真实姓名:<input type="text" v-model="user.name"/><br/>
      年龄:<input type="text" v-model="user.age"/><br/>
      邮箱:<input type="text" v-model="user.email"/><br/>
      <input type="button" @click="save()" value="注册"/>
    </form>
  </div>
</template>

<script>

export default {
  name: 'App',
  data(){
    return {
      user : {}
    }
  },
  methods: {
    save(){
      console.log(this.user);
      //把 this 赋值给vm，this在使用过程中会因为使用的地方不同，导致this所指不一样
      var vm = this;
      vm.axios({
        method : 'post',
        url : 'http://localhost:80/user/save',
        data : vm.user
      }).then(function (resp) {
        console.log(resp);
        var result = resp.data;

        if(result.success) {
          vm.user = {};
          alert(result.message);
          alert("去登录页");
        } else {
          alert(result.message);
        }

      });

    }
  }
}
</script>

<!-- css样式 -->
<style>
/*#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}*/
</style>

```

- App.vue【ES6】

```vue
<!-- 页面要显示的内容 -->
<template>
  <div id="app">
    <form>
      用户名:<input type="text" v-model="user.userName" /><br/>
      密码:<input type="text" v-model="user.password"/><br/>
      真实姓名:<input type="text" v-model="user.name"/><br/>
      年龄:<input type="text" v-model="user.age"/><br/>
      邮箱:<input type="text" v-model="user.email"/><br/>
      <input type="button" @click="save()" value="注册"/>
    </form>
  </div>
</template>

<script>

export default {
  name: 'App',

  data(){
    return {
      user : {}
    }
  },
  methods: {
    /*save(){
      console.log(this.user);
      //把 this 赋值给vm，this在使用过程中会因为使用的地方不同，导致this所指不一样
      var vm = this;
      vm.axios({
        method : 'post',
        url : 'http://localhost:80/user/save',
        data : vm.user
      }).then(function (resp) {
        console.log(resp);
        var result = resp.data;

        if(result.success) {
          vm.user = {};
          alert(result.message);
          alert("去登录页");
        } else {
          alert(result.message);
        }

      });

    },*/

    //es6 新语法
    //1. 箭头函数 ： this传递性
    //2. let : 局部变量
    //3. 常量 : const
    save(){
      console.log(this.user);
      this.axios({
        method : 'post',
        url : 'http://localhost:80/user/save',
        data : this.user

      }).then((resp) => {
        console.log(resp);
        let result = resp.data;
        if(result.success) {
          this.user = {
            userName : "1111"
          };
          alert(result.message);
          alert("去登录页");
        } else {
          alert(result.message);
        }

      });

    }

  }
}
</script>

<!-- css样式 -->
<style>
/*#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}*/
</style>

```



# 十二、路由

## 1、路由器

- 一种设备，具备路由功能

## 2、路由

- 让一台或者多台电脑分别选择一条可以上网的线路

## 3、vue中的路由

- 选择一条可以跳转的资源路径，到达目标组件
- 组件：router-view

## 4、快速入门

### 4.1 实现步骤

> 1. 创建vue项目，安装路由模块
> 2. 需要准备跳转的组件
> 3. 在路由表中注册路由信息【router/index.js】
> 4. 在main.js中使用路由
> 5. 定义路由出口

### 4.2 实现

| 需要准备跳转的组件                                           |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802150054749.png" alt="image-20210802150054749" style="zoom:80%;" /> |
| <img src="pic/image-20210802150118834.png" alt="image-20210802150118834" style="zoom:80%;" /> |

| 在路由表中注册路由信息【router/index.js】                    |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802150214683.png" alt="image-20210802150214683" style="zoom:80%;" /> |

| 在main.js中使用路由                                          |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802150313735.png" alt="image-20210802150313735" style="zoom:80%;" /> |

| 定义路由出口 <router-view></router-view>                     |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802150419563.png" alt="image-20210802150419563" style="zoom:80%;" /> |



## 5、通过router-link进行路由跳转

| router-link                                                  |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802153048163.png" alt="image-20210802153048163" style="zoom:80%;" /> |

## 6、路由传参

| 路由表路由信息注册时，指定参数名【router/index.js】          |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802153620201.png" alt="image-20210802153620201" style="zoom:80%;" /> |

| 实际传参【App.vue】                                          |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802153653253.png" alt="image-20210802153653253" style="zoom:80%;" /> |

| 在指定的组件获取值                                           |
| ------------------------------------------------------------ |
| <img src="pic/image-20210802153757262.png" alt="image-20210802153757262" style="zoom:80%;" /> |



# 十三、ElementUI

## 1、概述

- 是一个饿了么开发的UI库

- https://element.eleme.io/#/zh-CN

## 2、项目中使用

### 2.1 安装

```vue
npm i element-ui -S
```

### 2.2 使用

- main.js中引入

```vue
//导入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

//使用ElementUI
Vue.use(ElementUI);
```

### 2.3 登录案例

- Login.vue

```vue
<template>
    <div id="formDiv">
      <el-form :model="user" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login()">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
          return {
            user: {
              userName: '',
              password: ''
            }
          }
        },
        methods: {
          login() {
            let url = 'http://localhost:80/user/login';
            this.axios.post(url, this.user).then(resp => {
              var result = resp.data;
              if(result.success) {
                this.$message({
                  message: result.message,
                  type: 'success'
                });
                this.user = result.data;
                setTimeout(() => {
                  //跳转到首页
                  this.$router.push({path:'/home'})
                }, 500);
              } else {
                if(result.data == null) {
                  this.$message.error(result.message);
                } else {
                  this.$message.error(result.message + ":" + result.data);
                }

              }

            }).catch(error => {
              alert(error);
            });
          }
        }
    }
</script>

<style scoped>

  #formDiv{
    padding-left: 400px;
    width: 500px;
  }

</style>
```

- 后台
  - 自己实现



## 3、mock假数据

### 3.1 官网

- https://www.fastmock.site/



### 3.2 新增接口地址

| 新增接口地址                                                 |
| ------------------------------------------------------------ |
| <img src="pic/image-20210803091845852.png" alt="image-20210803091845852" style="zoom:80%;" /> |
| <img src="pic/image-20210803091818271.png" alt="image-20210803091818271" style="zoom:80%;" /> |
| <img src="pic/image-20210803091750122.png" alt="image-20210803091750122" style="zoom:80%;" /> |
| <img src="pic/image-20210803091939818.png" alt="image-20210803091939818" style="zoom:80%;" /> |



### 3.3 测试

- 可以通过mock的url来进行测试，不需要后台



## 4、显示用户名

- 完成登录案例的补充

| 实现                                                         |
| ------------------------------------------------------------ |
| <img src="pic/image-20210803093946264.png" alt="image-20210803093946264" style="zoom:80%;" /> |
| <img src="pic/image-20210803094021358.png" alt="image-20210803094021358" style="zoom:80%;" /> |



# 十四、解决前台保存登录用户的数据

## 1、前面实现的问题

- 登录成功后，把登录信息通过路由参数传递到目标页面，可以在页面显示用户名，但是一旦从此路由path过去，则登录信息就丢失了

## 2、如何解决

### 2.1 分析

- 后台可以通过session来保存用户信息，但是session是服务器的技术，在前台用不了
- 前台有自己模拟的session，叫SessionStorage。这块区域可以被共享

| F12，在浏览上查看SessionStorage                              |
| ------------------------------------------------------------ |
| <img src="pic/image-20210803094521710.png" alt="image-20210803094521710" style="zoom:80%;" /> |

| 原理                                                         |
| ------------------------------------------------------------ |
| <img src="pic/image-20210803095047354.png" alt="image-20210803095047354" style="zoom:80%;" /> |



### 2.2 解决

#### 2.2.1 权限控制

> 只有登录后才能访问目标资源

- Login.vue

  | 登录成功后，需要把登录标识存储到 SessionStorage              |
  | ------------------------------------------------------------ |
  | <img src="pic/image-20210803101036862.png" alt="image-20210803101036862" style="zoom:80%;" /> |

- main.js

| 加入路由过滤器                                               |
| ------------------------------------------------------------ |
| <img src="pic/image-20210803101142197.png" alt="image-20210803101142197" style="zoom:80%;" /> |

```javascript
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
//导入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import App from './App';
//导入路由组件
import router from './router';

import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.config.productionTip = false;

//使用ElementUI
Vue.use(ElementUI);

//使用Axios
Vue.use(VueAxios, axios)


//====================路由过滤器============开始================

// 在跳转前执行
//beforeEach : 路由过滤器，类似于后台的过滤器，在每次路由前都会进行拦截
router.beforeEach((to, from, next) => {
  //to : 目标资源【要去的资源】
  //from : 源资源【从哪来的资源】
  //next : 下一个路由过滤器
  // 获取用户登录标识
  let isLogin = sessionStorage.getItem('isLogin');
  // 注销
  if (to.path == '/logout') {
    // 清空sessionStorage
    sessionStorage.clear();
    // 跳转到登录
    next({path: '/login'});
  }
  // 如果请求的是登录页，判断是否登录过：如果是，直接跳转到首页
  else if (to.path == '/login') {
    if (isLogin != null) {
      // 跳转到首页
      next({path: '/home'});
    }
  }
  // 如果为非登录状态
  else if (isLogin == null) {
    // 跳转到登录页
    next({path: '/login'});
  }
  // 下一个路由
  next();
});

//====================路由过滤器============结束================


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,  //使用路由
  //router : router,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})

```

#### 2.2.2 通过Vuex来实现状态管理

> Vuex，是vue中一个状态管理模块。配置SessionStorage可以来实现用户名的显示

- 安装

```
npm install vuex --save
或者
cnpm install vuex --save
```

- 配置 store/index.js
  - 在src下新建 store/index.js

```javascript
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);

// 全局 state 对象，用于保存所有组件的公共数据
const state = {
  // 定义一个 user 对象
  // 在vue组件中是通过 this.$store.state.user 来获取
  user: {
    id:0,
    userName: ''
  }
};

//类比Java类中属性的get方法
// 实时监听 state 值的最新状态，注意这里的 getters 可以理解为计算属性
const getters = {
  // 在组件中是通过 this.$store.getters.getUser 来获取
  getUser(state) {
    return state.user;
  }
};

//类比Java类中属性的set方法
// 定义改变 state 初始值的方法，这里是唯一可以改变 state 的地方，缺点是只能同步执行
const mutations = {
  // 在组件中是通过 this.$store.commit('updateUser', user); 方法来调用 mutations
  updateUser(state, user) {
    state.user = user;
  }
};

//调用actions时，会异步执行mutations中的函数
// 定义触发 mutations 里函数的方法，可以异步执行 mutations 里的函数
const actions = {
  // 在组件中是通过 this.$store.dispatch('asyncUpdateUser', user); 来调用 actions
  asyncUpdateUser(context, user) {
    context.commit('updateUser', user);
  }
};

export default new Vuex.Store({
  state,
  getters,
  mutations,
  actions
});

```

> 注意 这里设置了state——公共空间，在该空间中存放了一个user对象，这个对象可以被任何人操作。
>
> 操作的方式有两种：
>
> - getter： this.$store.getters.getUser 获取user对象
> - setter：this.$store.dispatch('asyncUpdateUser', user); 来设置user对象

- 在main.js中使用上store模块

```javascript
import store from './store'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,  //使用路由
  //router : router,
  store,  //使用store
  components: { App },
  template: '<App/>',
  render: h => h(App)
})

```

- home.vue

| home.vue                                                     |
| ------------------------------------------------------------ |
| <img src="pic/image-20210803110412816.png" alt="image-20210803110412816" style="zoom:80%;" /> |

```javascript
<template>
    <div>
      <h1>首页</h1>

      <p>
        <router-link to="/logout">退出</router-link>
      </p>

      {{user.loginName}}

    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
          return {
            user : {
              loginName : ""
            }
          }
        },
        //当页面加载数据时会自动执行
        mounted() {
          this.user.loginName = this.$store.getters.getUser.name  //获取路由参数
        }

    }
</script>

<style scoped>

</style>
```

#### 2.3 解决页面刷新后，用户数据丢失的问题

- App.vue

| App.vue                                                      |
| ------------------------------------------------------------ |
| <img src="pic/image-20210803111313873.png" alt="image-20210803111313873" style="zoom:80%;" /> |

```vue
<script>
export default {
  name: 'App',
  //组件被挂载时，会自动调用
  mounted() {
    //绑定事件
    window.addEventListener('unload', this.saveState);
  },
  methods: {
    saveState() {
      //向sessionStorage中设置一个state标识，值是从vuex空间中拿到的state
      sessionStorage.setItem('state', JSON.stringify(this.$store.state));
    }
  }
}
</script>
```

- 修改store/index.js

| store/index.js                                               |
| ------------------------------------------------------------ |
| <img src="pic/image-20210803111429316.png" alt="image-20210803111429316" style="zoom:80%;" /> |

```javascript
// 全局 state 对象，用于保存所有组件的公共数据
const state = sessionStorage.getItem('state') ? JSON.parse(sessionStorage.getItem('state')) : {
  // 定义一个 user 对象
  // 在vue组件中是通过 this.$store.state.user 来获取
  user: {
    id:0,
    userName: '',
    name : ''
  }
};
```













