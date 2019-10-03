## 관리자 REST-API

 #### SKILL
 - Spring boot
 - JPA
 - MYSQL

 #### Tool
 - Intelli J
 - Restlet Client

#### Function
 - 관리자
    - 고객관리, 상품관리, 파트너사 관리, 주문관리, 카테고리 관리, 어드민 사용자관리

 - 고객센터
    - 고객관리, 주문관리
 #### Directory structure
 
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
    - network
        - request 
            - Entity의 request
        - response
            - Entity의 response
        - Header.class (Restlet Client 테스트)
    - enumd
        - 각Entity enum설정    


~~~

    응답코드
    private String resultCode;

    부가설명
    private String description;

    private T data;
    
    프론트  status 확인
    public static <T> Header<T> OK(){
        return (Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build());
    }
    데이터 부분 응답 확인
    public static <T> Header<T> OK(T data){
        return (Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build());
    }
    에러
    public static <T> Header<T> ERROR(String description){
        return (Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build());
    }
~~~

 - Entity
    - Lombok 어노테이션, JPA Auditing, Builder, Accessors사용
    
~~~
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) //by 감시
@Builder
@Accessors(chain = true)
~~~


 - repository
    - 각Entity JpaRepository 모음
 - service
    - Service 구현

#### DB TABLE 연관관게
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





