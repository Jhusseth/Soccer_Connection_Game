var metricDefinitions=[];metricDefinitions["Software Quality Attributes"]={link:"SQA",shortname:"SQA",desc:["Software external quality displays the visible symptons when there are issues, but the roots are invisible internal quality attributes: program structure, complexity, coupling, testability, reusability, readability, maintainability. Coupling, Complexity, Cohesion and Size are the fundemental internal quality attributes of a software."]},metricDefinitions.Coupling={shortname:"",link:"Coupling",desc:["Coupling between two classes A and B if:","<ul>","<li>A has an attribute that refers to (is of type) B.</li>","<li>A calls on services of an object B.</li>","<li>A has a method that references B (via return type or parameter).</li>","<li>A has a local variable which type is class B.</li>","<li>A is a subclass of (or implements) class B.</li>","</ul>","Tightly coupled systems tend to exhibit the following characteristics:","<ul>","<li>A change in a class usually forces a ripple effect of changes in other classes.</li>","<li>Require more effort and/or time due to the increased dependency.</li>","<li>Might be harder to reuse a class because dependent classes must be included.</li>","</ul>"]},metricDefinitions["Lack of Cohesion"]={shortname:"",link:"LCohesion",desc:["Measure how well the methods of a class are related to each other. High cohesion (low lack of cohesion) tend to be preferable, because high cohesion is associated with several desirable traits of software including robustness, reliability, reusability, and understandability. In contrast, low cohesion is associated with undesirable traits such as being difficult to maintain, test, reuse, or even understand."]},metricDefinitions.Complexity={link:"Complexity",shortname:"",desc:["Implies being difficult to understand and describes the interactions between a number of entities. Higher levels of complexity in software increase the risk of unintentionally interfering with interactions and so increases the chance of introducing defects when making changes."]},metricDefinitions.Size={shortname:"",link:"Size",desc:["Size is one of the oldest and most common forms of software measurement. Measured by the number of lines or methods in the code. A very high count might indicate that a class or method is trying to do too much work and should be split up. It might also indicate that the class might be hard to maintain."]},metricDefinitions["Class Lines of Code"]={q_attr:"Size",link:"CLOC",shortname:"CLOC",desc:["The number of all nonempty, non-commented lines of the body of the class. CLOC is a measure of size and also indirectly related to the class complexity."]},metricDefinitions["Weighted Method Count"]={q_attr:"Complexity, Size",shortname:"WMC",link:"WMC",desc:["The weighted sum of all class’ methods an represents the McCabe complexity of a class. It is equal to number of methods, if the complexity is taken as 1 for each method. The number of methods and complexity can be used to predict development, maintaining and testing effort estimation. In inheritance if base class has high number of method, it affects its' child classes and all methods are represented in sub-clasess. If number of methods is high, that class possibly domain spesific. Thefore they are less reusable. Also these classes tend to more change and defect prone."]},metricDefinitions["Depth of Inheritance Tree"]={q_attr:"Complexity",shortname:"DIT",link:"DIT",desc:["The position of the class in the inheritance tree. Has 0 (zero) value for root and non-inherited classes.For the multiple inheritance, the metric shows the maximum length. Deeper class in the inheritance tree, probably inherit. Therefore, it is harder to predict its behavior. Also this class relatively complex to develop, test and maintain."]},metricDefinitions["Number of Children"]={q_attr:"Coupling",shortname:"NOC",link:"NOC",desc:["The number of direct subclasses of a class. The size of NOC approximately indicates how an application reuses itself. It is assumed that the more children a class has, the more responsibility there is on the maintainer of the class not to break the children's behaviour. As a result, it is harder to modify the class and requires more testing."]},metricDefinitions["Coupling Between Object Classes"]={q_attr:"Coupling",shortname:"CBO",link:"CBO",desc:["The number of classes that a class is coupled to. It is calculated by counting other classes whose attributes or methods are used by a class, plus those that use the attributes or methods of the given class. Inheritance relations are excluded. As a measure of coupling CBO metric is related with reusability and testability of the class. More coupling means that the code becomes more difficult to maintain because changes in other classes can also cause changes in that class. Therefore these classes are less reusable and need more testing effort."]},metricDefinitions["CBO Lib"]={shortname:"CBO Lib",link:"CBO_LIB",q_attr:"Coupling",desc:["The number of dependent library classes."]},metricDefinitions["CBO App"]={q_attr:"Coupling",shortname:"CBO App",link:"CBO_APP",desc:["The number of dependent classes in the application."]},metricDefinitions["Response For a Class"]={q_attr:"Complexity",shortname:"RFC",link:"RFC",desc:["The number of the methods that can be potentially invoked in response to a public message received by an object of a particular class. It includes the full call graph of any method called from the originating method.If the number of methods that can be invoked at a class is high, then the class is considered more complex and can be highly coupled to other classes. Therefore more test and maintain effort is required."]},metricDefinitions["Simple Response For a Class"]={q_attr:"Complexity",shortname:"SRFC",link:"SRFC",desc:["The number of the methods that can be potentially invoked in response to a public message received by an object of a particular class. It includes methods directly invoced from the class. If the number of methods that can be invoked at a class is high, then the class is considered more complex and can be highly coupled to other classes. Therefore more test and maintain effort is required."]},metricDefinitions["Lack of Cohesion of Methods"]={q_attr:"Cohesion",shortname:"LCOM",link:"LCOM",desc:["Measure how methods of a class are related to each other. Low cohesion means that the class implements more than one responsibility. A change request by either a bug or a new feature, on one of these responsibilities will result change of that class. Lack of cohesion also influences understandability and implies classes should probably be split into two or more subclasses. LCOM3 defined as follows LCOM3 = (m - sum(mA)/a) / (m-1) where : <br><li>m\tnumber of procedures (methods) in class</li><li>a\tnumber of variables (attributes) in class. a contains all variables whether shared (static) or not.</li><li>mA\tnumber of methods that access a variable (attribute) </li><li>sum(mA)\tsum of mA over attributes of a class</li><br><p>LCOM3 varies between 0 and 2. Values 1..2 are considered alarming. In a normal class whose methods access the class's own variables, LCOM3 varies between 0 (high cohesion) and 1 (no cohesion). When LCOM3=0, each method accesses all variables. This indicates the highest possible cohesion. LCOM3=1 indicates extreme lack of cohesion. In this case, the class should be split. <br>When there are variables that are not accessed by any of the class's methods, 1 < LCOM3 <= 2. This happens if the variables are dead or they are only accessed outside the class. Both cases represent a design flaw. The class is a candidate for rewriting as a module. Alternatively, the class variables should be encapsulated with accessor methods or properties. There may also be some dead variables to remove. If there are no more than one method in a class, LCOM3 is undefined. If there are no variables in a class, LCOM3 is undefined. An undefined LCOM3 is displayed as zero [http://www.aivosto.com/project/help/pm-oo-cohesion.html]"]},metricDefinitions["Lack of Cohesion Among Methods(1-CAM)"]={q_attr:"Cohesion",shortname:"LCAM",link:"LCAM",desc:["CAM metric is the measure of cohesion based on parameter types of methods. LCAM = 1-CAM"]},metricDefinitions["Number of Fields"]={q_attr:"Size",shortname:"NOF",link:"NOF",desc:["The number of fields (attributes) in a class"]},metricDefinitions["Number of Methods"]={q_attr:"Size",shortname:"NOM",link:"NOM",desc:["The number of methods in a class."]},metricDefinitions["Number of Static Fields"]={q_attr:"Size",shortname:"NOSF",link:"NOSF",desc:["The number of static fields in a class."]},metricDefinitions["Number of Static Methods"]={q_attr:"Size",shortname:"NOSM",link:"NOSM",desc:["The number of static methods in a class."]},metricDefinitions["Specialization Index"]={shortname:"SI",link:"SI",q_attr:"Complexity",desc:["Defined as NORM * DIT / NOM. The Specialization Index metric measures the extent to which subclasses override their ancestors classes. This index is the ratio between the number of overrid- den methods and total number of methods in a Class, weighted by the depth of inheritance for this class. Lorenz and Kidd precise : Methods that invoke the superclass’ method or override template are not included."]},metricDefinitions["Class-Methods Lines of Code"]={q_attr:"Size",shortname:"CM-LOC",link:"CM-LOC",desc:["Total number of all nonempty, non-commented lines of methods inside a class."]},metricDefinitions["Efferent Coupling"]={q_attr:"Coupling",shortname:"EC",link:"EC",desc:["Outgoing Coupling. The number of classes in other packages that the classes in the package depend upon is an indicator of the package's dependence on externalities."]},metricDefinitions["Afferent Coupling"]={q_attr:"Coupling",shortname:"AC",link:"AC",desc:["Incoming Coupling. The number of classes in other packages that depend upon classes within the package is an indicator of the package's responsibility."]},metricDefinitions["Number of Interfaces"]={q_attr:"Size",link:"NoI",shortname:"NoI",desc:["Total number of Interfaces."]},metricDefinitions["Number of Classes"]={q_attr:"Size",shortname:"NoCls",link:"NoCls",desc:["Total number of classes."]},metricDefinitions["Number of Entities"]={q_attr:"Size",shortname:"NoE",link:"NoE",desc:["Total number of Interfaces and classes."]},metricDefinitions["Number of Overridden Methods"]={q_attr:"Complexity",shortname:"NORM",link:"NORM",desc:["The number of Overridden Methods."]},metricDefinitions.C3={q_attr:"Coupling, Cohesion, Complexity",shortname:"Coupling, Cohesion, Complexity",link:"C3",desc:["The max value of Coupling, Cohesion, Complexity metrics"]},metricDefinitions["Number of Packages"]={q_attr:"Size",shortname:"NOPa",link:"nofP",desc:["Number of Packages in the project"]},metricDefinitions["Number of External Packages"]={q_attr:"Size",shortname:"NoEP",link:"nofPa",desc:["Number of External Packages referenced by the project"]},metricDefinitions["Number of External Entities"]={q_attr:"Size",shortname:"NoEE",link:"nofEE",desc:["Number of External classes and interfaces referenced by the project"]},metricDefinitions["Number of Problematic Classes"]={q_attr:"",shortname:"Number of Problematic Classes",link:"NoPC",desc:["Number of classes with high coupling, high complexity or low cohesion in the project"]},metricDefinitions["Number of Highly Problematic Classes"]={q_attr:"",shortname:"Number of Highly Problematic Classes",link:"NoHPC",desc:["Number of classes with high coupling, high complexity and low cohesion in the project"]},metricDefinitions["Lack of Tight Class Cohesion"]={q_attr:"Cohesion",shortname:"LTCC",link:"LTCC",desc:["The Lack of Tight Class Cohesion metric measures the lack cohesion between the public methods of a class. That is the relative number of directly connected public methods in the class. Classes having a high lack of cohesion indicate errors in the design."]},metricDefinitions["Access to Foreign Data"]={q_attr:"Coupling",shortname:"ATFD",link:"ATFD",desc:["ATFD (Access to Foreign Data) is the number of classes whose attributes are directly or indirectly reachable from the investiggated class. Classes with a high ATFD value rely strongly on data of other classes and that can be the sign of the God Class."]},metricDefinitions.Instability={q_attr:" ",shortname:"I",link:"I",desc:["Instability metric is used to measure the relative susceptibility of class to changes. It is defined as the ration of outgoing dependencies to all package dependencies and it accepts value from 0 to 1. Instability =  EC / (EC+AC) where: EC – outgoing dependencies, AC – incoming dependencies"]},metricDefinitions.Abstractness={q_attr:" ",shortname:"Abs",link:"Abs",desc:["Abstractness metric is used to measure the degree of abstraction of the package. abstractness is defines as the number of abstract classes in the package to the number of all classes."]},metricDefinitions["Normalized Distance"]={q_attr:" ",shortname:"ND",link:"ND",desc:["Normalized Distance metric is used to measure the balance between stability and abstractness and is calculated as ND = |Abs + I - 1|"]},metricDefinitions.InDegree={q_attr:" ",shortname:"InDegree",link:"InDegree",desc:["In-degree of corresponding graph vertex of the class"]},metricDefinitions.OutDegree={q_attr:" ",shortname:"OutDegree",link:"OutDegree",desc:["Out-degree of corresponding graph vertex of the class"]},metricDefinitions.Degree={q_attr:" ",shortname:"Degree",link:"Degree",desc:["Degree of corresponding graph vertex of the class"]};