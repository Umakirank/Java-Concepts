# Interview Q&A Cheat Sheet

## Core Java
- **JDK vs JRE vs JVM?** JVM runs bytecode; JRE = JVM + libraries; JDK = JRE + compiler/tools.
- **== vs equals()?** `==` compares references (or primitive values); `equals()` compares content when overridden.
- **Why is String immutable?** Pool sharing, security, thread safety, cached hashCode.
- **String vs StringBuilder vs StringBuffer?** Immutable / mutable fast / mutable synchronized.
- **Overloading vs overriding?** Same class, different params, compile time / subclass redefines parent method, runtime.
- **Abstract class vs interface?** Abstract: state, constructors, single inheritance. Interface: contract, multiple implementation, default/static methods.
- **Four pillars?** Encapsulation, Inheritance, Polymorphism, Abstraction.
- **Is Java pass by value or reference?** Always by value (for objects the value is the reference).
- **final vs finally vs finalize?** Constant/no-override/no-extend; always-run block; GC hook (deprecated).
- **Checked vs unchecked exceptions?** Checked are compile-time enforced (IOException); unchecked extend RuntimeException.
- **equals/hashCode contract?** Equal objects must have equal hashCodes; override both.
- **Can we override static or private methods?** No (static is hidden, private is not visible).
- **What is the Integer cache?** Integers -128 to 127 are cached, so `==` works there only.
- **Serializable and transient?** Marker interface for serialization; transient fields are skipped.
- **Shallow vs deep copy?** Copy references vs copy referenced objects too.

## Collections
- **ArrayList vs LinkedList?** Array: fast random access. Linked: fast insert/delete at ends/known node.
- **HashMap internals?** Array of buckets, hash -> index, chaining, treeify at 8, resize at 0.75 load, capacity doubles.
- **HashMap vs Hashtable vs ConcurrentHashMap?** Not thread-safe + nulls / synchronized, no nulls / fine-grained concurrency, no nulls.
- **HashSet internals?** Backed by HashMap (element = key).
- **Comparable vs Comparator?** Natural order inside the class vs external custom order.
- **Fail-fast vs fail-safe?** Throws ConcurrentModificationException vs iterates a snapshot/weakly consistent view.
- **TreeMap complexity?** O(log n), sorted by key (Red-Black tree).
- **How to make a collection read-only?** `List.of`, `Collections.unmodifiableList`.

## Multithreading
- **Thread vs Runnable?** Prefer Runnable (keeps inheritance free, separates task from thread).
- **start() vs run()?** start creates a new thread; run executes in the caller thread.
- **synchronized vs ReentrantLock?** Keyword, automatic release vs explicit, tryLock, fairness, conditions.
- **volatile?** Visibility + ordering, no atomicity.
- **wait vs sleep?** wait releases the monitor and needs synchronized; sleep does not release locks.
- **Deadlock conditions and prevention?** Mutual exclusion, hold-and-wait, no preemption, circular wait; prevent via lock ordering/timeouts.
- **Thread pool benefits?** Reuse threads, bound concurrency, lower overhead.
- **Callable vs Runnable?** Callable returns value and throws checked exceptions.
- **CompletableFuture vs Future?** Non-blocking composition vs blocking get().
- **Virtual threads (21)?** Lightweight JVM-managed threads for high-concurrency blocking I/O.

## Java 8+ 
- **Functional interface?** Exactly one abstract method.
- **map vs flatMap?** One-to-one vs one-to-many flattened.
- **Intermediate vs terminal?** Lazy builders vs triggers execution; stream is single-use.
- **Optional best practice?** Return type only; avoid in fields/parameters; prefer orElseGet for costly defaults.
- **Records / sealed / pattern matching?** Immutable data carriers / closed hierarchies / type tests with binding.

## JVM
- **Heap vs stack?** Objects shared vs per-thread frames.
- **Young/Old generation?** New objects in Eden, survivors move to Old; minor vs major GC.
- **Memory leak in Java?** Objects still referenced but unused (static collections, listeners, unclosed resources).
- **Class loading?** Bootstrap -> Platform -> Application loaders; parent delegation.
- **Default GC?** G1 (since Java 9).

## Spring / Spring Boot
- **IoC and DI?** Container controls object creation and injects dependencies; reduces coupling and eases testing.
- **BeanFactory vs ApplicationContext?** ApplicationContext adds events, i18n, AOP, eager singleton creation.
- **Types of DI? Best?** Constructor, setter, field. Constructor is best (immutability, testability).
- **@Component vs @Service vs @Repository vs @Controller?** All beans; differ in role, @Repository translates persistence exceptions.
- **@Component vs @Bean?** Class-level auto-scan vs method-level explicit in @Configuration.
- **@Autowired resolution?** By type, then @Qualifier/@Primary, then name.
- **Bean scopes?** singleton (default), prototype, request, session, application.
- **Singleton thread-safety?** Spring does not make it thread-safe; keep beans stateless.
- **Bean lifecycle?** Instantiate -> inject -> Aware -> @PostConstruct -> ready -> @PreDestroy.
- **Circular dependency?** A needs B and B needs A; fails with constructor injection (Boot 2.6+ forbids by default); fix design, @Lazy, or setter.
- **What does @SpringBootApplication do?** @Configuration + @EnableAutoConfiguration + @ComponentScan.
- **How does auto-configuration work?** Conditional configs (`@ConditionalOnClass`, `@ConditionalOnMissingBean`) listed in `AutoConfiguration.imports`.
- **Starters?** Curated dependency bundles (spring-boot-starter-web, -data-jpa...).
- **application.properties vs yml; profiles?** Same config, different format; profiles switch config per environment.
- **@RestController vs @Controller?** RestController adds @ResponseBody.
- **@RequestParam vs @PathVariable vs @RequestBody?** Query param / URL segment / JSON body.
- **PUT vs PATCH vs POST?** Replace (idempotent) / partial update / create.
- **DispatcherServlet flow?** Request -> HandlerMapping -> Controller -> ViewResolver/Message converter -> response.
- **Global exception handling?** `@RestControllerAdvice` + `@ExceptionHandler`.
- **@Transactional details?** AOP proxy, rollback on runtime exceptions by default, self-invocation bypass, propagation and isolation levels.
- **AOP terms?** Aspect, advice, pointcut, join point, weaving (proxy at runtime).
- **JDK proxy vs CGLIB?** Interface-based vs subclass-based.
- **Spring Data JPA?** Interface-generated repositories, derived queries, @Query, paging.
- **Lazy vs eager, N+1?** Load on demand vs immediately; fix N+1 with JOIN FETCH/@EntityGraph.
- **save() vs saveAndFlush()?** Persist (flush at commit) vs flush immediately.
- **JPA entity lifecycle?** Transient, managed, detached, removed.
- **First-level vs second-level cache?** Per Session/EntityManager (always on) vs shared across sessions (optional).
- **Validation?** `@Valid` + jakarta constraints -> MethodArgumentNotValidException.
- **Actuator?** Production endpoints: health, metrics, info.
- **Spring Security basics?** Authentication (who), authorization (what), filter chain, JWT/OAuth2, password encoding with BCrypt.
- **@Async, @Scheduled, @Cacheable?** Background execution, cron/fixed rate jobs, method result caching (all proxy-based; need @Enable...).
- **Testing?** @SpringBootTest, @WebMvcTest, @DataJpaTest, Mockito (@Mock/@MockBean), MockMvc.

## Microservices & Architecture (awareness)
- **Monolith vs microservices?** One deployable vs independently deployable services (scale, resilience, complexity).
- **Key components?** API gateway, service discovery, config server, circuit breaker (Resilience4j), distributed tracing, messaging (Kafka/RabbitMQ).
- **REST principles?** Stateless, resource URLs, proper verbs/status codes, HATEOAS (optional).
- **CAP theorem?** Under partition choose consistency or availability.
- **Idempotency, pagination, versioning** are common REST design follow-ups.

## SQL basics often asked with Java
- Joins (inner/left/right), GROUP BY/HAVING, indexes, 2nd highest salary, normalization, ACID, isolation levels.
