**What is this?**

A small tool that illustrates how to compare different database tables using a pre-defined mapping.

Well, as a software engineer in a big ol' company with a non engineering focus, you are probably familiar with the situation where regression tests
are being performed manually. This is due to the fact that many of the so called test managers come from a non technical background and are not willing to
acquire the least amount of technical knowledge. As a result many of them don't have the know-how of what and how such tests can actually be automated. 
Such persons often claim that spending time and effort to write a tool that automates such processes is inefficient and a waste of money. This is because 
such people do not adopt a brighter future outlook and are anware that automating simple tasks such as that makes testing less error prone, reliable, easier, 
faster and cheaper in the long term.

**A simplified real life scenario:**

Consider an old system `S` that is about to be replaced with a newer and cooler system `C`. Both systems are responsible for managing some sort of
entity. Lets call that entity a `Trade`. A `Trade` in both systems has fictional properties such as an `Instrument Name`, an `ISIN` and some sort of unique 
`Identifier`. In both systems, `S` and `C`, the trades are being edited and enriched and then persisted into an Oracle database (different database schema 
for each system) in their final state. Lets assume the trades are inserted into a table `Trade_S` and `Trade_C` in the two databases respectively. 
Since, system `C` is supposed to be replacing system `S`, it is of vital importance, that `Trades` coming from system 
`C` have the same state as their counterparts in system `S`. That is, if a trade `X` was managed with system `S` and came out with a final state `t`, the same trade
must come out with a state `t` when handled in system `C`.

The approach one can pursue, is to create some sort of mapping between the different two databases and check their entries for equality, to see if the new
system persists the same states for the trades as the old one. Consider for instance a trade `X` that has a property called `isin` in the old system and that 
property is now called `tradeIsin` in the new system. If system `S` has persisted `Trade_S.isin = 'abc'` in the database, the new system should have persisted 
`Trade_C.tradeIsin = 'abc'` in its database. Yes we are comparing database entries :-]

This sample code illustrates how to connect to two different databases, read a given number of trades and compare their properties for equality based on a 
predefined mapping. A modified version of this code was used in a real working environment within a company. A JDBC and a Hibernat/JDBC version is available.

**How to Import into Eclipse**

* **File** -> **Import...** -> **Existing Maven Projects**
* Click **Next**
* Click **Browse...** for the **Root Directory**
* Select and open **RegressionToolHibernate** or **RegressionToolJDBC**
* Click **Finish**
* Do a mvn update on the project

**How to run**

* Run `com.lucaslouca.app.Regression.java`
