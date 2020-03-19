# tsvd4j: Thread Safety Violation detection for Java

**Trello**: [link](https://trello.com/b/UcUVXC8C/ecs251-synapse)

**Slides**: [link](https://docs.google.com/presentation/d/1SkQxILhSvI8lSgbs36Hn3DgG-Ot8Ey2qLZS7KThloS0/edit?usp=sharing)

tsvd4j is a Thread-Safety Violation detection tool for Java-based applications and programs. It is a Java implementation of the original [Thread Safety Violation Detector (TSVD)](https://github.com/microsoft/tsvd) system by Microsoft, first described in Li et al's [SOSP'19 Best Paper](https://www.microsoft.com/en-us/research/uploads/prod/2019/09/sosp19-final193.pdf), titled "Efficient and Scalable Thread-Safety Violation Detection - Finding thousands of concurrency bugs during testing".

### What is a Thread-Safety Violation?

A thread-safety violation (TSV) occurs when an application concurrently calls into a class or library such that it violates its thread-safety contract. TSVs are mostly a generalisation of data races to classes and libraries - however, unlike data race detectors, TSVD (and tsvd4j) do not monitor every shared memory location; instead they monitor *TSVD points*, which are determined by the thread-safety contract.

A thread-safety contract (TSC) determines when and how can/cannot threads call into a class / library concurrently. In our implementation, the TSC is a set of functions from thread-unsafe classes that cannot be called concurrently, and *TSVD points* are the points in the application code where these functions are used. 

A TSV is said to occur *only* when its corresponding TSC is violated. For example: in Java, `java.util.HashMap.put` is not thread-safe during concurrent accesses, when atleast one of the operations is a write operation. Thus, the following is a thread-safety violation (even if key1 and key2 are different):

```
// HashMap hashMap;

hashMap.put(key1, value); // Thread 1
hashMap.containsKey(key2); // Thread 2
```

For our TSC implementation, we focused on the classes from `java.util` - most of which are thread-unsafe (except the classes extending `Vector` and `Dictionary`).

### How does TSVD work?

TSVD broadly works on an approach known as *active delay-injection*, wherein threads are delayed at certain points (the aforementioned TSVD points) in the program in order to cause conflicting accesses. This is done by instrumenting the application bytecode. TSVD also monitors the calling behaviors of the TSVD points by registering them in a global trap set, and using that to check for violations. 

Further details can be found in our [slides](https://docs.google.com/presentation/d/1SkQxILhSvI8lSgbs36Hn3DgG-Ot8Ey2qLZS7KThloS0/edit?usp=sharing).

## Applying TSVD

You can directly used the [package jar file](TSVD-Core/out/artifacts/TSVD_Core_jar/TSVD-Core.jar) with your application.

```
java -javaagent:<path-to-TSVD-Core> <your-application>
```

If you wish to build TSVD from source, clone this repo:

```
git clone https://www.github.com/ECS-251-W2020/final-project-synapse
```

Open the `TSVD-Core` directory in IntelliJ / Eclipse (or use the Maven command line tools), build the project (for IntelliJ use `Build > Build Artifacts`), and then use the generated `.jar` file in `out` with your application (as described above).

## Project Structure

The project has been divided into two modules:

1. **TSVD-Core**: this consists of both the instrumenter (located in the `instrument` folder) and the core algorithm (located in the `tsvd` folder). This also contains the thread-safety contract (`tsc.json`).

2. **java-profiler-app**: a custom data race application we built to stress-test TSVD on various concurrency scenarios (test classes are given in the `tests` sub-folder).

The following is a schematic of our project structure. **NB:** auto-generated files and folders (.idea, target etc) are not a part of this schema (except `TSVD-Core.jar`, which can be directly used to run tsvd4j on Java applications). 

A brief description of the folders is also given. Usage of individual files and functions is given as `javadoc` within the files; to know more about the code and functions in the files, please open them, our code is well-commented :)

```
├── README.md
├── TSVD-Core (instrumenter + core)
│   ├── conf
│   │   └── config.properties (config file)
│   ├── out
│   │   └── artifacts
│   │       └── TSVD_Core_jar
│   │           └── TSVD-Core.jar (packaged jar)
│   ├── pom.xml (dependency file)
│   ├── src
│      ├── lib
│      │   └── json-20190722.jar (used as dependency)
│      └── main
│          ├── java
│          │   └── com
│          │       ├── conf (setting config)
│          │       │   └── Configuration.java
│          │       ├── instrument (instrumenter code)
│          │       │   ├── MonitorTransformer.java
│          │       │   └── MyAgent.java
│          │       └── tsvd (core algorithm)
│          │           ├── ProxyClass.java
│          │           ├── ThreadSafetyContract.java
│          │           └── trap
│          │               ├── Trap.java
│          │               └── TrapHandler.java
│          └── resources
│              └── tsc.json (Thread-Safety Contract)
├── java-profiler-app
   ├── conf
   │   └── config.properties (config file)
   └── src
       └── com
           └── company
               ├── Global.java (global variables)
               ├── Main.java (driver class)
               ├── conf
               │   └── Configuration.java (parsing config)
               └── datarace
                   ├── InitDataRace.java (creates TSVs)
                   ├── InitDictTSV.java (creates TSV for HashMap)
                   ├── objects (objects to be inserted)
                   │   ├── ConcurrentObjects.java
                   │   └── ListObject.java
                   └── tests (TSVD tests - simulate TSVs)
                       ├── NormalThread.java
                       ├── TSVLockThread.java
                       ├── TSVThread.java
                       ├── addDict.java
                       └── getDict.java
```


## Contributing

Given that this started off as an ambitious course project completed in a couple of weeks, we understand many things can be improved. This project welcomes contributions and suggestions for improvement: please open an issue or drop a pull-request. To know more, please contact the project maintainers.
