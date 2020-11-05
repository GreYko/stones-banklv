# BankLv REST caching money converter API

SpringBoot application that consumes data from www.bank.lv and exposes some logics via REST API.
On startup downloads a configurable amount of FX rates historical data and caches it in-memory.

## Dependencies

 * JDK 11
 * Maven

OR

 * Docker && docker-compose
 
## Build

Option 1

 * Execute `./mvnw clean install` to build the application.
 * Execute `./mvnw spring-boot:run` to run.

Option 2
 * Execute `docker-compose up` to build and run in virtualized environment.

## Usage

As per request - single endpoint `/convert` is provided.

REST API  generated documentation is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

Required use case can be found in [test.http](./test.http) file, IntelliJ IDEA is capable of executing POST requests.

## What's next

- [ ] Make self-review sanity check round, fix possible wording issues.
- [ ] Improve unit test code coverage density.
- [ ] MoneyConversionService might worth splitting into two classes as it has now 2 responsibilities.
- [ ] Cover MoneyConversionService.getFxRate with a concurrency stress-test. Synchronization might decrease performance.
- [ ] RealFxRateProvider migration from httpRequest to spring-integration-ws
- [ ] Improve error handling.
- [ ] Improve logging.
- [ ] Request validation layer.
- [ ] Improve documentation.
- [ ] Prettify pom.xml, externalize versions into a properties if there are duplicates.
- [ ] Make DTO's immutable - fast initial attempt caused Json being unable to parse LocalDate.
- [ ] Kotlin migration.
- [ ] Maven -> Gradle migration.
- [ ] Parallelism for pre-loading cache (whole startup with 400 days pre-load and with additional console output took 38.4 seconds).
- [ ] Move samples xml to test resources.
- [ ] Setup CI/CD pipeline.
   - [ ] reject commits with bad formatting.
   - [ ] reject commits with lower test coverage than threshold.
   - [ ] reject commits on static code analyzer error rules.
- [ ] Consider making solution reactive.
