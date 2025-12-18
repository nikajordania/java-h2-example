# Java Lecture 12 Resources

This short guide organizes key links and small examples to help you with Java JDBC, generating fake data, and reducing boilerplate with Lombok.

---

## 1) JDBC: Mapping a ResultSet to Java objects

**Why it matters**
- When you query a database with JDBC you receive a ResultSet. Mapping rows to Java objects (POJOs) makes the rest of your code easier to read and test.

**Quick concepts**
- Use `ResultSet#getX` methods (`getString`, `getInt`, `getLong`) to read column values.
- Prefer column names `rs.getString("name")` over indexes for readability and maintainability.
- Extract mapping logic into a method or a DAO to avoid duplicating mapping code.

**Example (conceptual)**


```java
// single row ResultSet to Person mapping example
Person person = null;
try (PreparedStatement ps = conn.prepareStatement("SELECT id, name, age FROM person WHERE id = ?")) {
    // Set ? parameter
    ps.setInt(1, 10);
    
    ResultSet rs = ps.executeQuery();
    
    // if there's a result, map it
    if (rs.next()) {
        person = new Person();
        person.setId(rs.getLong("id"));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));
    }
}


// ResultSet to List<Person> mapping example
List<Person> result = new ArrayList<>();
try (PreparedStatement ps = conn.prepareStatement("SELECT id, name, age FROM person lIMIT 10");
     ResultSet rs = ps.executeQuery()) {
    while (rs.next()) {
        Person p = new Person();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setAge(rs.getInt("age"));
        result.add(p);
    }
}
```

**Good links (for deeper reading)**
- [Coding Nomads: Java 301 - Map ResultSet to Java Objects](https://codingnomads.com/java-301-map-resultset-to-java-objects)
- [Baeldung: JDBC ResultSet](https://www.baeldung.com/jdbc-resultset)

---

## 2) Statement vs PreparedStatement

**Why it matters**
- Use `PreparedStatement` when you have parameters — it helps avoid SQL injection and can improve performance via statement caching.

**Quick comparison**
- **Statement**: simple, for static SQL; risk of SQL injection if you concatenate user input.
- **PreparedStatement**: parameterized queries, safer and often faster.

Example of using Statement (not recommended with user input):

```java
Statement stmt = conn.createStatement();
String sql = "SELECT * FROM person WHERE name = '" + userInput + "'";
ResultSet rs = stmt.executeQuery(sql);
```

Example of using PreparedStatement (recommended):

```java
PreparedStatement ps = conn.prepareStatement("SELECT * FROM person WHERE name = ?");
// 1 = first ? parameter
ps.setString(1, userInput);
ResultSet rs = ps.executeQuery();
```

**Read more**
- [Baeldung: Java Statement vs PreparedStatement](https://www.baeldung.com/java-statement-preparedstatement)

---

## 3) Java Faker — generate fake test data quickly

**Why it matters**
- When testing or seeding a demo database, writing realistic fake data saves time and makes demos clearer.

**Maven dependency**

```xml
<dependency>
  <groupId>com.github.javafaker</groupId>
  <artifactId>javafaker</artifactId>
  <version>1.0.2</version> <!-- check the repo for latest -->
</dependency>
```

**Quick usage**

```java
Faker faker = new Faker();
String name = faker.name().fullName();
String email = faker.internet().emailAddress();
int age = faker.number().numberBetween(18, 80);

Person person = new Person();
person.setName(name);
person.setEmail(email);
person.setAge(age);
```

**Links**
- Maven: [Java Faker on MVN Repository](https://mvnrepository.com/artifact/com.github.javafaker/javafaker)
- Usage guide & examples: [Java Faker GitHub](https://github.com/DiUS/java-faker)

---

## 4) Lombok — reduce boilerplate in Java classes

**Why it matters**
- Lombok generates getters/setters, constructors, toString, equals/hashCode, and more at compile time so you type less and focus on logic.

**IDE setup**
- To use Lombok in IntelliJ IDEA, install the Lombok plugin and enable annotation processing.
  - Plugin: [Lombok Plugin for IntelliJ](https://plugins.jetbrains.com/plugin/6317-lombok)
  - Lombok setup: [Project Lombok Setup](https://projectlombok.org/setup/maven)

**Common annotations and quick examples**
- `@Getter` / `@Setter` — generates getters and setters
- `@ToString` — generates a toString implementation
- `@AllArgsConstructor`, `@NoArgsConstructor`, `@RequiredArgsConstructor` — generate constructors

**Maven dependency**

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.28</version> <!-- check for latest -->
  <scope>provided</scope>
</dependency>
```

**Docs and examples**
- [Lombok homepage](https://projectlombok.org/)
- [Lombok maven setup](https://projectlombok.org/setup/maven)

---

## 5) practical exercises

- Create table `students` (id, name, email, age) in your database.
- Read the ResultSet mapping links and implement `StudentDataAccessObject` mapping in your project.
- Replace any string-concatenated SQL in examples with `PreparedStatement`.
- Add Java Faker to a small seeder class and generate 5 sample Student rows.
- Try converting a simple POJO to Lombok annotations and compile to see generated methods.
