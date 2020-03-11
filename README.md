# TSVD for Java - Instrumentation

## This branch includes work being done to implement instrumentation agents to facilitate delay injections.

Thread-Safety Violation Detector is an <i>delay-injection</i> approach which uses dynamic analysis and lightweight instrumentation to detect data race bugs, also referred to as Thread safety violations in Java applications.

<b>Definitions: </b>

<b>Data Race: </b> A data race occurs when two threads concurrently access the same variable and at least one of these accesses is a write.

<b>Thread Safety Contract: </b> Each class or library specifies, sometimes implicitly, a thread-safety contract that determines the set of functions that can be called concurrently by threads in the program.

<b>Thread Safety Violation: </b> A thread-safety violation occurs when the client fails to meet the thread-safety contract of a class or library



[Trello](https://trello.com/b/UcUVXC8C/ecs251-synapse)

java-profiler-app is the directory containing code with thread safety violation. 

ListObject contains the actual data and timestamps that are needed to observe how different threads affect each other.
