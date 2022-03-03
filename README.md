# springboot-invoice
1. JBoss EAP 7.4에 설치하기 위해 다음 작업 수행.  
   1.1 Main Class 재정의
  (참고: https://godekdls.github.io/Spring%20Boot/howto.traditional-deployment/)
  배포 가능한 war파일을 생성하는 첫 번째 단계는 SpringBootServletInitializer 하위 클래스를 만들고,  
  configure() method를 재정의한다. 이렇게 하면 Spring framework의 servlet 3.0지원을 통해  
  servlet container로 기동시킬 때 필요한 application 설정들을 넣어 줄 수 있다.
  아래와 같이 Application의 main class가 SpringBootServletInitializer를 상속하도록 해야 한다.  
  ```  
  @SpringBootApplication
  public class MyApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

  }
  ```  

  1.2 pom.xml 수정 (logback관련 춛돌 dependency 제거)  
   pom.xml을 수정하지 않고 jboss-deployment-structure.xml을 사용해서 JBoss에 제공하는 module를 사용하지 않고  
   사용자 module을 사용해도 됨.  
   ```  
   <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<!-- for deploy JBoss EAP 7 -->
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-logging</artifactId>
				</exclusion>
			</exclusions>
			<!-- for deploy JBoss EAP 7 -->
		</dependency>
    ```  
  
  1.3 context path 수정
  - application.properties 수정  
  server.servlet.context-path=/* 추가  
  - jboss-web.xml에 context-path 추가  





























