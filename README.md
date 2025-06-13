# Hands-on Design Patterns
***The Pizza Connection***

## Kapitel 01 - Vorstellung
Wir haben einen kleinen Pizza-Laden und verkaufen nur eine Sorte Pizza - die Kunden stehen drauf🤩 und lieben unsere einfache, aber extrem leckere Mozzarella Pizza🍕

Das dürfte zum Teil auch an unserem ausgefallenen Bestellprinzip liegen, das ganz retromäßig als Terminalanwendung daher kommt - man fühlt sich ein bisschen wie in einem Science-Fiction-Film aus den 80ern🤖

### Aufbau der Software
Der Anwendungscode unseres Pizza-Geschäfts befindet sich unter `src/main/java`:
* `de.infoteam.course.dp.pizzastore`:
  enthält unsere coole `PizzaStoreApp` - eine Konsolenanwendung, die Pizza-Bestellungen unsere Kunden annimmt
* `de.infoteam.course.dp.pizzastore.model`: Das Datenmodell unseres Pizza-Ladens.
	* `de.infoteam.course.dp.pizzastore.model.dishes`: alle Gerichte, die wir anbieten
	* `de.infoteam.course.dp.pizzastore.model.ingredients`: unsere Zutaten
		* `de.infoteam.course.dp.pizzastore.model.ingredients.cheese`: Käse
		* `de.infoteam.course.dp.pizzastore.model.ingredients.dough`: Teig-Sorten
		* `de.infoteam.course.dp.pizzastore.model.ingredients.sauce`: Soßen
* `de.infoteam.course.dp.pizzastore.service`: enthält den `PizzaService`, der auf Bestellung leckere Pizza produziert

**Schaut euch ein wenig um, und versucht mit dem Aufbau vertraut zu werden.**

Die Anwendung verfügt auch schon über entsprechende Testfälle (im Herzen sind wir doch alle Software Engineers🤓), die ihr wie bei Maven üblich unter `src/test/java` findet👍

### Maven verwenden

#### Anwendung mit Maven kompilieren
* CMD (Windows): `mvnw clean compile`
* Shell (Linux/Git Bash/macOS): `./mvnw clean compile`

#### Ausführen aller Tests
* CMD (Windows): `mvnw clean test`
* Shell (Linux/Git Bash/macOS): `./mvnw clean test`

Wenn ihr Tests mit Maven ausführt, wird automatisch die Code Coverage mit ermittelt. Die Berichte findet ihr nach dem Build in `target/site/jacoco`. Öffnet `index.html` im Browser für eine Übersicht.

#### Bauen der Anwendung als ausführbares JAR
* CMD (Windows): `mvnw clean install`
* Shell (Linux/Git Bash/macOS): `./mvnw clean install`

Wenn die Anwendung als ausführbares JAR gebaut wurde, kann sie mit dem Launch-Script gestartet werden. Dazu gebt ihr im Basisverzeichnis des Projekts folgendes ein:
* CMD (Windows): `launch`
* Shell (Linux/Git Bash/macOS): `./launch`