Készíts egy feladat nyilvántartó Java konzolos alkalmazást.
Az app képes feladatok nyilvántartására:

- Új feladatok felvételére
- Meglévő feladatok listázására
- Egy adott feladat állapotának módosítására
- Egy adott feladat törlésére.
- Meglehessen kérdezni, mely feladatok vannak blokkolt állapotban a rendszerben, melyek az éppen végrehajtás
alatt lévő feladatok és melyek vannak kész állapotban
- Lehessen lekérdezni egy adott felhasználóhoz rendelt összes feladatot

Minden feladat esetében az alábbi tulajdonságokat különbözethetjük meg:

- feladat tulajdonosa (user)
- Becsült végrehajtási idő egész órában(mennyi időbe telik a felhasználónak a végrehajtás). Itt a skála
csak a Fibonnaci sorozatból tartalmazó számokból kerülhet ki. (Előre definiált értékek listája)
- feladat státusza (meghatározott értékek: NEW, IN_PROGRESS, BLOCKED, DONE. A státuszok között korreláció van,
például DONE státuszba, csak IN_PROGRESS státuszból kerülhet a feladat, IN_PROGRESS státuszba, csak NEW és BLOCKED állapotból
kerülhet)
- feladat leírás
- Linkelt feladatok (egy vagy több feladat, amely kapcsolatban áll az aktuális feladattal,
a kapcsolat minősége különböző lehet: CHILD, PARENT, PRECEDESSOR)
- Egy feladatot ne lehessen törölni, míg a CHILD relációban lévő alfeladatok léteznek a rendszerben, vagy nincsenek DONE állapotban)
- Egy feladatot ne lehessen DONE státuszba helyezni, amíg a CHILD relációban lévő feladatok nincsenek mind DONE státuszban
- Egy feladatot ne lehessen IN_PROGRESS státuszba helyezni amíg a PRECEDESSOR típusú linkelt feladatok nincsenek DONE státuszban
