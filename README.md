# Warehouse-Backend

## Design Decisions for Backend
- This table below shows the comparison between some technologies that can be used for the backend:
  |  | .NET Core MVC | Spring MVC | Spring Webflux |
  | ------ | ------ | ------ | ------ |
  | Release Date (Maturity) | 2009 [[4][4]] | 2005 [[5][5]] | 2017 [[6][6]] |
  | Complexity | Normal | Normal | High. Different debugging experience [[7][7]] |
  | Performance | Good (extensive use of asynchronous) [[1][1]] | Normal (Servlet / Blocking) [[1][1]][[2][2]] | Good (Reactive / Non-blocking) [[2][2]] |
- The product quantity is calculated in the backend because if we fetch the data to the frontend and calculate it, then it can slow the browser performance when the product or article data is big.


## Assumptions
There are some assumptions related to the assignment description:
- Since the product in inventory.json does not contain price, the product price is set from the warehouse software.
- The product price has only one currency.
- The product price is not defined based on region.
- The product list does not have prioritization when showing the possible quantity.
- Each product shows its quantity without considering the other product.
- Primary key and Stock columns would be better to be set as Long/BigInt so that it can cover more rows.
- Multiple products can have the same names.
- When user sells the product, the system does not delete the product from database. The system only updates the available stock of the article.

## Compromises
- No DeletionDate in the table. **Future implementation**: set DeletionDate column in every tables, set store procedure that update the DeletionDate whenever DELETE query is executed. This is the best practice since it can cover up the data when things go messed up with our data.
- No spring hateoas since it is not mandatory.
- Upload Article stops when there is a value is duplicate. **Future implementation**: implement Upsert
- Upload Product ignores article that does not exists. **Future implementation**: implement Upsert
- No variable/entity validation in the backend and database layers due to the limited development time. Currently, only the UI layer that has the validation.
- No Pagination on the REST API.
- No chained transaction covering in the backend.
- No jwt token expiration date validation. **Future implementation**: implement token expiration date.
- The unit tests only cover the "happy" scenario due to limited time. The main goal is to show that the units are testable to fulfill one of the non-functional requirements, which is testability.
- No user manual for the API

## Testing Result
- Tested the backend in OS Windows 10

[1]: https://www.techempower.com/benchmarks/#section=data-r17&hw=ph&test=fortune
[2]: https://www.programmersought.com/article/76251137603/
[3]: https://techradar.ingka.com/
[4]: https://www.tutorialsteacher.com/mvc/asp.net-mvc-version-history
[5]: https://mvnrepository.com/artifact/org.springframework/spring-webmvc
[6]: https://mvnrepository.com/artifact/org.springframework/spring-webflux
[7]: https://itembase.com/resources/blog/tech/spring-boot-2-spring-webflux
