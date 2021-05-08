package com.just.ytdemo.util.springTest;

import com.just.ytdemo.BeanTest.Car;
import com.just.ytdemo.BeanTest.ChinaPersonCondition;
import com.just.ytdemo.BeanTest.Person;
import org.springframework.context.annotation.*;


//@ComponentScan("com.just.ytdemo.BeanTest")
//@Configuration
public class SpringBeanTest {


    /**
     * spring  bean  生命周期
     * bean创建   ---- 初始化   ----  销毁的过程
     * 容器管理bean的生命周期
     * 我们可以自定义初始化和销毁的方法 ，
     * 容器会去进行的生命周期的时候调用我们自定义的初始化和销毁的方法
     *
     * 1.xml方式的  bean的  init-method 以及  destory-method
     * 2. @Bean 通过  initMethod  与  destoryMethod
     * 3. 让Bean实现  initializingBean    与  DisposableBean
     * 4. 可以使用JSR250定义的2个注解   @PostConstruct   @PreDestory
     * 5.  实现 BeanPostProcessor   后置处理器
     *
     *
     *
     *
     * spring底层对BeanPostProcessor的使用
     *   ApplicationContextAwareProcessor   接口     可以直接拿到applicationContext对象
     *   bean赋值  注入其他组件  生命周期注解功能  @Async @Autowried  等
     *
     * @see com.just.ytdemo.BeanTest.Person
     *
     *
     */


    @Bean(name = "car" , initMethod = "initTest" ,destroyMethod = "destoryTest")
    public Car getCar(){
        return new Car();
    }





    @Bean
    public Person person1(){
        return new Person("习大大");
    }

    @Conditional({ChinaPersonCondition.class})
    @Bean
    public Person person2(){
        return new Person("奥巴马");
    }




    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringBeanTest.class);
        System.out.println("容器创建完成----");



        String[] beanNames =  context.getBeanNamesForType(Person.class);
        for (String name : beanNames) {
            System.out.println(name);
            Person person = (Person) context.getBean(name);
            System.out.println(person.getName());
        }

//        System.out.println(((Car)context.getBean("car")).getName());

//        ((Car)context.getBean("car")).init();


//        context.close();
    }


}
