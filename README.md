# How to test


```javascript 
> 1. Start EurekaServer springboot project.
> 2. Start ZuulProxy springboot project.
> 3. Start couponservice project.
> 4. run java -jar /springcloud/zipkin-server/zipkin-server-1.30.3-exec.jar

> 5. goto http://localhost:8080/api/coupon/create 

a sample request like below using postman

{
    "id":1,
    "code":"DIEHARD",
    "discount":50.0,
    "expDate":"10-05-2021"
} 

> 6. goto http://localhost:9411 
the coupon-service will be visible in the dropdown and we can search for the logs.


NOTE: Install mysql db as a dependency and run below script before runnin coupon-service app.

create table coupon (id INT NOT NULL AUTO_INCREMENT, code varchar(100), discount FLOAT(5), exp_date varchar(20), primary key (id));
