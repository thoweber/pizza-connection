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

Wir haben etwas recherche betrieben und möchten das Problem mit dem _Chain of Responsibility Pattern_ lösen: Jede einzelne Zubereitungsaufgabe soll dabei einen eigenen `Handler` verschoben werden.

Viel Erfolg beim Anwenden des Patterns🍀

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