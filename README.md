## 관리자 REST-API


#### Function
 - 관리자
    - 고객관리, 상품관리, 파트너사 관리, 주문관리, 카테고리 관리, 어드민 사용자관리

 - 고객센터
    - 고객관리, 주문관리
 #### Directory structure               
![pjt](/Users/macbook/Downloads/img/pjt.png)
 - component, config 
    - JPA Auditing 구현 
        - 생성자, 수정자 부문
 - controller, api
    - controller 부문
 - interfaces
    - controller, service에 사용하는 CRUD인터페이스
    
    
~~~
    Header<Res> create(Header<Req> request);
    
    Header<Res> read(Long id);
    
    Header<Res> update(Header<Req> request);
    
    Header delete(Long id);
~~~
    
    
 - model
    - Entity 부문
 - repository
    - 각Entity JpaRepository 모음
 - service

#### DB TABLE 
 - AdminUser
 - Category
    - Partner 1:N 
 - Item
    - OrderDetail 1:N 
 - OrderDetail
    - OrderGroup, Item N:1
 - OrderGroup
    - User N:1, OrderDetail 1:N
 - Partner
    - Item 1:N, Category N:1
 - User
    - OrderGroup 1:N



 #### SKILL
 - Spring boot
 - JPA
 - MYSQL

 #### Tool
 - Intelli J
 - Restlet Client

프로젝트 진행중.