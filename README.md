# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 10 - Chain of Responsibility Pattern

### Szenario
Neben Pizza🍕 wollen wir in unserem Geschäft jetzt auch Salate🥗 für die figurbewussten Kunden anbieten. Den Anfang macht ein leckerer Tomaten-Salat🍅, den es wie unsere Pizzen in den Stilrichtungen _sicilian_ und _gourmet_ gibt.

Tatsächlich kann unsere Software das neue Gericht bereits produzieren🏭, nur die Architektur macht nicht glücklich😪

### Was ist neu?
So einiges😁...

#### Ganz neu
* `Dish`: übergeordnetes Interface aller Gerichte
* `AbstractDish`: abstrakte Klasse, die alle Eigenschaften von `AbstractPizza` enthält, die für sämtliche Speisen gültig sind
* `Salad`: Interface für Salate
* `TomatoSalad`: Implementierung des Tomatensalats

#### Umbennungen:
* `PizzaController` -> `FoodController`
* `PizzaControllerImpl` -> `FoodControllerImpl`
* `PizzaControllerProxy` -> `FoodControllerProxy`
* `PizzaResponse` -> `FoodResponse`
* `PizzaOrderRequest` -> `OrderRequest`
* `PizzaOrderResponse` -> `OrderResponse`
* `PizzaStyle` -> `FoodStyle`
* `PizzaRepository` -> `DishRepository`
* `PizzaFactory` -> `FoodFactory`
* `PizzaService` -> `FoodOrderService`
* `PizzaPreparationTask` -> `FoodPreparationTask`
* `GourmetPizzaFactory` -> `GourmetFoodFactory`
* `SicilianPizzaFactory` -> `SicilianFoodFactory`
* `PizzaStateChange` -> `DishStateChange`
* `PizzaResponseTest` -> `FoodResponseTest`
* `PizzaOrderRequestTest` -> `OrderRequestTest`
* `PizzaOrderResponseTest` -> `OrderResponseTest`
* `PizzaStyleTest` -> `FoodStyleTest`
* `PizzaRepositoryTest` -> `DishRepositoryTest`
* `PizzaServiceTest` -> `FoodOrderServiceTest`
* `PizzaPreparationTaskTest` -> `FoodPreparationTaskTest`

Und viele weitere Anpassungen, die sich aus den Umbennenungen und den neuen Elementen ergeben haben.

### Aufgabe
Unser Schwachpunkt ist noch `FoodPreparationTask` (ehemals PizzaPreparationTask). Zwar konnten wir uns zunächst einmal mit einem If-Block behelfen und so den Betrieb aufrecht erhalten, aber diese Lösung skaliert nicht👎

Wir haben etwas Recherche betrieben und möchten das Problem mit dem _Chain of Responsibility Pattern_ lösen: Jede einzelne Zubereitungsaufgabe soll dabei einen eigenen `Handler` verschoben werden.

Viel Erfolg beim Anwenden des Patterns🍀

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