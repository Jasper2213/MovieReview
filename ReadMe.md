Een meerlagige JavaFX-applicatie met gradle als build-tool. De applicatie bestaat uit een inlogscherm en een scherm waarin gebruikers films kunnen opzoeken en raadplegen door middel van client-server technologie en reviews schrijven van de opgezochte films. Deze reviews worden in een lokale MySQL-database weggeschreven.

Film data werd origineel van een server opgehaald.  
Deze server is geanonimiseerd en vervangen door een placeholder (192.168.32.32:12345).

Communiecatie met de server ging als volgt:
1. De client verbindt met poort 12345 op het adres 192.168.32.32.
2. De client verzendt een instantie van MovieSearchMessage, met de query de zoekterm (vb.
   terminator) over het netwerk.
3. De server antwoordt met één van volgende:  
   a. Een instantie van MovieResultMessage. Diens property results levert een ArrayList op van
   Movie-objecten die overeenkomen met de query.  
   b. Een instantie van ErrorMessage als er iets misgegaan is. De property message geeft meer
   uitleg over wat er precies fout liep.