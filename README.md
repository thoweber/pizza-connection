# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 09 - Proxy-Pattern

### Szenario
Der Code unseres Pizza-Store ist gewuchert☠ und muss dringend aufgeräumt werden.

Es steht also ein Refactoring an. Der REST-Code zum Zugriff auf den `PizzaController` soll aus der `PizzaStoreApp` entfernt werden. Dazu verwenden wir das Proxy-Pattern.

### Was ist neu?

* der ursprüngliche `PizzaController` wurde in `PizzaControllerImpl` umbenannt
* `PizzaController` ist jetzt ein Interface, welches die Funktion des alten `PizzaController`s beschreibt

### Aufgabe
* erstelle einen `PizzaControllerProxy`, der die Funktionen des `PizzaController`s zur Verfügung stellt. Der Proxy soll mit dem Controller über REST kommunizieren
* verwende den `PizzaControllerProxy` in der `PizzaStoreApp` und entferne sämtlichen REST-basierten Code in `PizzaStoreApp`
* da du mit dem Proxy auch die länge der Warteschlange abfragen kannst, wird nach jeder Bestellung ausgegeben, wie lange die Schlange gerade ist

# Wichtige Tipps:
* ℹ Der Umgang mit einer Liste an Objekten/einem JSON-Array im Spring `RestTemplate` ist nicht sofort selbsterklären. Deshalb habe ich euch noch einen hilfreichen Link bereitgestellt:  
  **Informationen zum [Arbeiten mit einer Liste von Objekten](https://www.baeldung.com/spring-rest-template-list) im Spring `RestTemplate`.**
* Der `PizzaControllerProxy` benötigt die `@Component`-Annotation, damit er für die Dependency Injection von Spring verfügbar ist.
* Den Aufruf des Endpunkts `/close-kitchen` kannst du wie folgt umsetzen:
  ```java
  restTemplate.postForLocation(uri, null);
  ```

### Das Pattern als UML
![UML](pattern-uml.png)

Diese Implementierung nutzt das Prinzip der Objektkomposition: Der Adapter implementiert die Schnittstelle des einen Objekts und wickelt das andere ein. Sie kann in allen gängigen Programmiersprachen implementiert werden.

1. Der Client ist eine Klasse, die die vorhandene Geschäftslogik des Programms enthält.
2. Die Client-Schnittstelle beschreibt ein Protokoll, das andere Klassen folgen müssen, um mit dem Client-Code zusammenarbeiten zu können.
3. Der Service ist eine nützliche Klasse (in der Regel eine Drittanbieter- oder Legacy-Klasse). Der Client kann diese Klasse nicht direkt verwenden, da sie eine inkompatible Schnittstelle hat.
4. Der Adapter ist eine Klasse, die in der Lage ist, sowohl mit dem Client und dem Dienst arbeiten kann: Sie implementiert die Client-Schnittstelle und umhüllt das Dienstobjekt. Der Adapter empfängt Aufrufe vom Client über die Adapterschnittstelle und übersetzt sie an das verpackte Dienstobjekt in einem Format, das er versteht.
5. Der Client-Code wird nicht an die konkrete Adapterklasse gekoppelt, solange er mit dem Adapter über die Client-Schnittstelle arbeitet. Dadurch können neue Typen von Adaptern in das Programm eingeführt werden, ohne den bestehenden Client-Code zu zerstören.  
Dies kann nützlich sein, wenn die Schnittstelle der Serviceklasse geändert oder ersetzt wird.
----

### Maven verwenden

Um die Anwendung mit Maven zu bauen, verwendet ihr:
```
mvn clean compile
```
Zum Ausführen aller Tests:
```
mvn clean test
```
Zum Bauen der Anwendung als ausführbares JAR:
```
mvn clean install
```

#### Code Coverage
Wenn ihr Tests mit Maven ausführt, wird automatisch die Code Coverage mit ermittelt. Die Berichte findet ihr nach dem Build in `target/site/jacoco`. Öffnet `index.html` im Browser für eine Übersicht.