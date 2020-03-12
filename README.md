# TSVD for Java - Instrumentation

## This branch includes work being done to implement instrumentation agents to facilitate delay injections.

Thread-Safety Violation Detector is an <i>delay-injection</i> approach which uses dynamic analysis and lightweight instrumentation to detect data race bugs, also referred to as Thread safety violations in Java applications.

<b>Definitions: </b>

<b>Data Race: </b> A data race occurs when two threads concurrently access the same variable and at least one of these accesses is a write.

<b>Thread Safety Contract: </b> Each class or library specifies, sometimes implicitly, a thread-safety contract that determines the set of functions that can be called concurrently by threads in the program.

<b>Thread Safety Violation: </b> A thread-safety violation occurs when the client fails to meet the thread-safety contract of a class or library



[Trello](https://trello.com/b/UcUVXC8C/ecs251-synapse)

<b>Source code structure and how your code maps to the concepts in your project: </b>

java-profiler-app is the directory containing code with thread safety violation. 

ListObject contains the actual data and timestamps that are needed to observe how different threads affect each other.

java-profiler-app/src/com/company/datarace/objects/ConcurrentObjects.java contains a global variable arraylist1, an array of ListObject.

java-profiler-app/src/com/company/datarace/TSVThread.java has a run() method that adds a ListObject to arraylist1 and increments a global variable threadcounter.
