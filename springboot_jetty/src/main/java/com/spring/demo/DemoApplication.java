package com.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Jetty和Tomcat性能方面对比：
  Jetty可以同时处理大量连接而且可以长时间保持连接，适合于web聊天应用等等。
  Jetty的架构简单，因此作为服务器，Jetty可以按需加载组件，减少不需要的组件，减少了服务器内存开销，从而提高服务器性能。
  Jetty默认采用NIO（非阻塞IO）结束在处理I/O请求上更占优势，在处理静态资源时，性能较高。
  Tomcat适合处理少数非常繁忙的链接，也就是说链接生命周期短的话，Tomcat的总体性能更高。
  另外，Tomcat默认采用BIO（阻塞IO）处理I/O请求，在处理静态资源时，性能较差。*/

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}

