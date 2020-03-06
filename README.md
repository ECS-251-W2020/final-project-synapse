# TSVD for Java - Instrumentation

## This branch includes work being done to implement instrumentation agents to facilitate delay injections.

Thread-Safety Violation Detector is an <i>delay-injection</i> approach which uses dynamic analysis and lightweight instrumentation to detect data race bugs, also referred to as Thread safety violations in Java applications.

[Trello](https://trello.com/b/UcUVXC8C/ecs251-synapse)

java-profiler-app is the directory containing code with thread safety violation. 
profiler-app/src/com/company/datarace/objects/TSVDArrayList.java is an implementation that extends ArrayList. We overide add so that we can inject trap whenever add is called. 
